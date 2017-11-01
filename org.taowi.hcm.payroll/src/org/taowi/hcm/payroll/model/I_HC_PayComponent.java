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

/** Generated Interface for HC_PayComponent
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_PayComponent 
{

    /** TableName=HC_PayComponent */
    public static final String Table_Name = "HC_PayComponent";

    /** AD_Table_ID=1100124 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

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

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException;

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

    /** Column name AD_Rule_ID */
    public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

	/** Set Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID);

	/** Get Rule	  */
	public int getAD_Rule_ID();

	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException;

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

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

    /** Column name HC_AccumulatorType */
    public static final String COLUMNNAME_HC_AccumulatorType = "HC_AccumulatorType";

	/** Set Accumulator Type	  */
	public void setHC_AccumulatorType (String HC_AccumulatorType);

	/** Get Accumulator Type	  */
	public String getHC_AccumulatorType();

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

    /** Column name HC_IntValue */
    public static final String COLUMNNAME_HC_IntValue = "HC_IntValue";

	/** Set Integer Value	  */
	public void setHC_IntValue (int HC_IntValue);

	/** Get Integer Value	  */
	public int getHC_IntValue();

    /** Column name HC_NumValue */
    public static final String COLUMNNAME_HC_NumValue = "HC_NumValue";

	/** Set Numeric Value	  */
	public void setHC_NumValue (BigDecimal HC_NumValue);

	/** Get Numeric Value	  */
	public BigDecimal getHC_NumValue();

    /** Column name HC_ImportDataType */
    public static final String COLUMNNAME_HC_ImportDataType = "HC_ImportDataType";

	/** Set Import Data Type	  */
	public void setHC_ImportDataType (String HC_ImportDataType);

	/** Get Import Data Type	  */
	public String getHC_ImportDataType();

    /** Column name HC_PayComponent_ID */
    public static final String COLUMNNAME_HC_PayComponent_ID = "HC_PayComponent_ID";

	/** Set Payroll Component	  */
	public void setHC_PayComponent_ID (int HC_PayComponent_ID);

	/** Get Payroll Component	  */
	public int getHC_PayComponent_ID();

    /** Column name HC_PayComponent_UU */
    public static final String COLUMNNAME_HC_PayComponent_UU = "HC_PayComponent_UU";

	/** Set HC_PayComponent_UU	  */
	public void setHC_PayComponent_UU (String HC_PayComponent_UU);

	/** Get HC_PayComponent_UU	  */
	public String getHC_PayComponent_UU();

    /** Column name HC_PayComponentType */
    public static final String COLUMNNAME_HC_PayComponentType = "HC_PayComponentType";

	/** Set Pay Component Type	  */
	public void setHC_PayComponentType (String HC_PayComponentType);

	/** Get Pay Component Type	  */
	public String getHC_PayComponentType();

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

    /** Column name IsGlobalPayComponent */
    public static final String COLUMNNAME_IsGlobalPayComponent = "IsGlobalPayComponent";

	/** Set Global Pay Component ?	  */
	public void setIsGlobalPayComponent (boolean IsGlobalPayComponent);

	/** Get Global Pay Component ?	  */
	public boolean isGlobalPayComponent();

    /** Column name IsHasAttribute */
    public static final String COLUMNNAME_IsHasAttribute = "IsHasAttribute";

	/** Set Has Attribute?	  */
	public void setIsHasAttribute (boolean IsHasAttribute);

	/** Get Has Attribute?	  */
	public boolean isHasAttribute();

    /** Column name IsHasKey2 */
    public static final String COLUMNNAME_IsHasKey2 = "IsHasKey2";

	/** Set Has Key 2	  */
	public void setIsHasKey2 (boolean IsHasKey2);

	/** Get Has Key 2	  */
	public boolean isHasKey2();

    /** Column name IsHasKey3 */
    public static final String COLUMNNAME_IsHasKey3 = "IsHasKey3";

	/** Set Has Key 3	  */
	public void setIsHasKey3 (boolean IsHasKey3);

	/** Get Has Key 3	  */
	public boolean isHasKey3();

    /** Column name IsHasKey4 */
    public static final String COLUMNNAME_IsHasKey4 = "IsHasKey4";

	/** Set Has Key 4	  */
	public void setIsHasKey4 (boolean IsHasKey4);

	/** Get Has Key 4	  */
	public boolean isHasKey4();

    /** Column name IsHasKey5 */
    public static final String COLUMNNAME_IsHasKey5 = "IsHasKey5";

	/** Set Has Key 5	  */
	public void setIsHasKey5 (boolean IsHasKey5);

	/** Get Has Key 5	  */
	public boolean isHasKey5();

    /** Column name IsPaid */
    public static final String COLUMNNAME_IsPaid = "IsPaid";

	/** Set Paid.
	  * The document is paid
	  */
	public void setIsPaid (boolean IsPaid);

	/** Get Paid.
	  * The document is paid
	  */
	public boolean isPaid();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsProcessRetroactive */
    public static final String COLUMNNAME_IsProcessRetroactive = "IsProcessRetroactive";

	/** Set Process Retroactive?	  */
	public void setIsProcessRetroactive (boolean IsProcessRetroactive);

	/** Get Process Retroactive?	  */
	public boolean isProcessRetroactive();

    /** Column name IsTransferToGL */
    public static final String COLUMNNAME_IsTransferToGL = "IsTransferToGL";

	/** Set Transfer To GL?	  */
	public void setIsTransferToGL (boolean IsTransferToGL);

	/** Get Transfer To GL?	  */
	public boolean isTransferToGL();

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

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
