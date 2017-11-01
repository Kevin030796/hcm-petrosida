package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

public class MHCPayComponentGroupLine extends X_HC_PayComponentGroupLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5713128463063542711L;

	public MHCPayComponentGroupLine(Properties ctx,
			int HC_PayComponentGroupLine_ID, String trxName) {
		super(ctx, HC_PayComponentGroupLine_ID, trxName);
	}

	public MHCPayComponentGroupLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {

//		int p_HC_PayComponent_ID = getHC_PayComponent_ID();
//		
//		MHCPayComponentGroup componentGroup = new MHCPayComponentGroup(getCtx(), getHC_PayComponentGroup_ID(), get_TrxName());
//		
//		HashMap<Integer, Integer> map = componentGroup.getPayComponent();
//		
//		if (map.get(p_HC_PayComponent_ID) == null)
//			return false;
//		else 
//			return super.beforeSave(newRecord);
//		
		return true;
	}
	
	

}
