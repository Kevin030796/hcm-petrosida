package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MPeriod;

public class MHCPayrollTrigger extends X_HC_PayrollTrigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7662239961415490449L;

	public MHCPayrollTrigger(Properties ctx, int HC_PayrollTrigger_ID,
			String trxName) {
		super(ctx, HC_PayrollTrigger_ID, trxName);
	}
	
	public MHCPayrollTrigger(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		
		int C_Period_ID = MPeriod.getC_Period_ID(getCtx(), getEffectiveDateFrom(), getAD_Org_ID());
		setC_Period_ID(C_Period_ID);
		
		return true;
	}
	
}
