package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCAttributeMap extends X_HC_AttributeMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4554542596452737006L;

	public MHCAttributeMap(Properties ctx, int HC_AttributeMap_ID,
			String trxName) {
		super(ctx, HC_AttributeMap_ID, trxName);
	}

	public MHCAttributeMap(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
