package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCProcessEmployee extends X_HC_ProcessEmployee{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1457515556074308526L;

	public MHCProcessEmployee(Properties ctx, int HC_ProcessEmployee_ID,
			String trxName) {
		super(ctx, HC_ProcessEmployee_ID, trxName);
	}

	public MHCProcessEmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
}
