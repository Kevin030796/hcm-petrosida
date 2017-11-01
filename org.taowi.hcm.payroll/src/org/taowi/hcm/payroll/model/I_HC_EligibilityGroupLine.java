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

/** Generated Interface for HC_EligibilityGroupLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_EligibilityGroupLine 
{

    /** TableName=HC_EligibilityGroupLine */
    public static final String Table_Name = "HC_EligibilityGroupLine";

    /** AD_Table_ID=300226 */
    public static final int Table_ID = 300226;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name HC_EligibilityGroup_ID */
    public static final String COLUMNNAME_HC_EligibilityGroup_ID = "HC_EligibilityGroup_ID";

	/** Set HC_EligibilityGroup	  */
	public void setHC_EligibilityGroup_ID (int HC_EligibilityGroup_ID);

	/** Get HC_EligibilityGroup	  */
	public int getHC_EligibilityGroup_ID();

	public I_HC_EligibilityGroup getHC_EligibilityGroup() throws RuntimeException;

    /** Column name HC_EligibilityGroupLine_ID */
    public static final String COLUMNNAME_HC_EligibilityGroupLine_ID = "HC_EligibilityGroupLine_ID";

	/** Set HC_EligibilityGroupLine	  */
	public void setHC_EligibilityGroupLine_ID (int HC_EligibilityGroupLine_ID);

	/** Get HC_EligibilityGroupLine	  */
	public int getHC_EligibilityGroupLine_ID();

    /** Column name HC_EligibilityGroupLine_UU */
    public static final String COLUMNNAME_HC_EligibilityGroupLine_UU = "HC_EligibilityGroupLine_UU";

	/** Set HC_EligibilityGroupLine_UU	  */
	public void setHC_EligibilityGroupLine_UU (String HC_EligibilityGroupLine_UU);

	/** Get HC_EligibilityGroupLine_UU	  */
	public String getHC_EligibilityGroupLine_UU();

    /** Column name HC_PayComponentGroup_ID */
    public static final String COLUMNNAME_HC_PayComponentGroup_ID = "HC_PayComponentGroup_ID";

	/** Set Payroll Component Group	  */
	public void setHC_PayComponentGroup_ID (int HC_PayComponentGroup_ID);

	/** Get Payroll Component Group	  */
	public int getHC_PayComponentGroup_ID();

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
