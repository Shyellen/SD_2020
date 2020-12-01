package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; // import JDBC package

public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField IdField;
	private JPasswordField PwField;
	private JButton LoginBtn, JoinBtn;

	public LoginFrame(Connection conn, Statement stmt) {
		setTitle("Self Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel IdLabel = new JLabel("ID");
		IdLabel.setBounds(41, 52, 69, 35);
		contentPane.add(IdLabel);
		
		JLabel PwLabel = new JLabel("PW");
		PwLabel.setBounds(41, 103, 69, 35);
		contentPane.add(PwLabel);
		
		IdField = new JTextField();
		IdField.setBounds(157, 52, 176, 35);
		IdField.setColumns(10);
		contentPane.add(IdField);
		
		PwField = new JPasswordField();
		PwField.setBounds(157, 103, 176, 35);
		PwField.setColumns(10);
		contentPane.add(PwField);
		
		JoinBtn = new JButton("회원가입");
		JoinBtn.setBounds(229, 154, 104, 29);
		contentPane.add(JoinBtn);
		
		LoginBtn = new JButton("로그인");
		LoginBtn.setBounds(108, 154, 106, 29);
		contentPane.add(LoginBtn);
		
		setVisible(true);
		
		// 회원가입 액션
		JoinBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinFrame frame = new JoinFrame(conn, stmt);
			}
		});
		
		// 로그인 액션
		LoginBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String Id = IdField.getText();
				char[] Pw = PwField.getPassword();
				
				if (checkID(conn, stmt, Id, Pw)) {
					String Type = checkType(conn, stmt, Id);
					JOptionPane.showMessageDialog(null, "[성공] " + Id + " 타입: " + Type);
					if (Type.equals("expert")) {
						ExpertFrame frame = new ExpertFrame(conn, stmt, Id, Type);
					}
					else {
						User_Category frame = new User_Category(conn, stmt, Id, Type);
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "[실패] 아이디나 비밀번호를 확인하세요.");
				}
			}
		});
	}
	
	public static boolean checkID(Connection conn, Statement stmt, String Id, char[] Ppw) {	// 해당하는 ID가 DB에 존재하는지 확인합니다.
		ResultSet rs = null;
		String DBid = "";
		String DBpw = "";
		String Pw = String.valueOf(Ppw);

		try {
			String sql = "SELECT Id, Pw FROM PEOPLE as p \n" + 
		"WHERE p.Id = " + "'" + Id + "'" + " and p.Pw = " + "'" + Pw + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBid = rs.getString(1);
				DBpw = rs.getString(2);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		if (DBid.equals(Id) && DBpw.equals(Pw)) return true;	// 아이디랑 비밀번호가 있으면 1을 반환. 없으면 0을 반환합니다.
		else return false;
	}
	
	public static String checkType(Connection conn, Statement stmt, String Id) {	// 해당하는 ID의 Type을 확인합니다.
		ResultSet rs = null;
		String DBtype = "";
		try {
			String sql = "SELECT Type FROM People WHERE Id='" + Id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBtype = rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		return DBtype;
	}
}