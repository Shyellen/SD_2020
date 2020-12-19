package GUI;

import PROCESS.LoginProcess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment {
	
	public static boolean checkPayment(Connection conn, Statement stmt, String ID) {	//지불여부를 반환함 
		ResultSet rs = null;
		int check = 0;
		
		try {
			String sql = "SELECT payTF FROM PAYMENT WHERE Eid = '"+ID+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				check = rs.getInt(1);
			}
			rs.close();
			System.out.println("[Payment] 결제 여부: "+ check);
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
		}
		if (check == 1)
			return true;
		else
			return false;
	}
	
	public static void paymentRequest(Connection conn, Statement stmt, String ID) { 
		ResultSet rs = null;
		String sql = "UPDATE PAYMENT SET payTF=? WHERE Uid=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "1");
			pstmt.setString(2, ID);
			int r = pstmt.executeUpdate();
			
			System.out.println("[Payment] "+r);
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
		}
		
		
		return;
	}
	
}
