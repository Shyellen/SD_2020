package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; // import JDBC package

public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField IdField;
	private JPasswordField PwField;
	private JButton LoginBtn, JoinBtn;

	public LoginFrame(Connection conn, Statement stmt) {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("ID");
		lblLogin.setBounds(41, 52, 69, 35);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("PW");
		lblPassword.setBounds(41, 103, 69, 35);
		contentPane.add(lblPassword);
		
		IdField = new JTextField();
		IdField.setBounds(157, 52, 176, 35);
		contentPane.add(IdField);
		IdField.setColumns(10);
		
		JoinBtn = new JButton("ȸ������");
		JoinBtn.setBounds(229, 154, 104, 29);
		contentPane.add(JoinBtn);
		
		LoginBtn = new JButton("�α���");
		LoginBtn.setBounds(108, 154, 106, 29);
		contentPane.add(LoginBtn);
		
		PwField = new JPasswordField();
		PwField.setColumns(10);
		PwField.setBounds(157, 103, 176, 35);
		contentPane.add(PwField);
		
		setVisible(true);
		
		//ȸ������ �׼�
		JoinBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinFrame frame = new JoinFrame(conn, stmt);
			}
		});
		
		//�α��� �׼�
		LoginBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String Id = IdField.getText();
				char[] Pw = PwField.getPassword();
				
				if (checkID(conn, stmt, Id, Pw) && Id.length()!=0) {
				JOptionPane.showMessageDialog(null, Id+" �α��� ����");
				dispose();
				User_Category frame = new User_Category(conn, stmt);
				}
				//else {	�α��� ���н� UI 
				//}
			}
		});
	}
	
	public static boolean checkID(Connection conn, Statement stmt, String Id, char[] Ppw) {	// �ش��ϴ� ID�� DB�� �����ϴ��� Ȯ���մϴ�.
		ResultSet rs = null;
		String DBid = "";
		String DBpw = "";
		String Pw = String.valueOf(Ppw);
		//System.out.println(Id.getClass().getName());
		//System.out.println(Pw.getClass().getName());
		try {
			String sql = "select Id, Pw from PEOPLE as p \n" + 
					"where p.Id = " + "'" + Id + "'" + " and p.Pw = " + "'" + Pw + "'";
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
		if (DBid.equals(Id) && DBpw.equals(Pw)) return true;			// ���̵�� ��й�ȣ�� ������ 1�� ��ȯ. ������ 0�� ��ȯ�մϴ�.
		else return false;
 }
}