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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_EmployeeJob;

/** Generated Model for HC_PayrollTrigger
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayrollTrigger extends PO implements I_HC_PayrollTrigger, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161128L;

    /** Standard Constructor */
    public X_HC_PayrollTrigger (Properties ctx, int HC_PayrollTrigger_ID, String trxName)
    {
      super (ctx, HC_PayrollTrigger_ID, trxName);
      /** if (HC_PayrollTrigger_ID == 0)
        {
			setHC_PayrollTrigger_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PayrollTrigger (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_HC_PayrollTrigger[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
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

	/** Set Difference.
		@param DifferenceAmt 
		Difference Amount
	  */
	public void setDifferenceAmt (BigDecimal DifferenceAmt)
	{
		set_ValueNoCheck (COLUMNNAME_DifferenceAmt, DifferenceAmt);
	}

	/** Get Difference.
		@return Difference Amount
	  */
	public BigDecimal getDifferenceAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DifferenceAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Effective Date From.
		@param EffectiveDateFrom Effective Date From	  */
	public void setEffectiveDateFrom (Timestamp EffectiveDateFrom)
	{
		set_Value (COLUMNNAME_EffectiveDateFrom, EffectiveDateFrom);
	}

	/** Get Effective Date From.
		@return Effective Date From	  */
	public Timestamp getEffectiveDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EffectiveDateFrom);
	}

	/** Set Effective Date To.
		@param EffectiveDateTo Effective Date To	  */
	public void setEffectiveDateTo (Timestamp EffectiveDateTo)
	{
		set_Value (COLUMNNAME_EffectiveDateTo, EffectiveDateTo);
	}

	/** Get Effective Date To.
		@return Effective Date To	  */
	public Timestamp getEffectiveDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EffectiveDateTo);
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

	public I_HC_EmployeeJob getHC_EmployeeJob() throws RuntimeException
    {
		return (I_HC_EmployeeJob)MTable.get(getCtx(), I_HC_EmployeeJob.Table_Name)
			.getPO(getHC_EmployeeJob_ID(), get_TrxName());	}

	/** Set Employee Job Data.
		@param HC_EmployeeJob_ID Employee Job Data	  */
	public void setHC_EmployeeJob_ID (int HC_EmployeeJob_ID)
	{
		if (HC_EmployeeJob_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeJob_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeJob_ID, Integer.valueOf(HC_EmployeeJob_ID));
	}

	/** Get Employee Job Data.
		@return Employee Job Data	  */
	public int getHC_EmployeeJob_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeJob_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_PayrollProcess getHC_PayrollProcess() throws RuntimeException
    {
		return (I_HC_PayrollProcess)MTable.get(getCtx(), I_HC_PayrollProcess.Table_Name)
			.getPO(getHC_PayrollProcess_ID(), get_TrxName());	}

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

	/** Set HC_PayrollTrigger.
		@param HC_PayrollTrigger_ID HC_PayrollTrigger	  */
	public void setHC_PayrollTrigger_ID (int HC_PayrollTrigger_ID)
	{
		if (HC_PayrollTrigger_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayrollTrigger_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayrollTrigger_ID, Integer.valueOf(HC_PayrollTrigger_ID));
	}

	/** Get HC_PayrollTrigger.
		@return HC_PayrollTrigger	  */
	public int getHC_PayrollTrigger_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayrollTrigger_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_PayrollTrigger_ID()));
    }

	/** Set HC_PayrollTrigger_UU.
		@param HC_PayrollTrigger_UU HC_PayrollTrigger_UU	  */
	public void setHC_PayrollTrigger_UU (String HC_PayrollTrigger_UU)
	{
		set_Value (COLUMNNAME_HC_PayrollTrigger_UU, HC_PayrollTrigger_UU);
	}

	/** Get HC_PayrollTrigger_UU.
		@return HC_PayrollTrigger_UU	  */
	public String getHC_PayrollTrigger_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayrollTrigger_UU);
	}

	/** Set New Value.
		@param NewValue 
		New field value
	  */
	public void setNewValue (String NewValue)
	{
		set_ValueNoCheck (COLUMNNAME_NewValue, NewValue);
	}

	/** Get New Value.
		@return New field value
	  */
	public String getNewValue () 
	{
		return (String)get_Value(COLUMNNAME_NewValue);
	}

	/** Set Old Value.
		@param OldValue 
		The old file data
	  */
	public void setOldValue (String OldValue)
	{
		set_ValueNoCheck (COLUMNNAME_OldValue, OldValue);
	}

	/** Get Old Value.
		@return The old file data
	  */
	public String getOldValue () 
	{
		return (String)get_Value(COLUMNNAME_OldValue);
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
}