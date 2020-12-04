package PROCESS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryProcess {
	
	public static int checkCatCnt(Connection conn, Statement stmt, String Id) {	// 해당 ID로 생성된 카테고리 수를 확인한다.
		ResultSet rs = null;
		String DBcnt = "";
		try {
			String sql = "SELECT COUNT(*) FROM CMAKE WHERE Uid = '"+Id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBcnt = rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		return Integer.parseInt(DBcnt);
	}
	
	public static String[] checkCatCname(Connection conn, Statement stmt, String Id, int cnt) { // 해당 ID로 생성된 카테고리 이름을 확인한다.
		ResultSet rs = null;
		String[] DBname = new String[cnt];
		try {
			String sql = "SELECT Cname FROM Category"
					+ " WHERE Cnum = (SELECT Cnum FROM Cmake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()) {
				DBname[i++] = (String) rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		return DBname;
	}
	
	public static int insertCat(Connection conn, Statement stmt, String Id, String Cname) { // 새로운 카테고리를 생성해 넣는다.
		ResultSet rs = null;
		String max = "";
		int Cnum = 0;
		
		try {
			String sql1 = "SELECT MAX(cnum) FROM category";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				max = rs.getString(1);
			if (rs.wasNull())
				max = "0";
			rs.close();
			
			Cnum = Integer.parseInt(max) + 1;
			String sql2 = "INSERT INTO category(cnum, cname) VALUES ("+Cnum+", " + Cname+")";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			conn.commit();
			
			String sql3 = "INSERT INTO cmake(Uid, Cnum) VALUES ("+Id+", " + Cnum+")";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql3);
			conn.commit();
			
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		
		return 1;
	}
}
