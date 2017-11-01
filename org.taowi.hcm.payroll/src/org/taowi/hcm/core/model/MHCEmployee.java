package org.taowi.hcm.core.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.payroll.model.MHCPayrollTrigger;

public class MHCEmployee extends MEmployee{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6373653534751748382L;

	public MHCEmployee(Properties ctx, int HC_Employee_ID, String trxName) {
		super(ctx, HC_Employee_ID, trxName);
	}
	
	public MHCEmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return true if can be saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if(newRecord)
			return true;
		
		if(is_ValueChanged(COLUMNNAME_HC_Religion_ID) 
			|| is_ValueChanged(COLUMNNAME_HC_TaxStatus_ID)){
			
			String columnName = null;
			
			if(is_ValueChanged(COLUMNNAME_HC_Religion_ID))
				columnName = COLUMNNAME_HC_Religion_ID;
			else if(is_ValueChanged(COLUMNNAME_HC_TaxStatus_ID))
				columnName = COLUMNNAME_HC_TaxStatus_ID;
			
			String oldValue = (String) get_ValueOld(columnName);
			String newValue = (String) get_Value(columnName);
			
			MHCPayrollTrigger trigger = new MHCPayrollTrigger(getCtx(), 0, get_TrxName());
			trigger.setAD_Org_ID(getAD_Org_ID());
			trigger.setEffectiveDateFrom(getEffectiveDateFrom());
			trigger.setHC_Employee_ID(getHC_Employee_ID());
			trigger.setColumnName(columnName);
			trigger.setOldValue(oldValue);
			trigger.setNewValue(newValue);
			trigger.saveEx();
			
		}
		
		return true;
	}
	
}
