package org.taowi.hcm.payroll.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.payroll.model.MHCPayrollProcess;

public class ValidatePayrollProcess extends SvrProcess {


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
		
		int p_PayrollProcess_ID = getRecord_ID();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT HC_PayComponent_ID FROM HC_PayComponentGroupLine WHERE HC_PayComponentGroup_ID IN ")
			.append("(SELECT HC_PayComponentGroup_ID from HC_PayrollProcessLine where HC_PayrollProcess_ID=?) ")
			.append("GROUP BY HC_PayComponent_ID ")
			.append("HAVING COUNT(HC_PayComponent_ID) > 1");

		int no = DB.getSQLValueEx(get_TrxName(), sql.toString(), p_PayrollProcess_ID);

		if (no > 0)
			return "Invalid: Duplicate Payroll Component Found";
		else {
			MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), p_PayrollProcess_ID, get_TrxName());
			payrollProcess.set_CustomColumn("IsValid", "Y");
			payrollProcess.saveEx();
		}
		return null;
	}

}
