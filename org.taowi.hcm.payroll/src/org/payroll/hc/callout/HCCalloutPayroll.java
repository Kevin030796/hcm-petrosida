package org.payroll.hc.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class HCCalloutPayroll extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("HR_Concept_ID"))
			return ColumnType(ctx, WindowNo, mTab, mField,value);
		return null;
	}

	public String ColumnType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		final int HR_Concept_ID = (Integer) value;
		if (HR_Concept_ID == 0)
			return "";
		//
		final String columnType = DB.getSQLValueStringEx(null,
				"SELECT ColumnType FROM HR_Concept WHERE HR_Concept_ID=?",
				HR_Concept_ID);
		//mTab.setValue(MHRAttribute.COLUMNNAME_ColumnType, columnType);
		return "";
	}
}
