package GUI;

import PROCESS.LoginProcess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment {
	
	public static boolean checkPayment(Connection conn, Statement stmt, String ID) {	//지불여부를 반환함 
		ResultSet rs = null;
		String DBcnt = "";
		String sql="";
		int check=0;
		String type = LoginProcess.checkType(conn,stmt,ID);
		try {
			sql = "SELECT payTF FROM PAYMENT"
					+ "WHERE EID = (SELECT ID FROM PEOPLE WHERE ID = "+ ID +");";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()) {
				check = rs.getInt(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
//			System.exit(1);
		}
		if(check==1) return true;
		else return false;
	}
	
	public static boolean paymentRequest(Connection conn, Statement stmt, String ID) {	//지불여부를 반환함 
		ResultSet rs = null;
		String DBcnt = "";
		String sql="";
		boolean check = false;
		String type = LoginProcess.checkType(conn,stmt,ID);
		try {
			if(type.equals("user")) {
			sql = "UPDATE PAYMENT SET payTF = 1 "
					+ "WHERE UID = "+ ID +";";
			}
			else if(type.equals("expert")) {
			sql = "UPDATE PAYMENT SET payTF = 1 "
						+ "WHERE EID = "+ ID +";";
			}
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			check=true;
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
//			System.exit(1);
		}
		return check;
	}
}
