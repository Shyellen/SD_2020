package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PROCESS.UserEventProcess;

public class DataFrame extends JFrame {
	private JPanel contentPane;
	
	public DataFrame(Connection conn, Statement stmt, String Id, String Ename, String Cname) {
		setTitle("Self Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		
		String[] data = checkData(conn, stmt, Id, Ename, Cname);
		int len = data.length;
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl[] = new JLabel[len];
		for (int i=0; i<len; i++) {
			lbl[i] = new JLabel(data[i]);
			panel.add(lbl[i]);
		}
		contentPane.add(panel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static String[] checkData(Connection conn, Statement stmt, String Id, String Ename, String Cname) {
		ResultSet rs = null;
		String Enum = "";
		int cnt = 0;
		int i=0;
		
		try {
			String sql1 = "SELECT Enum FROM EVENT WHERE Ename='"+Ename+"' and Enum IN (SELECT Enum FROM Emake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				Enum = rs.getString(1);
			rs.close();
			System.out.println("[removeEve] Selected Enum: "+Enum);
			
			String sql2 = "SELECT COUNT(*) FROM USER_DATA WHERE Uindex IN (SELECT Uindex FROM ERECORD WHERE Enum='"+Enum+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				cnt = Integer.parseInt(rs.getString(1));	
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error2");
			e.printStackTrace();
		}
		
		String[] DBrs1 = new String[cnt];
		String[] DBrs2 = new String[cnt];
		String[] DBrs3 = new String[cnt];
		String[] Result = new String[cnt];
		
		try {
			String sql3 = "SELECT * FROM USER_DATA WHERE Uindex IN (SELECT Uindex FROM ERECORD WHERE Enum='"+Enum+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql3);
			while(rs.next()) {
				DBrs1[i] = (String) rs.getString(1);
				DBrs2[i] = (String) rs.getString(2);
				DBrs3[i] = (String) rs.getString(3);
				i++;
			}
			rs.close();
		}
		catch (SQLException e) {
				System.err.println("sql error = " + e.getMessage());
				System.out.println("error2");
				e.printStackTrace();
		}
		
		for (int j=0; j<cnt; j++) {
			Result[j] = (DBrs1[j]+" "+DBrs2[j]+" "+DBrs3[j]).toString();
			System.out.println(Result[j]);
		}

		return Result;
	}
}
