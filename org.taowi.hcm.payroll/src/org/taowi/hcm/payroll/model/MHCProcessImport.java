package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCProcessImport extends X_HC_ProcessImport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4919442977169373497L;

	public MHCProcessImport(Properties ctx, int HC_ProcessImport_ID,
			String trxName) {
		super(ctx, HC_ProcessImport_ID, trxName);
	}

	public MHCProcessImport(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
}
