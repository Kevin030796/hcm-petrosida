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

/** Generated Model for HC_PayComponentGroupLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayComponentGroupLine extends PO implements I_HC_PayComponentGroupLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161120L;

    /** Standard Constructor */
    public X_HC_PayComponentGroupLine (Properties ctx, int HC_PayComponentGroupLine_ID, String trxName)
    {
      super (ctx, HC_PayComponentGroupLine_ID, trxName);
      /** if (HC_PayComponentGroupLine_ID == 0)
        {
			setHC_PayComponentGroupLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PayComponentGroupLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_PayComponentGroupLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_HC_PayComponentGroup getHC_PayComponentGroup() throws RuntimeException
    {
		return (I_HC_PayComponentGroup)MTable.get(getCtx(), I_HC_PayComponentGroup.Table_Name)
			.getPO(getHC_PayComponentGroup_ID(), get_TrxName());	}

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

	/** Set Payroll Component Group Line.
		@param HC_PayComponentGroupLine_ID Payroll Component Group Line	  */
	public void setHC_PayComponentGroupLine_ID (int HC_PayComponentGroupLine_ID)
	{
		if (HC_PayComponentGroupLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayComponentGroupLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayComponentGroupLine_ID, Integer.valueOf(HC_PayComponentGroupLine_ID));
	}

	/** Get Payroll Component Group Line.
		@return Payroll Component Group Line	  */
	public int getHC_PayComponentGroupLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayComponentGroupLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_PayComponentGroupLine_ID()));
    }

	/** Set HC_PayComponentGroupLine_UU.
		@param HC_PayComponentGroupLine_UU HC_PayComponentGroupLine_UU	  */
	public void setHC_PayComponentGroupLine_UU (String HC_PayComponentGroupLine_UU)
	{
		set_Value (COLUMNNAME_HC_PayComponentGroupLine_UU, HC_PayComponentGroupLine_UU);
	}

	/** Get HC_PayComponentGroupLine_UU.
		@return HC_PayComponentGroupLine_UU	  */
	public String getHC_PayComponentGroupLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayComponentGroupLine_UU);
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

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}