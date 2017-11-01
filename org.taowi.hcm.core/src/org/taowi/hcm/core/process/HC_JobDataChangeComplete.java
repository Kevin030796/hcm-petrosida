package org.taowi.hcm.core.process;

import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJobDataChange;

public class HC_JobDataChangeComplete extends SvrProcess{

	int p_JobDataChange_ID = 0;
	boolean p_BlackList = false;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			
			else if (name.equals("IsBlackList")){
				p_BlackList = para[i].getParameterAsBoolean();
			}
			/*
			} else if (name.equals("EffectiveDateFrom")){
					EffectiveDateFrom = para[i].getParameterAsTimestamp();
			
			}*/
		else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_JobDataChange_ID = getRecord_ID();		
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_JobDataChange_ID==0){
			return "Data Change Transaction not selected";
		}
		
		MJobDataChange jDataChange = new MJobDataChange(getCtx(), p_JobDataChange_ID, get_TrxName());
		if (jDataChange.isProcessed())
			return "Abort.. Data Change Transaction Has Been Processed";
		
		int employeeJobID = 0;
		
		if (!jDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_Rehire)) {
			employeeJobID = jDataChange.get_ValueAsInt("HC_EmployeeJob_ID");
			if (employeeJobID <= 0)
				return "Abort.. No Employee Job Selected";
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), employeeJobID, get_TrxName());
		MEmployee employee = new MEmployee(getCtx(), jDataChange.getHC_Employee_ID(), get_TrxName());
		
		//Terminate
		if (jDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_Terminate)) {
			
			String p_HC_Status = MEmployee.HC_STATUS_Terminate;
			if (p_BlackList) {
				p_HC_Status = MEmployee.HC_STATUS_Blacklist;
			}
			
			if (employeeJob.getSeqNo() == 1) {

				employee.setHC_Status(p_HC_Status);
				employee.setHC_WorkEndDate(jDataChange.getEffectiveDateFrom());
				
				int[] employeeJobIDs = new Query(getCtx(), MEmployeeJob.Table_Name, "HC_Employee_ID=? AND SeqNo<>1 AND IsActive=?", get_TrxName())
				.setParameters(new Object[] {employee.get_ID(), true})
				.setOnlyActiveRecords(true)
				.setOrderBy(MEmployeeJob.COLUMNNAME_SeqNo + " ASC")
				.getIDs();
				
				if (employeeJobIDs.length > 0) {
					for (int employeeJobOtherID : employeeJobIDs) {
						MEmployeeJob employeeJobOther = new MEmployeeJob(getCtx(), employeeJobOtherID, get_TrxName());
						employeeJobOther.setHC_Status(p_HC_Status);
						employeeJobOther.setHC_WorkEndDate(jDataChange.getEffectiveDateFrom());
						employeeJobOther.saveEx();
					}
				}
				
			}
			employeeJob.setHC_Status(p_HC_Status);
			employeeJob.setHC_WorkEndDate(jDataChange.getEffectiveDateFrom());
			employeeJob.saveEx();
		}
		
		//Rehire
		if (jDataChange.getHC_JobAction()
				.equals(MJobDataChange.HC_JOBACTION_Rehire)) {
			employeeJob.setHC_Employee_ID(jDataChange.getHC_Employee_ID());
			employeeJob.setHC_Job_ID(jDataChange.getHC_Job_ID());
			employeeJob.setHC_Position_ID(jDataChange.getHC_Position_ID());
			employeeJob.setHC_Manager_ID(jDataChange.getHC_Manager_ID());
			employeeJob.setHC_ManagerPosition_ID(jDataChange.getHC_ManagerPosition_ID());
			employeeJob.setHC_Org_ID(jDataChange.getHC_Org_ID());
			employeeJob.setC_Location_ID(jDataChange.getC_Location_ID());
			employeeJob.setHC_EmployeeCategory_ID(jDataChange.getHC_EmployeeCategory_ID());
			employeeJob.setHC_EmployeeClass_ID(jDataChange.getHC_EmployeeClass_ID());
			employeeJob.setHC_EmployeeGrade_ID(jDataChange.getHC_EmployeeGrade_ID());
			employeeJob.setHC_Compensation1(jDataChange.getHC_Compensation1());
			employeeJob.setHC_Compensation2(jDataChange.getHC_Compensation2());
			employeeJob.setHC_Compensation3(jDataChange.getHC_Compensation3());
			employeeJob.setHC_Compensation4(jDataChange.getHC_Compensation4());
			employeeJob.setHC_PayGroup_ID(jDataChange.getHC_PayGroup_ID());
			employeeJob.setHC_WorkStartDate(jDataChange.getEffectiveDateFrom());
			employeeJob.setHC_Status(MEmployeeJob.HC_STATUS_Active);
		
		}
		
		if (jDataChange.getHC_JobAction()
				.equals(MJobDataChange.HC_JOBACTION_DataChange)) {
			employeeJob.setHC_EmployeeCategory_ID(jDataChange.getHC_EmployeeCategory_ID());
			employeeJob.setHC_EmployeeClass_ID(jDataChange.getHC_EmployeeClass_ID());
			employeeJob.setHC_EmployeeGrade_ID(jDataChange.getHC_EmployeeGrade_ID());
			employeeJob.setC_Location_ID(jDataChange.getC_Location_ID());
			employeeJob.setHC_Job_ID(jDataChange.getHC_Job_ID());
			employeeJob.setEffectiveDateFrom(jDataChange.getEffectiveDateFrom());
		}
		
		if (jDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_Transfer
				/*
				|| jDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_Promotion) ||
				|| jDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_Demotion) ||
				 */
				)) {
			employeeJob.setHC_Job_ID(jDataChange.getHC_Job_ID());
			employeeJob.setHC_Position_ID(jDataChange.getHC_Position_ID());
			employeeJob.setHC_Manager_ID(jDataChange.getHC_Manager_ID());
			employeeJob.setHC_ManagerPosition_ID(jDataChange.getHC_ManagerPosition_ID());
			employeeJob.setHC_Org_ID(jDataChange.getHC_Org_ID());
			employeeJob.setHC_Compensation1(jDataChange.getHC_Compensation1());
			employeeJob.setHC_Compensation2(jDataChange.getHC_Compensation2());
			employeeJob.setHC_Compensation3(jDataChange.getHC_Compensation3());
			employeeJob.setHC_Compensation4(jDataChange.getHC_Compensation4());
			employeeJob.setHC_PayGroup_ID(jDataChange.getHC_PayGroup_ID());
			employeeJob.setHC_Status(MEmployeeJob.HC_STATUS_Active);
		}
		
		/*
		if (jDataChange.getHC_JobAction()
				.equals(MJobDataChange.HC_JOBACTION_Hire)) {
			String whereClause = "SELECT COALESCE(Max(SeqNo),0)+1 FROM HC_EmployeeJOB WHERE HC_Employee_ID=?";
			int seqno = DB.getSQLValue(get_TrxName(), whereClause, jDataChange.getHC_Employee_ID());
			employeeJob.set_CustomColumn("SeqNo", seqno);
			employeeJob.setHC_EffectiveSeq(99);
			
		}*/

		
		if (jDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_SalaryChange)) {
			employeeJob.setHC_Compensation1(jDataChange.getHC_Compensation1());
			employeeJob.setHC_Compensation2(jDataChange.getHC_Compensation2());
			employeeJob.setHC_Compensation3(jDataChange.getHC_Compensation3());
			employeeJob.setHC_Compensation4(jDataChange.getHC_Compensation4());
			employeeJob.setHC_PayGroup_ID(jDataChange.getHC_PayGroup_ID());
			employeeJob.setHC_Status(MEmployeeJob.HC_STATUS_Active);
			
		}
		
		employeeJob.setDescription(jDataChange.getDescription());
		employeeJob.setEffectiveDateFrom(jDataChange.getEffectiveDateFrom());		
		employeeJob.setHC_JobAction(jDataChange.getHC_JobAction());
		employeeJob.setHC_Reason_ID(jDataChange.getHC_Reason_ID());
		employeeJob.saveEx();
		
		jDataChange.setProcessed(true);
		jDataChange.saveEx();
		
		return "Successfully Updated Job Data" ;
	}

}