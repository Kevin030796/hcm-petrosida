package org.taowi.hcm.core.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MEmployeeJob extends X_HC_EmployeeJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4004204955679151108L;

	public MEmployeeJob(Properties ctx, int HC_EmployeeJob_ID, String trxName) {
		super(ctx, HC_EmployeeJob_ID, trxName);
	}

	public MEmployeeJob(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * get job data change
	 * @return array of MJobDataChange
	 */
	public MJobDataChange[] getJobDataChanges(){
		
		final String whereClause = MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+"=?";
		List<MJobDataChange> list = new Query(getCtx(), MJobDataChange.Table_Name, whereClause, get_TrxName())
			.setParameters(get_ID())
			.list();
		MJobDataChange[] jobDataChanges = new MJobDataChange[list.size()];
		list.toArray(jobDataChanges);
		
		return jobDataChanges;
	}
	
	
}
