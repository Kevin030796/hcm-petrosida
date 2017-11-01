package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MHCProcessSection extends X_HC_ProcessSection{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2552955750536679723L;

	public MHCProcessSection(Properties ctx, int HC_ProcessSection_ID,
			String trxName) {
		super(ctx, HC_ProcessSection_ID, trxName);
	}

	public MHCProcessSection(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * get all process section line
	 * @return array of MHCProcessSectionLine
	 */
	public MHCProcessSectionLine[] getLines(){
		
		final String whereClause = MHCProcessSectionLine.COLUMNNAME_HC_ProcessSection_ID+"=?";
		
		List<MHCProcessSectionLine> list = new Query(getCtx(), MHCProcessSectionLine.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{this.get_ID()})
		.setOnlyActiveRecords(true)
		.list();
		
		MHCProcessSectionLine[] lines = new MHCProcessSectionLine[list.size()];
		list.toArray(lines);
		
		return lines;
	}
	
	/**
	 * get all pay component
	 * @return array of pay component
	 */
	public MHCPayComponent[] getAllPayComponent(){
		
		List<MHCPayComponent> list = new ArrayList<MHCPayComponent>();
		for (MHCProcessSectionLine line : getLines()) {
			list.add((MHCPayComponent) line.getHC_PayComponent());
		}
		
		MHCPayComponent[] lines = new MHCPayComponent[list.size()];
		list.toArray(lines);
		
		return lines;
	}
	
}
