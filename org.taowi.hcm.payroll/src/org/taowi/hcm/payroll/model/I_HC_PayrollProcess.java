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

/** Generated Interface for HC_PayrollProcess
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_PayrollProcess 
{

    /** TableName=HC_PayrollProcess */
    public static final String Table_Name = "HC_PayrollProcess";

    /** AD_Table_ID=300183 */
    public static final int Table_ID = 300183;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name HC_EligibilityGroup_ID */
    public static final String COLUMNNAME_HC_EligibilityGroup_ID = "HC_EligibilityGroup_ID";

	/** Set HC Eligibility Group	  */
	public void setHC_EligibilityGroup_ID (int HC_EligibilityGroup_ID);

	/** Get HC Eligibility Group	  */
	public int getHC_EligibilityGroup_ID();

	public I_HC_EligibilityGroup getHC_EligibilityGroup() throws RuntimeException;

    /** Column name HC_PayGroup_ID */
    public static final String COLUMNNAME_HC_PayGroup_ID = "HC_PayGroup_ID";

	/** Set Payroll Group	  */
	public void setHC_PayGroup_ID (int HC_PayGroup_ID);

	/** Get Payroll Group	  */
	public int getHC_PayGroup_ID();

	public org.taowi.hcm.core.model.I_HC_PayGroup getHC_PayGroup() throws RuntimeException;

    /** Column name HC_PayrollProcess_ID */
    public static final String COLUMNNAME_HC_PayrollProcess_ID = "HC_PayrollProcess_ID";

	/** Set Payroll Process	  */
	public void setHC_PayrollProcess_ID (int HC_PayrollProcess_ID);

	/** Get Payroll Process	  */
	public int getHC_PayrollProcess_ID();

    /** Column name HC_PayrollProcess_UU */
    public static final String COLUMNNAME_HC_PayrollProcess_UU = "HC_PayrollProcess_UU";

	/** Set HC_PayrollProcess_UU	  */
	public void setHC_PayrollProcess_UU (String HC_PayrollProcess_UU);

	/** Get HC_PayrollProcess_UU	  */
	public String getHC_PayrollProcess_UU();

    /** Column name HC_ProcessList_ID */
    public static final String COLUMNNAME_HC_ProcessList_ID = "HC_ProcessList_ID";

	/** Set HC Process List	  */
	public void setHC_ProcessList_ID (int HC_ProcessList_ID);

	/** Get HC Process List	  */
	public int getHC_ProcessList_ID();

	public org.taowi.hcm.payroll.model.I_HC_ProcessList getHC_ProcessList() throws RuntimeException;

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

    /** Column name IsValid */
    public static final String COLUMNNAME_IsValid = "IsValid";

	/** Set Valid.
	  * Element is valid
	  */
	public void setIsValid (boolean IsValid);

	/** Get Valid.
	  * Element is valid
	  */
	public boolean isValid();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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
