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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_AttributeList
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_AttributeList extends PO implements I_HC_AttributeList, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170605L;

    /** Standard Constructor */
    public X_HC_AttributeList (Properties ctx, int HC_AttributeList_ID, String trxName)
    {
      super (ctx, HC_AttributeList_ID, trxName);
      /** if (HC_AttributeList_ID == 0)
        {
			setHC_AttributeList_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_AttributeList (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_HC_AttributeList[")
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

	/** Set HC_AttributeList.
		@param HC_AttributeList_ID HC_AttributeList	  */
	public void setHC_AttributeList_ID (int HC_AttributeList_ID)
	{
		if (HC_AttributeList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_AttributeList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_AttributeList_ID, Integer.valueOf(HC_AttributeList_ID));
	}

	/** Get HC_AttributeList.
		@return HC_AttributeList	  */
	public int getHC_AttributeList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_AttributeList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_AttributeList_ID()));
    }

	/** Set HC_AttributeList_UU.
		@param HC_AttributeList_UU HC_AttributeList_UU	  */
	public void setHC_AttributeList_UU (String HC_AttributeList_UU)
	{
		set_Value (COLUMNNAME_HC_AttributeList_UU, HC_AttributeList_UU);
	}

	/** Get HC_AttributeList_UU.
		@return HC_AttributeList_UU	  */
	public String getHC_AttributeList_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_AttributeList_UU);
	}

	/** Set Value Numeric.
		@param HC_ValueNumeric Value Numeric	  */
	public void setHC_ValueNumeric (BigDecimal HC_ValueNumeric)
	{
		set_Value (COLUMNNAME_HC_ValueNumeric, HC_ValueNumeric);
	}

	/** Get Value Numeric.
		@return Value Numeric	  */
	public BigDecimal getHC_ValueNumeric () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_ValueNumeric);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Value String.
		@param HC_ValueString Value String	  */
	public void setHC_ValueString (String HC_ValueString)
	{
		set_Value (COLUMNNAME_HC_ValueString, HC_ValueString);
	}

	/** Get Value String.
		@return Value String	  */
	public String getHC_ValueString () 
	{
		return (String)get_Value(COLUMNNAME_HC_ValueString);
	}
}