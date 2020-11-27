import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*; // import JDBC package

import GUI.LoginFrame;

public class start {
	
	public static final String URL = "jdbc:postgresql://127.0.0.1:5432/HealthCareSW";
	public static final String USER_ID ="postgres";
	public static final String USER_PASSWD ="0001"; // 자신의 DB 비밀번호를 입력하세요 
	
	public static void main(String[] args) {
		
		Connection conn = null; // Connection object
		Statement stmt = null;	// Statement object
		
		
		System.out.println("-------- PostgreSQL"+"JDBC Connection");
		
		try {		// Load a JDBC driver for PostgreSQL DBMS
			Class.forName("org.postgresql.Driver");
			// Get a Connection object 
			System.out.println("Success!");
		}catch(ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver?"
		+ "Include in your library path!");
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		// Make a connection
		try{
			conn = DriverManager.getConnection(//"jdbc:postgresql://127.0.0.1:5432/HealthCareSW", "postgres","password"
					URL, USER_ID,
					USER_PASSWD); 
		}catch(SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
			return ;
		}
		
		
		
		
		createTable(conn, stmt);
		
		LoginFrame frame = new LoginFrame(conn, stmt);			////////////// loginFrame 
		
		
		
	}
	
	public static void createTable(Connection conn, Statement stmt) {
		// Fill out your code.
		String sql = ""; // an SQL statement
		String table_parsing = "";	// 테이블 String에서 $값을 없앤 결과를 담을 변수 
		String value = "";	// txt 파일에서 읽어들인 값을 저장할 변수 
		String[] value_parsing;	// value에 저장된 String을 파싱한 결과를 담을 변수 
		String[] thereistable;
		thereistable = new String[11];
		int cnt=0;
		
		// Execute an SQL statement for CREATE TABLE
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			int res;
			String check;
			// Let's execute an SQL statement.
			// 만약 pgAdmin에 테이블이 없다면 테이블을 생성합니다. 
			DatabaseMetaData metadata = conn.getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(null, null, "%", null);
			while(resultSet.next()) {
				check = resultSet.getString("TABLE_NAME");
				//System.out.println(check);
				if(check.equals("people")) thereistable[cnt++]="people";
				if(check.equals("category")) thereistable[cnt++]="category";
				if(check.equals("event")) thereistable[cnt++]="event";
				if(check.equals("user_data")) thereistable[cnt++]="user_data";
				if(check.equals("expert_data")) thereistable[cnt++]="expert_data";
				if(check.equals("payment")) thereistable[cnt++]="payment";
				if(check.equals("send")) thereistable[cnt++]="send";
				if(check.equals("make")) thereistable[cnt++]="make";
				if(check.equals("record")) thereistable[cnt++]="record";
				if(check.equals("manage")) thereistable[cnt++]="manage";
				if(check.equals("write")) thereistable[cnt++]="write";
			}
//			sql = "DROP TABLE " + "PEOPLE" + " CASCADE;";
//			sql += "DROP TABLE " + "CATEGORY" + " CASCADE;";
//			sql += "DROP TABLE " + "EVENT" + " CASCADE;";
//			sql += "DROP TABLE " + "USER_DATA" + " CASCADE;";
//			sql += "DROP TABLE " + "EXPERT_DATA" + " CASCADE;";
//			sql += "DROP TABLE " + "PAYMENT" + " CASCADE;";
//			sql += "DROP TABLE " + "SEND" + " CASCADE;";
//			sql += "DROP TABLE " + "MAKE" + " CASCADE;";
//			sql += "DROP TABLE " + "RECORD" + " CASCADE;";
//			sql += "DROP TABLE " + "MANAGE" + " CASCADE;";
//			sql += "DROP TABLE " + "WRITE" + " CASCADE;";
			
			System.out.println(sql);
			res = stmt.executeUpdate(sql); 
			//if(res == 0) 
			//	System.out.println("Table was successfully dropped.");
			StringBuffer sb = new StringBuffer();
			// 11개의 TABLE을 생성합니다. 
			if(!Isintable(thereistable, "people", cnt)) {
			sb.append("CREATE TABLE PEOPLE(\n" + 
					"	Name character varying(25) NOT NULL,\n" + 
					"	Id character varying(25) NOT NULL,\n" + 
					"	pw character varying(25) NOT NULL,\n" + 
					"	Sex  character varying(8) check (Sex in ('남','여')),\n" + 
					"	Type character varying(7),\n" + 
					"	phone character varying(15),\n" +
					"	Birth Date,\n" + 
					"primary key(ID))"+ ";");
			}
			if(!Isintable(thereistable, "category", cnt)) {
			sb.append("CREATE TABLE CATEGORY(\n" + 
					"	Cnum integer,\n" + 
					"	Cname character varying(30),\n" + 
					"primary key(Cnum)" + ");");
			}
			if(!Isintable(thereistable, "event", cnt)) {
			sb.append("CREATE TABLE EVENT(\n" + 
					"	Enum integer,\n" + 
					"	Eame character varying(30),\n" + 
					"	Etype character varying(5),\n" + 
					"primary key(Enum)" + ");");
			}
			if(!Isintable(thereistable, "user_data", cnt)) {
			sb.append("CREATE TABLE USER_DATA(\n" + 
					"	Uindex integer,\n" + 
					"	Date Date,\n" +
					"	Detail text,\n" + 
					"primary key(Uindex));");
			}
			if(!Isintable(thereistable, "expert_data", cnt)) {
			sb.append("CREATE TABLE EXPERT_DATA(\n" + 
					"	Eindex integer,\n" + 
					"	EComment text,\n" + 
					"	Emergency character varying(2) check (Emergency in ('1','0')),\n" + 
					"primary key(Eindex));");
			}
			if(!Isintable(thereistable, "payment", cnt)) {
			sb.append("CREATE TABLE PAYMENT(\n" + 
					"	payTF character varying(2) check (payTF in ('1','0')),\n" +
					"	UId character varying(25) NOT NULL,\n" +
					"	EId character varying(25) NOT NULL" +
					");");
			}
			if(!Isintable(thereistable, "send", cnt)) {
			sb.append("CREATE TABLE SEND(\n" + 
					"	Ucomment text,\n" +
					"	Important boolean\n" +
					");");
			}
			if(!Isintable(thereistable, "make", cnt)) {
			sb.append("CREATE TABLE MAKE(\n" + 
					"	UId character varying(25) NOT NULL,\n" +
					"	num integer\n" +
					");");
			}
			if(!Isintable(thereistable, "record", cnt)) {
			sb.append("CREATE TABLE RECORD(\n" + 
					"	Uindex integer,\n" +
					"	num integer\n" +
					");");
			}
			if(!Isintable(thereistable, "manage", cnt)) {
			sb.append("CREATE TABLE MANAGE(\n" + 
					"	UId character varying(25) NOT NULL,\n" +
					"	Uindex integer\n" +
					");");
			}
			if(!Isintable(thereistable, "write", cnt)) {
			sb.append("CREATE TABLE WRITE(\n" + 
					"	UId character varying(25) NOT NULL,\n" +
					"	Eindex integer\n" +
					");");
			}
			if(sb!=null) {
			System.out.println(sb);
			sql = sb.toString();
			res = stmt.executeUpdate(sql); 
			if(res == 0) 
				System.out.println("Table was successfully created.");
			conn.commit();			
			}

		}catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}

	}
	public static boolean Isintable(String[] table, String target, int n) {
		int i;
		for (i=0;i<n;i++) {
			if(table[i].equals(target)) return true;
		}
		return false;
	}
	
}
