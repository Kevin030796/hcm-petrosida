package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MHCPayComponentGroup extends X_HC_PayComponentGroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3432771373936467969L;

	public MHCPayComponentGroup(Properties ctx, int HC_PayComponentGroup_ID,
			String trxName) {
		super(ctx, HC_PayComponentGroup_ID, trxName);
	}

	public MHCPayComponentGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * get all pay component from all group line
	 * @return hash map <HC_PayComponent_ID, HC_PayComponentGroupLine_ID>
	 */
	public HashMap<Integer, Integer> getPayComponent() {
		
		MHCPayComponentGroupLine[] lines = getLines();
		if (lines == null)
			return null;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (MHCPayComponentGroupLine line : lines) {
			map.put(line.getHC_PayComponent_ID(), line.get_ID());
		}
		return map;
		
	}
	
	/**
	 * get all pay component from all group line
	 * @return array of MHCPayComponent
	 */
	public MHCPayComponent[] getAllPayComponent(){
		
		List<MHCPayComponent> list = new ArrayList<MHCPayComponent>();
		
		for (MHCPayComponentGroupLine groupLine : getLines()) {
			list.add((MHCPayComponent) groupLine.getHC_PayComponent());
		}
		
		return list.toArray(new MHCPayComponent[list.size()]);
	}
	
	/**
	 * get group line
	 * @return array of MHCPayComponentGroupLine
	 */
	public MHCPayComponentGroupLine[] getLines() {
		
		StringBuffer whereClause = new StringBuffer(MHCPayComponentGroupLine.COLUMNNAME_HC_PayComponentGroup_ID +"=?");
		List<MHCPayComponentGroupLine> componentGroupLine = new Query(getCtx(),MHCPayComponentGroupLine.Table_Name,
				whereClause.toString(), get_TrxName())		
				.setParameters(new Object[]{get_ID()})
				.setOnlyActiveRecords(true)
				.setOrderBy("SeqNo")
				.list();
		
		if (componentGroupLine.isEmpty())
			return null;
			
		return componentGroupLine.toArray(new MHCPayComponentGroupLine[componentGroupLine.size()]);
	}
}
