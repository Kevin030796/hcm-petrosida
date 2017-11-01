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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_PayComponentGroup
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayComponentGroup extends PO implements I_HC_PayComponentGroup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161120L;

    /** Standard Constructor */
    public X_HC_PayComponentGroup (Properties ctx, int HC_PayComponentGroup_ID, String trxName)
    {
      super (ctx, HC_PayComponentGroup_ID, trxName);
      /** if (HC_PayComponentGroup_ID == 0)
        {
			setHC_PayComponentGroup_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PayComponentGroup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_PayComponentGroup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Payroll Component Group.
		@param HC_PayComponentGroup_ID Payroll Component Group	  */
	public void setHC_PayComponentGroup_ID (int HC_PayComponentGroup_ID)
	{
		if (HC_PayComponentGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayComponentGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayComponentGroup_ID, Integer.valueOf(HC_PayComponentGroup_ID));
	}

	/** Get Payroll Component Group.
		@return Payroll Component Group	  */
	public int getHC_PayComponentGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayComponentGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_PayComponentGroup_ID()));
    }

	/** Set HC_PayComponentGroup_UU.
		@param HC_PayComponentGroup_UU HC_PayComponentGroup_UU	  */
	public void setHC_PayComponentGroup_UU (String HC_PayComponentGroup_UU)
	{
		set_Value (COLUMNNAME_HC_PayComponentGroup_UU, HC_PayComponentGroup_UU);
	}

	/** Get HC_PayComponentGroup_UU.
		@return HC_PayComponentGroup_UU	  */
	public String getHC_PayComponentGroup_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayComponentGroup_UU);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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