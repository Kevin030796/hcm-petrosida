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

/** Generated Interface for HC_ProcessImport
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_ProcessImport 
{

    /** TableName=HC_ProcessImport */
    public static final String Table_Name = "HC_ProcessImport";

    /** AD_Table_ID=300232 */
    public static final int Table_ID = 300232;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name HC_AmtValue */
    public static final String COLUMNNAME_HC_AmtValue = "HC_AmtValue";

	/** Set Amount Value	  */
	public void setHC_AmtValue (BigDecimal HC_AmtValue);

	/** Get Amount Value	  */
	public BigDecimal getHC_AmtValue();

    /** Column name HC_BooleanValue */
    public static final String COLUMNNAME_HC_BooleanValue = "HC_BooleanValue";

	/** Set Yes/No Value	  */
	public void setHC_BooleanValue (boolean HC_BooleanValue);

	/** Get Yes/No Value	  */
	public boolean isHC_BooleanValue();

    /** Column name HC_DateValue */
    public static final String COLUMNNAME_HC_DateValue = "HC_DateValue";

	/** Set Date Value	  */
	public void setHC_DateValue (Timestamp HC_DateValue);

	/** Get Date Value	  */
	public Timestamp getHC_DateValue();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

    /** Column name HC_PayComponent_ID */
    public static final String COLUMNNAME_HC_PayComponent_ID = "HC_PayComponent_ID";

	/** Set Payroll Component	  */
	public void setHC_PayComponent_ID (int HC_PayComponent_ID);

	/** Get Payroll Component	  */
	public int getHC_PayComponent_ID();

	public I_HC_PayComponent getHC_PayComponent() throws RuntimeException;

    /** Column name HC_PayrollProcess_ID */
    public static final String COLUMNNAME_HC_PayrollProcess_ID = "HC_PayrollProcess_ID";

	/** Set Payroll Process	  */
	public void setHC_PayrollProcess_ID (int HC_PayrollProcess_ID);

	/** Get Payroll Process	  */
	public int getHC_PayrollProcess_ID();

    /** Column name HC_ProcessImport_ID */
    public static final String COLUMNNAME_HC_ProcessImport_ID = "HC_ProcessImport_ID";

	/** Set HC_ProcessImport	  */
	public void setHC_ProcessImport_ID (int HC_ProcessImport_ID);

	/** Get HC_ProcessImport	  */
	public int getHC_ProcessImport_ID();

    /** Column name HC_ProcessImport_UU */
    public static final String COLUMNNAME_HC_ProcessImport_UU = "HC_ProcessImport_UU";

	/** Set HC_ProcessImport_UU	  */
	public void setHC_ProcessImport_UU (String HC_ProcessImport_UU);

	/** Get HC_ProcessImport_UU	  */
	public String getHC_ProcessImport_UU();

    /** Column name HC_QtyValue */
    public static final String COLUMNNAME_HC_QtyValue = "HC_QtyValue";

	/** Set Qty Value	  */
	public void setHC_QtyValue (BigDecimal HC_QtyValue);

	/** Get Qty Value	  */
	public BigDecimal getHC_QtyValue();

    /** Column name HC_StringValue */
    public static final String COLUMNNAME_HC_StringValue = "HC_StringValue";

	/** Set String Value	  */
	public void setHC_StringValue (String HC_StringValue);

	/** Get String Value	  */
	public String getHC_StringValue();

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
