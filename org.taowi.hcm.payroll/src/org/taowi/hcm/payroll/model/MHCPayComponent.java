package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class MHCPayComponent extends X_HC_PayComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHCPayComponent(Properties ctx, int HC_PayComponent_ID,
			String trxName) {
		super(ctx, HC_PayComponent_ID, trxName);
	}

	public MHCPayComponent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/** Cache */
	private static CCache<Integer, MHCPayComponent> s_cache = new CCache<Integer, MHCPayComponent>(Table_Name, 100);
	/** Cache by Value */
	private static CCache<String, MHCPayComponent> s_cacheValue = new CCache<String, MHCPayComponent>(Table_Name+"_Value", 100);
	
	public static MHCPayComponent get(Properties ctx, int p_HC_PayComponent_ID)
	{
		if (p_HC_PayComponent_ID <= 0)
			return null;
		//
		MHCPayComponent payComponent = s_cache.get(p_HC_PayComponent_ID);
		if (payComponent != null)
			return payComponent;
		//
		payComponent = new MHCPayComponent(ctx, p_HC_PayComponent_ID, null);
		if (payComponent.get_ID() == p_HC_PayComponent_ID) {
			s_cache.put(p_HC_PayComponent_ID, payComponent);
		}
		else {
			payComponent = null;	
		}
		
		return payComponent; 
	}

	/**
	 * Get Concept by Value
	 * @param ctx
	 * @param value
	 * @return
	 */
	public static MHCPayComponent forValue(Properties ctx, String value)
	{
		if (Util.isEmpty(value, true)) {
			return null;
		}
		
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		final String key = AD_Client_ID+"#"+value;
		
		MHCPayComponent payComponent = s_cacheValue.get(key);
		
		if (payComponent != null) {
			return payComponent;
		}
		
		final String whereClause = COLUMNNAME_Value+"=? AND AD_Client_ID IN (?,?)"; 
		
		payComponent = new Query(ctx, Table_Name, whereClause, null)
							.setParameters(new Object[]{value, 0, AD_Client_ID})
							.setOnlyActiveRecords(true)
							.setOrderBy("AD_Client_ID DESC")
							.first();
		
		if (payComponent != null) {
			s_cacheValue.put(key, payComponent);
			s_cache.put(payComponent.get_ID(), payComponent);
		}
		
		return payComponent;
	}
	
}
