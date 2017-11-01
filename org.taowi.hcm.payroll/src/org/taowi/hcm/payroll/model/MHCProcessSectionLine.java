package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCProcessSectionLine extends X_HC_ProcessSectionLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1132634827723254535L;

	public MHCProcessSectionLine(Properties ctx, int HC_ProcessSectionLine_ID,
			String trxName) {
		super(ctx, HC_ProcessSectionLine_ID, trxName);
	}

	public MHCProcessSectionLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
}
