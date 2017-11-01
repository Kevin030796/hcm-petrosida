package org.taowi.hcm.core.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.payroll.model.MHCPayrollTrigger;

public class MHCEmployeeJob extends MEmployeeJob{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7847776481872958694L;

	public MHCEmployeeJob(Properties ctx, int HC_EmployeeJob_ID, String trxName) {
		super(ctx, HC_EmployeeJob_ID, trxName);
	}
	
	public MHCEmployeeJob(Properties ctx, ResultSet rs, String trxName) {
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
		
		if((is_ValueChanged(COLUMNNAME_HC_Compensation1) 
			|| is_ValueChanged(COLUMNNAME_HC_Compensation2)
			|| is_ValueChanged(COLUMNNAME_HC_Compensation3)
			|| is_ValueChanged(COLUMNNAME_HC_Compensation4)) 
			&& getHC_EffectiveSeq() == 1){
			
			String columnName = null;
			if(is_ValueChanged(COLUMNNAME_HC_Compensation1))
				columnName = COLUMNNAME_HC_Compensation1;
			else if(is_ValueChanged(COLUMNNAME_HC_Compensation2))
				columnName = COLUMNNAME_HC_Compensation2;
			else if(is_ValueChanged(COLUMNNAME_HC_Compensation3))
				columnName = COLUMNNAME_HC_Compensation3;
			else if(is_ValueChanged(COLUMNNAME_HC_Compensation4))
				columnName = COLUMNNAME_HC_Compensation4;
			
			BigDecimal bdOldValue = (BigDecimal) get_ValueOld(columnName);
			BigDecimal bdNewValue = (BigDecimal) get_Value(columnName);
			BigDecimal diff = bdNewValue.subtract(bdOldValue);
			
			String oldValue = bdOldValue.toString();
			String newValue = bdNewValue.toString();
			
			MHCPayrollTrigger trigger = new MHCPayrollTrigger(getCtx(), 0, get_TrxName());
			trigger.setAD_Org_ID(getAD_Org_ID());
			trigger.setEffectiveDateFrom(getEffectiveDateFrom());
			trigger.setHC_Employee_ID(getHC_Employee_ID());
			trigger.setHC_EmployeeJob_ID(getHC_EmployeeJob_ID());
			trigger.setColumnName(columnName);
			trigger.setOldValue(oldValue);
			trigger.setNewValue(newValue);
			trigger.setDifferenceAmt(diff);
			trigger.saveEx();
		}
		
		
		return true;
	}

}
