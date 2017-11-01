package org.taowi.hcm.payroll.process;

import org.compiere.process.SvrProcess;
import org.taowi.hcm.payroll.model.MHCMovement;
import org.taowi.hcm.payroll.model.MHCPayrollProcess;
import org.taowi.hcm.payroll.model.MHCProcessEmployee;
import org.taowi.hcm.payroll.model.MHCProcessImport;

public class ReactivatePayrollProcess extends SvrProcess{

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		
		int HC_PayrollProcess_ID = getRecord_ID();
		MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), HC_PayrollProcess_ID, get_TrxName());
		payrollProcess.setProcessed(false);
		payrollProcess.saveEx();
		
		for (int HC_ProcessEmployee_ID : payrollProcess.getProcessEmployee()) {
			MHCProcessEmployee processEmployee = new MHCProcessEmployee(getCtx(), HC_ProcessEmployee_ID, get_TrxName());
			processEmployee.set_ValueOfColumn("Processed", false);
			processEmployee.saveEx();
		}
		
		for (int HC_ProcessImport_ID : payrollProcess.getProcessImport()) {
			MHCProcessImport processImport = new MHCProcessImport(getCtx(), HC_ProcessImport_ID, get_TrxName());
			processImport.setProcessed(false);
			processImport.saveEx();
		}
		
		for (MHCMovement movement : payrollProcess.getMovements(true)) {
			movement.setProcessed(false);
			movement.saveEx();
		}
		
		return "Complete Movement";
	}

}
