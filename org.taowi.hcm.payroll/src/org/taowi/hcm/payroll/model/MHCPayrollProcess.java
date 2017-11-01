package org.taowi.hcm.payroll.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MCalendar;
import org.compiere.model.MPeriod;
import org.compiere.model.MRule;
import org.compiere.model.Query;
import org.compiere.model.Scriptlet;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MHCPayGroup;
import org.taowi.hcm.core.model.MJobDataChange;
import org.taowi.hcm.core.model.X_HC_EmployeeCategory;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.core.model.X_HC_JobLevel;
import org.taowi.hcm.core.model.X_HC_TaxStatus;
import org.taowi.hcm.imported.classes.TimeUtil;

public class MHCPayrollProcess extends X_HC_PayrollProcess {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Object m_description = null;
	
	/** the context for rules */
	HashMap<String, Object> m_scriptCtx = new HashMap<String, Object>();

	private static StringBuilder s_scriptImport = new StringBuilder(" import org.eevolution.model.*;") 
	.append(" import org.compiere.model.*;")
	.append(" import org.adempiere.model.*;")
	.append(" import org.taowi.hcm.payroll.model.*;")
	.append(" import org.compiere.util.*;")
	.append(" import java.math.*;")
	.append(" import java.sql.*;");

	public int m_AD_User_ID = 0;
	public int m_HC_PayComponent_ID = 0;
	public String m_Type   = "";
	public Timestamp m_dateFrom;
	public Timestamp m_dateTo;	
	
	/** HC_PayComponent_ID->MHCMovement */
	public Hashtable<Integer, MHCMovement> m_EmployeeMovement = new Hashtable<Integer, MHCMovement>();
	
	/** HC_PayComponent_ID->MHCMovement */
	public Hashtable<Integer, MHCMovement> m_GlobalMovement = new Hashtable<Integer, MHCMovement>();
	
	/** HC_PayComponent_ID->MHCMovement */
	public Hashtable<Integer, MHCMovement> m_RetroMovement = new Hashtable<Integer, MHCMovement>();
	
	public boolean isRetroactive = false;
	
	/** The employee being processed */
	private MEmployee m_employee;
	private MEmployeeJob m_employeeJob;
	
	private MPeriod m_period;
	/* stack of concepts executing rules - to check loop in recursion */
	
