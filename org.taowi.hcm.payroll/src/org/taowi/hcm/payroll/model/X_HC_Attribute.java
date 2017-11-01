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

/** Generated Model for HC_Attribute
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Attribute extends PO implements I_HC_Attribute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170605L;

    /** Standard Constructor */
    public X_HC_Attribute (Properties ctx, int HC_Attribute_ID, String trxName)
    {
      super (ctx, HC_Attribute_ID, trxName);
      /** if (HC_Attribute_ID == 0)
        {
			setHC_Attribute_ID (0);
			setHC_PayComponent_ID (0);
// @HC_PayComponent_ID@
			setHC_ValueDataType (null);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_HC_Attribute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Attribute[")
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Attribute_ID()));
    }

	/** Set HC_Attribute_UU.
		@param HC_Attribute_UU HC_Attribute_UU	  */
	public void setHC_Attribute_UU (String HC_Attribute_UU)
	{
		set_Value (COLUMNNAME_HC_Attribute_UU, HC_Attribute_UU);
	}

	/** Get HC_Attribute_UU.
		@return HC_Attribute_UU	  */
	public String getHC_Attribute_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Attribute_UU);
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

	public org.taowi.hcm.core.model.I_HC_Employee getHC_Employee() throws RuntimeException
    {
		return (org.taowi.hcm.core.model.I_HC_Employee)MTable.get(getCtx(), org.taowi.hcm.core.model.I_HC_Employee.Table_Name)
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

	/** Set Integer Value.
		@param HC_IntValue Integer Value	  */
	public void setHC_IntValue (int HC_IntValue)
	{
		set_Value (COLUMNNAME_HC_IntValue, Integer.valueOf(HC_IntValue));
	}

	/** Get Integer Value.
		@return Integer Value	  */
	public int getHC_IntValue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_IntValue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Numeric Value.
		@param HC_NumValue Numeric Value	  */
	public void setHC_NumValue (BigDecimal HC_NumValue)
	{
		set_Value (COLUMNNAME_HC_NumValue, HC_NumValue);
	}

	/** Get Numeric Value.
		@return Numeric Value	  */
	public BigDecimal getHC_NumValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_NumValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** HC_PayComponentType AD_Reference_ID=300063 */
	public static final int HC_PAYCOMPONENTTYPE_AD_Reference_ID=300063;
	/** Variable = VAR */
	public static final String HC_PAYCOMPONENTTYPE_Variable = "VAR";
	/** Formula = FML */
	public static final String HC_PAYCOMPONENTTYPE_Formula = "FML";
	/** Query = QRY */
	public static final String HC_PAYCOMPONENTTYPE_Query = "QRY";
	/** System = SYS */
	public static final String HC_PAYCOMPONENTTYPE_System = "SYS";
	/** Bracket = BRC */
	public static final String HC_PAYCOMPONENTTYPE_Bracket = "BRC";
	/** Accumulator = ACM */
	public static final String HC_PAYCOMPONENTTYPE_Accumulator = "ACM";
	/** Date = DTE */
	public static final String HC_PAYCOMPONENTTYPE_Date = "DTE";
	/** String = STR */
	public static final String HC_PAYCOMPONENTTYPE_String = "STR";
	/** Integer = INT */
	public static final String HC_PAYCOMPONENTTYPE_Integer = "INT";
	/** Numeric = NUM */
	public static final String HC_PAYCOMPONENTTYPE_Numeric = "NUM";
	/** Earning = EAR */
	public static final String HC_PAYCOMPONENTTYPE_Earning = "EAR";
	/** Deduction = DED */
	public static final String HC_PAYCOMPONENTTYPE_Deduction = "DED";
	/** Yes/No = YES */
	public static final String HC_PAYCOMPONENTTYPE_YesNo = "YES";
	/** Map = MAP */
	public static final String HC_PAYCOMPONENTTYPE_Map = "MAP";
	/** Set Pay Component Type.
		@param HC_PayComponentType Pay Component Type	  */
	public void setHC_PayComponentType (String HC_PayComponentType)
	{

		set_Value (COLUMNNAME_HC_PayComponentType, HC_PayComponentType);
	}

	/** Get Pay Component Type.
		@return Pay Component Type	  */
	public String getHC_PayComponentType () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayComponentType);
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

	/** HC_ValueDataType AD_Reference_ID=300073 */
	public static final int HC_VALUEDATATYPE_AD_Reference_ID=300073;
	/** String = STR */
	public static final String HC_VALUEDATATYPE_String = "STR";
	/** Numeric = NUM */
	public static final String HC_VALUEDATATYPE_Numeric = "NUM";
	/** Integer = INT */
	public static final String HC_VALUEDATATYPE_Integer = "INT";
	/** Boolean = YES */
	public static final String HC_VALUEDATATYPE_Boolean = "YES";
	/** Date = DTE */
	public static final String HC_VALUEDATATYPE_Date = "DTE";
	/** Set Value Data Type.
		@param HC_ValueDataType Value Data Type	  */
	public void setHC_ValueDataType (String HC_ValueDataType)
	{

		set_Value (COLUMNNAME_HC_ValueDataType, HC_ValueDataType);
	}

	/** Get Value Data Type.
		@return Value Data Type	  */
	public String getHC_ValueDataType () 
	{
		return (String)get_Value(COLUMNNAME_HC_ValueDataType);
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}