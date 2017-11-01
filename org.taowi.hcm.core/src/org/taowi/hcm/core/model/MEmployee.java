package org.taowi.hcm.core.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.DB;

public class MEmployee extends X_HC_Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -634814915006843410L;

	public MEmployee(Properties ctx, int HC_Employee_ID, String trxName) {
		super(ctx, HC_Employee_ID, trxName);
	}

	public MEmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * get employee job effective sequence = 1
	 * @return MEmployeeJob
	 */
	public MEmployeeJob getEmployeeJob(){
		
		String where = COLUMNNAME_HC_Employee_ID+"="+getHC_Employee_ID()
				+" AND "+MEmployeeJob.COLUMNNAME_SeqNo+"=1";
		int HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, where, get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.firstId();
		
		return new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
	}
	
	/**
	 * author Kevin Yulianto
	 * get employee job effective sequence = 1
	 * @return 
	 * ID of employee job
	 */
	public int getActiveSequenceOneEmployeeJob(){
		
		String where = COLUMNNAME_HC_Employee_ID+"="+getHC_Employee_ID()
				+ " AND "+MEmployeeJob.COLUMNNAME_SeqNo+"=1 "
				+ " AND "+MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
		int HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, where, get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.firstId();
		
		if(HC_EmployeeJob_ID<= 0)
			return 0;
		else
			return HC_EmployeeJob_ID;
	}
	
	/**
	 * author Kevin Yulianto
	 * get Employee job with employee class for job data change (
	 * used for only callout employee job in HC_JobDataChange
	 * @return
	 * ID of employee job
	 */
	public int getJobDataChangeEmployeeJob(){
		String whereClause = "SELECT ej.HC_EmployeeJob_ID FROM HC_EmployeeJob ej"
				+ " LEFT JOIN HC_EmployeeClass ec ON ec.HC_EmployeeClass_ID = ej.HC_EmployeeClass_ID "
				+ " WHERE ec.IsForJobDataChange='Y'"
				+ " AND ej.HC_Employee_ID=?"
				+ " AND ej.SeqNo=1 "
				+ " AND ej.IsActive='Y' "
				+ " AND ej.HC_Status='A'";
		int HC_EmployeeJob_ID = DB.getSQLValue(null, whereClause, getHC_Employee_ID());//get employeeJob seq 1 and Active
		
		if(HC_EmployeeJob_ID<= 0)
			return 0;
		else
			return HC_EmployeeJob_ID;
		
	}
	
	
	/**
	 * Get Utang Cuti ID from employee and still active 
	 * @return
	 * Ids of HC_LeaveDebt
	 */
	public int[] getUtangCutiIDs(){
		String whereClause = "HC_Employee_ID = ?";
		int[] HC_UtangCuti_IDs = new Query(getCtx(), "HC_LeaveDebt", whereClause, get_TrxName())
								.setParameters(getHC_Employee_ID())
								.setOnlyActiveRecords(true)
								.getIDs();
		return HC_UtangCuti_IDs;
	}
	
}
