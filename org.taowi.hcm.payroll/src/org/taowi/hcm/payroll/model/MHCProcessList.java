package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MHCProcessList extends X_HC_ProcessList{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5807768632003545003L;

	public MHCProcessList(Properties ctx, int HC_ProcessList_ID, String trxName) {
		super(ctx, HC_ProcessList_ID, trxName);
	}

	public MHCProcessList(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * get all process list line
	 * @return array of MHCProcessListLine
	 */
	public MHCProcessListLine[] getLines(){
		
		final String whereClause = MHCProcessListLine.COLUMNNAME_HC_ProcessList_ID+"=?";
		
		List<MHCProcessListLine> list = new Query(getCtx(), MHCProcessListLine.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{this.get_ID()})
		.setOnlyActiveRecords(true)
		.list();
		
		MHCProcessListLine[] lines = new MHCProcessListLine[list.size()];
		list.toArray(lines);
		
		return lines;
	}
	
	/**
	 * get all pay component from section line
	 * @return array of pay component
	 */
	public MHCPayComponent[] getAllPayComponent(){
		
		List<MHCPayComponent> list = new ArrayList<MHCPayComponent>();
		
		for (MHCProcessListLine line : getLines()) {
			MHCProcessSection section = (MHCProcessSection) line.getHC_ProcessSection();
			for (MHCProcessSectionLine sectionLine : section.getLines()) {
				list.add((MHCPayComponent) sectionLine.getHC_PayComponent());
			}
		}
		
		MHCPayComponent[] lines = new MHCPayComponent[list.size()];
		list.toArray(lines);
		
		return lines;
	}
	
}
