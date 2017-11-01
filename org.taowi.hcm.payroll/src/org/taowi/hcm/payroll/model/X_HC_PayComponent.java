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

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for HC_PayComponent
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayComponent extends PO implements I_HC_PayComponent, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170609L;

    /** Standard Constructor */
    public X_HC_PayComponent (Properties ctx, int HC_PayComponent_ID, String trxName)
    {
      super (ctx, HC_PayComponent_ID, trxName);
      /** if (HC_PayComponent_ID == 0)
        {
			setHC_PayComponent_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PayComponent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_PayComponent[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
			.getPO(getAD_Column_ID(), get_TrxName());	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Accumulator Type.
		@param HC_AccumulatorType Accumulator Type	  */
	public void setHC_AccumulatorType (String HC_AccumulatorType)
	{
		set_Value (COLUMNNAME_HC_AccumulatorType, HC_AccumulatorType);
	}

	/** Get Accumulator Type.
		@return Accumulator Type	  */
	public String getHC_AccumulatorType () 
	{
		return (String)get_Value(COLUMNNAME_HC_AccumulatorType);
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

	/** HC_ImportDataType AD_Reference_ID=300074 */
	public static final int HC_IMPORTDATATYPE_AD_Reference_ID=300074;
	/** Numeric = NUM */
	public static final String HC_IMPORTDATATYPE_Numeric = "NUM";
	/** Integer = INT */
	public static final String HC_IMPORTDATATYPE_Integer = "INT";
	/** String = STR */
	public static final String HC_IMPORTDATATYPE_String = "STR";
	/** Date = DTE */
	public static final String HC_IMPORTDATATYPE_Date = "DTE";
	/** Set Import Data Type.
		@param HC_ImportDataType Import Data Type	  */
	public void setHC_ImportDataType (String HC_ImportDataType)
	{

		set_Value (COLUMNNAME_HC_ImportDataType, HC_ImportDataType);
	}

	/** Get Import Data Type.
		@return Import Data Type	  */
	public String getHC_ImportDataType () 
	{
		return (String)get_Value(COLUMNNAME_HC_ImportDataType);
	}

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

	/** Set HC_PayComponent_UU.
		@param HC_PayComponent_UU HC_PayComponent_UU	  */
	public void setHC_PayComponent_UU (String HC_PayComponent_UU)
	{
		set_Value (COLUMNNAME_HC_PayComponent_UU, HC_PayComponent_UU);
	}

	/** Get HC_PayComponent_UU.
		@return HC_PayComponent_UU	  */
	public String getHC_PayComponent_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayComponent_UU);
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
	/** List = LST */
	public static final String HC_PAYCOMPONENTTYPE_List = "LST";
	/** Imported = IMP */
	public static final String HC_PAYCOMPONENTTYPE_Imported = "IMP";
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

	/** Set Has Attribute?.
		@param IsHasAttribute Has Attribute?	  */
	public void setIsHasAttribute (boolean IsHasAttribute)
	{
		set_Value (COLUMNNAME_IsHasAttribute, Boolean.valueOf(IsHasAttribute));
	}

	/** Get Has Attribute?.
		@return Has Attribute?	  */
	public boolean isHasAttribute () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasAttribute);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Has Key 2.
		@param IsHasKey2 Has Key 2	  */
	public void setIsHasKey2 (boolean IsHasKey2)
	{
		set_Value (COLUMNNAME_IsHasKey2, Boolean.valueOf(IsHasKey2));
	}

	/** Get Has Key 2.
		@return Has Key 2	  */
	public boolean isHasKey2 () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasKey2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Has Key 3.
		@param IsHasKey3 Has Key 3	  */
	public void setIsHasKey3 (boolean IsHasKey3)
	{
		set_Value (COLUMNNAME_IsHasKey3, Boolean.valueOf(IsHasKey3));
	}

	/** Get Has Key 3.
		@return Has Key 3	  */
	public boolean isHasKey3 () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasKey3);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Has Key 4.
		@param IsHasKey4 Has Key 4	  */
	public void setIsHasKey4 (boolean IsHasKey4)
	{
		set_Value (COLUMNNAME_IsHasKey4, Boolean.valueOf(IsHasKey4));
	}

	/** Get Has Key 4.
		@return Has Key 4	  */
	public boolean isHasKey4 () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasKey4);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Has Key 5.
		@param IsHasKey5 Has Key 5	  */
	public void setIsHasKey5 (boolean IsHasKey5)
	{
		set_Value (COLUMNNAME_IsHasKey5, Boolean.valueOf(IsHasKey5));
	}

	/** Get Has Key 5.
		@return Has Key 5	  */
	public boolean isHasKey5 () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasKey5);
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

	/** Set Process Retroactive?.
		@param IsProcessRetroactive Process Retroactive?	  */
	public void setIsProcessRetroactive (boolean IsProcessRetroactive)
	{
		set_Value (COLUMNNAME_IsProcessRetroactive, Boolean.valueOf(IsProcessRetroactive));
	}

	/** Get Process Retroactive?.
		@return Process Retroactive?	  */
	public boolean isProcessRetroactive () 
	{
		Object oo = get_Value(COLUMNNAME_IsProcessRetroactive);
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
