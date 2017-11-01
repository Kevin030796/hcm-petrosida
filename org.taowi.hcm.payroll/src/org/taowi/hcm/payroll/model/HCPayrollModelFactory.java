package org.taowi.hcm.payroll.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.MHCEmployee;
import org.taowi.hcm.core.model.MHCEmployeeJob;

public class HCPayrollModelFactory implements IModelFactory{

	private static HashMap<String, String> mapTableModels = new HashMap<String, String>();
	static
	{
		/**org.taowi.hcm.payroll.model*/
		mapTableModels.put(I_HC_Movement.Table_Name, "org.taowi.hcm.payroll.model.MHCMovement");
		mapTableModels.put(I_HC_PayComponent.Table_Name, "org.taowi.hcm.payroll.model.MHCPayComponent");
		mapTableModels.put(I_HC_PayComponentGroup.Table_Name, "org.taowi.hcm.payroll.model.MHCPayComponentGroup");
		mapTableModels.put(I_HC_PayComponentGroupLine.Table_Name, "org.taowi.hcm.payroll.model.MHCPayComponentGroupLine");
		mapTableModels.put(I_HC_PayrollProcess.Table_Name, "org.taowi.hcm.payroll.model.MHCPayrollProcess");
		mapTableModels.put(I_HC_PayrollProcessLine.Table_Name, "org.taowi.hcm.payroll.model.MHCPayrollProcessLine");
		mapTableModels.put(I_HC_PayrollTrigger.Table_Name, "org.taowi.hcm.payroll.model.MHCPayrollTrigger");
		mapTableModels.put(I_HC_Attribute.Table_Name, "org.taowi.hcm.payroll.model.MHCAttribute");
		mapTableModels.put(I_HC_AttributeMap.Table_Name, "org.taowi.hcm.payroll.model.MHCAttributeMap");
		mapTableModels.put(I_HC_AttributeList.Table_Name, "org.taowi.hcm.payroll.model.MHCAttributeList");
		/*@KevinY */
		mapTableModels.put(I_HC_Sanctions.Table_Name, "org.taowi.hcm.payroll.model.X_HC_Sanctions");
		mapTableModels.put(I_HC_Sanction_Rule.Table_Name, "org.taowi.hcm.payroll.model.X_HC_Sanction_Rule");
		/*@KevinY end*/
		
		
		mapTableModels.put(MHCProcessListLine.Table_Name, "org.taowi.hcm.payroll.model.MHCProcessListLine");
		mapTableModels.put(MHCProcessList.Table_Name, "org.taowi.hcm.payroll.model.MHCProcessList");
		mapTableModels.put(MHCProcessSectionLine.Table_Name, "org.taowi.hcm.payroll.model.MHCProcessSectionLine");
		mapTableModels.put(MHCProcessSection.Table_Name, "org.taowi.hcm.payroll.model.MHCProcessSection");
		mapTableModels.put(MHCEligibilityGroupLine.Table_Name, "org.taowi.hcm.payroll.model.MHCEligibilityGroupLine");
		mapTableModels.put(MHCEligibilityGroup.Table_Name, "org.taowi.hcm.payroll.model.MHCEligibilityGroup");
		
		
		/**org.taowi.hcm.core.model*/
		mapTableModels.put(MHCEmployee.Table_Name, "org.taowi.hcm.core.model.MHCEmployee");
		mapTableModels.put(MHCEmployeeJob.Table_Name, "org.taowi.hcm.core.model.MHCEmployeeJob");
	}
	
	@Override
	public Class<?> getClass(String tableName) {
		if (mapTableModels.containsKey(tableName)) {
			Class<?> act = null;
			try {
				act = Class.forName(mapTableModels.get(tableName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
				return act;
		
		} else 
			return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if (mapTableModels.containsKey(tableName)) {
			Class<?> clazz = null;
			Constructor<?> ctor = null;
			PO object = null;
			try {
				clazz = Class.forName(mapTableModels.get(tableName));
				ctor = clazz.getConstructor(Properties.class, int.class, String.class);
				object = (PO) ctor.newInstance(new Object[] {Env.getCtx(), Record_ID, trxName});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return object;
		} else 	   
		   return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if (mapTableModels.containsKey(tableName)) {
			Class<?> clazz = null;
			Constructor<?> ctor = null;
			PO object = null;
			try {
				clazz = Class.forName(mapTableModels.get(tableName));
				ctor = clazz.getConstructor(Properties.class, ResultSet.class, String.class);
				object = (PO) ctor.newInstance(new Object[] {Env.getCtx(), rs, trxName});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return object;
				
		} else  
			return null;
	}

}
