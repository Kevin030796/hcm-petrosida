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
package org.taowi.hcm.payroll.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_PayrollProcess
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayrollProcess extends PO implements I_HC_PayrollProcess, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170609L;

    /** Standard Constructor */
    public X_HC_PayrollProcess (Properties ctx, int HC_PayrollProcess_ID, String trxName)
    {
      super (ctx, HC_PayrollProcess_ID, trxName);
      /** if (HC_PayrollProcess_ID == 0)
        {
			setHC_PayrollProcess_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PayrollProcess (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_HC_PayrollProcess[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public I_HC_EligibilityGroup getHC_EligibilityGroup() throws RuntimeException
    {
		return (I_HC_EligibilityGroup)MTable.get(getCtx(), I_HC_EligibilityGroup.Table_Name)
			.getPO(getHC_EligibilityGroup_ID(), get_TrxName());	}

	/** Set HC Eligibility Group.
		@param HC_EligibilityGroup_ID HC Eligibility Group	  */
	public void setHC_EligibilityGroup_ID (int HC_EligibilityGroup_ID)
	{
		if (HC_EligibilityGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EligibilityGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EligibilityGroup_ID, Integer.valueOf(HC_EligibilityGroup_ID));
	}

	/** Get HC Eligibility Group.
		@return HC Eligibility Group	  */
	public int getHC_EligibilityGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EligibilityGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.taowi.hcm.core.model.I_HC_PayGroup getHC_PayGroup() throws RuntimeException
    {
		return (org.taowi.hcm.core.model.I_HC_PayGroup)MTable.get(getCtx(), org.taowi.hcm.core.model.I_HC_PayGroup.Table_Name)
			.getPO(getHC_PayGroup_ID(), get_TrxName());	}

	/** Set Payroll Group.
		@param HC_PayGroup_ID Payroll Group	  */
	public void setHC_PayGroup_ID (int HC_PayGroup_ID)
	{
		if (HC_PayGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayGroup_ID, Integer.valueOf(HC_PayGroup_ID));
	}

	/** Get Payroll Group.
		@return Payroll Group	  */
	public int getHC_PayGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payroll Process.
		@param HC_PayrollProcess_ID Payroll Process	  */
	public void setHC_PayrollProcess_ID (int HC_PayrollProcess_ID)
	{
		if (HC_PayrollProcess_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayrollProcess_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayrollProcess_ID, Integer.valueOf(HC_PayrollProcess_ID));
	}

	/** Get Payroll Process.
		@return Payroll Process	  */
	public int getHC_PayrollProcess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayrollProcess_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_PayrollProcess_ID()));
    }

	/** Set HC_PayrollProcess_UU.
		@param HC_PayrollProcess_UU HC_PayrollProcess_UU	  */
	public void setHC_PayrollProcess_UU (String HC_PayrollProcess_UU)
	{
		set_Value (COLUMNNAME_HC_PayrollProcess_UU, HC_PayrollProcess_UU);
	}

	/** Get HC_PayrollProcess_UU.
		@return HC_PayrollProcess_UU	  */
	public String getHC_PayrollProcess_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayrollProcess_UU);
	}

	public org.taowi.hcm.payroll.model.I_HC_ProcessList getHC_ProcessList() throws RuntimeException
    {
		return (org.taowi.hcm.payroll.model.I_HC_ProcessList)MTable.get(getCtx(), org.taowi.hcm.payroll.model.I_HC_ProcessList.Table_Name)
			.getPO(getHC_ProcessList_ID(), get_TrxName());	}

	/** Set HC Process List.
		@param HC_ProcessList_ID HC Process List	  */
	public void setHC_ProcessList_ID (int HC_ProcessList_ID)
	{
		if (HC_ProcessList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessList_ID, Integer.valueOf(HC_ProcessList_ID));
	}

	/** Get HC Process List.
		@return HC Process List	  */
	public int getHC_ProcessList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ProcessList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid.
		@param IsValid 
		Element is valid
	  */
	public void setIsValid (boolean IsValid)
	{
		set_Value (COLUMNNAME_IsValid, Boolean.valueOf(IsValid));
	}

	/** Get Valid.
		@return Element is valid
	  */
	public boolean isValid () 
	{
		Object oo = get_Value(COLUMNNAME_IsValid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}