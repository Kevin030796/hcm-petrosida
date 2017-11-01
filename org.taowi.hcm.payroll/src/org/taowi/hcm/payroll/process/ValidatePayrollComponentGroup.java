package org.taowi.hcm.payroll.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.payroll.model.MHCPayComponentGroup;
import org.taowi.hcm.payroll.model.MHCPayComponentGroupLine;

public class ValidatePayrollComponentGroup extends SvrProcess {

	
	@Override
	protected void prepare() {

		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			/*
			if (name.equals("HR_Process_ID"))
				p_HR_Process_ID = para.getParameterAsInt();
			
			else
			*/
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {

		int p_HC_PayComponentGroup_ID = getRecord_ID();
		
		if (p_HC_PayComponentGroup_ID <= 0)
			return "Abort: No Payroll Component Group Selected";
		
		MHCPayComponentGroup payComponentGroup = new MHCPayComponentGroup(getCtx(), p_HC_PayComponentGroup_ID, get_TrxName());
		
		MHCPayComponentGroupLine[] payComponentGroupLines = payComponentGroup.getLines();
		
		
		
		return null;
	}

}
