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

/** Generated Interface for HC_ProcessListLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_ProcessListLine 
{

    /** TableName=HC_ProcessListLine */
    public static final String Table_Name = "HC_ProcessListLine";

    /** AD_Table_ID=300230 */
    public static final int Table_ID = 300230;

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

    /** Column name HC_ProcessList_ID */
    public static final String COLUMNNAME_HC_ProcessList_ID = "HC_ProcessList_ID";

	/** Set HC_ProcessList	  */
	public void setHC_ProcessList_ID (int HC_ProcessList_ID);

	/** Get HC_ProcessList	  */
	public int getHC_ProcessList_ID();

	public org.taowi.hcm.payroll.model.I_HC_ProcessList getHC_ProcessList() throws RuntimeException;

    /** Column name HC_ProcessListLine_ID */
    public static final String COLUMNNAME_HC_ProcessListLine_ID = "HC_ProcessListLine_ID";

	/** Set HC_ProcessListLine	  */
	public void setHC_ProcessListLine_ID (int HC_ProcessListLine_ID);

	/** Get HC_ProcessListLine	  */
	public int getHC_ProcessListLine_ID();

    /** Column name HC_ProcessListLine_UU */
    public static final String COLUMNNAME_HC_ProcessListLine_UU = "HC_ProcessListLine_UU";

	/** Set HC_ProcessListLine_UU	  */
	public void setHC_ProcessListLine_UU (String HC_ProcessListLine_UU);

	/** Get HC_ProcessListLine_UU	  */
	public String getHC_ProcessListLine_UU();

    /** Column name HC_ProcessSection_ID */
    public static final String COLUMNNAME_HC_ProcessSection_ID = "HC_ProcessSection_ID";

	/** Set Process Section	  */
	public void setHC_ProcessSection_ID (int HC_ProcessSection_ID);

	/** Get Process Section	  */
	public int getHC_ProcessSection_ID();

	public org.taowi.hcm.payroll.model.I_HC_ProcessSection getHC_ProcessSection() throws RuntimeException;

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
