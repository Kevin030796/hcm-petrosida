package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHCAttributeList extends X_HC_AttributeList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3463953509108832743L;

	public MHCAttributeList(Properties ctx, int HC_AttributeList_ID,
			String trxName) {
		super(ctx, HC_AttributeList_ID, trxName);
	}

	public MHCAttributeList(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
