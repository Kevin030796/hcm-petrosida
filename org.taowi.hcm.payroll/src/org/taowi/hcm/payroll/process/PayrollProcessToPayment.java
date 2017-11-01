package org.taowi.hcm.payroll.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.payroll.model.MHCMovement;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.taowi.hcm.payroll.model.MHCPayrollProcess;

public class PayrollProcessToPayment extends SvrProcess{

	private int p_HC_PayrollProcess_ID = 0;
	private int p_C_Charge_ID = 0;
	private int p_C_Currency_ID = 0;
	private int p_C_BPartner_ID = 0;
	/*@Kevin*/
	private int p_HC_PayComponent_ID = 0;
	/*@Kevin end*/
	private int p_C_BankAccount_ID = 0;
	private String p_TenderType = "";
	private String p_DocStatus = "";
	
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals(MHCPayrollProcess.COLUMNNAME_HC_PayrollProcess_ID))
				p_HC_PayrollProcess_ID = para[i].getParameterAsInt();
			else if (name.equals(MPayment.COLUMNNAME_C_Charge_ID))
				p_C_Charge_ID = para[i].getParameterAsInt();
			else if (name.equals(MPayment.COLUMNNAME_C_Currency_ID))
				p_C_Currency_ID = para[i].getParameterAsInt();
			else if (name.equals(MPayment.COLUMNNAME_C_BPartner_ID))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals(MPayment.COLUMNNAME_C_BankAccount_ID))
				p_C_BankAccount_ID = para[i].getParameterAsInt();
			else if (name.equals(MPayment.COLUMNNAME_TenderType))
				p_TenderType = para[i].getParameterAsString();
			else if (name.equals(MPayment.COLUMNNAME_DocStatus))
				p_DocStatus = para[i].getParameterAsString();
			/*@Kevin*/
			else if (name.equals(MHCPayComponent.COLUMNNAME_HC_PayComponent_ID))
				p_HC_PayComponent_ID = para[i].getParameterAsInt();
			/*@Kevin end*/
		}
	}

	@Override
	protected String doIt() throws Exception {
		//TODO remove this hardcode later
		p_TenderType = MPayment.TENDERTYPE_Check;
		
		int HC_PayrollProcess_ID = p_HC_PayrollProcess_ID;
		MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), HC_PayrollProcess_ID, get_TrxName());
		
		//@KevinY
		MHCPayComponent payComponent = new MHCPayComponent(getCtx(), p_HC_PayComponent_ID, get_TrxName());
		//@KevinY end
		
		if(!payrollProcess.isProcessed())
			throw new AdempiereException("Payroll Process Not Processed");
		
		List<Integer> listEmployeeID = new ArrayList<Integer>();
		HashMap<Integer, BigDecimal> employeeMap = new HashMap<>();
		
		StringBuilder sb = new StringBuilder();
		/*@KevinY
		sb.append("SELECT mov.HC_Employee_ID, SUM(COALESCE(mov.HC_AmtValue,0)) "
				+ "FROM HC_Movement mov "
				+ "JOIN HC_Employee emp ON emp.HC_Employee_ID = mov.HC_Employee_ID "
				+ "LEFT JOIN C_BPartner bp ON bp.C_BPartner_ID = emp.C_BPartner_ID "
				+ "WHERE mov.IsPaid = 'Y' AND mov.HC_PayrollProcess_ID = ? "
		*/
		sb.append("SELECT mov.HC_Employee_ID, SUM(COALESCE(mov.HC_AmtValue,0)) "
				+ "FROM HC_Movement mov "
				+ "JOIN HC_Employee emp ON emp.HC_Employee_ID = mov.HC_Employee_ID "
				+ "LEFT JOIN C_BPartner bp ON bp.C_BPartner_ID = emp.C_BPartner_ID "
				+ "WHERE mov.IsPaid = 'Y' AND mov.HC_PayrollProcess_ID = ? "
		/*@KevinY*/
				+ "AND mov.HC_PayComponent_ID = ? ");
		/*@Kevin end*/
		
		if(p_C_BPartner_ID > 0 && (payComponent.isPaid() && !payComponent.get_ValueAsBoolean("isAccumulation")))
			sb.append("AND emp.C_BPartner_ID = ? ");
		
		sb.append(" GROUP BY mov.HC_Employee_ID ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
			pstmt.setInt(1, HC_PayrollProcess_ID);
			/*@KevinY*/
			pstmt.setInt(2, p_HC_PayComponent_ID);
			/*@Kevin end*/
			if(p_C_BPartner_ID > 0 && (payComponent.isPaid() && !payComponent.get_ValueAsBoolean("isAccumulation")))
				pstmt.setInt(3, p_C_BPartner_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				listEmployeeID.add(rs.getInt(1));
				employeeMap.put(rs.getInt(1), rs.getBigDecimal(2));
			}
		}catch(Exception e){
			log.info(e.toString()+"-"+sb.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		StringBuilder docNo = new StringBuilder();
		
		/*@KevinY*/
		BigDecimal AccPayAmt = new BigDecimal(0);
		/*@KevinY end*/
		
		/*@KevinY*/
		if(payComponent.get_ValueAsBoolean("IsPaid") && !payComponent.get_ValueAsBoolean("isAccumulation")){
			for (Integer HC_Employee_ID : listEmployeeID) {
				MEmployee employee = new MEmployee(getCtx(), HC_Employee_ID, get_TrxName());
				Timestamp currentDate = new Timestamp(System.currentTimeMillis());
				
				MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
				payment.setAD_Org_ID(payrollProcess.getAD_Org_ID());
				payment.setC_Charge_ID(p_C_Charge_ID);
				payment.setC_Currency_ID(p_C_Currency_ID);
				payment.setC_BPartner_ID(employee.getC_BPartner_ID());
				payment.setC_BankAccount_ID(p_C_BankAccount_ID);
				payment.setTenderType(p_TenderType);
				payment.setDateTrx(currentDate);
				payment.setDateAcct(currentDate);
				payment.setIsReceipt(false);
				payment.setDescription(payrollProcess.getDocumentNo());
				payment.setPayAmt(employeeMap.get(HC_Employee_ID));
				payment.saveEx();
				
				if(p_DocStatus.equals(MPayment.DOCSTATUS_Completed)){
					payment.setDocAction(MPayment.DOCACTION_Complete);
					payment.saveEx();
					if(!payment.processIt(MPayment.DOCACTION_Complete))
						throw new AdempiereException("Cant Complete Payment "+payment.getProcessMsg());
				}
				
				docNo.append(payment.getDocumentNo()+",");
				
				//TODO put method create matching here
			}
		}else if(payComponent.get_ValueAsBoolean("IsPaid") && payComponent.get_ValueAsBoolean("isAccumulation")){
			for (Integer HC_Employee_ID : listEmployeeID) {
				AccPayAmt = AccPayAmt.add(employeeMap.get(HC_Employee_ID));
			}
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			
			MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
			payment.setAD_Org_ID(payrollProcess.getAD_Org_ID());
			payment.setC_Charge_ID(p_C_Charge_ID);
			payment.setC_Currency_ID(p_C_Currency_ID);
			payment.setC_BPartner_ID(p_C_BPartner_ID);
			payment.setC_BankAccount_ID(p_C_BankAccount_ID);
			payment.setTenderType(p_TenderType);
			payment.setDateTrx(currentDate);
			payment.setDateAcct(currentDate);
			payment.setIsReceipt(false);
			payment.setPayAmt(AccPayAmt);
			payment.saveEx();
			
			if(p_DocStatus.equals(MPayment.DOCSTATUS_Completed)){
				payment.setDocAction(MPayment.DOCACTION_Complete);
				payment.saveEx();
				if(!payment.processIt(MPayment.DOCACTION_Complete))
					throw new AdempiereException("Cant Complete Payment "+payment.getProcessMsg());
			}
			docNo.append(payment.getDocumentNo()+",");
		}
		/*@KevinY end*/
		
		
		String message = Msg.parseTranslation(getCtx(), "@GeneratedPayment@ "+docNo);
		addBufferLog(0, null, null, message, 0, 0);
		
		return "";
	}

	/**
	 * create matching table from payroll process to payment
	 * TODO finish this method
	 */
	private void createMatching(int HC_PayrollProcess_ID, int HC_Employee_ID){
		
		String whereClause = MHCMovement.COLUMNNAME_HC_PayrollProcess_ID+"=? AND "
				+MHCMovement.COLUMNNAME_HC_Employee_ID+"=? AND "+MHCMovement.COLUMNNAME_IsPaid+"=? ";
		int[] HC_Movement_IDs = new Query(getCtx(), MHCMovement.Table_Name, whereClause, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{HC_PayrollProcess_ID, HC_Employee_ID, true})
		.getIDs();
		
		for (int HC_Movement_ID : HC_Movement_IDs) {
			//TODO create matching here
		}
	}
	
}
