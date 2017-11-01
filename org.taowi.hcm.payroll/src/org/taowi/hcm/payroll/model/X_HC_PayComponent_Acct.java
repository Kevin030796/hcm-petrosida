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

/** Generated Model for HC_PayComponent_Acct
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_PayComponent_Acct extends PO implements I_HC_PayComponent_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170718L;

    /** Standard Constructor */
    public X_HC_PayComponent_Acct (Properties ctx, int HC_PayComponent_Acct_ID, String trxName)
    {
      super (ctx, HC_PayComponent_Acct_ID, trxName);
      /** if (HC_PayComponent_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setHC_Expense_Acct (0);
			setHC_PayComponent_ID (0);
// @HC_PayComponent_ID@
        } */
    }

    /** Load Constructor */
    public X_HC_PayComponent_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_PayComponent_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException
    {
		return (org.compiere.model.I_C_AcctSchema)MTable.get(getCtx(), org.compiere.model.I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema_ID(), get_TrxName());	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getHC_Expense_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getHC_Expense_Acct(), get_TrxName());	}

	/** Set HC Expense Acct.
		@param HC_Expense_Acct HC Expense Acct	  */
	public void setHC_Expense_Acct (int HC_Expense_Acct)
	{
		set_Value (COLUMNNAME_HC_Expense_Acct, Integer.valueOf(HC_Expense_Acct));
	}

	/** Get HC Expense Acct.
		@return HC Expense Acct	  */
	public int getHC_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_PayComponent_Acct_UU.
		@param HC_PayComponent_Acct_UU HC_PayComponent_Acct_UU	  */
	public void setHC_PayComponent_Acct_UU (String HC_PayComponent_Acct_UU)
	{
		set_Value (COLUMNNAME_HC_PayComponent_Acct_UU, HC_PayComponent_Acct_UU);
	}

	/** Get HC_PayComponent_Acct_UU.
		@return HC_PayComponent_Acct_UU	  */
	public String getHC_PayComponent_Acct_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PayComponent_Acct_UU);
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
}