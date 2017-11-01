package org.taowi.hcm.payroll.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.taowi.hcm.payroll.model.MHCMovement;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.taowi.hcm.payroll.model.MHCPayrollProcess;


/**
 * @author KevinY
 * Process for Payroll Process generate Invoice and invoice line
 */
public class PayrollProcessToInvoice extends SvrProcess {
	
	private int p_HC_PayrollProcess_ID = 0;
	private int p_C_Charge_ID = 0;
	private int p_C_Currency_ID = 0;
	//private int p_C_BPartner_ID = 0;
	//private int p_HC_PayComponent_ID = 0;
	private int p_C_DocTypeTarget_ID = 0;
	private int p_M_PriceList_ID = 0;
	private int p_C_Tax_ID = 0;
	private String p_DocStatus = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if (name.equals(MHCPayrollProcess.COLUMNNAME_HC_PayrollProcess_ID))
				p_HC_PayrollProcess_ID = para[i].getParameterAsInt();
			else if (name.equals(MInvoice.COLUMNNAME_C_Charge_ID))
				p_C_Charge_ID = para[i].getParameterAsInt();
			else if (name.equals(MInvoice.COLUMNNAME_C_Currency_ID))
				p_C_Currency_ID = para[i].getParameterAsInt();
			else if (name.equals(MInvoice.COLUMNNAME_C_DocTypeTarget_ID))
				p_C_DocTypeTarget_ID = para[i].getParameterAsInt();
			else if (name.equals(MInvoice.COLUMNNAME_DocStatus))
				p_DocStatus = para[i].getParameterAsString();
			else if(name.equals(MInvoice.COLUMNNAME_M_PriceList_ID))
				p_M_PriceList_ID = para[i].getParameterAsInt();
			else if(name.equals("C_Tax_ID"))
				p_C_Tax_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		int HC_PayrollProcess_ID = p_HC_PayrollProcess_ID;
		MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), HC_PayrollProcess_ID, get_TrxName());
		
		if(!payrollProcess.isProcessed())
			throw new AdempiereException("Payroll Process Not Processed");
		
		//check completed document journal related with payroll process
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String whereClause = "SELECT "+MJournal.COLUMNNAME_GL_Journal_ID+" FROM "+MJournal.Table_Name+" "
				+ "WHERE "+MJournal.COLUMNNAME_Description+" like ? "
				+ "AND "+MJournal.COLUMNNAME_DocStatus+" IN('"+MJournal.DOCSTATUS_Completed+"')";
		try{
			pstmt = DB.prepareStatement(whereClause, get_TrxName());
			pstmt.setString(1, payrollProcess.get_ID() + "");
			rs = pstmt.executeQuery();
			if(!rs.next()){
				throw new AdempiereException("Payroll Process doesn't have completed journal, please generate completed journal");
			}
		}catch(Exception e){
			log.severe("Error get GL Journal : "+e.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		List<Integer> BPartners = new ArrayList<Integer>();
		List<MHCPayComponent> payComponents = new ArrayList<MHCPayComponent>();
		HashMap<Integer, List<MHCPayComponent>> BpMap = new HashMap<Integer, List<MHCPayComponent>>();
		BigDecimal AccPayAmt = new BigDecimal(0);
		BigDecimal TotalAccPayAmt = new BigDecimal(0);
		int C_BPartner_ID = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT(C_BPartner_ID) FROM "+MHCPayComponent.Table_Name+" WHERE ");
		sb.append("IsForInvoice = 'Y' AND C_BPartner_ID is not null AND "+MHCPayComponent.COLUMNNAME_IsActive+"='Y'");
		rs = null;
		pstmt = null;
		try{
			pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				BPartners.add(rs.getInt(1));
			}
		}catch(Exception e){
			log.info(e.toString()+"-"+sb.toString());
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		sb = new StringBuilder();
		sb.append("SELECT HC_PayComponent_ID FROM "+MHCPayComponent.Table_Name+" WHERE ");
		sb.append("IsForInvoice='Y' AND C_BPartner_ID=? AND "+MHCPayComponent.COLUMNNAME_IsActive+"='Y'");
		for(int i = 0 ; i < BPartners.size() ; i++){
			payComponents = new ArrayList<MHCPayComponent>();
			C_BPartner_ID = BPartners.get(i).intValue();
			pstmt = null;
			rs = null;
			try{
				pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
				pstmt.setInt(1, C_BPartner_ID);
				rs = pstmt.executeQuery();
				while(rs.next()){
					MHCPayComponent PayComponent = new MHCPayComponent(getCtx(), rs.getInt(1), get_TrxName());
					payComponents.add(PayComponent);
				}
			}catch(Exception e){
				log.info(e.toString()+"-"+sb.toString());
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			BpMap.put(C_BPartner_ID, payComponents);
			C_BPartner_ID = 0;
		}
		StringBuilder docNo = new StringBuilder();
		
		
		for(int i = 0 ; i < BPartners.size()  ; i++ ) {
			MBPartner partner = new MBPartner(getCtx(), BPartners.get(i).intValue(), get_TrxName());
			//create Invoice
			MBPartnerLocation[] BpLocations = 
					MBPartnerLocation.getForBPartner(getCtx(), BPartners.get(i).intValue(), get_TrxName());
			
			if(BpLocations.length <= 0)
				throw new AdempiereException("Error: Business Partner doesn't have Business Partner location");
		
			TotalAccPayAmt = new BigDecimal(0);
			AccPayAmt = new BigDecimal(0);
			List<MHCMovement> movements = new ArrayList<MHCMovement>();
			movements = getMovements(p_HC_PayrollProcess_ID);
			
			//getAllPayComponents with C_BPartner related
			payComponents = new ArrayList<MHCPayComponent>();
			payComponents = BpMap.get(BPartners.get(i));
			System.out.println("bpname : "+ partner.getName());
			for(int k = 0 ; k < payComponents.size() ; k++ ){
				MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponents.get(k).get_ID(), get_TrxName());
				for(int j = 0 ; j < movements.size();j++ ) {
					if(payComponents.get(k).get_ID() == movements.get(j).getHC_PayComponent_ID()){
						System.out.println("payComponent : " + payComponent.getValue());
						//System.out.println("payCOmponent : "+ payComponents.get(k).get_ID() + ": " + movements.get(j).getHC_AmtValue());
						AccPayAmt = AccPayAmt.add(movements.get(j).getHC_AmtValue());
						//System.out.println("nilai AccPayAmt sementara : " + AccPayAmt);
					}
				}//endForMovements
			}//endForPayComponents
			TotalAccPayAmt = AccPayAmt;
			
			//create invoice
			MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
			invoice.setAD_Org_ID(payrollProcess.getAD_Org_ID());
			invoice.setC_Currency_ID(p_C_Currency_ID);
			invoice.setC_Charge_ID(p_C_Charge_ID);
			invoice.setC_BPartner_ID(BPartners.get(i).intValue());
			invoice.setC_BPartner_Location_ID(BpLocations[0].getC_BPartner_Location_ID());
			invoice.setDateInvoiced(currentDate);
			invoice.setDateAcct(currentDate);
			//invoice.setDescription(BPartners.get(i).intValue() + "");
			invoice.setDescription(HC_PayrollProcess_ID + "");
			invoice.setM_PriceList_ID(p_M_PriceList_ID);
			invoice.setC_DocTypeTarget_ID(p_C_DocTypeTarget_ID);
			invoice.setC_DocType_ID(p_C_DocTypeTarget_ID);
			invoice.setIsSOTrx(false);
			invoice.set_ValueOfColumn("C_Tax_ID", p_C_Tax_ID);
			invoice.saveEx();
			
			//create Invoice line
			createLine(invoice, TotalAccPayAmt);
			
			//docAction
			if(p_DocStatus.equals(MInvoice.DOCSTATUS_Completed)){
				invoice.setDocAction(MInvoice.DOCACTION_Complete);
				invoice.saveEx();
				if(!invoice.processIt(MInvoice.DOCACTION_Complete))
					throw new AdempiereException("Cant Complete invoice "+invoice.getProcessMsg());
			}
			
			docNo.append(invoice.getDocumentNo()+",");
			
			String message = Msg.parseTranslation(getCtx(), "@GeneratedInvoice@ "+docNo);
			addBufferLog(0, null, null, message, 0, 0);	
			
		}//endForC_BPartner
		return "";
	}//doIt

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
				+ "LEFT JOIN HC_PayComponent comp ON comp.HC_PayComponent_ID = mov.HC_PayComponent_ID "
				+ "LEFT JOIN HC_Employee emp ON emp.HC_Employee_ID = mov.HC_Employee_ID "
				+ "LEFT JOIN HC_EmployeeJob job ON job.HC_Employee_ID = emp.HC_Employee_ID "
				+ "WHERE mov.HC_PayrollProcess_ID=? AND comp.IsForInvoice = 'Y' AND comp.C_BPartner_ID is not null AND comp.IsActive='Y' "
				+ "AND job.HC_Status = 'A' AND job.IsActive='Y' AND job.HC_EmployeeCategory_ID is not null");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
			pstmt.setInt(1, HC_PayrollProcess_ID);
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
	}//getMovements

	/**
	 * Create Line for Invoice
	 * @param invoice
	 * @param Amt
	 */
	private void createLine(MInvoice invoice, BigDecimal Amt){
		MInvoiceLine invoiceLine = new MInvoiceLine(getCtx(), 0, get_TrxName());
		invoiceLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
		invoiceLine.setAD_Org_ID(invoice.getAD_Org_ID());
		invoiceLine.setPrice(Amt);
		invoiceLine.setPriceActual(Amt);
		invoiceLine.setQtyEntered(Env.ONE);
		invoiceLine.setQtyInvoiced(Env.ONE);
		invoiceLine.setC_Charge_ID(p_C_Charge_ID);
		invoiceLine.setC_Tax_ID(p_C_Tax_ID);
		invoiceLine.setTaxAmt(Env.ZERO);
		invoiceLine.setPriceList(Env.ZERO);
		invoiceLine.setLineNetAmt(Amt);
		invoiceLine.setLineTotalAmt(Amt);
		invoiceLine.saveEx();
	}//createLine
	
}//endClass
