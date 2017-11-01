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

/** Generated Interface for HC_ProcessSectionLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_ProcessSectionLine 
{

    /** TableName=HC_ProcessSectionLine */
    public static final String Table_Name = "HC_ProcessSectionLine";

    /** AD_Table_ID=300228 */
    public static final int Table_ID = 300228;

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

    /** Column name HC_PayComponent_ID */
    public static final String COLUMNNAME_HC_PayComponent_ID = "HC_PayComponent_ID";

	/** Set Payroll Component	  */
	public void setHC_PayComponent_ID (int HC_PayComponent_ID);

	/** Get Payroll Component	  */
	public int getHC_PayComponent_ID();

	public I_HC_PayComponent getHC_PayComponent() throws RuntimeException;

    /** Column name HC_ProcessSection_ID */
    public static final String COLUMNNAME_HC_ProcessSection_ID = "HC_ProcessSection_ID";

	/** Set Process Section	  */
	public void setHC_ProcessSection_ID (int HC_ProcessSection_ID);

	/** Get Process Section	  */
	public int getHC_ProcessSection_ID();

	public org.taowi.hcm.payroll.model.I_HC_ProcessSection getHC_ProcessSection() throws RuntimeException;

    /** Column name HC_ProcessSectionLine_ID */
    public static final String COLUMNNAME_HC_ProcessSectionLine_ID = "HC_ProcessSectionLine_ID";

	/** Set HC_ProcessSectionLine	  */
	public void setHC_ProcessSectionLine_ID (int HC_ProcessSectionLine_ID);

	/** Get HC_ProcessSectionLine	  */
	public int getHC_ProcessSectionLine_ID();

    /** Column name HC_ProcessSectionLine_UU */
    public static final String COLUMNNAME_HC_ProcessSectionLine_UU = "HC_ProcessSectionLine_UU";

	/** Set HC_ProcessSectionLine_UU	  */
	public void setHC_ProcessSectionLine_UU (String HC_ProcessSectionLine_UU);

	/** Get HC_ProcessSectionLine_UU	  */
	public String getHC_ProcessSectionLine_UU();

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
