package org.taowi.hcm.payroll.process;


import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.payroll.model.MHCPayrollProcess;

public class GeneratePayrollProcess extends SvrProcess{

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		
		int HC_PayrollProcess_ID = getRecord_ID();
		MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), HC_PayrollProcess_ID, get_TrxName());
		if(payrollProcess.isProcessed())
			throw new AdempiereException("Already Processed");
		String result = payrollProcess.createMovements();
		
		return result;
	}

}
