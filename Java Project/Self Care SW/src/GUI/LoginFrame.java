package GUI;

import PROCESS.LoginProcess;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; // import JDBC package

public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField IdField;
	private JPasswordField PwField;

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
		contentPane.setBackground(Color.white);
		
		ImageIcon loginImage = new ImageIcon("images/Self-Care.jpg");
		Image img = loginImage.getImage();
		Image changeImg = img.getScaledInstance(400, 100, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel imgLabel = new JLabel(changeIcon);
		imgLabel.setBounds(0, 0, 400, 100);
		contentPane.add(imgLabel);
		
		JLabel IdLabel = new JLabel("ID");
		IdLabel.setBounds(41, 122, 69, 35);
		contentPane.add(IdLabel);
		
		JLabel PwLabel = new JLabel("PW");
		PwLabel.setBounds(41, 173, 69, 35);
		contentPane.add(PwLabel);
		
		IdField = new JTextField();
		IdField.setBounds(157, 122, 176, 35);
		IdField.setColumns(10);
		contentPane.add(IdField);
		
		PwField = new JPasswordField();
		PwField.setBounds(157, 173, 176, 35);
		PwField.setColumns(10);
		contentPane.add(PwField);
		
		RoundedButton JoinBtn = new RoundedButton("ȸ������");
		JoinBtn.setBounds(229, 224, 104, 29);
		contentPane.add(JoinBtn);
		
		RoundedButton LoginBtn = new RoundedButton("�α���");
		LoginBtn.setBounds(108, 224, 106, 29);
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
				
				if (LoginProcess.checkID(conn, stmt, Id, Pw)) {
					String Type = LoginProcess.checkType(conn, stmt, Id);
					JOptionPane.showMessageDialog(null, "[����] " + Id + " Ÿ��: " + Type);
					if (Type.equals("expert")) {
						ExpertFrame frame = new ExpertFrame(conn, stmt, Id);
					}
					else {
						CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "[����] ���̵� ��й�ȣ�� Ȯ���ϼ���.");
				}
			}
		});
	}
	
}