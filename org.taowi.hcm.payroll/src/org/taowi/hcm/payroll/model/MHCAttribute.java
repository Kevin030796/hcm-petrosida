package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

public class MHCAttribute extends X_HC_Attribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2936534265633618052L;

	private Timestamp validTo = null;
	
	public MHCAttribute(Properties ctx, int HC_Attribute_ID, String trxName) {
		super(ctx, HC_Attribute_ID, trxName);
	}

	public MHCAttribute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

}