	private List<MHCPayComponent> activePayComponentRule = new ArrayList<MHCPayComponent>(); 

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MHCPayrollProcess.class);
	
	public static void addScriptImportPackage(String packageName) {
		s_scriptImport.append(" import ").append(packageName).append(";");
	}

	public MHCPayrollProcess(Properties ctx, int HC_PayrollProcess_ID, String trxName) {
		super(ctx, HC_PayrollProcess_ID, trxName);
	}

	public MHCPayrollProcess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		if (getAD_Client_ID() == 0) {
			throw new AdempiereException("@AD_Client_ID@ = 0");
		}
		if (getAD_Org_ID() == 0) {
			int context_AD_Org_ID = getAD_Org_ID();
			if (context_AD_Org_ID == 0) {
				throw new AdempiereException("@AD_Org_ID@ = *");
			}
			setAD_Org_ID(context_AD_Org_ID);
			log.warning("Changed Org to Context=" + context_AD_Org_ID);
		}

		return true;
	}       
	
	/*
	public HashMap<Integer, Integer> getMapPayComponent() {
		MHCPayComponentGroup[] payComponentGroups = getPayComponentGroup();
		if (payComponentGroups == null)
			return null;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (MHCPayComponentGroup payComponentGroup : payComponentGroups) {
			MHCPayComponentGroupLine[] payComponentGroupLines = payComponentGroup.getLines();
			for (MHCPayComponentGroupLine line : payComponentGroupLines) {
				map.put(line.getHC_PayComponent_ID(), line.get_ID());			
			}
		}
		return map;
	}*/

	/**
	 * get all pay component in payroll process
	 * @return list MHCPayComponent
	 */
	/*
	public List<MHCPayComponent> getListPayComponent(){
		
		List<MHCPayComponent> listPayComponent = new ArrayList<MHCPayComponent>();
		List<MHCPayComponentGroup> listGroup = getPayComponentGroup();
		
		for (MHCPayComponentGroup payComponentGroup : listGroup) {			
			MHCPayComponentGroupLine[] groupLines = payComponentGroup.getLines();
			for (MHCPayComponentGroupLine groupLine : groupLines) {
				MHCPayComponent component = (MHCPayComponent) groupLine.getHC_PayComponent();
				listPayComponent.add(component);
			}	
		}
		
		//MHCPayComponent[] payComponent = new MHCPayComponent[listPayComponent.size()];
		//listPayComponent.toArray();
		
		return listPayComponent;
	}
	*/
	
	/**
	 * get all pay component group in payroll process
	 * @return list MHCPayComponentGroup
	 */
	/*
	public List<MHCPayComponentGroup> getPayComponentGroup() {
		
		StringBuffer whereClause = new StringBuffer(MHCPayrollProcessLine.COLUMNNAME_HC_PayrollProcess_ID +"=?");
		List<MHCPayrollProcessLine> list = new Query(getCtx(),MHCPayrollProcessLine.Table_Name,
				whereClause.toString(), get_TrxName())		
				.setParameters(new Object[]{get_ID()})
				.setOnlyActiveRecords(true)
				.list();
		
		List<MHCPayComponentGroup> listGroup = new ArrayList<MHCPayComponentGroup>();
		for (MHCPayrollProcessLine processLine : list) {
			MHCPayComponentGroup group = (MHCPayComponentGroup) processLine.getHC_PayComponentGroup();
			listGroup.add(group);
		}
		
		return listGroup;
	}*/
	
	/**
	 * get pay component from eligibility group
	 */
	/*
	private MHCPayComponent[] getPayComponentFromPayGroup(){
		
		// get from pay group - eligibility group - eligibility group line - pay component group - component group line
		MHCPayGroup payGroup = new MHCPayGroup(getCtx(), this.getHC_PayGroup_ID(), get_TrxName());
		MHCEligibilityGroup eligibilityGroup = new MHCEligibilityGroup(getCtx(), 
				payGroup.get_ValueAsInt("HC_EligibilityGroup_ID"), get_TrxName());
		return eligibilityGroup.getAllPayComponent();
		
	}*/
	
	/**
	 * get pay component from eligibility group
	 */
	/*
	private MHCPayComponent[] getPayComponentFromPayrollProcess(){
		
		MHCEligibilityGroup eligibilityGroup = new MHCEligibilityGroup(getCtx(), 
				this.getHC_EligibilityGroup_ID(), get_TrxName());
		return eligibilityGroup.getAllPayComponent();
		
	}*/
	
	/**
	 * 	Get movement
	 *	@param requery requery
	 *	@return lines
	 */
	public MHCMovement[] getMovements (boolean requery)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuilder whereClause = new StringBuilder();
		
		// For HR_Process:
		whereClause.append(MHCMovement.COLUMNNAME_HC_PayrollProcess_ID+"=?");
		params.add(this.getHC_PayrollProcess_ID());
		
		// Only Active Concepts
		whereClause.append(" AND EXISTS (SELECT 1 FROM HC_PayComponent c WHERE c.HC_PayComponent_ID=HC_Movement.HC_PayComponent_ID AND c.IsActive='Y')"); 
		
		// ORDER BY
		StringBuilder orderByClause = new StringBuilder();
		orderByClause.append("HC_Employee_ID, HC_Movement_ID");
		//
		List<MHCMovement> list = new Query (getCtx(), MHCMovement.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(orderByClause.toString())
		.list();
		
		return list.toArray(new MHCMovement[list.size()]);
	}

	/**
	 * Load HC_Movements and store them in a HC_PayComponent_ID->MHCMovement hashtable
	 * @param movements hashtable
	 * @param HC_Employee_ID
	 */
	private void loadMovements(Hashtable<Integer,MHCMovement> movements, boolean IsGlobalPayComponent)
	{
		final StringBuilder whereClause = new StringBuilder(MHCMovement.COLUMNNAME_HC_PayrollProcess_ID).append("=? AND ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(this.getHC_PayrollProcess_ID());
		
		if (IsGlobalPayComponent) {
			whereClause.append("IsGlobalPayComponent='Y'");
		} else {
			whereClause.append(MHCMovement.COLUMNNAME_HC_Employee_ID).append("=?");;
			params.add(m_employee.get_ID());
		}
		
		List<MHCMovement> list = new Query(getCtx(), MHCMovement.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOnlyActiveRecords(true)
		.list();

		for (MHCMovement mvm : list) {
			if(!movements.containsKey(mvm.getHC_PayComponent_ID())) {
				continue;
			}
			
			MHCMovement lastM = movements.get(mvm.getHC_PayComponent_ID());
			String columntype = lastM.getType();
			switch (columntype) {
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric : case MHCPayComponent.HC_PAYCOMPONENTTYPE_Formula :
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning : case MHCPayComponent.HC_PAYCOMPONENTTYPE_Deduction :
				mvm.setColumnValue(lastM.getHC_AmtValue());
				break;

			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Integer : 
				mvm.setColumnValue(lastM.getHC_QtyValue());
				break;

			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Date : 
				mvm.setColumnValue(lastM.getHC_DateValue());
				break;

			case MHCPayComponent.HC_PAYCOMPONENTTYPE_String : 
				mvm.setColumnValue(lastM.getHC_StringValue());
				break;

			case MHCPayComponent.HC_PAYCOMPONENTTYPE_YesNo :
				mvm.setColumnValue(lastM.isHC_BooleanValue());
				break;
			default :
				break;
			}
			movements.put(mvm.getHC_PayComponent_ID(), mvm);
		}
	}

	/**
	 * Execute the script
	 * @param AD_Rule_ID
	 * @param string 
	 * @return
	 */
	private Object executeScript(int AD_Rule_ID, String columnType)
	{
		MRule rulee = MRule.get(getCtx(), AD_Rule_ID);
		Object result = null;
		m_description = null;
		try
		{
			String text = "";
			if (rulee.getScript() != null)
			{
				text = rulee.getScript().trim().replaceAll("\\bget", "process.get")
				.replace(".process.get", ".get");
			}
			String resultType = "double";
			//@Stephan temporary comment
			//if  (MHRAttribute.COLUMNTYPE_Date.equals(columnType))
			//	resultType = "Timestamp";
			//else if  (MHRAttribute.COLUMNTYPE_Text.equals(columnType))
			//	resultType = "String";
			final String script =
				s_scriptImport.toString()
				+" " + resultType + " result = 0;"
				+" String description = null; "
				+ text;
			
			Scriptlet engine = new Scriptlet (Scriptlet.VARIABLE, script, m_scriptCtx);	
			Exception ex = engine.execute();
			if (ex != null)
			{
				throw ex;
			}
			result = engine.getResult(false);
			m_description = engine.getDescription();
		}
		catch (Exception e)
		{
			throw new AdempiereException("Execution error - @AD_Rule_ID@="+rulee.getValue());
		}
		return result;
	}

	/**
	 * create Movements for corresponding process , period
	 * @param MHCPayComponent
	 * @throws Exception
	 */
	
	public String createMovements() throws Exception
	{
		m_scriptCtx.clear();
		m_scriptCtx.put("process", this);
		m_scriptCtx.put("_Process", getHC_PayrollProcess_ID());
		m_scriptCtx.put("_Period", getC_Period_ID());

		log.info("info data - " + " Process: " +getHC_PayrollProcess_ID()+ ", Period: " +getC_Period_ID());
		
		// initialize for period
		m_period = new MPeriod(getCtx(), this.getC_Period_ID(), get_TrxName());
		if (m_period != null) {
			m_dateFrom = m_period.getStartDate();
			m_dateTo   = m_period.getEndDate();
			m_scriptCtx.put("_From", m_period.getStartDate());
			m_scriptCtx.put("_To", m_period.getEndDate());
		}

		// RE-Process, delete movement except concept type Incidence 
		clearMovement();

		//get employee list that will be processed 
		
		int eligibilityGroupID = 0;
		
		if (getHC_PayGroup_ID() <= 0){
			if (getHC_EligibilityGroup_ID() == 0)
				return "Process Aborted: Eligibility Group is mandatory";
			
			eligibilityGroupID = getHC_EligibilityGroup_ID();

		} else{
			MHCPayGroup payGroup = new MHCPayGroup(getCtx(), this.getHC_PayGroup_ID(), get_TrxName());
			if (payGroup.get_ValueAsInt("HC_EligibilityGroup_ID") == 0)
				return "Process Aborted: Eligibility Group is mandatory";
			
			eligibilityGroupID = payGroup.get_ValueAsInt("HC_EligibilityGroup_ID");			
		}
		
		if (getHC_ProcessList_ID()==0)
			return "Process Aborted: Process List is mandatory";
		
		ArrayList<Integer> payComponents = getProcessPayComponents(this.getHC_ProcessList_ID(), eligibilityGroupID);
		
		if (payComponents.isEmpty())
			return "Process Aborted: No Eligible Pay Components "; 
		
		int[] employeeIDs = getPayrollEmployee();
		
		if (employeeIDs.length == 0)
			return "Process Aborted: No Pay Group or Employee Selected";
		
		createGlobalMovements(m_period, payComponents);

		int retroActivePeriod_ID = -1;
		
		for (int employeeID : employeeIDs) {
			System.out.println("Employee: "+ employeeID);
			createEmployeeMovements(employeeID, m_period, payComponents);
			if(retroActivePeriod_ID > 0)
				createRetroActiveMovements(employeeID, retroActivePeriod_ID, payComponents);
			
		}
		
		return "Process Complete";

	} // createMovements

	private void createRetroActiveMovements(int employeeID,
			int retroActivePeriod_ID, ArrayList<Integer> payComponents) {

		// get periods from the start retroactive to one period before the current period
		MPeriod retroPeriod = new MPeriod(getCtx(), retroActivePeriod_ID, get_TrxName());
		MPeriod currentPeriod = new MPeriod(getCtx(), this.getC_Period_ID(), get_TrxName());
		MPeriod beforeCurrentPeriod = MPeriod.get(getCtx(), TimeUtil.addDays(currentPeriod.getStartDate(), -1), 0, get_TrxName());
		
		MPeriod[] periods = getPeriodBetween(retroPeriod.getStartDate(), beforeCurrentPeriod.getStartDate());
		BigDecimal retroActiveAmt = Env.ZERO;
		
		for (int payComponent_ID : payComponents) {
			for (MPeriod period : periods) {
				//get all processed movements for the period, incl global movements and employee specific movements
				//m_period = period;
				
				String sql = "SELECT mov.HC_Movement_ID FROM HC_Movement mov "
						+ "JOIN HC_PayrollProcess proc ON proc.HC_PayrollProcess_ID = mov.HC_PayrollProcess_ID "
						+ "WHERE proc.C_Period_ID=? AND mov.HC_Employee_ID=? AND mov.HC_PayComponent_ID=?";
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try{
					pstmt = DB.prepareStatement(sql, get_TrxName());
					pstmt.setInt(1, period.get_ID());
					pstmt.setInt(2, employeeID);
					pstmt.setInt(3, payComponent_ID);
					rs = pstmt.executeQuery();
					while(rs.next()){
						MHCMovement movement = new MHCMovement(getCtx(), rs.getInt(1), get_TrxName());
						retroActiveAmt = retroActiveAmt.add(movement.getHC_AmtValue());
					}
				}catch(Exception e){
					s_log.log(Level.SEVERE, sql, e);
				}finally{
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			}
			// create movement here, per pay component
			MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponent_ID, get_TrxName());
			log.info("Calculating Retroactive payComponent " + payComponent.getValue());
			
			m_Type = payComponent.getHC_PayComponentType();

			log.info("Pay Component - " + payComponent.getValue());
			
			MHCMovement movement = null;
			
			movement = new MHCMovement (getCtx(), 0, get_TrxName());
			movement.set_ValueOfColumn("IsTransferToGL", payComponent.get_ValueAsBoolean("IsTransferToGL"));
			movement.set_ValueOfColumn("IsPrinted", payComponent.get_ValueAsBoolean("IsPrinted"));
			movement.set_ValueOfColumn("IsPaid", payComponent.get_ValueAsBoolean("IsPaid"));
			movement.set_ValueOfColumn("IsGlobalPayComponent", payComponent.isGlobalPayComponent());
			movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
			movement.setHC_PayrollProcess_ID(getHC_PayrollProcess_ID());
			movement.setType(m_Type);
			movement.setAD_Rule_ID(payComponent.getAD_Rule_ID());
			movement.setAD_Org_ID(this.getAD_Org_ID());
			movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
			movement.setColumnValue(retroActiveAmt);
			movement.saveEx();
		}
	}

	/**
	 * create movement for global payroll components
	 * @param m_period
	 * @param payComponents
	 */
	private void createGlobalMovements(MPeriod m_period,
			ArrayList<Integer> payComponents) {
		
		m_GlobalMovement.clear();
		loadMovements(m_GlobalMovement,true);
		
		for (Integer payComponentID:payComponents) {
			MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponentID, get_TrxName());
			if (payComponent.isGlobalPayComponent() && !payComponent.getHC_PayComponentType().equalsIgnoreCase("IMP")) {
				createMovementFromPayComponent(payComponent);
			}
		}

		// Save movements:
		for (MHCMovement m: m_GlobalMovement.values()) {
			MHCPayComponent payComponent = new MHCPayComponent(getCtx(), m.getHC_PayComponent_ID(), get_TrxName());
			System.out.println(m.getAD_Org_ID());
			
			//MHCPayComponent c = (MHCPayComponent) m.getHC_PayComponent();
			if (m.isEmpty()) {	
				log.fine("Skip saving "+m);
			
			} else {			
				boolean saveThisRecord = true;     //m.get_ValueAsBoolean("IsTracked");
				if (saveThisRecord)
					m.saveEx();
			}
		}
	}
	

	/**
	 * Create employee movements
	 * @param HC_Employee_ID, MPeriod
	 * @throws Exception
	 */
	private void createEmployeeMovements(int HC_Employee_ID, MPeriod period, ArrayList<Integer> payComponents) throws Exception
	{
		
		m_employee = new MEmployee(getCtx(), HC_Employee_ID, get_TrxName());
		if(m_employee.getHC_Status().equals("PE")){
			m_employeeJob = new Query(getCtx(), MEmployeeJob.Table_Name, "HC_Employee_ID=? AND SeqNo=1 AND HC_Status='IA'", get_TrxName())
							.setParameters(new Object[]{HC_Employee_ID})
							.setOnlyActiveRecords(true)
							.setOrderBy("Created DESC")
							.first();
		}else{
			m_employeeJob = new Query(getCtx(), MEmployeeJob.Table_Name, "HC_Employee_ID=? AND SeqNo=1 AND HC_Status='A'", get_TrxName())
							.setParameters(new Object[]{HC_Employee_ID})
							.setOnlyActiveRecords(true)
							.first();
		}
		log.info("Calculate payroll movement fo employee: " + m_employee.getName());
		
		m_scriptCtx.remove("_DateStart");
		m_scriptCtx.remove("_DateEnd");	
		m_scriptCtx.remove("_Days");
		m_scriptCtx.remove("_C_BPartner_ID");
		//m_scriptCtx.put("_DateStart", m_employee.getStartDate());
		//m_scriptCtx.put("_DateEnd", m_employee.getEndDate() == null ? TimeUtil.getDay(2999, 12, 31) : m_employee.getEndDate());
		m_scriptCtx.put("_Days", org.compiere.util.TimeUtil.getDaysBetween(period.getStartDate(),period.getEndDate())+1);
		m_scriptCtx.put("_HC_Employee_ID", m_employee.get_ID());
		m_scriptCtx.put("_HC_EmployeeJob_ID", m_employeeJob.get_ID());

		m_EmployeeMovement.clear();
		loadMovements(m_EmployeeMovement, false);
		
		for (Integer payComponentID : payComponents) {	
			MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponentID, get_TrxName());
			if (!payComponent.isGlobalPayComponent())
				createMovementFromPayComponent(payComponent);
		}
		
		
		/*
		for(MHRPayrollConcept pc : linesConcept) // ==================================================== Concept
		{
			m_HC_PayComponent_ID      = pc.getHR_Concept_ID();
			MHRConcept concept = MHRConcept.get(getCtx(), m_HC_PayComponent_ID);
			boolean printed = pc.isPrinted() || concept.isPrinted();
			MHRMovement movement = m_movement.get(concept.get_ID()); // as it's now recursive, it can happen that the concept is already generated
			if (movement == null) {
				movement = createMovementFromConcept(concept, printed);
				movement = m_movement.get(concept.get_ID());
			}
			if (movement == null)
			{
				throw new AdempiereException("Concept " + concept.getValue() + " not created");
			}
		} // concept
		*/
		// Save movements:
		for (MHCMovement m: m_EmployeeMovement.values()) {
			//MHCPayComponent c = (MHCPayComponent) m.getHC_PayComponent();
			if (m.isEmpty()) {	
				log.fine("Skip saving "+m);
			
			} else {			
				boolean saveThisRecord = true;     //m.get_ValueAsBoolean("IsTracked");
				if (saveThisRecord)
					m.saveEx();
			}
		}
		
	}
	
	/**
	 * create movement with some parameter
	 * @param payComponent
	 * @param HC_Employee_ID
	 * @param bigdecimal value
	 * @param help
	 * @return MHCMovement
	 */
	/*
	private MHCMovement createMovement(MHCPayComponent payComponent,
			int p_HC_Employee_ID, double value, String help) {
		log.info("Calculating payComponent " + payComponent.getValue());
		m_Type = payComponent.getHC_PayComponentType();

		log.info("Concept - " + payComponent.getValue());
		MHCMovement movement = new MHCMovement (getCtx(), 0, get_TrxName());
		movement.setAD_Org_ID(this.getAD_Org_ID());
		movement.setHC_Employee_ID(p_HC_Employee_ID);
		movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
		movement.setHC_PayrollProcess_ID(getHC_PayrollProcess_ID());
		movement.setType(m_Type);
		movement.setAD_Rule_ID(payComponent.getAD_Rule_ID());
		movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
		movement.setHC_AmtValue(new BigDecimal(value));
		movement.setComments(help);
		
		movement.setProcessed(true);
		movement.saveEx();
		m_movement.put(payComponent.getHC_PayComponent_ID(), movement);
		return movement;
	}*/
	
	/**
	 * 
	 * @param payComponent
	 * @param printed
	 * @return
	 */
	private MHCMovement createMovementFromPayComponent(MHCPayComponent payComponent) {
		log.info("Calculating payComponent " + payComponent.getValue());
		//String name = payComponent.getValue();
		
		m_Type = payComponent.getHC_PayComponentType();

		log.info("Pay Component - " + payComponent.getValue());

		MHCMovement movement = null;

		if (!m_Type.equals(MHCPayComponent.HC_PAYCOMPONENTTYPE_Imported)) {
			movement = new MHCMovement (getCtx(), 0, get_TrxName());
			if (!payComponent.isGlobalPayComponent()) {
				movement.setHC_Employee_ID(m_employee.get_ID());
				movement.set_ValueOfColumn("HC_Org_ID", m_employeeJob.getHC_Org_ID());
				//@KevinY
				X_HC_EmployeeCategory empCategory = new X_HC_EmployeeCategory(getCtx(),0,get_TrxName());
				movement.set_ValueOfColumn(movement.COLUMNNAME_Description, empCategory.getName());
				movement.set_ValueOfColumn("HC_NIK", m_employee.get_ValueAsString("HC_NIK"));
				//@KevinY end
				
			}
			System.out.println(payComponent.getValue());
			movement.set_ValueOfColumn("IsTransferToGL", payComponent.get_ValueAsBoolean("IsTransferToGL"));
			movement.set_ValueOfColumn("IsPrinted", payComponent.get_ValueAsBoolean("IsPrinted"));
			movement.set_ValueOfColumn("IsPaid", payComponent.get_ValueAsBoolean("IsPaid"));
			movement.set_ValueOfColumn("IsGlobalPayComponent", payComponent.isGlobalPayComponent());
			movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
			movement.setHC_PayrollProcess_ID(getHC_PayrollProcess_ID());
			movement.setType(m_Type);
			movement.setAD_Rule_ID(payComponent.getAD_Rule_ID());
			movement.setAD_Org_ID(payComponent.getAD_Org_ID());
			movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
			movement.saveEx();
		}
		/*//@win
		movement.setHC_Job_ID(m_employee.getHC_Job_ID());
		movement.setIsPrinted(printed);
		movement.setIsRegistered(payComponent.isRegistered());
		*/
		boolean isHasAttribute = payComponent.isHasAttribute();
		/*
		boolean hasFormula = MHCPayComponent.HC_PAYCOMPONENTTYPE_Formula.equals(payComponent.getHC_PayComponentType()) |
				MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning.equals(payComponent.getHC_PayComponentType()) |
				MHCPayComponent.HC_PAYCOMPONENTTYPE_Deduction.equals(payComponent.getHC_PayComponentType());
		*/
		if (payComponent.getAD_Rule_ID() > 0) {
			//@comment: ask jorvan why must checking valid from paycomponent (BPJS_TK_JP)
			//check valid from pay component, if period end date after valid from then execute
			if((m_period.getEndDate().after(payComponent.getValidFrom())|| m_period.getEndDate().equals(payComponent.getValidFrom()))){
				log.info("Executing rule for payComponent " + payComponent.getValue());
				if (activePayComponentRule.contains(payComponent)) {
					throw new AdempiereException("Recursion loop detected in payComponent " + payComponent.getValue());
				}
				activePayComponentRule.add(payComponent);
				Object result = executeScript(payComponent.getAD_Rule_ID(), payComponent.getHC_PayComponentType());
				activePayComponentRule.remove(payComponent);
				if (result == null)
				{
					log.warning("Variable (result) is null");
					return movement;
				} else {
					movement.setColumnValue(result);
					movement.set_ValueNoCheck("IsTracked", "Y");
				}

				if (m_description != null)
					movement.setDescription(m_description.toString());
			}			
		} else switch (payComponent.getHC_PayComponentType()) {
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Integer : {
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.getHC_IntValue());
				MHCAttribute attribute = getAttribute(payComponent);
				movement.setColumnValue(attribute.getHC_IntValue());
				break;
			}
			
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric : {
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.getHC_NumValue());
				
				else if(!payComponent.isGlobalPayComponent()){
					BigDecimal result = getAttributeForNumeric(payComponent);
					movement.setColumnValue(result);
				}
				
				else{
					MHCAttribute attribute = getAttribute(payComponent);
					movement.setColumnValue(attribute.getHC_NumValue());
				}
				break;
			}
				
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Date : {
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.getHC_DateValue());
				MHCAttribute attribute = getAttribute(payComponent);
				movement.setColumnValue(attribute.getHC_DateValue());
				break;
			}
				
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_String : {
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.getHC_StringValue());
				MHCAttribute attribute = getAttribute(payComponent);
				movement.setColumnValue(attribute.getHC_StringValue());
				break;
			}
				
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_YesNo : {
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.isHC_BooleanValue());
				MHCAttribute attribute = getAttribute(payComponent);
				movement.setColumnValue(attribute.isHC_BooleanValue());
				break;
			} 
			
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Earning : {
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.getHC_NumValue());

				/*//@win temporary comment
				BigDecimal result = getAttributeForNumeric(payComponent);
				movement.setColumnValue(result);
				*/
				MHCAttribute attribute = getAttribute(payComponent);
				movement.setColumnValue(attribute.getHC_NumValue());
				
				break;
			}
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Deduction :{
				if(!isHasAttribute)
					movement.setColumnValue(payComponent.getHC_NumValue());

				/*//@win temporary comment
				BigDecimal result = getAttributeForNumeric(payComponent);
				movement.setColumnValue(result);
				*/
				
				MHCAttribute attribute = getAttribute(payComponent);
				movement.setColumnValue(attribute.getHC_NumValue());
				
				break;
			}
			case MHCPayComponent.HC_PAYCOMPONENTTYPE_Imported : {
				String whereClause = "HC_PayrollProcess_ID=? AND HC_PayComponent_ID=? AND HC_Employee_ID=?";
				int HC_ProcessImport_ID = new Query(getCtx(), MHCProcessImport.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{getHC_PayrollProcess_ID(), payComponent.get_ID(), m_employee.get_ID()})
											.setOnlyActiveRecords(true)
											.firstId();
				
				if (HC_ProcessImport_ID == -1) {
					break;
				} else {
					MHCProcessImport processImport = new MHCProcessImport(getCtx(), HC_ProcessImport_ID, get_TrxName());
					MEmployeeJob employeeJob = new Query(getCtx(), MEmployeeJob.Table_Name, "HC_Employee_ID=? AND SeqNo=1 AND HC_Status='A'", get_TrxName())
					.setParameters(new Object[]{processImport.getHC_Employee_ID()})
					.setOnlyActiveRecords(true)
					.first();
	
					movement = new MHCMovement(getCtx(), 0, get_TrxName());
					movement.setHC_Employee_ID(m_employee.get_ID());
					movement.setHC_PayComponent_ID(payComponent.getHC_PayComponent_ID());
					movement.setHC_PayrollProcess_ID(getHC_PayrollProcess_ID());
					movement.setType(processImport.getHC_PayComponent().getHC_PayComponentType());
					//movement.setAD_Rule_ID(processImport.getHC_PayComponent().getAD_Rule_ID());
					movement.setAD_Org_ID(this.getAD_Org_ID());
					
					if (employeeJob!=null)
						movement.setHC_Org_ID(employeeJob.getHC_Org_ID());
					
					movement.setIsGlobalPayComponent(false);
					movement.setIsPaid(payComponent.isPaid());
					movement.setIsPrinted(payComponent.isPrinted());
					movement.setIsTransferToGL(payComponent.isTransferToGL());
					movement.setC_Period_ID(getC_Period_ID());
					movement.setDateTrx((Timestamp)this.get_Value("DateTrx"));
					movement.set_ValueOfColumn("IsTracked", "Y");
					movement.setProcessed(false);
					
					switch (payComponent.get_ValueAsString("HC_ImportDataType")) {
					case "NUM": 
						if (processImport.getHC_AmtValue()!=null)
						movement.setHC_AmtValue(processImport.getHC_AmtValue());
						break;
						
					case "INT":
						if (processImport.getHC_QtyValue()!=null)
						movement.setHC_QtyValue(processImport.getHC_QtyValue());
						break;
						
					case "STR":
						if (processImport.getHC_StringValue()!=null)
						movement.setHC_StringValue(processImport.getHC_StringValue());
						break;
					
					case "DTE":
						if (processImport.getHC_DateValue()!=null)
						movement.setHC_DateValue(processImport.getHC_DateValue());
						break;
					}
					break;
				}
			}
		}
		//movement.setProcessed(true);
		if (movement==null)
			return movement;
		
		if (!payComponent.isGlobalPayComponent()) {
			m_EmployeeMovement.put(payComponent.getHC_PayComponent_ID(), movement);
		} else {
			m_GlobalMovement.put(payComponent.getHC_PayComponent_ID(), movement);			
		}
		return movement;
	}

	private ArrayList<Integer> getProcessPayComponents(int p_HC_ProcessList_ID, int p_HC_EligibilityGroup_ID ) {
		
		ArrayList<Integer> payComponentIDs = new ArrayList<Integer>();
		String query = "SELECT psl.HC_PayComponent_ID FROM HC_ProcessList pl " +
		"JOIN HC_ProcessListLine pll ON pll.HC_ProcessList_ID=pl.HC_ProcessList_ID " +
		"JOIN HC_ProcessSection ps ON ps.HC_ProcessSection_ID=pll.HC_ProcessSection_ID " +
		"JOIN HC_ProcessSectionLine psl ON psl.HC_ProcessSection_ID=ps.HC_ProcessSection_ID " +
		"WHERE psl.HC_PayComponent_ID IN " +
			"(SELECT pcgl.HC_PayComponent_ID FROM HC_EligibilityGroup eg " +
			"JOIN HC_EligibilityGroupLine egl ON egl.HC_EligibilityGroup_ID=eg.HC_EligibilityGroup_ID " + 
			"JOIN HC_PayComponentGroup pcg ON pcg.HC_PayComponentGroup_ID=egl.HC_PayComponentGroup_ID " +
			"JOIN HC_PayComponentGroupLine pcgl ON pcgl.HC_PayComponentGroup_ID=pcg.HC_PayComponentGroup_ID " +
			"WHERE eg.HC_EligibilityGroup_ID=" + p_HC_EligibilityGroup_ID + ") " +
		"AND pl.HC_ProcessList_ID=" + p_HC_ProcessList_ID + " " +
		"ORDER BY pll.seqno, psl.seqno ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(query, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				payComponentIDs.add(rs.getInt(1));
			}
			
		} catch (SQLException e){
			log.log(Level.SEVERE, query, e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	
		return payComponentIDs;
		
	}
	/**
	 * get attribute
	 * @param pay component, component type
	 * @return MHCAttribute
	 */
	public MHCAttribute getAttribute(MHCPayComponent payComponent){
		MPeriod period = (MPeriod) this.getC_Period();
		//Timestamp currentDateFrom = period.getStartDate();
		Timestamp currentDateTo = period.getEndDate();
		
			
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=? AND "
//				+ MHCAttribute.COLUMNNAME_HC_PayComponentType+"=? " //@win temporary comment
						+ MHCAttribute.COLUMNNAME_ValidFrom+" <= ? ");
				
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(payComponent.get_ID());
		params.add(currentDateTo);
		
		if (!payComponent.isGlobalPayComponent()) {
			whereClause.append(" AND " + MHCAttribute.COLUMNNAME_HC_Employee_ID+"=?");
			params.add(m_employee.get_ID());
		}
		
		int HC_Attribute_ID = new Query(getCtx(), MHCAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(params)
		.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom + " DESC")
		.firstId();
		
		MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
		return attribute;
	}

	/**
	 * get attribute
	 * @param pay component, component type
	 * @return MHCAttribute
	 */
	public BigDecimal getAttributeForNumeric(MHCPayComponent payComponent){
		
		MPeriod period = new MPeriod(getCtx(), this.getC_Period_ID(), get_TrxName());
		
		Timestamp workDateFrom = period.getStartDate();
		Timestamp workDateTo = period.getEndDate();
		
		boolean newJoin = false;
		
		MEmployee employee = new MEmployee(getCtx(), m_employee.get_ID(), get_TrxName());
		MEmployeeJob job = employee.getEmployeeJob();
		
		if(job.getHC_WorkStartDate().after(workDateFrom) || job.getHC_WorkStartDate() == workDateFrom)
			newJoin = true;
		
		List<MHCAttribute> list = new ArrayList<>();
		
		// this code for collect attribute, and split into new join or not
		if(newJoin){
			// if new join, change work date from to work start date from employee job
			int HC_EmployeeClass_ID = job.getHC_EmployeeClass_ID();
			X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), HC_EmployeeClass_ID, get_TrxName());
		
			//for employee outsource must change work date from to work start date from employee job to calculate reduction 
			//for employee permanent not calculate reduction and not change work date from
			if(!empClass.get_ValueAsBoolean("IsDifferentPayCalculation"))
				workDateFrom = job.getHC_WorkStartDate();
			
			
			StringBuilder whereClause = new StringBuilder();
			whereClause.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=? AND "
					+ MHCAttribute.COLUMNNAME_ValidFrom+" BETWEEN ? AND ?");
			
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(payComponent.get_ID());
			params.add(workDateFrom);
			params.add(workDateTo);
			
			if (!payComponent.isGlobalPayComponent()) {
				whereClause.append(" AND " + MHCAttribute.COLUMNNAME_HC_Employee_ID+"=?");
				params.add(m_employee.get_ID());
			}
			
			int[] HC_Attribute_IDs = new Query(getCtx(), MHCAttribute.Table_Name, whereClause.toString(), get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setParameters(params)
			.getIDs();
			
			for (int HC_Attribute_ID : HC_Attribute_IDs) {
				MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
				list.add(attribute);
			}
		}
		
		else{
			// else, use query for get attribute between period and get last attribute before start period
			// but if on the valid from first attribute same with start date period, 
			// don't get last attribute before start period (skip with if else later, not with query)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT HC_Attribute_ID FROM HC_Attribute WHERE HC_PayComponent_ID=? AND "		//1
			+ MHCAttribute.COLUMNNAME_ValidFrom+" < ? ORDER BY "+ MHCAttribute.COLUMNNAME_ValidFrom		//2
			+ " DESC LIMIT 1"
			+ " UNION"
			+ " SELECT HC_Attribute_ID FROM HC_Attribute WHERE HC_PayComponent_ID=? AND"				//3
			+ MHCAttribute.COLUMNNAME_ValidFrom+">=? AND "+ MHCAttribute.COLUMNNAME_ValidFrom+"<=?"		//4..5
			+" ORDER BY "+MHCAttribute.COLUMNNAME_ValidFrom);
			
			if (!payComponent.isGlobalPayComponent()) {
				sb = new StringBuilder();
				sb.append("SELECT HC_Attribute_ID FROM (SELECT HC_Attribute_ID FROM HC_Attribute "
						+ "WHERE HC_PayComponent_ID=? AND ValidFrom>=? AND ValidFrom<=? "
						+ "AND HC_Employee_ID=? ORDER BY ValidFrom)b");
			}
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
				if(!payComponent.isGlobalPayComponent()){
					pstmt.setInt(1, payComponent.get_ID());
					pstmt.setTimestamp(2, workDateFrom);
					pstmt.setTimestamp(3, workDateTo);
					pstmt.setInt(4, m_employee.get_ID());
				}
				else{
					pstmt.setInt(3, payComponent.get_ID());
					pstmt.setTimestamp(4, workDateFrom);
					pstmt.setTimestamp(5, workDateTo);
				}
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					int HC_Attribute_ID = rs.getInt(1);
					MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
					list.add(attribute);
				}
			}catch(Exception e){
				log.severe("Cant load attribute");
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			String whereClause = "HC_PayComponent_ID=? AND ValidFrom < ? AND HC_Employee_ID=? ";
			int currentAttributeID = new Query(getCtx(), MHCAttribute.Table_Name, whereClause, get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setParameters(new Object[]{payComponent.get_ID(), workDateFrom, m_employee.get_ID()})
			.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom+" DESC")
			.firstId();
			
			if(currentAttributeID > 0){
				MHCAttribute currentAttribute = new MHCAttribute(getCtx(), currentAttributeID, get_TrxName());
				list.add(currentAttribute);
			}
		}
		
		// this code for calculate, split into prorate or not
		BigDecimal retValue = Env.ZERO;
		// if list just have many attribute, it must be prorate
		if(list.size() > 1){
			List<MHCAttribute> activeAttribute = new ArrayList<MHCAttribute>();
			Timestamp validTo = null;
			for (MHCAttribute attribute : list) {
				if(validTo == null){
					validTo = attribute.getValidFrom();
					attribute.setValidTo(TimeUtil.addDays(workDateTo, 1));
					activeAttribute.add(attribute);
					continue;
				}
				else if(validTo != null){
					attribute.setValidTo(TimeUtil.addDays(validTo, -1));
					activeAttribute.add(attribute);
				}
			}
			
			retValue = Env.ZERO;
			for (MHCAttribute attribute : activeAttribute) {
				
				Timestamp validDateFrom = attribute.getValidFrom();
				Timestamp validDateTo = attribute.getValidTo();
				
				BigDecimal numberOfDaysInMonth = new BigDecimal(TimeUtil.getDaysBetween(workDateFrom, TimeUtil.addDays(workDateTo, 1)));
				BigDecimal numValue = Env.ZERO;
				int day = 0;
				
				if(validDateFrom.before(workDateFrom) && validDateTo.after(workDateTo))
					day = TimeUtil.getDaysBetween(workDateFrom, workDateTo)+1;
				
				else if(validDateFrom.before(workDateFrom) && validDateTo.before(workDateTo) && validDateTo.after(workDateFrom))
					day = TimeUtil.getDaysBetween(workDateFrom, validDateTo)+1;
				
				else if(validDateFrom.after(workDateFrom) && validDateTo.after(workDateTo) && validDateFrom.before(workDateTo))
					day = TimeUtil.getDaysBetween(validDateFrom, workDateTo)+1;
				
				else if(validDateFrom.after(workDateFrom) && validDateTo.before(workDateTo))
					day = TimeUtil.getDaysBetween(validDateFrom, validDateTo)+1;
				
				else
					continue;
				
				numValue = attribute.getHC_NumValue().divide(numberOfDaysInMonth,2,RoundingMode.HALF_UP);
				numValue = numValue.multiply(new BigDecimal(day));
				numValue = numValue.setScale(2, RoundingMode.HALF_UP);
				retValue = retValue.add(numValue);
			}
		}
		// else, just use last attribute
		else{
			for (MHCAttribute attribute : list) {
				retValue = attribute.getHC_NumValue();
			}
			if(newJoin){
				int workDay = TimeUtil.getDaysBetween(workDateFrom, workDateTo)+1;
				workDateFrom = period.getStartDate();
				workDateTo = period.getEndDate();
				int day = TimeUtil.getDaysBetween(workDateFrom, workDateTo)+1;
				retValue = retValue.multiply(new BigDecimal(workDay)).divide(new BigDecimal(day),2,RoundingMode.HALF_UP);
			}
		}
		
		return retValue;
	}
	
	/**
	 * get attribute
	 * @param pay component, component type
	 * @return MHCAttribute
	 */
	/*
	public BigDecimal getAttributeForNumeric(MHCPayComponent payComponent){
		
		// for example cut off 25-04-2017, it will be calculate from 26-03-2017 to 25-04-2017
		
		MPeriod period = new MPeriod(getCtx(), this.getC_Period_ID(), get_TrxName());
		
		Timestamp workDateFrom = period.getStartDate();
		Timestamp workDateTo = period.getEndDate();
		
		boolean isProrate = false;
		
		//get the first attribute
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=? AND "
				+ MHCAttribute.COLUMNNAME_ValidFrom+" <= ? ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(payComponent.get_ID());
		params.add(workDateTo);
		
		if (!payComponent.isGlobalPayComponent()) {
			whereClause.append(" AND " + MHCAttribute.COLUMNNAME_HC_Employee_ID+"=?");
			params.add(m_employee.get_ID());
		}
		
		
		MHCAttribute firstAttribute = new Query(getCtx(), MHCAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(params)
		.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom+" DESC ")
		.first();
		
		firstAttribute.setValidFrom(workDateFrom);
		
		
		//get the other attributes if avail
		StringBuilder whereClauseOthers = new StringBuilder();
		whereClauseOthers.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=? AND "
				+ MHCAttribute.COLUMNNAME_ValidFrom+" BETWEEN ? AND ?");
		
		ArrayList<Object> params2 = new ArrayList<Object>();
		params2.add(payComponent.get_ID());
		params2.add(workDateFrom);
		params2.add(workDateTo);
		
		if (!payComponent.isGlobalPayComponent()) {
			whereClauseOthers.append(" AND " + MHCAttribute.COLUMNNAME_HC_Employee_ID+"=?");
			params2.add(m_employee.get_ID());
		}
		
		List<MHCAttribute> attributeOthers = new Query(getCtx(), MHCAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(params)
		.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom+" DESC ")
		.list();
		
		
		if(attributeOthers.size() > 0)
			isProrate = true;
		
		BigDecimal retValue = Env.ZERO;
		
		if(isProrate){
			//commented by @win
			// get current attribute before first attribute
			whereClause = new StringBuilder();
			whereClause.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=? AND "
					+ MHCAttribute.COLUMNNAME_ValidFrom+" <= ? ");
			
			int HC_CurrentAttribute_ID = new Query(getCtx(), MHCAttribute.Table_Name, whereClause.toString(), get_TrxName())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.setParameters(new Object[]{payComponent.get_ID(), workDateFrom})
			.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom+" DESC ")
			.firstId();
			
			List<Integer> allAttribute_IDs = new ArrayList<>();
			
			for (Integer HC_Attribute_ID : HC_Attribute_IDs) {
				allAttribute_IDs.add(HC_Attribute_ID);
			}
			allAttribute_IDs.add(HC_CurrentAttribute_ID);
			
			 //end commented by @win
			
			// this prorate calculation begin here
			// set valid to per attribute
			// loop per attribute
			// get next attribute with query valid from greater than current attribute order by valid from ascending
			ArrayList<MHCAttribute> attributeList = new ArrayList<MHCAttribute>();

			int i=0;
			for (MHCAttribute attributeOther: attributeOthers) {
				if (i==0) {
					firstAttribute.setValidTo(attributeOther.getValidFrom());
					attributeList.add(firstAttribute);
				}
			}
			
			Timestamp validTo = null;
			for (Integer HC_Attribute_ID : attributeOthers) {
				MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
				if(validTo == null){
					validTo = attribute.getValidFrom();
					attribute.setValidTo(TimeUtil.addDays(workDateTo, 1));
					listAtt.add(attribute);
					continue;
				}
				else if(validTo != null){
					attribute.setValidTo(TimeUtil.addDays(validTo, -1));
					listAtt.add(attribute);
				}
			}
			
			retValue = Env.ZERO;
			for (MHCAttribute attribute : listAtt) {
				
				Timestamp validDateFrom = attribute.getValidFrom();
				Timestamp validDateTo = attribute.getValidTo();
				
				//BigDecimal numberOfDaysInMonth = new BigDecimal(TimeUtil.getNumberOfDaysInMonth(workDateFrom));
				BigDecimal numberOfDaysInMonth = new BigDecimal(TimeUtil.getDaysBetween(workDateFrom, workDateTo));
				BigDecimal numValue = Env.ZERO;
				int day = 0;
				
				if(validDateFrom.before(workDateFrom) && validDateTo.after(workDateTo))
					day = TimeUtil.getDaysBetween(workDateFrom, workDateTo)+1;
				
				else if(validDateFrom.before(workDateFrom) && validDateTo.before(workDateTo) && validDateTo.after(workDateFrom))
					day = TimeUtil.getDaysBetween(workDateFrom, validDateTo)+1;
				
				else if(validDateFrom.after(workDateFrom) && validDateTo.after(workDateTo) && validDateFrom.before(workDateTo))
					day = TimeUtil.getDaysBetween(validDateFrom, workDateTo)+1;
				
				else if(validDateFrom.after(workDateFrom) && validDateTo.before(workDateTo))
					day = TimeUtil.getDaysBetween(validDateFrom, validDateTo)+1;
				
				else
					continue;
				
				numValue = attribute.getHC_NumValue().divide(numberOfDaysInMonth,2,RoundingMode.HALF_UP);
				numValue = numValue.multiply(new BigDecimal(day));
				numValue = numValue.setScale(2, RoundingMode.HALF_UP);
				retValue = retValue.add(numValue);
			}
			
		}
		
		// for !prorate, use current 
		else {
			whereClause = new StringBuilder();
			whereClause.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID
					+ "=? AND " + MHCAttribute.COLUMNNAME_ValidFrom + " <= ? ");

			int HC_CurrentAttribute_ID = new Query(getCtx(),
					MHCAttribute.Table_Name, whereClause.toString(),
					get_TrxName())
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(new Object[] { payComponent.get_ID(), workDateFrom })
					.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom + " DESC ")
					.firstId();

			MHCAttribute attribute = new MHCAttribute(getCtx(), HC_CurrentAttribute_ID, get_TrxName());
			retValue = retValue.add(attribute.getHC_NumValue());
		}
		
		/* @Stephan temporary comment
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=? AND "
		//		+ MHCAttribute.COLUMNNAME_HC_PayComponentType+"=? " //@win temporary comment
				+ MHCAttribute.COLUMNNAME_ValidFrom+" <= ? ");
		
		int[] HC_Attribute_IDs = new Query(getCtx(), MHCAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{payComponent.get_ID(), 
		//		MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric, //@win temporary comment 
				workDateTo})
		.setOrderBy(MHCAttribute.COLUMNNAME_ValidFrom)
		.getIDs();
		
		Stack<MHCAttribute> stackAtt = new Stack<>();
		for (int HC_Attribute_ID : HC_Attribute_IDs) {
			MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
			stackAtt.add(attribute);
		}
		
		Stack<MHCAttribute> stackAtt1 = stackAtt;
		MHCAttribute lastAtt = stackAtt.get(stackAtt.size()-1);
		
		// container of attribute
		List<MHCAttribute> listAtt = new ArrayList<MHCAttribute>();
		
		for (MHCAttribute att : stackAtt) {
			for (MHCAttribute att1 : stackAtt1) {
				if(att.getHC_Attribute_ID() == lastAtt.getHC_Attribute_ID()){
					att.setValidTo(TimeUtil.addDays(att.getValidFrom(), 1));
					break;
				}
				if(att.getHC_Attribute_ID() == att1.getHC_Attribute_ID()){
					stackAtt1.remove(att1);
					continue;
				}
				att.setValidTo(TimeUtil.addDays(att1.getValidFrom(), -1));
				listAtt.add(att);
				stackAtt.remove(att);
				break;
			}
		}
		
		double retValue = 0;
		for (MHCAttribute attribute : listAtt) {
			
			Timestamp validDateFrom = attribute.getValidFrom();
			Timestamp validDateTo = attribute.getValidTo();
			
			//BigDecimal numberOfDaysInMonth = new BigDecimal(TimeUtil.getNumberOfDaysInMonth(workDateFrom));
			BigDecimal numberOfDaysInMonth = new BigDecimal(TimeUtil.getDaysBetween(workDateFrom, workDateTo));
			double numValue = 0;
			int day = 0;
			
			if(validDateFrom.before(workDateFrom) && validDateTo.after(workDateTo))
				day = TimeUtil.getDaysBetween(workDateFrom, workDateTo);
			
			else if(validDateFrom.before(workDateFrom) && validDateTo.before(workDateTo) && validDateTo.after(workDateFrom))
				day = TimeUtil.getDaysBetween(workDateFrom, validDateTo);
			
			else if(validDateFrom.after(workDateFrom) && validDateTo.after(workDateTo) && validDateFrom.before(workDateTo))
				day = TimeUtil.getDaysBetween(validDateFrom, workDateTo);
			
			else if(validDateFrom.after(workDateFrom) && validDateTo.before(workDateTo))
				day = TimeUtil.getDaysBetween(validDateFrom, validDateTo);
			
			else
				continue;
			
			numValue = attribute.getHC_NumValue().divide(numberOfDaysInMonth,2,RoundingMode.HALF_UP).doubleValue();
			numValue = numValue * day;
			retValue += numValue;
		}*/
		
		//return retValue;
	//}
	
	// Helper methods -------------------------------------------------------------------------------

	/**
	 * 
	 * Helper Method : get the value of the concept
	 * @param payrollComponent
	 * @return
	 */
	public double getPayComponent(String payrollComponent)
	{	
		
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payrollComponent.trim());

		if (payComponent == null) {   
			throw new AdempiereException("Oh no! " + payrollComponent + " does not exist. Please create it first in Payroll Component");
		}

		MHCMovement m = null;
		
		if (isRetroactive) {
			m = m_RetroMovement.get(payComponent.get_ID());
			if (m==null)
				return 0;
			
		} else {
			if (payComponent.isGlobalPayComponent()) {
				m = m_GlobalMovement.get(payComponent.get_ID());
				if (m == null) {
					createMovementFromPayComponent(payComponent);
					m = m_GlobalMovement.get(payComponent.get_ID());
					if(m==null) return 0;
				}
				
			} else {
				m = m_EmployeeMovement.get(payComponent.get_ID());
				if (m == null) {
					createMovementFromPayComponent(payComponent);
					m = m_EmployeeMovement.get(payComponent.get_ID());
					if(m==null) return 0;
				}
			}
		}
