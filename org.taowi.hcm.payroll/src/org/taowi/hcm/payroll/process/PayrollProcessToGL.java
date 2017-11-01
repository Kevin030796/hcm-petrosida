package org.taowi.hcm.payroll.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClientInfo;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.payroll.model.MHCMovement;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.taowi.hcm.payroll.model.MHCPayrollProcess;
import org.taowi.hcm.payroll.model.X_HC_PayComponent_Acct;

public class PayrollProcessToGL extends SvrProcess{

	int p_C_DocType_ID = 0;
	int p_C_AcctSchema = 0;
	int p_HC_PayrollProcess_ID = 0;
	int p_GL_Category_ID = 0;
	int p_C_Currency_ID = 0;
	int p_C_ConversionType_ID = 0;
	String p_PostingType = "";
	String p_DocStatus = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals(MHCPayrollProcess.COLUMNNAME_HC_PayrollProcess_ID))
				p_HC_PayrollProcess_ID = para[i].getParameterAsInt();
			else if (name.equals(MJournal.COLUMNNAME_C_DocType_ID))
				p_C_DocType_ID = para[i].getParameterAsInt();
			else if (name.equals(MJournal.COLUMNNAME_C_AcctSchema_ID))
				p_C_AcctSchema = para[i].getParameterAsInt();
			else if (name.equals(MJournal.COLUMNNAME_PostingType))
				p_PostingType = para[i].getParameterAsString();
			else if (name.equals(MJournal.COLUMNNAME_GL_Category_ID))
				p_GL_Category_ID = para[i].getParameterAsInt();
			else if (name.equals(MJournal.COLUMNNAME_C_Currency_ID))
				p_C_Currency_ID = para[i].getParameterAsInt();
			else if (name.equals(MJournal.COLUMNNAME_C_ConversionType_ID))
				p_C_ConversionType_ID = para[i].getParameterAsInt();
			else if (name.equals(MJournal.COLUMNNAME_DocStatus))
				p_DocStatus = para[i].getParameterAsString();
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), p_HC_PayrollProcess_ID, get_TrxName());
		
		//@KevinY
		//addValidaton for payroll Process
		if(!payrollProcess.get_ValueAsBoolean("Processed"))
			throw new AdempiereException("Payroll Process is not processed yet");
		//@KevinY end
		
		//get employee category
		List<Integer> m_EmployeeJob_IDs = new ArrayList<Integer>();
		List<Integer> m_EmployeeCategory_IDs = new ArrayList<Integer>();
		Hashtable<Integer, List<MHCPayComponent>> PayComponentCategory = new Hashtable<Integer, List<MHCPayComponent>>();
		String whereClause = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//Check GL Journal with complete or draft status
		whereClause = "SELECT "+MJournal.COLUMNNAME_GL_Journal_ID+" FROM "+MJournal.Table_Name+" "
				+ "WHERE "+MJournal.COLUMNNAME_Description+" like ? "
				+ "AND "+MJournal.COLUMNNAME_DocStatus+" IN('"+MJournal.DOCSTATUS_Drafted+"','"+MJournal.DOCSTATUS_Completed+"')";
		try{
			pstmt = DB.prepareStatement(whereClause, get_TrxName());
			pstmt.setString(1, payrollProcess.get_ID() + "");
			rs = pstmt.executeQuery();
			if(rs.next()){
				MJournal checkJournal = new MJournal(getCtx(), rs.getInt(1), get_TrxName());
				throw new AdempiereException("Payroll Process already has "+checkJournal.getDocStatus()+
						" journal with document no :"+ checkJournal.getDocumentNo());
			}
		}catch(Exception e){
			log.severe("Error get GL Journal : "+e.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		//if payroll process has paygroup_ID then select all employee category from each employee job with related paygroup_ID
		if(payrollProcess.getHC_PayGroup_ID() > 0){
			whereClause = "SELECT HC_EmployeeJob_ID FROM HC_EmployeeJob empJob "
					+ "WHERE empJob.HC_Status='"+MEmployeeJob.HC_STATUS_Active+"' AND "
					+ "empJob.IsActive='Y' AND empJob.SeqNo=1 AND HC_PayGroup_ID=?";
			pstmt = null;
			rs = null;
			try{
				pstmt = DB.prepareStatement(whereClause, get_TrxName());
				pstmt.setInt(1, payrollProcess.getHC_PayGroup_ID());
				rs = pstmt.executeQuery();
				while(rs.next()){
					m_EmployeeJob_IDs.add(rs.getInt(1));
				}
			}catch(Exception e){
				log.severe("Error get pay component : "+e.toString());
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			int check = 0;
			for(int m_employeeJob_id: m_EmployeeJob_IDs) {
				MEmployeeJob empJob = new MEmployeeJob(getCtx(),m_employeeJob_id, get_TrxName());
				if(m_EmployeeCategory_IDs.size() > 0) {
					check = 0;
					//check employeeCategory already exists
					for(int employeeCategory_ID : m_EmployeeCategory_IDs){
						if(employeeCategory_ID == empJob.getHC_EmployeeCategory_ID()) {
							check = 1;
						}
					}//endLoopCategory
					
					//set employeeCategory
					if(check == 0){
						m_EmployeeCategory_IDs.add(empJob.getHC_EmployeeCategory_ID());
						PayComponentCategory.put(empJob.getHC_EmployeeCategory_ID(), new ArrayList<MHCPayComponent>());
					}
				}else{
					m_EmployeeCategory_IDs.add(empJob.getHC_EmployeeCategory_ID());
					PayComponentCategory.put(empJob.getHC_EmployeeCategory_ID(), new ArrayList<MHCPayComponent>());
				}
			}
		}else {
			whereClause = "SELECT DISTINCT(emj.HC_EmployeeCategory_ID) FROM HC_ProcessEmployee pe "
					+ "JOIN HC_Employee em ON pe.HC_Employee_ID = em.HC_Employee_ID "
					+ "LEFT JOIN HC_EmployeeJob emj ON em.HC_Employee_ID = emj.HC_Employee_ID "
					+ "WHERE pe.HC_PayrollProcess_ID = ? AND "
					+ "em.HC_Status = '"+MEmployee.HC_STATUS_Active+"' AND "
					+ "emj.HC_Status= '"+MEmployeeJob.HC_STATUS_Active+"' AND "
					+ "emj.IsActive='Y' AND emj.SeqNo=1";
		
			pstmt = null;
			rs = null;
			try{
				pstmt = DB.prepareStatement(whereClause, get_TrxName());
				pstmt.setInt(1, p_HC_PayrollProcess_ID);
				rs = pstmt.executeQuery();
				while(rs.next()){
					if(rs.getObject(1)!= null){
						m_EmployeeCategory_IDs.add(rs.getInt(1));
						PayComponentCategory.put(rs.getInt(1), new ArrayList<MHCPayComponent>());
					}
				}
			}catch(Exception e){
				log.severe("Error get pay component : "+e.toString());
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		}
		
		//get period
		Calendar calToday = Calendar.getInstance();
		calToday.setTimeInMillis(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp today = Timestamp.valueOf(sdf.format(calToday.getTime()) + " 00:00:00.00");
		MClientInfo clientInfo = MClientInfo.get(getCtx(), getAD_Client_ID());
		MPeriod c_Period = MPeriod.findByCalendar(getCtx(), today, clientInfo.getC_Calendar_ID(), get_TrxName());
		
		// create header gl journal here
		MJournal journal = new MJournal(getCtx(), 0, get_TrxName());
		journal.setAD_Org_ID(payrollProcess.getAD_Org_ID());
		journal.setC_DocType_ID(p_C_DocType_ID);
		journal.setDateDoc(today);
		journal.setDateAcct(payrollProcess.getDateTrx());
		journal.setC_AcctSchema_ID(p_C_AcctSchema);
		journal.setPostingType(p_PostingType);
		journal.setGL_Category_ID(p_GL_Category_ID);
		journal.setC_Period_ID(c_Period.get_ID());
		journal.setC_Currency_ID(p_C_Currency_ID);
		//@KevinY
		journal.setC_ConversionType_ID(p_C_ConversionType_ID);
		journal.setDescription(payrollProcess.get_ID() + "");
		//@KevinY end
		journal.saveEx();
		
		//@KevinY
		BigDecimal sumDebit		= Env.ZERO;
		BigDecimal sumCredit	= Env.ZERO;
		BigDecimal AmtSourceDr	= Env.ZERO;
		BigDecimal AmtAcctDr	= Env.ZERO;
		BigDecimal AmtSourceCr  = Env.ZERO;
		BigDecimal AmtAcctCr    = Env.ZERO;
		int count = 0;
		
		List<MHCPayComponent> list = getPayComponents(p_HC_PayrollProcess_ID);
		for(int employeeCategory_ID : m_EmployeeCategory_IDs){
			System.out.println("employeeCategory: "+ employeeCategory_ID);
			PayComponentCategory.put(employeeCategory_ID, list);
		}
		
		
		for(int i = 0 ; i < m_EmployeeCategory_IDs.size() ; i++){
			for(MHCPayComponent PayComponent : PayComponentCategory.get(m_EmployeeCategory_IDs.get(i))){
				AmtSourceDr = Env.ZERO;
				AmtAcctDr   = Env.ZERO;
				AmtSourceCr = Env.ZERO;
				AmtAcctCr   = Env.ZERO;
				for(MHCMovement movement : getMovements(p_HC_PayrollProcess_ID)) {
					//getEmployeeJob sequence 1 and active
					whereClause = "HC_Employee_ID = ? AND "
							+ "HC_Status='"+ MEmployeeJob.HC_STATUS_Active+ "' AND "
							+ "IsActive='Y' AND SeqNo=1";
					int hc_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, whereClause, get_TrxName())
												.setParameters(movement.getHC_Employee_ID())
												.firstId();
					int hc_PayComponent_ID = movement.getHC_PayComponent_ID();
					MEmployeeJob empJob = new MEmployeeJob(getCtx(), hc_EmployeeJob_ID, get_TrxName());
					
					if(empJob.getHC_EmployeeCategory_ID() == m_EmployeeCategory_IDs.get(i)){
						if(PayComponent.get_ID() == hc_PayComponent_ID) {
							System.out.println("employee category : " + empJob.getHC_EmployeeCategory_ID() 
									+ " PayComponent: " + PayComponent.getValue());
							if(PayComponent.getHC_PayComponentType().equals(MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning)) {
								AmtSourceDr = AmtSourceDr.add(movement.getHC_AmtValue());
								AmtAcctDr   = AmtAcctDr.add(movement.getHC_AmtValue());
								sumDebit 	= sumDebit.add(movement.getHC_AmtValue());
							}else {
								AmtSourceCr = AmtSourceCr.add(movement.getHC_AmtValue());
								AmtAcctCr   = AmtAcctCr.add(movement.getHC_AmtValue());
								sumCredit 	= sumCredit.add(movement.getHC_AmtValue());
							}//endElse
						}//endIf
					}//endIf
				}//endForMovement
				
				// create gl journal line here
				if(getHC_Expense_Acct(PayComponent.get_ID(), journal.getC_AcctSchema_ID(), m_EmployeeCategory_IDs.get(i)) > 0){
					MJournalLine line = new MJournalLine(journal);
					line.setAD_Org_ID(journal.getAD_Org_ID());
					line.setLine(count+=10);
					line.setC_Currency_ID(journal.getC_Currency_ID());
					//tambahan temporary comment
					line.setDescription(m_EmployeeCategory_IDs.get(i).toString());
					line.setC_ValidCombination_ID(
							getHC_Expense_Acct(PayComponent.get_ID(), journal.getC_AcctSchema_ID(), m_EmployeeCategory_IDs.get(i))
					);
					line.setDescription(PayComponent.getValue());
					
					int Account_ID = 0;
					whereClause = "SELECT Account_id FROM C_ValidCombination "
							+ "WHERE C_ValidCombination_ID = ?";
					pstmt = null;
					rs = null;
					try {
						pstmt = DB.prepareStatement(whereClause, get_TrxName());
						pstmt.setInt(1, line.getC_ValidCombination_ID());
						rs = pstmt.executeQuery();
						while(rs.next()){
							Account_ID = rs.getInt(1);
						}
					}catch(Exception e) {
						log.severe("Error get pay component : "+e.toString());
					}finally {
						DB.close(rs, pstmt);
						rs = null;
						pstmt = null;
					}
					
					line.setAccount_ID(Account_ID);
					if(PayComponent.getHC_PayComponentType().equals(MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning)){
						System.out.println("AmtSourceDr : "+ AmtSourceDr);
						System.out.println("AmtAcctDr : "+ AmtAcctDr);
						System.out.println("sumDebit : "+ sumDebit);
						line.setAmtSourceDr(AmtSourceDr);
						line.setAmtAcctDr(AmtAcctDr);
					}else{
						System.out.println("AmtSourceDr : "+ AmtSourceCr);
						System.out.println("AmtAcctDr : "+ AmtAcctCr);
						System.out.println("sumCredit : "+ sumCredit);
						line.setAmtSourceCr(AmtSourceCr);
						line.setAmtAcctCr(AmtAcctCr);
					}
					line.saveEx();
				}
			}//endForPayComponent
		}//endForEmployeeCategory
		
		//@KevinY end
		journal.setTotalDr(sumDebit.setScale(0, RoundingMode.DOWN));
		journal.setTotalCr(sumCredit.setScale(0, RoundingMode.DOWN));
		journal.saveEx();
		
		if(p_DocStatus.equals(MJournal.DOCSTATUS_Completed)){
			journal.setDocStatus(MJournal.DOCSTATUS_Drafted);
			journal.setDocAction(MJournal.DOCACTION_Complete);
			journal.saveEx();
			if(!journal.processIt(MJournal.DOCACTION_Complete))
				throw new AdempiereException("Cant Complete Journal "+journal.getProcessMsg());
		}
		
		String message = Msg.parseTranslation(getCtx(), "@GeneratedGLJournal@ "+journal.getDocumentNo());
		addBufferLog(0, null, null, message, 0, 0);
		
		return "";
	}
	
	
	/**
	 * 
	 * @param HC_PayrollProcess_ID
	 * @return
	 */
	private List<MHCPayComponent> getPayComponents(int HC_PayrollProcess_ID){
		List<MHCPayComponent> PayComponents = new ArrayList<MHCPayComponent>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT(mov.HC_PayComponent_ID) FROM HC_Movement mov "
				+ "JOIN HC_PayComponent comp ON comp.HC_PayComponent_ID = mov.HC_PayComponent_ID "
				+ "WHERE mov.HC_PayrollProcess_ID=? AND comp.HC_PayComponentType IN(?,?) AND mov.IsTransferToGL='Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
			pstmt.setInt(1, HC_PayrollProcess_ID);
			pstmt.setString(2, MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning);
			pstmt.setString(3, MHCPayComponent.HC_PAYCOMPONENTTYPE_Deduction);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MHCPayComponent paycomponent = new MHCPayComponent(getCtx(), rs.getInt(1), get_TrxName());
				PayComponents.add(paycomponent);
			}
		}catch(Exception e){
			log.severe("Error get pay component : "+e.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return PayComponents;
	}
	
	/**
	 * get all pay component in payroll process 
	 * earning and deduction only
	 * @param HC_PayrollProcess_ID
	 * @return list of pay component
	 */
	private List<MHCMovement> getMovements(int HC_PayrollProcess_ID){
		
		List<MHCMovement> list = new ArrayList<MHCMovement>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT mov.HC_Movement_ID FROM HC_Movement mov "
				+ "JOIN HC_PayComponent comp ON comp.HC_PayComponent_ID = mov.HC_PayComponent_ID "
				+ "WHERE mov.HC_PayrollProcess_ID=? AND comp.HC_PayComponentType IN(?,?) AND mov.IsTransferToGL='Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
			pstmt.setInt(1, HC_PayrollProcess_ID);
			pstmt.setString(2, MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning);
			pstmt.setString(3, MHCPayComponent.HC_PAYCOMPONENTTYPE_Deduction);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MHCMovement movement = new MHCMovement(getCtx(), rs.getInt(1), get_TrxName());
				list.add(movement);
			}
		}catch(Exception e){
			log.severe("Error get pay component : "+e.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return list;
	}
	
	/**
	 * get expense account
	 * @param HC_PayComponent_ID
	 * @return valid combination id
	 */
	private int getHC_Expense_Acct(int HC_PayComponent_ID, int C_AcctSchema_ID, int HC_EmployeeCategory_ID){
		
		//@KevinY get HC_Expense_Acct
		String query ="SELECT HC_Expense_Acct FROM HC_PayComponent_Acct "
				+ "WHERE "+ X_HC_PayComponent_Acct.COLUMNNAME_HC_PayComponent_ID+"=? AND "
				+ X_HC_PayComponent_Acct.COLUMNNAME_C_AcctSchema_ID+"=? AND "
				+ "HC_EmployeeCategory_ID= ?";
		
		int HC_Expense_Acct = 0;
		//HC_Expense_Acct = DB.getSQLValue(get_TrxName(), query, HC_PayComponent_ID, C_AcctSchema_ID);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1, HC_PayComponent_ID);
			pstmt.setInt(2, C_AcctSchema_ID);
			pstmt.setInt(3, HC_EmployeeCategory_ID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HC_Expense_Acct = rs.getInt(1);
			}
		}catch(Exception e){
			log.severe("Error get pay component : "+e.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		/*
		int HC_PayComponent_Acct_ID = new Query(getCtx(), X_HC_PayComponent_Acct.Table_Name, whereClause, get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setParameters(new Object[]{HC_PayComponent_ID, C_AcctSchema_ID})
			.firstId();
		
		if(HC_PayComponent_Acct_ID <=0 ){
			MHCPayComponent payComponent = new MHCPayComponent(getCtx(), HC_PayComponent_ID, get_TrxName());
			throw new AdempiereException("Cant get Account for Component "+payComponent.getValue());
		}
			
		X_HC_PayComponent_Acct payComponentAcct = new X_HC_PayComponent_Acct(getCtx(), 
				HC_PayComponent_Acct_ID, get_TrxName());
		*/
		return HC_Expense_Acct;
		//@KevinY end
	}
	
}
