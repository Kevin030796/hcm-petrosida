package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

public class MHCPayrollProcessLine extends X_HC_PayrollProcessLine {

	public MHCPayrollProcessLine(Properties ctx, int HC_PayrollProcessLine_ID,
			String trxName) {
		super(ctx, HC_PayrollProcessLine_ID, trxName);
	}

	public MHCPayrollProcessLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {

		//int p_HC_PayComponentGroup_ID = getHC_PayComponentGroup_ID();
		
		//MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), getHC_PayrollProcess_ID(), get_TrxName());
		
		//HashMap<Integer, Integer> map = payrollProcess.getPayComponent();
		
		//if (map.get(p_HC_PayComponent_ID) == null)
			//return false;
		//else 
		//return super.beforeSave(newRecord);
		return true;
	}
	
}
