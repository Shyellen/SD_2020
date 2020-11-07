package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField IdField;
	private JPasswordField PwField;
	private JButton LoginBtn, JoinBtn;

	public LoginFrame() {
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
		
		JoinBtn = new JButton("회원가입");
		JoinBtn.setBounds(229, 154, 104, 29);
		contentPane.add(JoinBtn);
		
		LoginBtn = new JButton("로그인");
		LoginBtn.setBounds(108, 154, 106, 29);
		contentPane.add(LoginBtn);
		
		PwField = new JPasswordField();
		PwField.setColumns(10);
		PwField.setBounds(157, 103, 176, 35);
		contentPane.add(PwField);
		
		setVisible(true);
		
		//회원가입 액션
		JoinBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinFrame frame = new JoinFrame();
			}
		});
		
		//로그인 액션
		LoginBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, IdField.getText()+" 로그인 성공");
				dispose();
				User_Category frame = new User_Category();
			}
		});
	}
}