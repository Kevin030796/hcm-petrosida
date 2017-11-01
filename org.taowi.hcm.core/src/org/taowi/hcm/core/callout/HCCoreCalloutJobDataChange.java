package org.taowi.hcm.core.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJobDataChange;


public class HCCoreCalloutJobDataChange extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(MJobDataChange.COLUMNNAME_HC_Employee_ID)){
			return jobData(ctx, WindowNo, mTab, mField, value);
		}

		//end habco-1665 callout paymentterm by edwin handy
				
		return "";
	}
	
	private String jobData(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value) {
		String msg="";
		if (value==null){
			return msg;
		}
		
		int employeeID= (Integer)value;
		
		int jobDataID = new Query(Env.getCtx(),MEmployeeJob.Table_Name,"HC_Employee_ID=?",null)
			.setParameters(employeeID)
			.setOnlyActiveRecords(true)
			.firstId();
		
		if (jobDataID > 0) {
			MEmployeeJob employeeJob = new MEmployeeJob(Env.getCtx(), jobDataID, null);
			mTab.setValue(MJobDataChange.COLUMNNAME_AD_Org_ID, employeeJob.getAD_Org_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_C_Location_ID, employeeJob.getC_Location_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_Description, employeeJob.getDescription());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Compensation1, employeeJob.getHC_Compensation1());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Compensation2, employeeJob.getHC_Compensation2());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Compensation3, employeeJob.getHC_Compensation3());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Compensation4, employeeJob.getHC_Compensation4());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Employee_ID, employeeJob.getHC_Employee_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_EmployeeCategory_ID, employeeJob.getHC_EmployeeCategory_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_EmployeeClass_ID, employeeJob.getHC_EmployeeClass_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_EmployeeGrade_ID, employeeJob.getHC_EmployeeGrade_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Job_ID, employeeJob.getHC_Job_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_JobAction, employeeJob.getHC_JobAction());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Manager_ID, employeeJob.getHC_Manager_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_ManagerPosition_ID, employeeJob.getHC_ManagerPosition_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_PayGroup_ID, employeeJob.getHC_PayGroup_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Position_ID, employeeJob.getHC_Position_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Reason_ID, employeeJob.getHC_Reason_ID());
			mTab.setValue(MJobDataChange.COLUMNNAME_HC_Status, employeeJob.getHC_Status());
		}
		return "";
	}
	//end of HABCO-1588

}
