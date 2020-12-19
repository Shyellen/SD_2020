package GUI;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
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
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		
		UserEventProcess.checkData(conn, stmt, Id, Ename, Cname);
		
		setVisible(true);
	}
	
}
