package PROCESS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginProcess {

	public static boolean checkID(Connection conn, Statement stmt, String Id, char[] Ppw) {	// �ش��ϴ� ID�� DB�� �����ϴ��� Ȯ���մϴ�.
		ResultSet rs = null;
		String DBid = "";
		String DBpw = "";
		String Pw = String.valueOf(Ppw);

		try {
			String sql = "SELECT Id, Pw FROM PEOPLE as p \n" + 
		"WHERE p.Id = " + "'" + Id + "'" + " and p.Pw = " + "'" + Pw + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBid = rs.getString(1);
				DBpw = rs.getString(2);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		if (DBid.equals(Id) && DBpw.equals(Pw)) return true;	// ���̵�� ��й�ȣ�� ������ 1�� ��ȯ. ������ 0�� ��ȯ�մϴ�.
		else return false;
	}
	
	public static String checkType(Connection conn, Statement stmt, String Id) {	// �ش��ϴ� ID�� Type�� Ȯ���մϴ�.
		ResultSet rs = null;
		String DBtype = "";
		try {
			String sql = "SELECT Type FROM People WHERE Id='" + Id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBtype = rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		return DBtype;
	}
}