//		if (m == null) {
//			throw new AdempiereException("Concept " + payComponent.getValue() + " not created");
//		}
		
		String type = m.getType();
		if (MHCMovement.TYPE_Numeric.equals(type) || MHCMovement.TYPE_Earning.equals(type) 
				|| MHCMovement.TYPE_Deduction.equals(type) || MHCMovement.TYPE_Formula.equals(type) || "IMP".equals(type)) {
			return m.getHC_AmtValue().doubleValue();
			
		} else if (MHCMovement.TYPE_Integer.equals(type)) {
			return m.getHC_QtyValue().doubleValue();
					
		} else {
			log.severe("Not Support data type while get component");
 			return 0;
		}
		

	} // getPayComponent

	/**
	 * Helper Method : get the value of the concept
	 * @param payrollComponent
	 * @return
	 */
	public boolean getPayComponentBoolean(String payrollComponent)
	{
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payrollComponent.trim());

		if (payComponent == null) {   
			throw new AdempiereException("Oh no! " + payrollComponent + " does not exist. Please create it first in Payroll Component");
		}

		MHCMovement m = null;
		
		if (isRetroactive) {
			m = m_RetroMovement.get(payComponent.get_ID());	
			
		} else {
			if (payComponent.isGlobalPayComponent()) {
				m = m_GlobalMovement.get(payComponent.get_ID());
				if (m == null) {
					createMovementFromPayComponent(payComponent);
					m = m_GlobalMovement.get(payComponent.get_ID());
				}
				
			} else {
				m = m_EmployeeMovement.get(payComponent.get_ID());
				if (m == null) {
					createMovementFromPayComponent(payComponent);
					m = m_EmployeeMovement.get(payComponent.get_ID());
				}
			}
		}
		
		if (m == null)
			return false;
		
		String type = m.getType();
		if (MHCMovement.TYPE_YesNo.equals(type) || "IMP".equals(type)) {
			return m.isHC_BooleanValue();
					
		} else {
			log.severe("Error: Type for Pay Component " + payComponent.getValue() + " is not boolean");
			return false;
		}
		

	} // getPayComponentBoolean
	
	/**
	 * get base salary
	 * @param payroll component, employee, current period
	 * @return base salary
	 */
	public double getBaseSalary(String payrollComponent){
		
		double retValue = 0;
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payrollComponent.trim());

		if (payComponent == null)
		{   
			throw new AdempiereException("Oh no! " + payrollComponent + " does not exist. Please create it first in Payroll Component");
		}
		
		MEmployeeJob employeeJob = m_employee.getEmployeeJob();
		List<MJobDataChange> activeJobDataChanges = new ArrayList<MJobDataChange>();
		MJobDataChange[] jobDataChanges = employeeJob.getJobDataChanges();
		for (MJobDataChange jobDataChange : jobDataChanges) {
			// skip if date null and base salary null
			if((jobDataChange.getEffectiveDateFrom() == null || jobDataChange.get_Value("HC_WorkEndDate") == null) 
					&& jobDataChange.getHC_Compensation1().compareTo(Env.ZERO)<=0)
				continue;
			
			Timestamp effectiveDateFrom = jobDataChange.getEffectiveDateFrom();
			Timestamp workEndDate = (Timestamp) jobDataChange.get_Value("HC_WorkEndDate");
			
			MPeriod effectivePeriodFrom = MPeriod.get(getCtx(), effectiveDateFrom, 0, get_TrxName());
			MPeriod effectivePeriodTo = MPeriod.get(getCtx(), workEndDate, 0, get_TrxName());
			
			if(effectivePeriodFrom.get_ID() == m_period.get_ID() 
					|| effectivePeriodTo.get_ID() == m_period.get_ID())
				activeJobDataChanges.add(jobDataChange);
		}
		
		Timestamp currentStartDate = m_period.getStartDate();
		Timestamp currentEndDate = m_period.getEndDate();
		
		double totalBaseSalary = 0;
		for (MJobDataChange jobDataChange : activeJobDataChanges) {
			
			Timestamp effectiveDateFrom = jobDataChange.getEffectiveDateFrom();
			Timestamp workEndDate = (Timestamp) jobDataChange.get_Value("HC_WorkEndDate");
			BigDecimal numberOfDaysInMonth = new BigDecimal(TimeUtil.getNumberOfDaysInMonth(currentStartDate));
			double baseSalary = 0;
			int day = 0;
			
			if(effectiveDateFrom.before(currentStartDate) && workEndDate.after(currentEndDate))
				day = TimeUtil.getDaysBetween(currentStartDate, currentEndDate);
			
			else if(effectiveDateFrom.before(currentStartDate) && workEndDate.before(currentEndDate))
				day = TimeUtil.getDaysBetween(currentStartDate, workEndDate);
			
			else if(effectiveDateFrom.after(currentStartDate) && workEndDate.after(currentEndDate))
				day = TimeUtil.getDaysBetween(effectiveDateFrom, currentEndDate);
			
			else if(effectiveDateFrom.after(currentStartDate) && workEndDate.before(currentEndDate))
				day = TimeUtil.getDaysBetween(effectiveDateFrom, workEndDate);
			
			baseSalary = jobDataChange.getHC_Compensation1().divide(numberOfDaysInMonth,2,RoundingMode.HALF_UP).doubleValue();
			baseSalary = baseSalary * day;
			totalBaseSalary += baseSalary;
		}
		retValue = totalBaseSalary;
		
		return retValue;
	}
	
	/**
	 * get back pay for employee
	 * @return back pay
	 */
	/*
	public double getBackPay(String payrollComponent){
		
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payrollComponent.trim());

		if (payComponent == null)
		{   
			throw new AdempiereException("Oh no! " + payrollComponent + " does not exist. Please create it first in Payroll Component");
		}
		//
		
		StringBuilder help = new StringBuilder();
		
		MEmployee employee = m_employee;
		int HC_Employee_ID = employee.get_ID();
		BigDecimal sumBackPay = Env.ZERO;
		
		// get created movement with possibly back pay
		int firstPeriod = 0;
		for (MHCPayrollTrigger payrollTrigger : getPayrollTrigerByPeriod(employee.get_ID())) {
			firstPeriod = payrollTrigger.getC_Period_ID();
			break;
		}
		
		if(firstPeriod == 0)
			return 0;
		
		int lastPeriod = getLastPeriod(HC_Employee_ID);
		int[] periodBetween = getPeriodBetween(firstPeriod, lastPeriod);
		help.append("RETROACTIVE#"+firstPeriod+"-"+lastPeriod);
		
		for (int period_id : periodBetween) {
			
			// validate end date trigger
			validatePayrollTrigger(period_id);
			
			MHCPayrollTrigger[] triggers = getPayrollTrigger(HC_Employee_ID, period_id);
			
			int count = 0;
			for (MHCPayrollTrigger trigger : triggers) {
				count += 1;
				Timestamp dateFrom = trigger.getEffectiveDateFrom();
				Timestamp dateTo = trigger.getEffectiveDateTo();
				Timestamp firstDateInMonth = TimeUtil.getMonthFirstDay(dateFrom);
				boolean isProrate = dateFrom.after(firstDateInMonth);
				
				if(isProrate && count == 1 ){
					int day = TimeUtil.getDaysBetween(firstDateInMonth, dateFrom);
					int dayInMonth = TimeUtil.getNumberOfDaysInMonth(dateFrom);
					
					BigDecimal firstValue = new BigDecimal(trigger.getOldValue());
					BigDecimal firstBackPay = firstValue.multiply(new BigDecimal(day))
							.divide(new BigDecimal(dayInMonth),2,RoundingMode.HALF_UP);
						
					day = TimeUtil.getDaysBetween(dateFrom, dateTo);
					BigDecimal secondValue = new BigDecimal(trigger.getNewValue());
					BigDecimal secondBackPay = secondValue.multiply(new BigDecimal(day))
							.divide(new BigDecimal(dayInMonth),2,RoundingMode.HALF_UP);
						
					BigDecimal diffAmt = (firstBackPay.add(secondBackPay)).subtract(firstValue);
					sumBackPay = sumBackPay.add(diffAmt);
					
					help.append(" || "+count+".FIRST_AND_PRORATE_10#"+firstDateInMonth+"_TO_"+dateFrom+"#"+firstBackPay)
						.append("_20#"+dateFrom.toString().substring(0, 10)+"_TO_"+dateTo.toString().substring(0,10)+"#"+secondBackPay)
						.append("_30#CURRENT#"+firstValue)
						.append("_40#SUM#"+diffAmt);
				}else{
					BigDecimal diffAmt = trigger.getDifferenceAmt();
					sumBackPay = sumBackPay.add(diffAmt);
					
					help.append(" || "+count+".STANDARD_ACCUM#"+diffAmt);
				}
			
				// processed true if used
				trigger.setHC_PayrollProcess_ID(this.getHC_PayrollProcess_ID());
				trigger.setProcessed(true);
				trigger.saveEx();
				
				help.append(System.getProperty("line.separator"));
				
			}
			
			//end for
		}
		
		double retValue = sumBackPay.doubleValue();
		
		//
		MHCMovement m = m_movement.get(payComponent.get_ID());
		if (m == null) {
			createMovement(payComponent, HC_Employee_ID, retValue, help.toString());
			m = m_movement.get(payComponent.get_ID());
		}
		if (m == null)
		{
			throw new AdempiereException("Concept " + payComponent.getValue() + " not created");
		}
		//
		
		log.info(help.toString());
		
		return sumBackPay.doubleValue();
	}
	*/
	
	/**
	 * validate effective date to
	 */
	/*
	public void validatePayrollTrigger(int C_Period_ID){
		
		int HC_Employee_ID = m_employee.get_ID();
		
		int count = 0;
		Timestamp dateTo = null;
		for (MHCPayrollTrigger trigger : getPayrollTriggerDesc(HC_Employee_ID, C_Period_ID)) {
			count += 1;
			if(count == 1){
				dateTo = TimeUtil.getMonthLastDay(trigger.getEffectiveDateFrom());
			}
			trigger.setEffectiveDateTo(dateTo);
			trigger.saveEx();
			dateTo = trigger.getEffectiveDateFrom();
		}
		
	}*/
	
	/**
	 * get period between
	 * @param first period id, last period id
	 * @return array of period id
	 * @deprecated
	 */
	public int[] getPeriodBetween(int firstPeriod, int lastPeriod){
		
		String where = COLUMNNAME_C_Period_ID+">="+firstPeriod
				+ " AND "+COLUMNNAME_C_Period_ID+"<="+lastPeriod;
		
		int[] period_ids = new Query(getCtx(), MPeriod.Table_Name, where, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.getIDs();
		
		return period_ids;
	}
	
	/**
	 * get period between
	 * @param first period id, last period id
	 * @return array of period id
	 */
	public MPeriod[] getPeriodBetween(Timestamp dateStart, Timestamp dateEnd){
		List<MPeriod> list = new ArrayList<MPeriod>();
		String sql = "SELECT C_Period_ID FROM C_Period per "
				+ "JOIN C_Year ye ON ye.C_Year_ID = per.C_Year_ID "
				+ "JOIN C_Calendar cal ON cal.C_Calendar_ID = ye.C_Calendar_ID "
				+ "WHERE IsHCCalendar = ? "
				+ "AND per.StartDate BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setBoolean(1, true);
			pstmt.setTimestamp(2, dateStart);
			pstmt.setTimestamp(3, dateEnd);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MPeriod period = new MPeriod(getCtx(), rs.getInt(1), get_TrxName());
				list.add(period);
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, sql, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		MPeriod[] periods = new MPeriod[list.size()];
		list.toArray(periods);
		
		return periods;
	}
	
	/**
	 * get last period id from payroll process
	 * @param HC_Employee_ID
	 * @return last period id
	 */
	/*
	public int getLastPeriod(int HC_Employee_ID){
		
		String where = COLUMNNAME_HC_Employee_ID +"="+HC_Employee_ID;
		int id = new Query(getCtx(), MHCPayrollProcess.Table_Name, where, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setOrderBy("C_Period_ID DESC")
		.firstId();
		
		MHCPayrollProcess payrollProcess = new MHCPayrollProcess(getCtx(), id, get_TrxName());
		
		return payrollProcess.getC_Period_ID();
	}*/
	
	/**
	 * get payroll trigger with distinct period id
	 * @param HC_Employee_ID
	 * @return array of MPayrollTrigger
	 */
	public MHCPayrollTrigger[] getPayrollTrigerByPeriod(int HC_Employee_ID){
	
		List<MHCPayrollTrigger> list = new ArrayList<>();
		
		String sql = "SELECT DISTINCT C_Period_ID, HC_PayrollTrigger_ID "
				+ "FROM HC_PayrollTrigger "
				+ "WHERE HC_Employee_ID = ? "
				+ "AND AD_Client_ID = ? "
				+ "AND Processed = ? "
				+ "ORDER BY C_Period_ID ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, HC_Employee_ID);
			pstmt.setInt(2, getAD_Client_ID());
			pstmt.setString(3, "N");
			rs = pstmt.executeQuery();
			while(rs.next()){
				int HC_PayrollTrigger_ID = rs.getInt(2);
				MHCPayrollTrigger payrollTrigger = new MHCPayrollTrigger(getCtx(), HC_PayrollTrigger_ID, get_TrxName());
				list.add(payrollTrigger);
			}
		} catch(Exception e){
			log.warning("ERROR getPayrollTrigerByPeriod : "+e.toString());
		
		} finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		MHCPayrollTrigger[] payrollTrigger = new MHCPayrollTrigger[list.size()];
		list.toArray(payrollTrigger);
		
		return payrollTrigger;
	}
	
	/**
	 * get payroll trigger with parameter period id
	 * @param HC_Employee_ID, C_Period_ID
	 * @return array of MPayrollTrigger
	 */
	/*
	public MHCPayrollTrigger[] getPayrollTrigger(int HC_Employee_ID, int C_Period_ID){
		String where = COLUMNNAME_HC_Employee_ID+"="+HC_Employee_ID
				+" AND "+COLUMNNAME_Processed+"='N'"
				+" AND "+COLUMNNAME_C_Period_ID+"="+C_Period_ID;
		List<MHCPayrollTrigger> list = new Query(getCtx(), MHCPayrollTrigger.Table_Name, where, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setOrderBy(MHCPayrollTrigger.COLUMNNAME_EffectiveDateFrom)
		.list();
		
		MHCPayrollTrigger[] payrollTrigger = new MHCPayrollTrigger[list.size()];
		list.toArray(payrollTrigger);
		
		return payrollTrigger;
	}
	*/
	
	/**
	 * get payroll trigger order by effective date from descending
	 * @param HC_Employee_ID
	 * @return array of MPayrollTrigger
	 */
	/*
	public MHCPayrollTrigger[] getPayrollTriggerDesc(int HC_Employee_ID, int C_Period_ID){
		String where = COLUMNNAME_HC_Employee_ID+"="+HC_Employee_ID
				+" AND "+COLUMNNAME_Processed+"='N'"
				+" AND "+COLUMNNAME_C_Period_ID+"="+C_Period_ID;
		
		List<MHCPayrollTrigger> list = new Query(getCtx(), MHCPayrollTrigger.Table_Name, where, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setOrderBy(MHCPayrollTrigger.COLUMNNAME_EffectiveDateFrom+" DESC")
		.list();
		
		MHCPayrollTrigger[] payrollTrigger = new MHCPayrollTrigger[list.size()];
		list.toArray(payrollTrigger);
		
		return payrollTrigger;
	}*/
	
	/**
	 * Helper Method : get the value of the concept string type
	 * @param payComponentString
	 * @return
	 */
	public String getpayComponentString (String payComponentString)
	{
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentString.trim());

		if (payComponent == null) {
			log.severe("Component is null");
			return null;
		}

		MHCMovement m = m_EmployeeMovement.get(payComponent.get_ID());
		if (m == null) {
			createMovementFromPayComponent(payComponent);
			m = m_EmployeeMovement.get(payComponent.get_ID());
		}
		
		String type = m.getType();
		if (MHCMovement.TYPE_String.equals(type)) {
			return m.getHC_StringValue();
		}
		else {
			log.severe("Not supported type :"+type);
			return null;
		}
	} // getpayComponentString

	/**
	 * Helper Method : get the value of the concept date type
	 * @param payComponentDate
	 * @return
	 */
	public Timestamp getPayComponentDate (String payComponentDate)
	{
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentDate.trim());

		if (payComponent == null) {
			log.severe("Component is null");
			return null;
		}

		MHCMovement m = m_EmployeeMovement.get(payComponent.get_ID());
		if (m == null) {
			createMovementFromPayComponent(payComponent);
			m = m_EmployeeMovement.get(payComponent.get_ID());
		}
		
		String type = m.getType();
		if (MHCMovement.TYPE_Date.equals(type)) {
			return m.getHC_DateValue();
		}
		else {
			log.severe("Not supported type :"+type);
			return null;
		}

	} // getPayComponentDate

	/**
	 * Helper Method : sets the value of a concept
	 * @param payComponentValue
	 * @param value
	 */
	public void setPayComponent (String payComponentValue, double value)
	{
		try
		{
			MHCPayComponent c = MHCPayComponent.forValue(getCtx(), payComponentValue); 
			if (c == null)
			{
				log.severe("Component is null");
				return;
			}
			MHCMovement m = new MHCMovement(getCtx(), 0, get_TrxName());
			m.setType(c.getHC_PayComponentType());
			m.setColumnValue(BigDecimal.valueOf(value));
			m.setHC_PayrollProcess_ID(getHC_PayrollProcess_ID());
			m.setHC_PayComponent_ID(m_HC_PayComponent_ID);
			m.setHC_Employee_ID(m_HC_PayComponent_ID);
			m.setDescription("Added From Rule");
			m.saveEx();
		} 
		catch(Exception e)
		{
			s_log.warning(e.getMessage());
		}
	} // setPayComponent

	/**
	 * 
	 * @return
	 */
	public double getCalculateSalary() {
		BigDecimal number = new BigDecimal("1000");
		
		return number.doubleValue();
	}
	/**
	 * Helper Method : Concept for a range from-to in periods.
	 * Periods with values of 0 -1 1, etc. actual previous one period, next period
	 * 0 corresponds to actual period.
	 * @param payComponentValue concept key(value)
	 * @param periodFrom the search is done by the period value, it helps to search from previous years
	 * @param periodTo
	 */
	public double getPayComponent (String payComponentValue, int periodFrom, int periodTo)
	{
		return getPayComponent(payComponentValue, null, periodFrom, periodTo);
	} // getPayComponent

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  @param payComponentValue 
	 *  @param pFrom 
	 *  @param pTo the search is done by the period value, it helps to search from previous years
	 *  @param payrollValue is the value of the payroll.
	 */
	public double getPayComponent(String payComponentValue, String payrollValue,int periodFrom,int periodTo)
	{
	
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentValue);
		if (payComponent == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHCPayComponent.HC_PAYCOMPONENTTYPE_Integer.equals(payComponent.getHC_PayComponentType())) {
			fieldName = MHCMovement.COLUMNNAME_HC_QtyValue;
		}
		else if (MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric.equals(payComponent.getHC_PayComponentType())) {
			fieldName = MHCMovement.COLUMNNAME_HC_AmtValue;
		}
		else {
			log.warning("Not supported field");
			return 0;
		}
		//
		MPeriod p = MPeriod.get(getCtx(), getC_Period_ID());
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuilder whereClause = new StringBuilder();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND " + MHCMovement.COLUMNNAME_HC_PayComponent_ID + "=?");
		params.add(payComponent.get_ID());
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HC_PayrollProcess p"
				+" INNER JOIN C_Period pr ON (pr.C_Period_id=p.C_Period_ID)"
				+" WHERE HC_Movement.HC_PayrollProcess_ID = p.HC_PayrollProcess_ID" 
				+" AND p.HC_PayrollProcess_ID=?");

		params.add(get_ID());
		if (periodFrom < 0) {
			whereClause.append(" AND pr.PeriodNo >= ?");
			params.add(p.getPeriodNo() +periodFrom);
		}
		if (periodTo > 0) {
			whereClause.append(" AND pr.PeriodNo <= ?");
			params.add(p.getPeriodNo() +periodTo);
		}
		whereClause.append(")");
		//
		StringBuilder sql = new StringBuilder("SELECT COALESCE(SUM(").append(fieldName).append("),0) FROM ").append(MHCMovement.Table_Name)
		.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(get_TrxName(), sql.toString(), params);
		return value.doubleValue();

	} // getPayComponent

	/**
	 * 
	 * @param payComponentValue
	 * @param from
	 * @param to
	 * @return
	 */
	
	public double getPayComponent (String payComponentValue, Timestamp from,Timestamp to) {
		
		MHCPayComponent concept = MHCPayComponent.forValue(getCtx(), payComponentValue);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHCPayComponent.HC_PAYCOMPONENTTYPE_Integer.equals(concept.getHC_PayComponentType())) {
			fieldName = MHCMovement.COLUMNNAME_HC_QtyValue;
			
		} else if (MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric.equals(concept.getHC_PayComponentType())) {
			fieldName = MHCMovement.COLUMNNAME_HC_AmtValue;
			
		} else {
			log.warning("Not supported field");
			return 0;
		}
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuilder whereClause = new StringBuilder();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND " + MHCMovement.COLUMNNAME_HC_PayComponent_ID + "=?");
		params.add(concept.get_ID());
		//Adding dates 
		whereClause.append(" AND validTo BETWEEN ? AND ?");
		params.add(from);
		params.add(to);
		//
		//
		StringBuilder sql = new StringBuilder("SELECT COALESCE(SUM(").append(fieldName).append("),0) FROM ").append(MHCMovement.Table_Name)
								.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(get_TrxName(), sql.toString(), params);
		return value.doubleValue();
		
	} // getPayComponent
	
	/**
	 * Helper Method: gets Concept value of payrroll(s) between 2 dates
	 * if payrollValue is null then sum all payrolls between 2 dates
	 * if dates range are null then set them based on first and last day of period
	 * @param pConcept
	 * @param from
	 * @param to
	 * */
	public double getPayComponentRangeOfPeriod (String payComponentValue, String dateFrom, String dateTo) {
				
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentValue);
		if (payComponent == null)
			return 0.0;
		
		Timestamp from = null;
		Timestamp to = null;

		if (dateFrom != null)
			from = Timestamp.valueOf(dateFrom);
		if (dateTo != null)
			to = Timestamp.valueOf(dateTo);
		
		// Detect field name
		final String fieldName;
		if (MHCPayComponent.HC_PAYCOMPONENTTYPE_Integer.equals(payComponent.getHC_PayComponentType())) {
			fieldName = MHCMovement.COLUMNNAME_HC_QtyValue;
		
		} else if (MHCPayComponent.HC_PAYCOMPONENTTYPE_Numeric.equals(payComponent.getHC_PayComponentType())) {
			fieldName = MHCMovement.COLUMNNAME_HC_AmtValue;
		
		} else {
			log.warning("Not supported field");
			return 0;
		}
		//
		MPeriod p = MPeriod.get(getCtx(), getC_Period_ID());
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND " + MHCMovement.COLUMNNAME_HC_PayComponent_ID + "=?");
		params.add(payComponent.get_ID());
		//Adding dates 
		whereClause.append(" AND validTo BETWEEN ? AND ?");
		if (from == null)
			from = getFirstDayOfPeriod(p.getC_Period_ID());
		if (to == null)
			to = getLastDayOfPeriod(p.getC_Period_ID());
		
		params.add(from);
		params.add(to);
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(").append(fieldName).append("),0) FROM ").append(MHCMovement.Table_Name)
								.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(get_TrxName(), sql.toString(), params);
		return value.doubleValue();
		
	} // getConceptRangeOfPeriod

	/**
	 * 	Helper Method : Get the number of days between start and end, in Timestamp format
	 *  @param date1 
	 *  @param date2
	 *  @return no. of days
	 */ 
	public int getDays (Timestamp date1, Timestamp date2) {		
		// adds one for the last day
		return org.compiere.util.TimeUtil.getDaysBetween(date1,date2) + 1;
	} // getDays

	/**
	 * 	Helper Method : Get the number of days between start and end, in String format
	 *  @param date1 
	 *  @param date2
	 *  @return no. of days
	 */  
	public  int getDays (String date1, String date2) {		
		Timestamp dat1 = Timestamp.valueOf(date1);
		Timestamp dat2 = Timestamp.valueOf(date2);
		return getDays(dat1, dat2);
	}  // getDays

	/**
	 * 	Helper Method : Get Months, Date in Format Timestamp
	 *  @param start
	 *  @param end
	 *  @return no. of month between two dates
	 */ 
	public int getMonths(Timestamp startParam,Timestamp endParam) {
		
		boolean negative = false;
		Timestamp start = startParam;
		Timestamp end = endParam;
		if (end.before(start)) {
			negative = true;
			Timestamp temp = start;
			start = end;
			end = temp;
		}

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();

		calEnd.setTime(end);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);

		if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR)) {
			if (negative)
				return (calEnd.get(Calendar.MONTH) - cal.get(Calendar.MONTH)) * -1;
			return calEnd.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
		}

		//	not very efficient, but correct
		int counter = 0;
		while (calEnd.after(cal)) {
			cal.add (Calendar.MONTH, 1);
			counter++;
		}
		if (negative)
			return counter * -1;
		
		return counter;
		
	} // getMonths	

	/**
	 * Helper Method : get days from specific period
	 * @param period
	 * @return no. of days
	 */
	public double getDays (int period) {
		/* TODO: @win replace this code
		log.warning("instead of using getDays in the formula it's recommended to use _DaysPeriod+1");
		return Env.getContextAsInt(getCtx(), "_DaysPeriod") + 1;
		*/
		return 0;
		
	} // getDays
	
	/**
	 * Helper Method : get actual period
	 * @param N/A
	 * @return period id
	 */
	public int getPayrollPeriod () {
			
		MPeriod p = MPeriod.get(getCtx(), getC_Period_ID());
		return p.getC_Period_ID();
		
	} // getPayrollPeriod


	/**
	 * Helper Method : get first date from specific period
	 * @param period
	 * @return date from
	 */
	public Timestamp getFirstDayOfPeriod (int period_id) {
		
		MPeriod period = new MPeriod(getCtx(), period_id, get_TrxName());
		Calendar firstdayofperiod = Calendar.getInstance();
		Timestamp datefromofperiod = period.getStartDate();
		firstdayofperiod.setTime(datefromofperiod);
		firstdayofperiod.set(Calendar.DAY_OF_MONTH, 1);
		datefromofperiod.setTime(firstdayofperiod.getTimeInMillis());
		return datefromofperiod;
		
	} // getFirstDayOfPeriod

	/**
	 * Helper Method : get last date to specific period
	 * @param period
	 * @return date to
	 */
	public Timestamp getLastDayOfPeriod (int period_id) {
		
		MPeriod period = new MPeriod(getCtx(), period_id, get_TrxName());
		Calendar firstdayofperiod = Calendar.getInstance();
		Timestamp datetoofperiod = period.getEndDate();
		firstdayofperiod.setTime(datetoofperiod);
		firstdayofperiod.set(Calendar.DAY_OF_MONTH, firstdayofperiod.getActualMaximum(Calendar.DAY_OF_MONTH));
		datetoofperiod.setTime(firstdayofperiod.getTimeInMillis());
		return datetoofperiod;

	} // getLastDayOfPeriod

	/**
	 * Helper Method : get first year date from specific period
	 * @param period
	 * @return date from
	 */
	public Timestamp getFirstDayOfPeriodYear (int period_id) {
		
		MPeriod period = new MPeriod(getCtx(), period_id, get_TrxName());
		Calendar firstdayofperiod = Calendar.getInstance();
		Timestamp datefromofperiod = period.getStartDate();
		firstdayofperiod.setTime(datefromofperiod);
		firstdayofperiod.set(Calendar.DAY_OF_YEAR, 1);
		datefromofperiod.setTime(firstdayofperiod.getTimeInMillis());
		return datefromofperiod;
		
	} // getFirstDayOfPeriodYear

	/**
	 * Helper Method : get last year date to specific period
	 * @param period
	 * @return date to
	 */
	public Timestamp getLastDayOfPeriodYear (int period_id) {
		
		MPeriod period = new MPeriod(getCtx(), period_id, get_TrxName());
		Calendar firstdayofperiod = Calendar.getInstance();
		Timestamp datetoofperiod = period.getEndDate();
		firstdayofperiod.setTime(datetoofperiod);
		firstdayofperiod.set(Calendar.DAY_OF_YEAR, firstdayofperiod.getActualMaximum(Calendar.DAY_OF_YEAR));
		datetoofperiod.setTime(firstdayofperiod.getTimeInMillis());
		return datetoofperiod;

	} // getLastDayOfPeriodYear

	/**
	 * Helper Method : get first history date from specific period
	 * @param period, servicedate, months
	 * @return date from
	 */
	public Timestamp getFirstDayOfPeriodHistory (int period_id, Timestamp servicedate, Integer months) {
		
		if (months == null)
			months = 12;
		
		MPeriod period = new MPeriod(getCtx(), period_id, get_TrxName());
		Calendar firstdayofhistory = Calendar.getInstance();
		Timestamp datefromofhistory = period.getStartDate();
		firstdayofhistory.setTime(datefromofhistory);
		firstdayofhistory.add(Calendar.MONTH, months * -1);
		firstdayofhistory.set(Calendar.DAY_OF_MONTH, 1);
		datefromofhistory.setTime(firstdayofhistory.getTimeInMillis());
		
		if (servicedate != null && datefromofhistory.before(servicedate))
			return servicedate;
		
		return datefromofhistory;
		
	} // getFirstDayOfPeriodHistory

	/**
	 * Helper Method : get first history date from specific period
	 * @param period, servicedate, months
	 * @return date to
	 */
	public Timestamp getLastDayOfPeriodHistory (int period_id, Timestamp servicedate, Integer months) {

		if (months == null)
			months = 1;
		
		MPeriod period = new MPeriod(getCtx(), period_id, get_TrxName());
		Calendar lastdayofhistory = Calendar.getInstance();
		Timestamp datetoofhistory = period.getStartDate();
		lastdayofhistory.setTime(datetoofhistory);
		lastdayofhistory.add(Calendar.MONTH, months * -1);
		lastdayofhistory.set(Calendar.DAY_OF_MONTH, lastdayofhistory.getActualMaximum(Calendar.DAY_OF_MONTH));
		datetoofhistory.setTime(lastdayofhistory.getTimeInMillis());
		
		if (servicedate != null && datetoofhistory.before(servicedate))
			return servicedate;
		
		return datetoofhistory;
		
	} // getLastDayOfPeriodHistory
	
	/**
	 * Helper Method : get timestamp date
	 * @param sdate
	 * @return sdate Timestamp
	 */
	public Timestamp getStringToTimestamp (String sdate) {
		return Timestamp.valueOf(sdate);
	} // getStringToTimestamp

	/**
	 * Helper Method : get string date
	 * @param tsdate
	 * @return tsdate String
	 */
	public String getTimestampToString (Timestamp tsdate) {
		return tsdate.toString();
	} // getTimestampToString

	/**
	 * get process employee
	 * @return array of process employee id
	 */
	public int[] getPayrollEmployee() {
		
		if (getHC_PayGroup_ID() > 0) {
			MHCPayGroup payGroup = new MHCPayGroup(getCtx(), getHC_PayGroup_ID(), get_TrxName());
			return payGroup.getEmployeeIDs();
		
		} else {		
			String whereClause = "HC_Employee_ID IN (SELECT HC_Employee_ID FROM HC_ProcessEmployee WHERE HC_PayrollProcess_ID=?)";
			int[] result = new Query(getCtx(), MEmployee.Table_Name, whereClause, get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(new Object[]{getHC_PayrollProcess_ID()})
			.getIDs();
			
			return result;
		}
	}
	
	/**
	 * get process import
	 * @return array of process import id
	 */
	public int[] getProcessImport(){
		
		String whereClause = MHCProcessImport.COLUMNNAME_HC_PayrollProcess_ID+"=?";
		int[] result = new Query(getCtx(), MHCProcessImport.Table_Name, whereClause, get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{getHC_PayrollProcess_ID()})
		.getIDs();
		
		return result;
		
	}
	
	/**
	 * get process import
	 * @return array of process import id
	 */
	public int[] getProcessEmployee(){
		
		String whereClause = MHCProcessEmployee.COLUMNNAME_HC_PayrollProcess_ID+"=?";
		int[] result = new Query(getCtx(), MHCProcessEmployee.Table_Name, whereClause, get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{getHC_PayrollProcess_ID()})
		.getIDs();
		
		return result;
		
	}
	
	/**
	 * create movement from process import for selected employee and period
	 * @param p_HC_Employee_ID
	 * @param p_C_Period_ID
	 * @return
	 */
	/*
	private String createMovementFromImport() {
		
		for (int HC_ProcessImport_ID : getProcessImport()) {
			

		}
		return "";
		
	}*/
	
	/**
	 * delete movement related with HC_PayrollProcess_ID
	 */
	private void clearMovement(){
		int no = DB.executeUpdateEx("DELETE FROM HC_Movement m WHERE HC_PayrollProcess_ID=?",
				new Object[]{getHC_PayrollProcess_ID()},
				get_TrxName());
		log.info("HC_Movement deleted #"+ no);
	}
	
	public double getMapValueDouble(String payComponentMap, String key) {
		double result = 0.0;
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentMap.trim());		
		MHCAttribute attribute = getAttribute(payComponent);
		
		String whereClause = MHCAttributeMap.COLUMNNAME_HC_Attribute_ID+"=? AND " + MHCAttributeMap.COLUMNNAME_HC_AtrributeMapKey+ "=?";
		
		MHCAttributeMap attributeMap = new Query(getCtx(), MHCAttributeMap.Table_Name,whereClause, get_TrxName())
											.setOnlyActiveRecords(true)
											.setParameters(new Object[] {attribute.get_ID(), key.toUpperCase()})
											.first();
		
		if (attributeMap == null)
			result = 0.0;
		else result = attributeMap.getHC_ValueNumeric().doubleValue();
		
		return result;
	
	}
	public Timestamp getEmployeeData(String columnName) {
		return (Timestamp) m_employee.get_Value(columnName);
	}

	public String getEmployeeClass() {
		return m_employeeJob.getHC_EmployeeClass().getValue();
	}
	public String getJobLevelValue() {
		int jobLevelID = m_employeeJob.getHC_Job().getHC_JobLevel_ID();		
		X_HC_JobLevel jobLevel = new X_HC_JobLevel(getCtx(), jobLevelID, get_TrxName());
		String result = jobLevel.getValue();
		return result;
		
	}
	
	public String getJobFunctionValue() {
		String result = m_employeeJob.getHC_Job().getHC_JobFunction().getValue();		
		return result;
		
	}
	
	public int getMonth(){
		
		MPeriod period = new MPeriod(getCtx(), this.getC_Period_ID(), get_TrxName());
		return TimeUtil.getMonth(period.getEndDate());
	}
	
	public int getWorkPeriodInMonth(){
		Timestamp dateFrom = m_employee.getHC_WorkStartDate();
		Timestamp dateTo = this.getC_Period().getEndDate();
		return TimeUtil.getMonthsBetween(dateFrom, dateTo)+1;
	}
	
	public int getWorkPeriodInYear(){
		int year = m_employee.get_ValueAsInt("HC_WorkPeriodDate");
		return year;
	}
	
	public int getYear(){
		MPeriod period = new MPeriod(getCtx(), this.getC_Period_ID(), get_TrxName());
		return TimeUtil.getYear(period.getEndDate());
	}
	
	public int getEmployeeStartMonth(){
		MEmployeeJob job = m_employee.getEmployeeJob();
		return TimeUtil.getMonth(job.getHC_WorkStartDate());
	}
	
	public int getEmployeeStartYear(){
		MEmployeeJob job = m_employee.getEmployeeJob();
		return TimeUtil.getYear(job.getHC_WorkStartDate());
	}
	
	public String getTaxStatus(){
		int HC_TaxStatus_ID = m_employee.getHC_TaxStatus_ID();
		X_HC_TaxStatus taxStatus = new X_HC_TaxStatus(getCtx(), HC_TaxStatus_ID, get_TrxName());
		return taxStatus.getValue();
	}
	
	public double getRoundDown(double amt, int scale){
		
		BigDecimal bd = new BigDecimal(amt);
		bd = bd.setScale(scale, RoundingMode.HALF_DOWN);
		
		return bd.doubleValue();
	}
	
	public double getRoundUp(double amt, int scale){
		
		BigDecimal bd = new BigDecimal(amt);
		bd = bd.setScale(scale, RoundingMode.HALF_UP);
		
		return bd.doubleValue();
	}
	
	
	
	public double getAccumValueDouble(String payComponentValue){
		
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentValue.trim());
		
		MPeriod period = MPeriod.get(getCtx(), this.getC_Period_ID());
		
		if(TimeUtil.getMonth(period.getEndDate()) == 0)
			return 0;
		
		Timestamp currentDate = TimeUtil.addDays(period.getStartDate(), -1);
		int C_Calendar_ID = period.getC_Calendar_ID();
		//@KevinY
		period = MPeriod.findByCalendar(getCtx(), currentDate, C_Calendar_ID, get_TrxName()); 
		//period = MPeriod.get(getCtx(), currentDate, 0, get_TrxName());
		//@KevinY end
		
		int month = TimeUtil.getMonth(currentDate);
		BigDecimal totalAmtValue = Env.ZERO;
		
		while(month != 11){
			BigDecimal amtValue = Env.ZERO;
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT mov.HC_AmtValue FROM HC_Movement mov "
					+ "JOIN HC_PayrollProcess proc ON proc.HC_PayrollProcess_ID = mov.HC_PayrollProcess_ID "
					+ "WHERE mov.HC_Employee_ID=? AND mov.HC_PayComponent_ID = ? AND proc.C_Period_ID = ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
				pstmt.setInt(1, m_employee.get_ID());
				pstmt.setInt(2, payComponent.get_ID());
				pstmt.setInt(3, period.get_ID());
				rs = pstmt.executeQuery();
				if(rs.next()){
					amtValue = rs.getBigDecimal(1);
				}
			}catch(Exception e){
				s_log.log(Level.SEVERE, sb.toString(), e);
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			totalAmtValue = totalAmtValue.add(amtValue);
			currentDate = TimeUtil.addDays(period.getStartDate(), -1);
			//@KevinY
			period = MPeriod.findByCalendar(getCtx(), currentDate, C_Calendar_ID, get_TrxName()); 
			//period = MPeriod.get(getCtx(), currentDate, 0, get_TrxName());
			//@KevinY end
			month = TimeUtil.getMonth(currentDate);
		}
		
		return totalAmtValue.doubleValue();
	}
	
	public double getValueDoubleFromMovement(String payComponentValue, int day, int month, int year){
		
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentValue);
		// get period from month and year
		//@KevinY
		month = month + 1;
		//@KevinY end
		Timestamp date = TimeUtil.getDay(year, month, day);
		int HC_Calendar_ID = new Query(getCtx(), MCalendar.Table_Name, "IsHCCalendar='Y'", get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.firstId();
		
		MPeriod period = MPeriod.findByCalendar(getCtx(), date, HC_Calendar_ID, get_TrxName());
		//@KevinY 
		/*int HC_PayrollProcess_ID = new Query(getCtx(), MHCPayrollProcess.Table_Name, "HC_PayrollProcess.C_Period_ID=? AND hpm.HC_Employee_ID = ?", get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.addJoinClause("INNER JOIN HC_ProcessEmployee hpm ON hpm.HC_PayrollProcess_ID = HC_PayrollProcess.HC_PayrollProcess_ID")
		.setParameters(new Object[]{period.get_ID(),m_employee.get_ID()})
		.firstId();
		*/
		int HC_PayrollProcess_ID = new Query(getCtx(), MHCPayrollProcess.Table_Name, "HC_PayrollProcess.C_Period_ID=? AND hpl.IsMonthlySalary='Y'", get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.addJoinClause("INNER JOIN HC_ProcessList hpl ON hpl.HC_ProcessList_ID = HC_PayrollProcess.HC_ProcessList_ID")
		.setParameters(new Object[]{period.get_ID()})
		.firstId();
		//@KevinY end
		
		String whereClause = "HC_PayrollProcess_ID=? AND HC_PayComponent_ID=? AND HC_Employee_ID=?";
		int HC_Movement_ID = new Query(getCtx(), MHCMovement.Table_Name, whereClause, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{HC_PayrollProcess_ID, payComponent.get_ID(), m_employee.get_ID()})
		.firstId();
		
		double retValue = 0;
		
		if(HC_Movement_ID > 0){
			MHCMovement movement = new MHCMovement(getCtx(), HC_Movement_ID, get_TrxName());
			retValue = movement.getHC_AmtValue().doubleValue();
		}
		
		return retValue;
	}
	
	public boolean getIsSanctions(){
		boolean isSanctions = m_employee.get_ValueAsBoolean("IsSanctions");
		return isSanctions;
	}
	
	public boolean getIsMemberKoperasi(){
		boolean isMemberKoperasi = m_employeeJob.get_ValueAsBoolean("MemberKoperasi");
		return isMemberKoperasi;
	}
	
	/*
	 * @KevinY 
	 */
	
	public int getEmployeeDataStartMonth(){
		if(m_employee.get_Value(MEmployee.COLUMNNAME_HC_WorkStartDate) == null)
			return 0;
		else
			return TimeUtil.getMonth(m_employee.getHC_WorkStartDate());
	}
	
	public int getEmployeeDataStartYear(){
		if(m_employee.get_Value(MEmployee.COLUMNNAME_HC_WorkStartDate) == null)
			return 0;
		else
			return TimeUtil.getYear(m_employee.getHC_WorkStartDate());
	}
	
	public int getEmployeeDataEndMonth(){
		if(m_employee.get_Value(MEmployee.COLUMNNAME_HC_WorkEndDate) == null)
			return 0;
		else
			return TimeUtil.getMonth(m_employee.getHC_WorkEndDate());
	}
	
	public int getEmployeeDataEndYear(){
		if(m_employee.get_Value(MEmployee.COLUMNNAME_HC_WorkEndDate) == null)
			return 0;
		else
			return TimeUtil.getYear(m_employee.getHC_WorkEndDate());
	}
	
	public double getRoundDownFull(double amt, int scale){
		
		BigDecimal bd = new BigDecimal(amt);
		bd = bd.setScale(scale, RoundingMode.DOWN);
		
		return bd.doubleValue();
	}
	
	public double getRoundUpFull(double amt, int scale){
		
		BigDecimal bd = new BigDecimal(amt);
		bd = bd.setScale(scale, RoundingMode.UP);
		
		return bd.doubleValue();
	}
	
	public String getSanctionRule(){
		String sql = "SELECT sr.Value FROM HC_Sanction_Rule sr INNER JOIN HC_Sanctions s "
				+ "ON sr.HC_Sanction_Rule_ID = s.HC_Sanction_Rule_ID "
				+ "WHERE s."+ X_HC_Sanctions.COLUMNNAME_HC_Employee_ID + "= ? AND "
				+ "s." + X_HC_Sanctions.COLUMNNAME_IsActive + "= 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, m_employee.get_ID());
			rs = pstmt.executeQuery();
			while(rs.next()){
				return rs.getString(1);
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, sql, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return "";
	}
	
	public boolean getIsMarriageAllowance(){
		boolean isMarriageAllowance = m_employee.get_ValueAsBoolean("IsMarriageAllowance");
		return isMarriageAllowance;
	}
	
	public int getMaritalDay(){
		GregorianCalendar cal		= new GregorianCalendar(Language.getLoginLanguage().getLocale());
		Timestamp HC_MaritalDate 	= (Timestamp)m_employee.get_Value("HC_MaritalDate");
		
		if(HC_MaritalDate == null)
			return 0;
		
		cal.setTimeInMillis(HC_MaritalDate.getTime());
		int day = cal.get(Calendar.DATE);
		return day;
	}
	
	public int getMaritalMonth(){
		Timestamp HC_MaritalDate = (Timestamp)m_employee.get_Value("HC_MaritalDate");
		
		if(HC_MaritalDate == null)
			return 0;
		
		int month = TimeUtil.getMonth(HC_MaritalDate);
		return month;
	}
	
	public int getMaritalYear(){
		Timestamp HC_MaritalDate = (Timestamp)m_employee.get_Value("HC_MaritalDate");
		if(HC_MaritalDate == null)
			return 0;
		
		int year = TimeUtil.getYear(HC_MaritalDate);
		return year;
	}
	
	public String getContractEducation(){
		if(m_employeeJob.getHC_Status().equals(MEmployeeJob.HC_STATUS_Active) 
				&& m_employeeJob.get_ValueAsBoolean("IsActive") 
				&& m_employeeJob.get_Value("ContractEducation") != null
				&& m_employeeJob.getSeqNo() == 1){
			return m_employeeJob.get_ValueAsString("ContractEducation");
		}else{
			return "";
		}
	}
	
	public double getAccumAllPeriod(String PayComponent){
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PayComponent.trim());
		
		MPeriod period = MPeriod.get(getCtx(), this.getC_Period_ID());
		
		if(TimeUtil.getMonth(period.getEndDate()) == 0)
			return 0;
		
		Timestamp currentDate = period.getStartDate();
		int C_Calendar_ID = period.getC_Calendar_ID();
		period = MPeriod.findByCalendar(getCtx(), currentDate, C_Calendar_ID, get_TrxName()); 
			
		int Curr_year = TimeUtil.getYear(period.getStartDate());
		int Check_year = TimeUtil.getYear(period.getStartDate());
		BigDecimal totalAmtValue = Env.ZERO;
		
		//get totalAmtValue from period until period of january
		while(Curr_year == Check_year){
			BigDecimal amtValue = Env.ZERO;
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT SUM(mov.HC_AmtValue) FROM HC_Movement mov "
					+ "JOIN HC_PayrollProcess proc ON proc.HC_PayrollProcess_ID = mov.HC_PayrollProcess_ID "
					+ "WHERE mov.HC_Employee_ID=? AND mov.HC_PayComponent_ID = ? AND proc.C_Period_ID = ?");
		
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				pstmt = DB.prepareStatement(sb.toString(), get_TrxName());
				pstmt.setInt(1, m_employee.get_ID());
				pstmt.setInt(2, payComponent.get_ID());
				pstmt.setInt(3, period.get_ID());
				rs = pstmt.executeQuery();
				if(rs.next()){
					if(rs.getBigDecimal(1)==null){
						amtValue = Env.ZERO;
					}else{
						amtValue = rs.getBigDecimal(1);
					}
				}
			}catch(Exception e){
				s_log.log(Level.SEVERE, sb.toString(), e);
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			totalAmtValue = totalAmtValue.add(amtValue);
			
			currentDate = TimeUtil.addDays(period.getStartDate(), -1);
			period = MPeriod.findByCalendar(getCtx(), currentDate, C_Calendar_ID, get_TrxName()); 
			Check_year = TimeUtil.getYear(period.getEndDate());
		}
		
		return totalAmtValue.doubleValue();
	}
	
	public double getValueDoubleFromMovement(String payComponentValue,String HC_ProcessList, int day, int month, int year){
		System.out.println(m_employee.getName());
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), payComponentValue);
		month = month + 1;
		Timestamp date = TimeUtil.getDay(year, month, day);
		int HC_Calendar_ID = new Query(getCtx(), MCalendar.Table_Name, "IsHCCalendar='Y'", get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.firstId();
		
		int HC_PayrollProcess_ID = 0;
		MPeriod period = MPeriod.findByCalendar(getCtx(), date, HC_Calendar_ID, get_TrxName());
		String query = "SELECT "+MHCPayrollProcess.Table_Name+"."+MHCPayrollProcess.COLUMNNAME_HC_PayrollProcess_ID+" FROM "+MHCPayrollProcess.Table_Name+" "
				+ "INNER JOIN HC_ProcessList hpl ON hpl.HC_ProcessList_ID = "+MHCPayrollProcess.Table_Name+"."+MHCPayrollProcess.COLUMNNAME_HC_ProcessList_ID+" "
				+ "WHERE HC_PayrollProcess.C_Period_ID=? AND hpl.Value like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1, period.get_ID());
			pstmt.setString(2, HC_ProcessList);
			rs = pstmt.executeQuery();
			if(rs.next()){
				HC_PayrollProcess_ID = rs.getInt(1);
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, query, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		String whereClause = "HC_PayrollProcess_ID=? AND HC_PayComponent_ID=? AND HC_Employee_ID=?";
		int HC_Movement_ID = new Query(getCtx(), MHCMovement.Table_Name, whereClause, get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{HC_PayrollProcess_ID, payComponent.get_ID(), m_employee.get_ID()})
		.firstId();
		
		double retValue = 0;
		
		if(HC_Movement_ID > 0){
			MHCMovement movement = new MHCMovement(getCtx(), HC_Movement_ID, get_TrxName());
			retValue = movement.getHC_AmtValue().doubleValue();
		}
		
		return retValue;
	}
	
	public String getNPWP(){
		if(m_employee.get_Value("HC_NationalID3")!= null)
			return m_employee.getHC_NationalID3();
		else 
			return "";
	}//getNPWP
	
	/**
	 * get employee status
	 * @return
	 * output (HC Status employee)
	 */
	public String getEmployeeStatus(){
		String status = m_employee.getHC_Status();
		return status;
	}//getEmployeeStatus
	
	/**
	 * take employee saldo status
	 */
	public int getSisaCutiTahunan(){
		String typeCutiTahunan = "CT";
		String query = "SELECT empLeave.SaldoCutiTahunan FROM HC_EmployeeLeaveBalance empLeave "
				+ "LEFT JOIN HC_LeaveType type ON type.HC_LeaveType_ID = empLeave.HC_LeaveType_ID "
				+ "WHERE type.Value like ? "
				+ "AND empLeave.HC_Employee_ID = ? "
				+ "AND  empLeave.IsActive='Y' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalSisa = 0;
		try{
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setString(1, typeCutiTahunan);
			pstmt.setInt(2, m_employee.get_ID());
			rs = pstmt.executeQuery();
			while(rs.next()){
				totalSisa = totalSisa + rs.getInt(1);
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, query, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return totalSisa;
	}
	/*kevinY end*/
	
	
	/**
	 * Get Total Accumulation NilaiPotonganUpah HC_RequestPermit 
	 * from period start date (date : 16) until period end date (date : 15)
	 * @return
	 */
	public double getAccumNilaiPotonganUpah(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp startDate = m_period.getStartDate();
		Timestamp endDate = m_period.getEndDate(); 
		Calendar calStartDate = null;
		Calendar calEndDate = null;
		try {
			calStartDate.setTime(sdf.parse(startDate.toString()));
			calEndDate.setTime(sdf.parse(endDate.toString()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		calStartDate.set(Calendar.DATE, 16);
		calEndDate.set(Calendar.DATE, 15);
		startDate = Timestamp.valueOf(sdf.format(calStartDate.getTime())+ " 00:00:00.0");
		endDate = Timestamp.valueOf(sdf.format(calEndDate.getTime() + " 00:00:00.0"));
		String query = "SELECT NilaiPotonganUpah FROM HC_RequestPermit WHERE "
				+ " HC_Employee_ID = ? AND IsActive='Y' AND Status='Acc' AND PermitDate "
				+ " BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal AccumNilaiPotonganUpah = new BigDecimal(0);
		try{
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1, m_employee.get_ID());
			pstmt.setTimestamp(2, startDate);
			pstmt.setTimestamp(3, endDate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AccumNilaiPotonganUpah = AccumNilaiPotonganUpah.add(rs.getBigDecimal(1));
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, query, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return AccumNilaiPotonganUpah.doubleValue();
	}
	
	/**
	 * Get Total Accumulation HC_1NormalHour,HC_2NormalHour,HC_8HolidayHour,HC_9HolidayHour,HC_10HolidayHour
	 *  HC_RequestOvertime 
	 * from period start date (date : 16) until period end date (date : 15)
	 * @return
	 */
	public double getAccumHourOvertime(String Field){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp startDate = m_period.getStartDate();
		Timestamp endDate = m_period.getEndDate(); 
		Calendar calStartDate = null;
		Calendar calEndDate = null;
		try {
			calStartDate.setTime(sdf.parse(startDate.toString()));
			calEndDate.setTime(sdf.parse(endDate.toString()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		calStartDate.set(Calendar.DATE, 16);
		calEndDate.set(Calendar.DATE, 15);
		startDate = Timestamp.valueOf(sdf.format(calStartDate.getTime())+ " 00:00:00.0");
		endDate = Timestamp.valueOf(sdf.format(calEndDate.getTime() + " 00:00:00.0"));
		String query = "SELECT HC_1NormalHour,HC_2NormalHour,HC_8HolidayHour,HC_9HolidayHour,HC_10HolidayHour "
				+ " FROM HC_RequestOvertime WHERE "
				+ " HC_Employee_ID = ? AND IsActive='Y' AND Status='Cnf' AND WorktimeDate "
				+ " BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal AccumNilai = new BigDecimal(0);
		try{
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1, m_employee.get_ID());
			pstmt.setTimestamp(2, startDate);
			pstmt.setTimestamp(3, endDate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(Field.equalsIgnoreCase("HC_1NormalHour")){
					AccumNilai = AccumNilai.add(rs.getBigDecimal(1));
				}else if(Field.equalsIgnoreCase("HC_2NormalHour")){
					AccumNilai = AccumNilai.add(rs.getBigDecimal(2));
				}else if(Field.equalsIgnoreCase("HC_8HolidayHour")){
					AccumNilai = AccumNilai.add(rs.getBigDecimal(3));
				}else if(Field.equalsIgnoreCase("HC_9HolidayHour")){
					AccumNilai = AccumNilai.add(rs.getBigDecimal(4));
				}else if(Field.equalsIgnoreCase("HC_10HolidayHour")){
					AccumNilai = AccumNilai.add(rs.getBigDecimal(5));
				}
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, query, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return AccumNilai.doubleValue();
	}
	
	public void getAndCreateEmployeeMovement(String PayComponent){
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PayComponent.trim());
		MHCMovement m = m_EmployeeMovement.get(payComponent.get_ID());
		if (m == null) {
			createMovementFromPayComponent(payComponent);
			m = m_EmployeeMovement.get(payComponent.get_ID());
			if(m==null) 
				throw new AdempiereException("Error: not found movement ");
		}
	}
	
}
