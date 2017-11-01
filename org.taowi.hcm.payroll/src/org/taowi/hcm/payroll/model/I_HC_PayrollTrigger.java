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
package org.taowi.hcm.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_EmployeeJob;

/** Generated Interface for HC_PayrollTrigger
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_PayrollTrigger 
{

    /** TableName=HC_PayrollTrigger */
    public static final String Table_Name = "HC_PayrollTrigger";

    /** AD_Table_ID=300185 */
    public static final int Table_ID = 300185;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name ColumnName */
    public static final String COLUMNNAME_ColumnName = "ColumnName";

	/** Set DB Column Name.
	  * Name of the column in the database
	  */
	public void setColumnName (String ColumnName);

	/** Get DB Column Name.
	  * Name of the column in the database
	  */
	public String getColumnName();

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DifferenceAmt */
    public static final String COLUMNNAME_DifferenceAmt = "DifferenceAmt";

	/** Set Difference.
	  * Difference Amount
	  */
	public void setDifferenceAmt (BigDecimal DifferenceAmt);

	/** Get Difference.
	  * Difference Amount
	  */
	public BigDecimal getDifferenceAmt();

    /** Column name EffectiveDateFrom */
    public static final String COLUMNNAME_EffectiveDateFrom = "EffectiveDateFrom";

	/** Set Effective Date From	  */
	public void setEffectiveDateFrom (Timestamp EffectiveDateFrom);

	/** Get Effective Date From	  */
	public Timestamp getEffectiveDateFrom();

    /** Column name EffectiveDateTo */
    public static final String COLUMNNAME_EffectiveDateTo = "EffectiveDateTo";

	/** Set Effective Date To	  */
	public void setEffectiveDateTo (Timestamp EffectiveDateTo);

	/** Get Effective Date To	  */
	public Timestamp getEffectiveDateTo();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_EmployeeJob_ID */
    public static final String COLUMNNAME_HC_EmployeeJob_ID = "HC_EmployeeJob_ID";

	/** Set Employee Job Data	  */
	public void setHC_EmployeeJob_ID (int HC_EmployeeJob_ID);

	/** Get Employee Job Data	  */
	public int getHC_EmployeeJob_ID();

	public I_HC_EmployeeJob getHC_EmployeeJob() throws RuntimeException;

    /** Column name HC_PayrollProcess_ID */
    public static final String COLUMNNAME_HC_PayrollProcess_ID = "HC_PayrollProcess_ID";

	/** Set Payroll Process	  */
	public void setHC_PayrollProcess_ID (int HC_PayrollProcess_ID);

	/** Get Payroll Process	  */
	public int getHC_PayrollProcess_ID();

	public I_HC_PayrollProcess getHC_PayrollProcess() throws RuntimeException;

    /** Column name HC_PayrollTrigger_ID */
    public static final String COLUMNNAME_HC_PayrollTrigger_ID = "HC_PayrollTrigger_ID";

	/** Set HC_PayrollTrigger	  */
	public void setHC_PayrollTrigger_ID (int HC_PayrollTrigger_ID);

	/** Get HC_PayrollTrigger	  */
	public int getHC_PayrollTrigger_ID();

    /** Column name HC_PayrollTrigger_UU */
    public static final String COLUMNNAME_HC_PayrollTrigger_UU = "HC_PayrollTrigger_UU";

	/** Set HC_PayrollTrigger_UU	  */
	public void setHC_PayrollTrigger_UU (String HC_PayrollTrigger_UU);

	/** Get HC_PayrollTrigger_UU	  */
	public String getHC_PayrollTrigger_UU();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name NewValue */
    public static final String COLUMNNAME_NewValue = "NewValue";

	/** Set New Value.
	  * New field value
	  */
	public void setNewValue (String NewValue);

	/** Get New Value.
	  * New field value
	  */
	public String getNewValue();

    /** Column name OldValue */
    public static final String COLUMNNAME_OldValue = "OldValue";

	/** Set Old Value.
	  * The old file data
	  */
	public void setOldValue (String OldValue);

	/** Get Old Value.
	  * The old file data
	  */
	public String getOldValue();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
