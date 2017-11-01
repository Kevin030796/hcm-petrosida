package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCProcessListLine extends X_HC_ProcessListLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6991674461349319200L;

	public MHCProcessListLine(Properties ctx, int HC_ProcessListLine_ID,
			String trxName) {
		super(ctx, HC_ProcessListLine_ID, trxName);
	}
	
	public MHCProcessListLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
