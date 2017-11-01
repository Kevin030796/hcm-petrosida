package org.taowi.hcm.core.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeDataChange;

public class HC_EmployeeDataChangeComplete extends SvrProcess{

	int p_EmployeeDataChange_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			/*
			else if (name.equals("C_Project_ID")){
				p_C_Project_ID = para[i].getParameterAsInt();
				
			} else if (name.equals("EffectiveDateFrom")){
					EffectiveDateFrom = para[i].getParameterAsTimestamp();
			
			}*/
		else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_EmployeeDataChange_ID = getRecord_ID();		
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_EmployeeDataChange_ID==0){
			return "Data Change Transaction not selected";
		}
		
		MEmployeeDataChange eDataChange = new MEmployeeDataChange(getCtx(), p_EmployeeDataChange_ID, get_TrxName());
		if (eDataChange.isProcessed())
			return "Abort.. Data Change Transaction Has Been Processed";
		
		MEmployee employee = new MEmployee(getCtx(), eDataChange.get_ValueAsInt("HC_Employee_ID"), get_TrxName());
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_NameChange)) {
			employee.setName(eDataChange.getName());
			employee.setName2(eDataChange.getName2());		
		}
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_EmailChange)) {
			employee.setEMail(eDataChange.getEMail());
			employee.setEmail2(eDataChange.getEmail2());		
		}
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_PhoneChange)) {
			employee.setPhone(eDataChange.getPhone());
			employee.setPhone2(eDataChange.getPhone2());
			employee.setPhoneExt1(eDataChange.getPhoneExt1());
			employee.setPhoneExt2(eDataChange.getPhoneExt2());		
		}
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_NationalIDChange)) {
			employee.setHC_NationalID1(eDataChange.getHC_NationalID1());
			employee.setHC_NationalID2(eDataChange.getHC_NationalID2());
			employee.setHC_NationalID3(eDataChange.getHC_NationalID3());
			employee.setHC_NationalID4(eDataChange.getHC_NationalID4());
			employee.setHC_NationalID5(eDataChange.getHC_NationalID5());
			employee.setHC_ID1_ExpDate(eDataChange.getHC_ID1_ExpDate());
			employee.setHC_ID2_ExpDate(eDataChange.getHC_ID2_ExpDate());
			employee.setHC_ID3_ExpDate(eDataChange.getHC_ID3_ExpDate());
			employee.setHC_ID4_ExpDate(eDataChange.getHC_ID4_ExpDate());
			employee.setHC_ID5_ExpDate(eDataChange.getHC_ID5_ExpDate());
			
		}
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_AlternateIDChange)) {
			employee.setHC_AltID1(eDataChange.getHC_AltID1());
			employee.setHC_AltID2(eDataChange.getHC_AltID2());	
		}
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_TaxStatusChange)) {
			employee.setHC_TaxStatus_ID(eDataChange.getHC_TaxStatus_ID());
			employee.setC_TaxOffice_ID(eDataChange.getC_TaxOffice_ID());
		}
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_MaritalStatusChange)) {
			employee.set_CustomColumn("HC_MaritalStatus", eDataChange.getHC_MaritalStatus());
			employee.set_CustomColumn("HC_MaritalDate", eDataChange.getHC_MaritalDate());
		}
		
		//@TommyAng
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_AddressChange)) {
			employee.setC_Location_ID(eDataChange.getC_Location_ID());
		}
		//end@TommyAng
		
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New) ||
			eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_PersonalDataChange)) {
			employee.setBirthday(eDataChange.getBirthday());
			employee.setHC_BirthCountry_ID(eDataChange.getHC_BirthCountry_ID());
			employee.setHC_BirthRegion_ID(eDataChange.getHC_BirthRegion_ID());
			employee.setHC_BloodType(eDataChange.getHC_BloodType());
			employee.setHC_Ethnic_ID(eDataChange.getHC_Ethnic_ID());
			employee.setHC_Gender(eDataChange.getHC_Gender());
			employee.setHC_Religion_ID(eDataChange.getHC_Religion_ID());
			//@TommyAng
			employee.setCity(eDataChange.getCity());
			employee.setHC_WorkStartDate((Timestamp) eDataChange.get_Value("HC_WorkStartDate"));
			employee.setValue(eDataChange.getValue());
			//end @TommyAng
		}
		if (eDataChange.getHC_PersonalDataAction()
				.equals(MEmployeeDataChange.HC_PERSONALDATAACTION_New)){
			employee.setValue(eDataChange.getValue());
			employee.setC_BPartner_ID(eDataChange.getC_BPartner_ID());
		}
			employee.setDescription(eDataChange.getDescription());
			employee.setEffectiveDateFrom(eDataChange.getEffectiveDateFrom());
			employee.setHC_PersonalDataAction(eDataChange.getHC_PersonalDataAction());
			employee.saveEx();
			
			eDataChange.setProcessed(true);
			eDataChange.saveEx();
		
		return "Successfully Updated Employee Data" ;
	}

}
