package org.taowi.hcm.payroll.model;

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
/** Generated Model - DO NOT CHANGE */


import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_Org;

/** Generated Model for HC_Sanctions
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Sanctions extends PO implements I_HC_Sanctions, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170712L;

    /** Standard Constructor */
    public X_HC_Sanctions (Properties ctx, int HC_Sanctions_ID, String trxName)
    {
      super (ctx, HC_Sanctions_ID, trxName);
      /** if (HC_Sanctions_ID == 0)
        {
			setHC_Employee_ID (0);
			setHC_Sanctions_ID (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_HC_Sanctions (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HC_Sanctions[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	public I_HC_Employee getHC_Employee() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_Employee_ID(), get_TrxName());	}

	/** Set Employee Data.
		@param HC_Employee_ID Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID)
	{
		if (HC_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Employee_ID, Integer.valueOf(HC_Employee_ID));
	}

	/** Get Employee Data.
		@return Employee Data	  */
	public int getHC_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Job getHC_Job() throws RuntimeException
    {
		return (I_HC_Job)MTable.get(getCtx(), I_HC_Job.Table_Name)
			.getPO(getHC_Job_ID(), get_TrxName());	}

	/** Set Job.
		@param HC_Job_ID Job	  */
	public void setHC_Job_ID (int HC_Job_ID)
	{
		if (HC_Job_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Job_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Job_ID, Integer.valueOf(HC_Job_ID));
	}

	/** Get Job.
		@return Job	  */
	public int getHC_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Employee getHC_Manager() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_Manager_ID(), get_TrxName());	}

	/** Set Manager Name.
		@param HC_Manager_ID Manager Name	  */
	public void setHC_Manager_ID (int HC_Manager_ID)
	{
		if (HC_Manager_ID < 1) 
			set_Value (COLUMNNAME_HC_Manager_ID, null);
		else 
			set_Value (COLUMNNAME_HC_Manager_ID, Integer.valueOf(HC_Manager_ID));
	}

	/** Get Manager Name.
		@return Manager Name	  */
	public int getHC_Manager_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Manager_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Org getHC_Org() throws RuntimeException
    {
		return (I_HC_Org)MTable.get(getCtx(), I_HC_Org.Table_Name)
			.getPO(getHC_Org_ID(), get_TrxName());	}

	/** Set HC Organization.
		@param HC_Org_ID HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID)
	{
		if (HC_Org_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Org_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Org_ID, Integer.valueOf(HC_Org_ID));
	}

	/** Get HC Organization.
		@return HC Organization	  */
	public int getHC_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Sanction_Rule getHC_Sanction_Rule() throws RuntimeException
    {
		return (I_HC_Sanction_Rule)MTable.get(getCtx(), I_HC_Sanction_Rule.Table_Name)
			.getPO(getHC_Sanction_Rule_ID(), get_TrxName());	}

	/** Set Aturan Sanksi.
		@param HC_Sanction_Rule_ID Aturan Sanksi	  */
	public void setHC_Sanction_Rule_ID (int HC_Sanction_Rule_ID)
	{
		if (HC_Sanction_Rule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Sanction_Rule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Sanction_Rule_ID, Integer.valueOf(HC_Sanction_Rule_ID));
	}

	/** Get Aturan Sanksi.
		@return Aturan Sanksi	  */
	public int getHC_Sanction_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Sanction_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pelanggaran.
		@param HC_Sanctions_ID Pelanggaran	  */
	public void setHC_Sanctions_ID (int HC_Sanctions_ID)
	{
		if (HC_Sanctions_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Sanctions_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Sanctions_ID, Integer.valueOf(HC_Sanctions_ID));
	}

	/** Get Pelanggaran.
		@return Pelanggaran	  */
	public int getHC_Sanctions_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Sanctions_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Sanctions_ID()));
    }

	/** Set HC_Sanctions_UU.
		@param HC_Sanctions_UU HC_Sanctions_UU	  */
	public void setHC_Sanctions_UU (String HC_Sanctions_UU)
	{
		set_Value (COLUMNNAME_HC_Sanctions_UU, HC_Sanctions_UU);
	}

	/** Get HC_Sanctions_UU.
		@return HC_Sanctions_UU	  */
	public String getHC_Sanctions_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Sanctions_UU);
	}

	/** Set Masa Berlaku.
		@param Sanction_Period Masa Berlaku	  */
	public void setSanction_Period (int Sanction_Period)
	{
		set_Value (COLUMNNAME_Sanction_Period, Integer.valueOf(Sanction_Period));
	}

	/** Get Masa Berlaku.
		@return Masa Berlaku	  */
	public int getSanction_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sanction_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Date.
		@param StartDate 
		The Start Date indicates the first or starting date
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return The Start Date indicates the first or starting date
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}