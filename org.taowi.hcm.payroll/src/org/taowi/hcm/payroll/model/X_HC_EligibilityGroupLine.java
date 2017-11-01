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

/** Generated Model for HC_EligibilityGroupLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_EligibilityGroupLine extends PO implements I_HC_EligibilityGroupLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170606L;

    /** Standard Constructor */
    public X_HC_EligibilityGroupLine (Properties ctx, int HC_EligibilityGroupLine_ID, String trxName)
    {
      super (ctx, HC_EligibilityGroupLine_ID, trxName);
      /** if (HC_EligibilityGroupLine_ID == 0)
        {
			setHC_EligibilityGroupLine_ID (0);
			setHC_PayComponentGroup_ID (0);
// @HC_PayComponentGroup_ID@
        } */
    }

    /** Load Constructor */
    public X_HC_EligibilityGroupLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_EligibilityGroupLine[")
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

	public I_HC_EligibilityGroup getHC_EligibilityGroup() throws RuntimeException
    {
		return (I_HC_EligibilityGroup)MTable.get(getCtx(), I_HC_EligibilityGroup.Table_Name)
			.getPO(getHC_EligibilityGroup_ID(), get_TrxName());	}

	/** Set HC_EligibilityGroup.
		@param HC_EligibilityGroup_ID HC_EligibilityGroup	  */
	public void setHC_EligibilityGroup_ID (int HC_EligibilityGroup_ID)
	{
		if (HC_EligibilityGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EligibilityGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EligibilityGroup_ID, Integer.valueOf(HC_EligibilityGroup_ID));
	}

	/** Get HC_EligibilityGroup.
		@return HC_EligibilityGroup	  */
	public int getHC_EligibilityGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EligibilityGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_EligibilityGroupLine.
		@param HC_EligibilityGroupLine_ID HC_EligibilityGroupLine	  */
	public void setHC_EligibilityGroupLine_ID (int HC_EligibilityGroupLine_ID)
	{
		if (HC_EligibilityGroupLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EligibilityGroupLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EligibilityGroupLine_ID, Integer.valueOf(HC_EligibilityGroupLine_ID));
	}

	/** Get HC_EligibilityGroupLine.
		@return HC_EligibilityGroupLine	  */
	public int getHC_EligibilityGroupLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EligibilityGroupLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_EligibilityGroupLine_ID()));
    }

	/** Set HC_EligibilityGroupLine_UU.
		@param HC_EligibilityGroupLine_UU HC_EligibilityGroupLine_UU	  */
	public void setHC_EligibilityGroupLine_UU (String HC_EligibilityGroupLine_UU)
	{
		set_Value (COLUMNNAME_HC_EligibilityGroupLine_UU, HC_EligibilityGroupLine_UU);
	}

	/** Get HC_EligibilityGroupLine_UU.
		@return HC_EligibilityGroupLine_UU	  */
	public String getHC_EligibilityGroupLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_EligibilityGroupLine_UU);
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
}