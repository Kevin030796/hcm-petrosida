package org.taowi.hcm.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MCurrency;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class MHCMovement extends X_HC_Movement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8547208349127534368L;

	public MHCMovement(Properties ctx, int HC_Movement_ID, String trxName) {
		super(ctx, HC_Movement_ID, trxName);
	}

	public MHCMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * According to the concept type, it's saved in the column specified for the purpose
	 * @param columnType column type (see MHRConcept.COLUMNTYPE_*)
	 * @param value
	 */
	public void setColumnValue(Object value)
	{
		try {
			final String dataType = getType();
			switch (dataType) {
				case MHCPayComponent.HC_PAYCOMPONENTTYPE_Integer: 
					BigDecimal qty = new BigDecimal(value.toString()); 
					setHC_QtyValue(qty);
					setHC_AmtValue(Env.ZERO);
					break;
				case MHCPayComponent.HC_PAYCOMPONENTTYPE_String: 
					setHC_StringValue(value.toString().trim());
					break;
				case MHCPayComponent.HC_PAYCOMPONENTTYPE_Date: 
					if (value instanceof Timestamp)
						setHC_DateValue((Timestamp)value);
					else
						setHC_DateValue(Timestamp.valueOf(value.toString().trim().substring(0, 10)+ " 00:00:00.0"));
					break;
				case MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric: case MHCPayComponent.HC_PAYCOMPONENTTYPE_Formula: 
				case MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning: case MHCPayComponent.HC_PAYCOMPONENTTYPE_Deduction:
					//int precision = MCurrency.getStdPrecision(getCtx(), Env.getContextAsInt(p_ctx, "$C_Currency_ID"));				
					BigDecimal amount = new BigDecimal(value.toString()).setScale(4, BigDecimal.ROUND_HALF_UP);
					setHC_AmtValue(amount);
					setHC_QtyValue(Env.ZERO);
					break;
				case MHCPayComponent.HC_PAYCOMPONENTTYPE_YesNo:
					if (value instanceof Boolean)
						setHC_BooleanValue((Boolean)value);
					else
						setHC_BooleanValue(false);
					break;
				default:
					throw new AdempiereException("@NotSupported@ @Type@ - "+dataType);
			}
	
		}
		catch (Exception e) 
		{
			throw new AdempiereException("@Script Error@");
		}
	
	}
	
	public boolean isEmpty()
	{
		if (getHC_QtyValue() == null && getHC_AmtValue() == null
			&& getHC_DateValue() == null
			&& Util.isEmpty(getHC_StringValue()))
				return true;
		else return false;

	}
}
