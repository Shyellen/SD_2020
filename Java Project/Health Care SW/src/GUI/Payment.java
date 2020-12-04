package GUI;

import PROCESS.LoginProcess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Payment {
	public static boolean paymentRequest(Connection conn, Statement stmt, String ID) {	// 해당 ID로 생성된 카테고리 수를 확인한다.
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
		if(check==true)
			return true;
		else
			return false;
	}
}
