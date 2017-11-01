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

/** Generated Model for HC_ProcessImport
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_ProcessImport extends PO implements I_HC_ProcessImport, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170606L;

    /** Standard Constructor */
    public X_HC_ProcessImport (Properties ctx, int HC_ProcessImport_ID, String trxName)
    {
      super (ctx, HC_ProcessImport_ID, trxName);
      /** if (HC_ProcessImport_ID == 0)
        {
			setHC_ProcessImport_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_ProcessImport (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_ProcessImport[")
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

	/** Set HC_ProcessImport.
		@param HC_ProcessImport_ID HC_ProcessImport	  */
	public void setHC_ProcessImport_ID (int HC_ProcessImport_ID)
	{
		if (HC_ProcessImport_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessImport_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessImport_ID, Integer.valueOf(HC_ProcessImport_ID));
	}

	/** Get HC_ProcessImport.
		@return HC_ProcessImport	  */
	public int getHC_ProcessImport_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ProcessImport_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_ProcessImport_ID()));
    }

	/** Set HC_ProcessImport_UU.
		@param HC_ProcessImport_UU HC_ProcessImport_UU	  */
	public void setHC_ProcessImport_UU (String HC_ProcessImport_UU)
	{
		set_Value (COLUMNNAME_HC_ProcessImport_UU, HC_ProcessImport_UU);
	}

	/** Get HC_ProcessImport_UU.
		@return HC_ProcessImport_UU	  */
	public String getHC_ProcessImport_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_ProcessImport_UU);
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