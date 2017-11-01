package org.taowi.hcm.payroll.model;

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


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for HC_Sanction_Rule
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Sanction_Rule extends PO implements I_HC_Sanction_Rule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170712L;

    /** Standard Constructor */
    public X_HC_Sanction_Rule (Properties ctx, int HC_Sanction_Rule_ID, String trxName)
    {
      super (ctx, HC_Sanction_Rule_ID, trxName);
      /** if (HC_Sanction_Rule_ID == 0)
        {
			setHC_Sanction_Rule_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HC_Sanction_Rule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Sanction_Rule[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Potongan Bonus Tahunan.
		@param Annual_Bonus_Deduction Potongan Bonus Tahunan	  */
	public void setAnnual_Bonus_Deduction (int Annual_Bonus_Deduction)
	{
		set_Value (COLUMNNAME_Annual_Bonus_Deduction, Integer.valueOf(Annual_Bonus_Deduction));
	}

	/** Get Potongan Bonus Tahunan.
		@return Potongan Bonus Tahunan	  */
	public int getAnnual_Bonus_Deduction () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Annual_Bonus_Deduction);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Aturan Sanksi.
		@param HC_Sanction_Rule_ID Aturan Sanksi	  */
	public void setHC_Sanction_Rule_ID (int HC_Sanction_Rule_ID)
	{
		if (HC_Sanction_Rule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Sanction_Rule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Sanction_Rule_ID, Integer.valueOf(HC_Sanction_Rule_ID));
	}

	/** Get Aturan Sanksi.
		@return Aturan Sanksi	  */
	public int getHC_Sanction_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Sanction_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_Sanction_Rule_UU.
		@param HC_Sanction_Rule_UU HC_Sanction_Rule_UU	  */
	public void setHC_Sanction_Rule_UU (String HC_Sanction_Rule_UU)
	{
		set_Value (COLUMNNAME_HC_Sanction_Rule_UU, HC_Sanction_Rule_UU);
	}

	/** Get HC_Sanction_Rule_UU.
		@return HC_Sanction_Rule_UU	  */
	public String getHC_Sanction_Rule_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Sanction_Rule_UU);
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

	/** Set Potongan Insentif Triwulan.
		@param Quarterly_Incentive_Deduction Potongan Insentif Triwulan	  */
	public void setQuarterly_Incentive_Deduction (BigDecimal Quarterly_Incentive_Deduction)
	{
		set_Value (COLUMNNAME_Quarterly_Incentive_Deduction, Quarterly_Incentive_Deduction);
	}

	/** Get Potongan Insentif Triwulan.
		@return Potongan Insentif Triwulan	  */
	public BigDecimal getQuarterly_Incentive_Deduction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Quarterly_Incentive_Deduction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Masa Berlaku.
		@param Sanction_Period Masa Berlaku	  */
	public void setSanction_Period (int Sanction_Period)
	{
		set_Value (COLUMNNAME_Sanction_Period, Integer.valueOf(Sanction_Period));
	}

	/** Get Masa Berlaku.
		@return Masa Berlaku	  */
	public int getSanction_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sanction_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
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