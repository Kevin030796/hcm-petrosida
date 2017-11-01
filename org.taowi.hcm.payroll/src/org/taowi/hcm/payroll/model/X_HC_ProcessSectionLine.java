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

/** Generated Model for HC_ProcessSectionLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_ProcessSectionLine extends PO implements I_HC_ProcessSectionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170606L;

    /** Standard Constructor */
    public X_HC_ProcessSectionLine (Properties ctx, int HC_ProcessSectionLine_ID, String trxName)
    {
      super (ctx, HC_ProcessSectionLine_ID, trxName);
      /** if (HC_ProcessSectionLine_ID == 0)
        {
			setHC_ProcessSectionLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_ProcessSectionLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_ProcessSectionLine[")
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

	public org.taowi.hcm.payroll.model.I_HC_ProcessSection getHC_ProcessSection() throws RuntimeException
    {
		return (org.taowi.hcm.payroll.model.I_HC_ProcessSection)MTable.get(getCtx(), org.taowi.hcm.payroll.model.I_HC_ProcessSection.Table_Name)
			.getPO(getHC_ProcessSection_ID(), get_TrxName());	}

	/** Set Process Section.
		@param HC_ProcessSection_ID Process Section	  */
	public void setHC_ProcessSection_ID (int HC_ProcessSection_ID)
	{
		if (HC_ProcessSection_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessSection_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessSection_ID, Integer.valueOf(HC_ProcessSection_ID));
	}

	/** Get Process Section.
		@return Process Section	  */
	public int getHC_ProcessSection_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ProcessSection_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_ProcessSectionLine.
		@param HC_ProcessSectionLine_ID HC_ProcessSectionLine	  */
	public void setHC_ProcessSectionLine_ID (int HC_ProcessSectionLine_ID)
	{
		if (HC_ProcessSectionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessSectionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessSectionLine_ID, Integer.valueOf(HC_ProcessSectionLine_ID));
	}

	/** Get HC_ProcessSectionLine.
		@return HC_ProcessSectionLine	  */
	public int getHC_ProcessSectionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ProcessSectionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_ProcessSectionLine_ID()));
    }

	/** Set HC_ProcessSectionLine_UU.
		@param HC_ProcessSectionLine_UU HC_ProcessSectionLine_UU	  */
	public void setHC_ProcessSectionLine_UU (String HC_ProcessSectionLine_UU)
	{
		set_Value (COLUMNNAME_HC_ProcessSectionLine_UU, HC_ProcessSectionLine_UU);
	}

	/** Get HC_ProcessSectionLine_UU.
		@return HC_ProcessSectionLine_UU	  */
	public String getHC_ProcessSectionLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_ProcessSectionLine_UU);
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