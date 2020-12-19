package PROCESS;
import GUI.LoginFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*; // import JDBC package

public class start {
	public static final String URL = "jdbc:postgresql://127.0.0.1:5432/HealthCareSW";
	public static final String USER_ID ="postgres";
	public static final String USER_PASSWD ="0001"; // 자신의 DB 비밀번호를 입력하세요
	
	public static Connection conn = null; // Connection object
	public static Statement stmt = null;	// Statement object 
	
	public static void main(String[] args) {
		
		System.out.println("-------- PostgreSQL"+"JDBC Connection");
		
		try {	// Load a JDBC driver for PostgreSQL DBMS
			Class.forName("org.postgresql.Driver");
				// Get a Connection object 
			System.out.println("Success!");
		} catch(ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
		+ "Include in your library path!");
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		
		// Make a connection
		try{
			conn = DriverManager.getConnection(//"jdbc:postgresql://127.0.0.1:5432/HealthCareSW", "postgres","password"
					URL, USER_ID, USER_PASSWD); 
		} catch(SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
			return;
		}
		
		createTable(conn, stmt);
		
		LoginFrame frame = new LoginFrame(conn, stmt);	// loginFrame 
	}
	
	public static void createTable(Connection conn, Statement stmt) {
		String sql = ""; 			// an SQL statement
		String table_parsing = "";	// 테이블 String에서 $값을 없앤 결과를 담을 변수 
		String value = "";			// txt 파일에서 읽어들인 값을 저장할 변수 
		String[] value_parsing;		// value에 저장된 String을 파싱한 결과를 담을 변수 
		String[] thereistable;
		thereistable = new String[14];
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
				if(check.equals("user_data")) thereistable[cnt++]="user_data";
				if(check.equals("expert_data")) thereistable[cnt++]="expert_data";
				if(check.equals("category")) thereistable[cnt++]="category";
				if(check.equals("event")) thereistable[cnt++]="event";
				if(check.equals("cmake")) thereistable[cnt++]="cmake";
				if(check.equals("emake")) thereistable[cnt++]="emake";
				if(check.equals("payment")) thereistable[cnt++]="payment";
				if(check.equals("manage")) thereistable[cnt++]="manage";
				if(check.equals("crecord")) thereistable[cnt++]="crecord";
				if(check.equals("erecord")) thereistable[cnt++]="erecord";
				if(check.equals("send")) thereistable[cnt++]="send";
				if(check.equals("write")) thereistable[cnt++]="write";
				if(check.equals("catevt")) thereistable[cnt++]="catevt";
			}
			
			System.out.println(sql);
			res = stmt.executeUpdate(sql); 

			StringBuffer sb = new StringBuffer();
			
			// 13개의 TABLE을 생성합니다. 
			if(!Isintable(thereistable, "people", cnt)) {
			sb.append("CREATE TABLE PEOPLE(\n" + 
					"	Id character varying(25) NOT NULL,\n" + 
					"	Pw character varying(25) NOT NULL,\n" +
					"	Name character varying(25) NOT NULL,\n" + 
					"	Sex  character varying(8) check (Sex in ('남','여')),\n" + 
					"	Phone character varying(15),\n" +
					"	Birth Date,\n" + 
					"	Type character varying(7) NOT NULL,\n" + 
					"primary key(Id))"+ ";");
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
						"	Ecomment text,\n" + 
						"	Emergency character varying(2) check (Emergency in ('1','0')),\n" + 
						"primary key(Eindex));");
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
					"	Ename character varying(30),\n" + 
					"	Etype character varying(8),\n" + 
					"primary key(Enum)" + ");");
			}
			if(!Isintable(thereistable, "cmake", cnt)) {
				sb.append("CREATE TABLE CMAKE(\n" + 
						"	Uid character varying(25) NOT NULL,\n" +
						"	Cnum integer,\n" +
						"primary key(Uid, Cnum)" + ");");
				}
			if(!Isintable(thereistable, "emake", cnt)) {
				sb.append("CREATE TABLE EMAKE(\n" + 
						"	Uid character varying(25) NOT NULL,\n" +
						"	Enum integer,\n" +
						"primary key(Uid, Enum)" + ");");
				}
			if(!Isintable(thereistable, "payment", cnt)) {
			sb.append("CREATE TABLE PAYMENT(\n" +
					"	Uid character varying(25) NOT NULL,\n" +
					"	Eid character varying(25) NOT NULL,\n" +
					"	PayTF character varying(2) check (payTF in ('1','0')),\n" +
					"primary key(Uid, Eid)" + ");");
			}
			if(!Isintable(thereistable, "manage", cnt)) {
				sb.append("CREATE TABLE MANAGE(\n" + 
						"	Uid character varying(25) NOT NULL,\n" +
						"	Uindex integer,\n" +
						"primary key(Uid, Uindex)" + ");");
				}
			if(!Isintable(thereistable, "crecord", cnt)) {
				sb.append("CREATE TABLE CRECORD(\n" +
						"	Cnum integer,\n" +
						"	Uindex integer,\n" +
						"primary key(Cnum, Uindex)" + ");");
				}
			if(!Isintable(thereistable, "erecord", cnt)) {
				sb.append("CREATE TABLE ERECORD(\n" + 
						"	Enum integer,\n" +
						"	Uindex integer,\n" +
						"primary key(Enum, Uindex)" + ");");
				}	
			if(!Isintable(thereistable, "send", cnt)) {
			sb.append("CREATE TABLE SEND(\n" +
					"	Uindex integer,\n" +
					"	Eindex integer,\n" +
					"	Ucomment text,\n" +
					"primary key(Uindex, Eindex)" + ");");
				}
			if(!Isintable(thereistable, "write", cnt)) {
			sb.append("CREATE TABLE WRITE(\n" + 
					"	Eid character varying(25) NOT NULL,\n" +
					"	Eindex integer,\n" +
					"primary key(Eid, Eindex)" + ");");
				}
			if(!Isintable(thereistable, "catevt", cnt)) {
				sb.append("CREATE TABLE CATEVT(\n" + 
						"	Cnum integer,\n" +
						"	Enum integer,\n" +
						"primary key(Cnum, Enum)" + ");");
					}
			
			
			if(sb!=null) {
				System.out.println(sb);
				sql = sb.toString();
				res = stmt.executeUpdate(sql); 
				if(res == 0) 
					System.out.println("Table was successfully created.");
				conn.commit();			
			}

		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}

	}
	public static boolean Isintable(String[] table, String target, int n) {
		int i;
		
		for (i=0; i<n; i++) {
			if(table[i].equals(target)) {
				return true;
			}
		}
		return false;
	}
}