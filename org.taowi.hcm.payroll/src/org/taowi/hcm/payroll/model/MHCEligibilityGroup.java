package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MHCEligibilityGroup extends X_HC_EligibilityGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5387506278936409895L;

	public MHCEligibilityGroup(Properties ctx, int HC_EligibilityGroup_ID,
			String trxName) {
		super(ctx, HC_EligibilityGroup_ID, trxName);
	}

	public MHCEligibilityGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * get all Eligibility Group Line
	 * @return array of Eligibility Group Line
	 */
	public MHCEligibilityGroupLine[] getLines(){
		final String whereClause = MHCEligibilityGroupLine.COLUMNNAME_HC_EligibilityGroup_ID+"=?";
		
		List<MHCEligibilityGroupLine> list = new Query(getCtx(), MHCEligibilityGroupLine.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{this.get_ID()})
		.setOnlyActiveRecords(true)
		.list();
		
		MHCEligibilityGroupLine[] lines = new MHCEligibilityGroupLine[list.size()];
		list.toArray(lines);
		
		return lines;
	}
	
	/**
	 * get all pay component from component group
	 * @return array of pay component
	 */
	public MHCPayComponent[] getAllPayComponent(){
		
		List<MHCPayComponent> list = new ArrayList<MHCPayComponent>();
		for (MHCEligibilityGroupLine line : getLines()) {
			MHCPayComponentGroup group = new MHCPayComponentGroup(getCtx(), line.getHC_PayComponentGroup_ID(), get_TrxName());
			MHCPayComponent[] payComponents = group.getAllPayComponent();
			for (MHCPayComponent payComponent : payComponents) {
				list.add(payComponent);
			}
		}
		
		MHCPayComponent[] lines = new MHCPayComponent[list.size()];
		list.toArray(lines);
		
		return lines;
	}
	
}
