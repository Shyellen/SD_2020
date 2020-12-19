package PROCESS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserEventProcess {
	public static int checkEveCnt(Connection conn, Statement stmt, String Id, String CatName) {	// 
		ResultSet rs = null;
		String DBcnt = "";
		String Cnum = "";
		try {
			String sql4 = "SELECT Cnum FROM category WHERE Cname='"+CatName+"' and Cnum IN (SELECT Cnum FROM Cmake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql4);
			while(rs.next())
				Cnum = rs.getString(1);
			rs.close();
			System.out.println("[checkEveCnt] Selected Cnum: "+Cnum);
			
			String sql = "SELECT COUNT(*) FROM CATEVT WHERE Cnum = '"+Cnum+"'";
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

	//////////////////////////////////////// 해당 ID로 생성된 이벤트 이름을 확인한다.
	public static String[] checkEveCname(Connection conn, Statement stmt, String Id, int cnt, String CatName) {
		ResultSet rs = null;
		String[] DBname = new String[cnt];
		String Cnum = "";
		
		try {
			String sql4 = "SELECT Cnum FROM category WHERE Cname='"+CatName+"' and Cnum IN (SELECT Cnum FROM Cmake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql4);
			while(rs.next())
				Cnum = rs.getString(1);
			rs.close();
			System.out.println("[checkEveCnt] Selected Cnum: "+Cnum);
			
			String sql = "SELECT Ename FROM EVENT WHERE Enum IN (SELECT Enum FROM CATEVT WHERE Cnum='"+Cnum+"')";
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
		System.out.print("[checkEveCname] 이벤트 명: ");
		for (int i=0; i<cnt; i++)
			System.out.print(DBname[i]+" ");
		System.out.println("");
		
		return DBname;
	}
	
	public static String checkEveType(Connection conn, Statement stmt, String Ename) {
		ResultSet rs = null;
		String type = "";
		
		System.out.print("[checkEveType] 진입");
		try {
			String sql = "SELECT Etype FROM EVENT WHERE Ename = '"+Ename+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				type = rs.getString(1).toString();
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error2");
			e.printStackTrace();
		}
		System.out.print("[checkEveType] 이벤트 타입: "+type);
		
		return type;
	}
	//////////////////////////////////////// 새로운 이벤트를 생성해 넣는다.
	public static int insertEve(Connection conn, Statement stmt, String Id, String Ename, String type, String catName) {
		ResultSet rs = null;
		String max = "";
		int Enum = 0;
		String Cnum ="";
		
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
			
			String sql4 = "SELECT Cnum FROM category WHERE Cname='"+catName+"' and Cnum IN (SELECT Cnum FROM Cmake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql4);
			while(rs.next())
				Cnum = rs.getString(1);
			rs.close();
			System.out.println("[insertEve] Selected Cnum: "+Cnum);
			
			String sql5 = "INSERT INTO CATEVT(Cnum, Enum) VALUES ('"+Cnum+"', '" + Enum+"')";
			System.out.println("[insertEve]: "+sql5);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql5);
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
