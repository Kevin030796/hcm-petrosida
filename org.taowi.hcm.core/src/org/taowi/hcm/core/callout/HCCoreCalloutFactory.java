package org.taowi.hcm.core.callout;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeDataChange;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.MJobDataChange;



public class HCCoreCalloutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) {
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		
		if (tableName.equals("Table_Name")){
			list.add(new HCCalloutTemplate());
		}else if (tableName.equals(MEmployee.Table_Name)){
			list.add(new HCCoreCalloutEmployee());
		}else if (tableName.equals(MJob.Table_Name)){
			list.add(new HCCoreCalloutJob());
		}else if (tableName.equals(MEmployeeDataChange.Table_Name)){
			list.add(new HCCoreCalloutEmployeeDataChange());
		}else if (tableName.equals(MJobDataChange.Table_Name)){
			list.add(new HCCoreCalloutJobDataChange());
		}
		
		return list != null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
