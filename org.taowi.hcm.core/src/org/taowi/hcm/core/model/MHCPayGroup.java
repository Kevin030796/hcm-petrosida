package org.taowi.hcm.core.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.DB;

public class MHCPayGroup extends X_HC_PayGroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8650780512126989442L;

	public MHCPayGroup(Properties ctx, int HC_PayGroup_ID, String trxName) {
		super(ctx, HC_PayGroup_ID, trxName);
	}

	public MHCPayGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public int[] getEmployeeIDs() {
		
		List<Integer> employeeList = new ArrayList<Integer>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT DISTINCT HC_Employee_ID FROM HC_EmployeeJob WHERE HC_PayGroup_ID=?");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, get_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				employeeList.add(rs.getInt(1));
			}
			
		} catch (SQLException e){
			log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if (employeeList.isEmpty())
			return null;
		
		int [] employeeIDs = new int [employeeList.size()];
		int i = 0;
		
		for (Integer employeeID : employeeList) {
			if (employeeID!=null) {
				employeeIDs[i] = employeeID;
				i++;
			}
		}
		
		return employeeIDs;
	}
}
