package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCEligibilityGroupLine extends X_HC_EligibilityGroupLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4566115804263338215L;

	public MHCEligibilityGroupLine(Properties ctx,
			int HC_EligibilityGroupLine_ID, String trxName) {
		super(ctx, HC_EligibilityGroupLine_ID, trxName);
	}

	public MHCEligibilityGroupLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
}
