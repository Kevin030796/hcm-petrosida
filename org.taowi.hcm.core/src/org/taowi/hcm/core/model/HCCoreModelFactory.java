/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/

package org.taowi.hcm.core.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;



/**
 * Generic Model Factory 
 * 
 * @author Double Click Sistemas C.A. - http://dcs.net.ve
 * @author Sa�l Pi�a - spina@dcs.net.ve
 */
public class HCCoreModelFactory implements IModelFactory {

	private static HashMap<String, String> mapTableModels = new HashMap<String, String>();
	static
	{
		mapTableModels.put(I_HC_Employee.Table_Name, "org.taowi.hcm.core.model.MEmployee");
		mapTableModels.put(I_HC_EmployeeCategory.Table_Name, "org.taowi.hcm.core.model.X_HC_EmployeeCategory");
		mapTableModels.put(I_HC_EmployeeClass.Table_Name, "org.taowi.hcm.core.model.X_HC_EmployeeClass");
		mapTableModels.put(I_HC_EmployeeDataChange.Table_Name, "org.taowi.hcm.core.model.MEmployeeDataChange");
		mapTableModels.put(I_HC_EmployeeGrade.Table_Name, "org.taowi.hcm.core.model.MEmployeeGrade");
		mapTableModels.put(I_HC_EmployeeJob.Table_Name, "org.taowi.hcm.core.model.MEmployeeJob");
		mapTableModels.put(I_HC_Ethnic.Table_Name, "org.taowi.hcm.core.model.X_HC_Ethnic");
		mapTableModels.put(I_HC_Job.Table_Name, "org.taowi.hcm.core.model.MJob");
		mapTableModels.put(I_HC_JobDataChange.Table_Name, "org.taowi.hcm.core.model.MJobDataChange");
		mapTableModels.put(I_HC_JobFamily.Table_Name, "org.taowi.hcm.core.model.X_HC_JobFamily");
		mapTableModels.put(I_HC_JobFunction.Table_Name, "org.taowi.hcm.core.model.X_HC_JobFunction");
		mapTableModels.put(I_HC_Org.Table_Name, "org.taowi.hcm.core.model.X_HC_Org");
		mapTableModels.put(I_HC_PayGroup.Table_Name, "org.taowi.hcm.core.model.X_HC_PayGroup");
		mapTableModels.put(I_HC_Position.Table_Name, "org.taowi.hcm.core.model.X_HC_Position");
		mapTableModels.put(I_HC_Reason.Table_Name, "org.taowi.hcm.core.model.X_HC_Reason");
		mapTableModels.put(I_HC_Religion.Table_Name, "org.taowi.hcm.core.model.X_HC_Religion");
		mapTableModels.put(I_HC_TaxStatus.Table_Name, "org.taowi.hcm.core.model.X_HC_TaxStatus");
		
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
