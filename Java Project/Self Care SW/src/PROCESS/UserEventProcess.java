package PROCESS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserEventProcess {
	public static int checkEveCnt(Connection conn, Statement stmt, String Id, String CatName) {	// 
		ResultSet rs = null;
		String DBcnt = "";
		try {
			String sql = "SELECT COUNT(*) FROM EMAKE WHERE Uid = '"+Id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBcnt = rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error1");
			e.printStackTrace();
		}
		System.out.println("[checkEveCnt] 이벤트 수: "+DBcnt);
		return Integer.parseInt(DBcnt);
	}

	//////////////////////////////////////// 해당 ID로 생성된 카테고리 이름을 확인한다.
	public static String[] checkEveCname(Connection conn, Statement stmt, String Id, int cnt) {
		ResultSet rs = null;
		String[] DBname = new String[cnt];
		
		try {
			String sql = "SELECT Ename FROM EVENT WHERE Enum IN (SELECT Enum FROM Emake WHERE Uid='"+Id+"')";
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
			System.out.println("error2");
			e.printStackTrace();
		}
		System.out.print("[checkEveCname] 카테고리 명: ");
		for (int i=0; i<cnt; i++)
			System.out.print(DBname[i]+" ");
		System.out.println("");
		
		return DBname;
	}
	
	//////////////////////////////////////// 새로운 이벤트를 생성해 넣는다.
	public static int insertEve(Connection conn, Statement stmt, String Id, String Ename, String type) {
		ResultSet rs = null;
		String max = "";
		int Enum = 0;
		
		try {
			String sql1 = "SELECT MAX(Enum) FROM EVENT";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				max = rs.getString(1);
			if (rs.wasNull())
				max = "0";
			rs.close();
			System.out.println("[insertEve] Enum 최댓값: "+max);
			
			Enum = Integer.parseInt(max) + 1;
			String sql2 = "INSERT INTO EVENT(Enum, Ename, Etype) VALUES ('"+Enum+"', '"+Ename+"', '"+type+"')";
			System.out.println("[insertEve]: "+sql2);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			conn.commit();
			System.out.println("[insertEve]: "+sql2);
			
			String sql3 = "INSERT INTO Emake(Uid, Enum) VALUES ('"+Id+"', '" + Enum+"')";
			System.out.println("[insertEve]: "+sql3);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql3);
			conn.commit();
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.out.println("error3");
			System.exit(1);
		}
		return 1;
	}
	
	public static int removeEve(Connection conn, Statement stmt, String Id, String Ename) {
		ResultSet rs = null;
		String Enum = null;
		
		try {
			String sql1 = "SELECT Enum FROM EVENT WHERE Ename='"+Ename+"' and Enum IN (SELECT Enum FROM Emake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				Enum = rs.getString(1);
			rs.close();
			System.out.println("[removeEve] Selected Enum: "+Enum);
			
			
			String sql2 = "DELETE FROM EVENT WHERE Enum='"+Enum+"'";
			System.out.println("[removeEve]: "+sql2);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			conn.commit();
			
			String sql3 = "DELETE FROM EMAKE WHERE Enum='"+Enum+"'";
			System.out.println("[removeEve]: "+sql3);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql3);
			conn.commit();
			
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.out.println("error3");
			System.exit(1);
		}
		return 1;
	}
	
	

}
