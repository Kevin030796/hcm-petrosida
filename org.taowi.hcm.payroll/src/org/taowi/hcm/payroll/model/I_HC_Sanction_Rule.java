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


import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HC_Sanction_Rule
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
public interface I_HC_Sanction_Rule 
{

    /** TableName=HC_Sanction_Rule */
    public static final String Table_Name = "HC_Sanction_Rule";

    /** AD_Table_ID=1000207 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

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

    /** Column name Annual_Bonus_Deduction */
    public static final String COLUMNNAME_Annual_Bonus_Deduction = "Annual_Bonus_Deduction";

	/** Set Potongan Bonus Tahunan	  */
	public void setAnnual_Bonus_Deduction (int Annual_Bonus_Deduction);

	/** Get Potongan Bonus Tahunan	  */
	public int getAnnual_Bonus_Deduction();

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

    /** Column name HC_Sanction_Rule_ID */
    public static final String COLUMNNAME_HC_Sanction_Rule_ID = "HC_Sanction_Rule_ID";

	/** Set Aturan Sanksi	  */
	public void setHC_Sanction_Rule_ID (int HC_Sanction_Rule_ID);

	/** Get Aturan Sanksi	  */
	public int getHC_Sanction_Rule_ID();

    /** Column name HC_Sanction_Rule_UU */
    public static final String COLUMNNAME_HC_Sanction_Rule_UU = "HC_Sanction_Rule_UU";

	/** Set HC_Sanction_Rule_UU	  */
	public void setHC_Sanction_Rule_UU (String HC_Sanction_Rule_UU);

	/** Get HC_Sanction_Rule_UU	  */
	public String getHC_Sanction_Rule_UU();

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Quarterly_Incentive_Deduction */
    public static final String COLUMNNAME_Quarterly_Incentive_Deduction = "Quarterly_Incentive_Deduction";

	/** Set Potongan Insentif Triwulan	  */
	public void setQuarterly_Incentive_Deduction (BigDecimal Quarterly_Incentive_Deduction);

	/** Get Potongan Insentif Triwulan	  */
	public BigDecimal getQuarterly_Incentive_Deduction();

    /** Column name Sanction_Period */
    public static final String COLUMNNAME_Sanction_Period = "Sanction_Period";

	/** Set Masa Berlaku	  */
	public void setSanction_Period (int Sanction_Period);

	/** Get Masa Berlaku	  */
	public int getSanction_Period();

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
