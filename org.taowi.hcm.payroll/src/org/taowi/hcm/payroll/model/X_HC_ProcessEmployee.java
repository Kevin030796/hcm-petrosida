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

/** Generated Model for HC_ProcessEmployee
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_ProcessEmployee extends PO implements I_HC_ProcessEmployee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170606L;

    /** Standard Constructor */
    public X_HC_ProcessEmployee (Properties ctx, int HC_ProcessEmployee_ID, String trxName)
    {
      super (ctx, HC_ProcessEmployee_ID, trxName);
      /** if (HC_ProcessEmployee_ID == 0)
        {
			setHC_ProcessEmployee_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_ProcessEmployee (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_ProcessEmployee[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set HC_ProcessEmployee.
		@param HC_ProcessEmployee_ID HC_ProcessEmployee	  */
	public void setHC_ProcessEmployee_ID (int HC_ProcessEmployee_ID)
	{
		if (HC_ProcessEmployee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessEmployee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_ProcessEmployee_ID, Integer.valueOf(HC_ProcessEmployee_ID));
	}

	/** Get HC_ProcessEmployee.
		@return HC_ProcessEmployee	  */
	public int getHC_ProcessEmployee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ProcessEmployee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_ProcessEmployee_ID()));
    }

	/** Set HC_ProcessEmployee_UU.
		@param HC_ProcessEmployee_UU HC_ProcessEmployee_UU	  */
	public void setHC_ProcessEmployee_UU (String HC_ProcessEmployee_UU)
	{
		set_Value (COLUMNNAME_HC_ProcessEmployee_UU, HC_ProcessEmployee_UU);
	}

	/** Get HC_ProcessEmployee_UU.
		@return HC_ProcessEmployee_UU	  */
	public String getHC_ProcessEmployee_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_ProcessEmployee_UU);
	}
}