package package0;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;
import java.util.Calendar;

@SuppressWarnings("serial")
public class RecordBoolean extends JFrame {	
	public int year, month, date, hour, minute, second;
	
	public RecordBoolean(Connection conn, Statement stmt, String ID, String Ename, String Cname) {
		Calendar Calendar0 = Calendar.getInstance();
		
		JFrame Frame0 = new JFrame("Write Record");
		Frame0.setSize(215, 163);
		Frame0.setLocationRelativeTo(null);
		Frame0.setResizable(false);
		Frame0.setLayout(null);
		Frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Panel0 = new JPanel();
		Panel0.setBounds(0, 25, 200, 50);
		Panel0.setLayout(new FlowLayout());
		Frame0.add(Panel0);
		
		Container Container = getContentPane();
		Container.setLayout(new FlowLayout());
		
		JRadioButton yesRB = new JRadioButton("Yes", true);
		JRadioButton noRB = new JRadioButton("No");
		ButtonGroup ButtonGroup = new ButtonGroup();
		ButtonGroup.add(yesRB);
		ButtonGroup.add(noRB);
		Panel0.add(yesRB);
		Panel0.add(noRB);
		
		JPanel Panel1 = new JPanel();
		Panel1.setBounds(0, 75, 200, 50);
		Panel1.setLayout(null);
		Frame0.add(Panel1);	
		
		JButton Button0 = new JButton("Save");
		JButton Button1 = new JButton("Cancel");
		Panel1.add(Button0);
		Panel1.add(Button1);
		Button0.setBounds(17, 10, 75, 30);
		Button1.setBounds(108, 10, 75, 30);	
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				java.sql.Date date = new java.sql.Date(Calendar0.getTime().getTime());
					if (yesRB.isSelected() == true) {
						insertData(conn, stmt, ID, Ename, Cname, date.toString(), "O");
						Frame0.dispose();
					}
					else if (noRB.isSelected() == true) {
						insertData(conn, stmt, ID, Ename, Cname, date.toString(), "X");
						Frame0.dispose();
					}
				
			}
		});
		
		Button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Frame0.dispose();
			}
		});
		
		Frame0.setVisible(true);
	}
	
	public static int insertData(Connection conn, Statement stmt, String ID, String Ename, String Cname, String date, String detail) {
		ResultSet rs = null;
		String max = "";
		int Uindex = 0;
		String Enum ="";
		
		try {
			String sql1 = "SELECT MAX(Uindex) FROM USER_DATA";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				max = rs.getString(1);
			if (rs.wasNull())
				max = "0";
			rs.close();
			System.out.println("[insertData] Uindex ÃÖ´ñ°ª: "+max);
			
			Uindex = Integer.parseInt(max) + 1;
			String sql2 = "INSERT INTO USER_DATA(Uindex, Date, Detail) VALUES ('"+Uindex+"', '"+date+"', '"+detail+"')";
			System.out.println("[insertEve]: "+sql2);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			conn.commit();
			System.out.println("[insertEve]: "+sql2);
			
			String sql3 = "SELECT Enum FROM EVENT WHERE Ename='"+Ename+"' and Enum IN (SELECT Enum FROM Emake WHERE Uid='"+ID+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql3);
			while(rs.next())
				Enum = rs.getString(1);
			rs.close();
			System.out.println("[removeEve] Selected Enum: "+Enum);
			
			String sql4 = "INSERT INTO ERECORD(Enum, Uindex) VALUES ('"+Enum+"', '" + Uindex+"')";
			System.out.println("[insertEve]: "+sql4);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql4);
			conn.commit();
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.out.println("error3");
			System.exit(1);
		}
		return 1;
	}
}
