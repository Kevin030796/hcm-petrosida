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

/** Generated Model for HC_PayrollProcessLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayrollProcessLine extends PO implements I_HC_PayrollProcessLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161120L;

    /** Standard Constructor */
    public X_HC_PayrollProcessLine (Properties ctx, int HC_PayrollProcessLine_ID, String trxName)
    {
      super (ctx, HC_PayrollProcessLine_ID, trxName);
      /** if (HC_PayrollProcessLine_ID == 0)
        {
			setHC_PayrollProcessLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PayrollProcessLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_PayrollProcessLine[")
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

	/** Set HC_PayrollProcessLine.
		@param HC_PayrollProcessLine_ID HC_PayrollProcessLine	  */
	public void setHC_PayrollProcessLine_ID (int HC_PayrollProcessLine_ID)
	{
		if (HC_PayrollProcessLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayrollProcessLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayrollProcessLine_ID, Integer.valueOf(HC_PayrollProcessLine_ID));
	}

	/** Get HC_PayrollProcessLine.
		@return HC_PayrollProcessLine	  */
	public int getHC_PayrollProcessLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayrollProcessLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_PayrollProcessLine_ID()));
    }

	/** Set HC_PayrollProcessLine_UU.
		@param HC_PayrollProcessLine_UU HC_PayrollProcessLine_UU	  */
	public void setHC_PayrollProcessLine_UU (String HC_PayrollProcessLine_UU)
	{
		set_Value (COLUMNNAME_HC_PayrollProcessLine_UU, HC_PayrollProcessLine_UU);
	}

	/** Get HC_PayrollProcessLine_UU.
		@return HC_PayrollProcessLine_UU	  */
	public String getHC_PayrollProcessLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayrollProcessLine_UU);
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