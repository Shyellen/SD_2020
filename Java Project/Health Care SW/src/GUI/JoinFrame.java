package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JoinFrame extends JFrame {

	private JPanel contentPane;
	private JLabel JoinLabel;
	private JButton JoinCompleteBtn;
	private JTextField IdField;
	private JPasswordField PwField;
	private JTextField NameField;
	private JRadioButton MaleRB;
	private JRadioButton FemaleRB;
	private JTextField PhoneField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JoinFrame frame = new JoinFrame();
	}

	/**
	 * Create the frame.
	 */
	public JoinFrame() {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JoinLabel = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20); //궁서 바탕 돋움
		JoinLabel.setFont(f1); 
		JoinLabel.setBounds(159, 41, 101, 20);
		contentPane.add(JoinLabel);
		
		JLabel label = new JLabel("ID");
		label.setBounds(69, 113, 69, 20);
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("PW");
		lblUsername.setBounds(69, 163, 69, 20);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(69, 210, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("성별");
		lblEmail.setBounds(69, 257, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setBounds(69, 304, 69, 20);
		contentPane.add(lblPhone);
		
		IdField = new JTextField();
		IdField.setColumns(10);
		IdField.setBounds(159, 106, 186, 35);
		contentPane.add(IdField);
		
		PwField = new JPasswordField();
		PwField.setColumns(10);
		PwField.setBounds(159, 156, 186, 35);
		contentPane.add(PwField);
		
		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(159, 203, 186, 35);
		contentPane.add(NameField);

		ButtonGroup SexRGroup = new ButtonGroup();
		MaleRB = new JRadioButton("남성");
		FemaleRB = new JRadioButton("여성", true);
		SexRGroup.add(MaleRB);
		SexRGroup.add(FemaleRB);
        MaleRB.setBounds(159, 250, 186, 35);
        FemaleRB.setBounds(240, 250, 186, 35);
		contentPane.add(FemaleRB);
		contentPane.add(MaleRB);
		
		
		PhoneField = new JTextField();
		PhoneField.setColumns(10);
		PhoneField.setBounds(159, 297, 186, 35);
		contentPane.add(PhoneField);
		
		JoinCompleteBtn = new JButton("완료");
		JoinCompleteBtn.setBounds(206, 363, 139, 29);
		contentPane.add(JoinCompleteBtn);
		
		setVisible(true);
		//회원가입완료 액션
		JoinCompleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, IdField.getText()+" 계정의 회원가입이 완료되었습니다.");
				dispose();
				
			}
		});

	}
}