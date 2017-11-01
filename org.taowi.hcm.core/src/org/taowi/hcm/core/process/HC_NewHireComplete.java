package org.taowi.hcm.core.process;

import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;

public class HC_NewHireComplete extends SvrProcess{

	int p_Employee_ID = 0;
	int p_C_BP_Group_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			
			/*else if (name.equals("EffectiveDateFrom")){
					EffectiveDateFrom = para[i].getParameterAsTimestamp();
			
			}*/
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_Employee_ID = getRecord_ID();		
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_Employee_ID==0){
			return "No employee selected";
		}

		MEmployee employee = new MEmployee(getCtx(), p_Employee_ID, get_TrxName());
		
		if (!employee.getHC_Status().equals(MEmployee.HC_STATUS_Pending)) {
			return "Abort.. Can only hire data with status pending";
		}
		/*//temporary comment by @win
		boolean match = new Query(getCtx(), MEmployeeJob.Table_Name, "HC_Employee_ID=? AND SeqNo=1 AND IsActive=?", get_TrxName())
		.setParameters(new Object[] {p_Employee_ID, true})
		.setOnlyActiveRecords(true)
		.match();
		
		if (match) {
			return "Abort.. Cannot Have More Than One Active Employee Job with Sequence No = 1";
		}
		*/
		
		int[] employeeJobIDs = new Query(getCtx(), MEmployeeJob.Table_Name, "HC_Employee_ID=? AND IsActive=?", get_TrxName())
		.setParameters(new Object[] {p_Employee_ID, true})
		.setOnlyActiveRecords(true)
		.setOrderBy(MEmployeeJob.COLUMNNAME_SeqNo + " ASC")
		.getIDs();
		
		if (employeeJobIDs.length == 0) {
			return "Abort.. Missing Job Data";
		}
		
		employee.setHC_Status(MEmployee.HC_STATUS_Active);
		employee.setHC_WorkStartDate(employee.getEffectiveDateFrom());
		employee.saveEx();
		
		//int p_EffectiveSeqNo = 99; //@win temporary commented
		
		for (int employeeJobID : employeeJobIDs) {
			MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), employeeJobID, get_TrxName());
			if (employeeJob.getSeqNo() == 1) {
				//TODO: @stephan please put the payroll trigger here
			}
			
			employeeJob.setHC_Status(MEmployeeJob.HC_STATUS_Active);
			employeeJob.setHC_JobAction(MEmployeeJob.HC_JOBACTION_Hire);
			//employeeJob.setHC_EffectiveSeq(p_EffectiveSeqNo); //@win temporary commented
			employeeJob.saveEx();
			//p_EffectiveSeqNo--; //@win temporary comment
			
		}
		
		if (employee.getC_BPartner_ID() <= 0) {
			MBPartner bPartner = new MBPartner(getCtx(), 0, get_TrxName());
			bPartner.setClientOrg(employee.getAD_Client_ID(), employee.getAD_Org_ID());
			bPartner.setValue(employee.getValue());
			bPartner.setName(employee.getName());
			bPartner.setName2(employee.getName2());
			bPartner.setIsEmployee(true);
			bPartner.setIsCustomer(false);
			bPartner.setIsVendor(false);
			bPartner.setC_BP_Group_ID(p_C_BP_Group_ID);
			bPartner.setIsSummary(false);
			bPartner.saveEx();
			
			MBPartnerLocation bpLocation = new MBPartnerLocation(bPartner);
			bpLocation.setC_Location_ID(employee.getC_Location_ID());
			bpLocation.saveEx();
			
			employee.setC_BPartner_ID(bPartner.getC_BPartner_ID());
			employee.saveEx();
		}
		return "New Hire Process for Employee" + employee.getValue() + " - " + employee.getName() + " Complete";
	}

}
