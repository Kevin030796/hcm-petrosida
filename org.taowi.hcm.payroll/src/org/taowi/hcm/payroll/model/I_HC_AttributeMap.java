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

/** Generated Interface for HC_AttributeMap
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_AttributeMap 
{

    /** TableName=HC_AttributeMap */
    public static final String Table_Name = "HC_AttributeMap";

    /** AD_Table_ID=300221 */
    public static final int Table_ID = 300221;

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

    /** Column name HC_AtrributeMapKey */
    public static final String COLUMNNAME_HC_AtrributeMapKey = "HC_AtrributeMapKey";

	/** Set Attribute Map Key	  */
	public void setHC_AtrributeMapKey (String HC_AtrributeMapKey);

	/** Get Attribute Map Key	  */
	public String getHC_AtrributeMapKey();

    /** Column name HC_Attribute_ID */
    public static final String COLUMNNAME_HC_Attribute_ID = "HC_Attribute_ID";

	/** Set HC_Attribute	  */
	public void setHC_Attribute_ID (int HC_Attribute_ID);

	/** Get HC_Attribute	  */
	public int getHC_Attribute_ID();

	public org.taowi.hcm.payroll.model.I_HC_Attribute getHC_Attribute() throws RuntimeException;

    /** Column name HC_AttributeMap_ID */
    public static final String COLUMNNAME_HC_AttributeMap_ID = "HC_AttributeMap_ID";

	/** Set HC_AttributeMap	  */
	public void setHC_AttributeMap_ID (int HC_AttributeMap_ID);

	/** Get HC_AttributeMap	  */
	public int getHC_AttributeMap_ID();

    /** Column name HC_AttributeMap_UU */
    public static final String COLUMNNAME_HC_AttributeMap_UU = "HC_AttributeMap_UU";

	/** Set HC_AttributeMap_UU	  */
	public void setHC_AttributeMap_UU (String HC_AttributeMap_UU);

	/** Get HC_AttributeMap_UU	  */
	public String getHC_AttributeMap_UU();

    /** Column name HC_ValueNumeric */
    public static final String COLUMNNAME_HC_ValueNumeric = "HC_ValueNumeric";

	/** Set Value Numeric	  */
	public void setHC_ValueNumeric (BigDecimal HC_ValueNumeric);

	/** Get Value Numeric	  */
	public BigDecimal getHC_ValueNumeric();

    /** Column name HC_ValueString */
    public static final String COLUMNNAME_HC_ValueString = "HC_ValueString";

	/** Set Value String	  */
	public void setHC_ValueString (String HC_ValueString);

	/** Get Value String	  */
	public String getHC_ValueString();

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
