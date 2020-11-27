import java.sql.*; // import JDBC package

public class CreateDB {
	public static final String URL = "jdbc:postgresql://127.0.0.1:5432/HealthCareSW";
	public static final String USER_ID ="postgres";
	public static final String USER_PASSWD =""; // 자신의 DB 비밀번호를 입력하세요 
	
	public static void main(String[] args) {
		Connection conn = null; // Connection object
		Statement stmt = null;	// Statement object
		
		
		System.out.println("-------- PostgreSQL"+"JDBC Connection");
		
		try {			// Load a JDBC driver for PostgreSQL DBMS
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
			conn = DriverManager.getConnection(
					//"jdbc:postgresql://127.0.0.1:5432/company", "postgres",
					URL, USER_ID,
					USER_PASSWD); 
		}catch(SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
			return ;
		}
		
		createTable(conn, stmt);
	}
	
	public static void createTable(Connection conn, Statement stmt) {
		// Fill out your code.
		String sql = ""; // an SQL statement
		String table_parsing = "";	// 테이블 String에서 $값을 없앤 결과를 담을 변수 
		String value = "";	// txt 파일에서 읽어들인 값을 저장할 변수 
		String[] value_parsing;	// value에 저장된 String을 파싱한 결과를 담을 변수 
		
		// Execute an SQL statement for CREATE TABLE
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			int res;
			// Let's execute an SQL statement.
			// 만약 pgAdmin에 이미 테이블이 있다면 해당 코드를 사용해서 제거하고 다시 CREATE합니다.
			// DB에 11개의 table이 존재하지 않는다면 주석처리 해주세요 
			sql = "DROP TABLE " + "PEOPLE" + " CASCADE;";
			sql += "DROP TABLE " + "CATEGORY" + " CASCADE;";
			sql += "DROP TABLE " + "EVENT" + " CASCADE;";
			sql += "DROP TABLE " + "USER_DATA" + " CASCADE;";
			sql += "DROP TABLE " + "EXPERT_DATA" + " CASCADE;";
			sql += "DROP TABLE " + "PAYMENT" + " CASCADE;";
			sql += "DROP TABLE " + "SEND" + " CASCADE;";
			sql += "DROP TABLE " + "MAKE" + " CASCADE;";
			sql += "DROP TABLE " + "RECORD" + " CASCADE;";
			sql += "DROP TABLE " + "MANAGE" + " CASCADE;";
			sql += "DROP TABLE " + "WRITE" + " CASCADE;";
			//System.out.println(sql);
			res = stmt.executeUpdate(sql); 
			if(res == 0) 
				System.out.println("Table was successfully dropped.");
			StringBuffer sb = new StringBuffer();
			// 11개의 TABLE을 생성합니다. 
			sb.append("CREATE TABLE PEOPLE(\n" + 
					"	Name character varying(25) NOT NULL,\n" + 
					"	Id character varying(25) NOT NULL,\n" + 
					"	pw character varying(25) NOT NULL,\n" + 
					"	Sex  character varying(8) check (Sex in ('남','여')),\n" + 
					"	Type character varying(5),\n" + 
					"	Birth Date,\n" + 
					"primary key(ID))"+ ";");
			sb.append("CREATE TABLE CATEGORY(\n" + 
					"	Cnum integer,\n" + 
					"	Cname character varying(30),\n" + 
					"primary key(Cnum)" + ");");
			sb.append("CREATE TABLE EVENT(\n" + 
					"	Enum integer,\n" + 
					"	Eame character varying(30),\n" + 
					"	Etype character varying(5),\n" + 
					"primary key(Enum)" + ");");
			sb.append("CREATE TABLE USER_DATA(\n" + 
					"	Uindex integer,\n" + 
					"	Date Date,\n" +
					"	Detail text,\n" + 
					"primary key(Uindex));");
			sb.append("CREATE TABLE EXPERT_DATA(\n" + 
					"	Eindex integer,\n" + 
					"	EComment text,\n" + 
					"	Emergency character varying(2) check (Emergency in ('1','0')),\n" + 
					"primary key(Eindex));");
			sb.append("CREATE TABLE PAYMENT(\n" + 
					"	payTF character varying(2) check (payTF in ('1','0')),\n" +
					"	UId character varying(25) NOT NULL,\n" +
					"	EId character varying(25) NOT NULL" +
					");");
			sb.append("CREATE TABLE SEND(\n" + 
					"	Ucomment text,\n" +
					"	Important boolean\n" +
					");");
			sb.append("CREATE TABLE MAKE(\n" + 
					"	UId character varying(25) NOT NULL,\n" +
					"	num integer\n" +
					");");
			sb.append("CREATE TABLE RECORD(\n" + 
					"	Uindex integer,\n" +
					"	num integer\n" +
					");");
			sb.append("CREATE TABLE MANAGE(\n" + 
					"	UId character varying(25) NOT NULL,\n" +
					"	Uindex integer\n" +
					");");
			sb.append("CREATE TABLE WRITE(\n" + 
					"	UId character varying(25) NOT NULL,\n" +
					"	Eindex integer\n" +
					");");
			System.out.println(sb);
			sql = sb.toString();
			//System.out.println(sql);
			// Try 'CREATE TABLE ...'
			res = stmt.executeUpdate(sql); 
			if(res == 0) 
				System.out.println("Table was successfully created.");
			// Make the table permanently stored by a commit.
			conn.commit();			

		}catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}

	}
}