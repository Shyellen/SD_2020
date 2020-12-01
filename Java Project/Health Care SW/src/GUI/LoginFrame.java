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
		
		JoinBtn = new JButton("ȸ������");
		JoinBtn.setBounds(229, 154, 104, 29);
		contentPane.add(JoinBtn);
		
		LoginBtn = new JButton("�α���");
		LoginBtn.setBounds(108, 154, 106, 29);
		contentPane.add(LoginBtn);
		
		setVisible(true);
		
		// ȸ������ �׼�
		JoinBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinFrame frame = new JoinFrame(conn, stmt);
			}
		});
		
		// �α��� �׼�
		LoginBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String Id = IdField.getText();
				char[] Pw = PwField.getPassword();
				
				if (checkID(conn, stmt, Id, Pw)) {
					String Type = checkType(conn, stmt, Id);
					JOptionPane.showMessageDialog(null, "[����] " + Id + " Ÿ��: " + Type);
					if (Type.equals("expert")) {
						ExpertFrame frame = new ExpertFrame(conn, stmt, Id, Type);
					}
					else {
						User_Category frame = new User_Category(conn, stmt, Id, Type);
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "[����] ���̵� ��й�ȣ�� Ȯ���ϼ���.");
				}
			}
		});
	}
	
	public static boolean checkID(Connection conn, Statement stmt, String Id, char[] Ppw) {	// �ش��ϴ� ID�� DB�� �����ϴ��� Ȯ���մϴ�.
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
		if (DBid.equals(Id) && DBpw.equals(Pw)) return true;	// ���̵�� ��й�ȣ�� ������ 1�� ��ȯ. ������ 0�� ��ȯ�մϴ�.
		else return false;
	}
	
	public static String checkType(Connection conn, Statement stmt, String Id) {	// �ش��ϴ� ID�� Type�� Ȯ���մϴ�.
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