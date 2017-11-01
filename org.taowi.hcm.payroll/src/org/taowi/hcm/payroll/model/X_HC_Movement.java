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

/** Generated Model for HC_Movement
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Movement extends PO implements I_HC_Movement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170610L;

    /** Standard Constructor */
    public X_HC_Movement (Properties ctx, int HC_Movement_ID, String trxName)
    {
      super (ctx, HC_Movement_ID, trxName);
      /** if (HC_Movement_ID == 0)
        {
			setHC_Employee_ID (0);
			setHC_Movement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_Movement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Movement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Rule)MTable.get(getCtx(), org.compiere.model.I_AD_Rule.Table_Name)
			.getPO(getAD_Rule_ID(), get_TrxName());	}

	/** Set Rule.
		@param AD_Rule_ID Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID)
	{
		if (AD_Rule_ID < 1) 
			set_Value (COLUMNNAME_AD_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Rule_ID, Integer.valueOf(AD_Rule_ID));
	}

	/** Get Rule.
		@return Rule	  */
	public int getAD_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
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

	/** Set Amount Value.
		@param HC_AmtValue Amount Value	  */
	public void setHC_AmtValue (BigDecimal HC_AmtValue)
	{
		set_Value (COLUMNNAME_HC_AmtValue, HC_AmtValue);
	}

	/** Get Amount Value.
		@return Amount Value	  */
	public BigDecimal getHC_AmtValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_AmtValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.taowi.hcm.payroll.model.I_HC_Attribute getHC_Attribute() throws RuntimeException
    {
		return (org.taowi.hcm.payroll.model.I_HC_Attribute)MTable.get(getCtx(), org.taowi.hcm.payroll.model.I_HC_Attribute.Table_Name)
			.getPO(getHC_Attribute_ID(), get_TrxName());	}

	/** Set HC_Attribute.
		@param HC_Attribute_ID HC_Attribute	  */
	public void setHC_Attribute_ID (int HC_Attribute_ID)
	{
		if (HC_Attribute_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Attribute_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Attribute_ID, Integer.valueOf(HC_Attribute_ID));
	}

	/** Get HC_Attribute.
		@return HC_Attribute	  */
	public int getHC_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Attribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Yes/No Value.
		@param HC_BooleanValue Yes/No Value	  */
	public void setHC_BooleanValue (boolean HC_BooleanValue)
	{
		set_Value (COLUMNNAME_HC_BooleanValue, Boolean.valueOf(HC_BooleanValue));
	}

	/** Get Yes/No Value.
		@return Yes/No Value	  */
	public boolean isHC_BooleanValue () 
	{
		Object oo = get_Value(COLUMNNAME_HC_BooleanValue);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date Value.
		@param HC_DateValue Date Value	  */
	public void setHC_DateValue (Timestamp HC_DateValue)
	{
		set_Value (COLUMNNAME_HC_DateValue, HC_DateValue);
	}

	/** Get Date Value.
		@return Date Value	  */
	public Timestamp getHC_DateValue () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_DateValue);
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

	/** Set HC_Movement.
		@param HC_Movement_ID HC_Movement	  */
	public void setHC_Movement_ID (int HC_Movement_ID)
	{
		if (HC_Movement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Movement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Movement_ID, Integer.valueOf(HC_Movement_ID));
	}

	/** Get HC_Movement.
		@return HC_Movement	  */
	public int getHC_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Movement_ID()));
    }

	/** Set HC_Movement_UU.
		@param HC_Movement_UU HC_Movement_UU	  */
	public void setHC_Movement_UU (String HC_Movement_UU)
	{
		set_Value (COLUMNNAME_HC_Movement_UU, HC_Movement_UU);
	}

	/** Get HC_Movement_UU.
		@return HC_Movement_UU	  */
	public String getHC_Movement_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Movement_UU);
	}

	public org.taowi.hcm.core.model.I_HC_Org getHC_Org() throws RuntimeException
    {
		return (org.taowi.hcm.core.model.I_HC_Org)MTable.get(getCtx(), org.taowi.hcm.core.model.I_HC_Org.Table_Name)
			.getPO(getHC_Org_ID(), get_TrxName());	}

	/** Set HC Organization.
		@param HC_Org_ID HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID)
	{
		if (HC_Org_ID < 1) 
			set_Value (COLUMNNAME_HC_Org_ID, null);
		else 
			set_Value (COLUMNNAME_HC_Org_ID, Integer.valueOf(HC_Org_ID));
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

	public I_HC_PayComponent getHC_PayComponent() throws RuntimeException
    {
		return (I_HC_PayComponent)MTable.get(getCtx(), I_HC_PayComponent.Table_Name)
			.getPO(getHC_PayComponent_ID(), get_TrxName());	}

	/** Set Payroll Component.
		@param HC_PayComponent_ID Payroll Component	  */
	public void setHC_PayComponent_ID (int HC_PayComponent_ID)
	{
		if (HC_PayComponent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayComponent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayComponent_ID, Integer.valueOf(HC_PayComponent_ID));
	}

	/** Get Payroll Component.
		@return Payroll Component	  */
	public int getHC_PayComponent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayComponent_ID);
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

	/** Set Qty Value.
		@param HC_QtyValue Qty Value	  */
	public void setHC_QtyValue (BigDecimal HC_QtyValue)
	{
		set_Value (COLUMNNAME_HC_QtyValue, HC_QtyValue);
	}

	/** Get Qty Value.
		@return Qty Value	  */
	public BigDecimal getHC_QtyValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_QtyValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set String Value.
		@param HC_StringValue String Value	  */
	public void setHC_StringValue (String HC_StringValue)
	{
		set_Value (COLUMNNAME_HC_StringValue, HC_StringValue);
	}

	/** Get String Value.
		@return String Value	  */
	public String getHC_StringValue () 
	{
		return (String)get_Value(COLUMNNAME_HC_StringValue);
	}

	/** Set Global Pay Component ?.
		@param IsGlobalPayComponent Global Pay Component ?	  */
	public void setIsGlobalPayComponent (boolean IsGlobalPayComponent)
	{
		set_Value (COLUMNNAME_IsGlobalPayComponent, Boolean.valueOf(IsGlobalPayComponent));
	}

	/** Get Global Pay Component ?.
		@return Global Pay Component ?	  */
	public boolean isGlobalPayComponent () 
	{
		Object oo = get_Value(COLUMNNAME_IsGlobalPayComponent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_ValueNoCheck (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	public boolean isPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_ValueNoCheck (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tracked ?.
		@param IsTracked Tracked ?	  */
	public void setIsTracked (boolean IsTracked)
	{
		set_Value (COLUMNNAME_IsTracked, Boolean.valueOf(IsTracked));
	}

	/** Get Tracked ?.
		@return Tracked ?	  */
	public boolean isTracked () 
	{
		Object oo = get_Value(COLUMNNAME_IsTracked);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Transfer To GL?.
		@param IsTransferToGL Transfer To GL?	  */
	public void setIsTransferToGL (boolean IsTransferToGL)
	{
		set_Value (COLUMNNAME_IsTransferToGL, Boolean.valueOf(IsTransferToGL));
	}

	/** Get Transfer To GL?.
		@return Transfer To GL?	  */
	public boolean isTransferToGL () 
	{
		Object oo = get_Value(COLUMNNAME_IsTransferToGL);
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

	/** Type AD_Reference_ID=300063 */
	public static final int TYPE_AD_Reference_ID=300063;
	/** Variable = VAR */
	public static final String TYPE_Variable = "VAR";
	/** Formula = FML */
	public static final String TYPE_Formula = "FML";
	/** Query = QRY */
	public static final String TYPE_Query = "QRY";
	/** System = SYS */
	public static final String TYPE_System = "SYS";
	/** Bracket = BRC */
	public static final String TYPE_Bracket = "BRC";
	/** Accumulator = ACM */
	public static final String TYPE_Accumulator = "ACM";
	/** Date = DTE */
	public static final String TYPE_Date = "DTE";
	/** String = STR */
	public static final String TYPE_String = "STR";
	/** Integer = INT */
	public static final String TYPE_Integer = "INT";
	/** Numeric = NUM */
	public static final String TYPE_Numeric = "NUM";
	/** Earning = EAR */
	public static final String TYPE_Earning = "EAR";
	/** Deduction = DED */
	public static final String TYPE_Deduction = "DED";
	/** Yes/No = YES */
	public static final String TYPE_YesNo = "YES";
	/** Map = MAP */
	public static final String TYPE_Map = "MAP";
	/** List = LST */
	public static final String TYPE_List = "LST";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}