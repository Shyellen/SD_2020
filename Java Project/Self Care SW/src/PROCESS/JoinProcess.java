package PROCESS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JoinProcess {
	public static int signup(Connection conn, Statement stmt, String[] values) {
		ResultSet rs = null;
		
		try {
			String sql;
			String sql2 = "select Id, name from PEOPLE as p \n" + 
					"where p.Id = " + "'" + values[0] + "'" + " or p.name = " + "'" + values[2] + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				if (values[0].equals(rs.getString(1))) return 0;
				if (values[2].equals(rs.getString(2))) return 0;
			}
			rs.close();
			
			sql = "INSERT INTO people(id, pw, name, sex, birth, phone, type) \n"
					+ "VALUES (" + "'" + values[0] + "', " + "'" + values[1] + "', " 
					+ "'" + values[2] + "', " + "'" + values[3] + "', " 
					+ "'" + values[4] + "', " + "'" + values[5] + "', "
					+ "'" + values[6] + "'" + ")";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.commit();
			
			String sql3 = "INSERT INTO payment(Uid, Eid, PayTF) VALUES ("+"'"+values[0]+"', '"+values[0]+"', '0')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql3);
			System.out.println(" row inserted.");
			conn.commit();
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		return 1;
	}
}
