package org.taowi.hcm.core.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Msg;


/**
 * @author KevinY
 * Model for HC_EmployeeGrade
 * PSD - 2777
 */
public class MEmployeeGrade extends X_HC_EmployeeGrade{
	
	//@KevinY PSD - 2777
	public MEmployeeGrade(Properties ctx, int HC_EmployeeGrade_ID,
			String trxName) {
		super(ctx, HC_EmployeeGrade_ID, trxName);
	}

	/**
	 * Make Object HC_EmployeeGrade by Result Set
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEmployeeGrade(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);

	}//MEmployeeGrade

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Before Save
	 * @return true
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (newRecord || is_ValueChanged(COLUMNNAME_SalaryMin) || is_ValueChanged(COLUMNNAME_SalaryMax)) {
			BigDecimal salaryMax = getSalaryMax();
			BigDecimal salaryMin = getSalaryMin();
			if(salaryMin.compareTo(salaryMax) == 1){
				log.saveError("Error", Msg.getMsg(getCtx(), "Salary Min must be less than equals salary Max"));
				return false;	
			}
		}
		return true;
	}//beforeSave
	//@KevinY end
}//endClass
