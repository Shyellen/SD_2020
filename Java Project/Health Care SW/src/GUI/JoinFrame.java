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
		
		JoinLabel = new JLabel("ȸ������");
		Font f1 = new Font("����", Font.BOLD, 20); //�ü� ���� ����
		JoinLabel.setFont(f1); 
		JoinLabel.setBounds(159, 41, 101, 20);
		contentPane.add(JoinLabel);
		
		JLabel label = new JLabel("ID");
		label.setBounds(69, 113, 69, 20);
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("PW");
		lblUsername.setBounds(69, 163, 69, 20);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("�̸�");
		lblName.setBounds(69, 210, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("����");
		lblEmail.setBounds(69, 257, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("��ȭ��ȣ");
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
		MaleRB = new JRadioButton("����");
		FemaleRB = new JRadioButton("����", true);
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
		
		JoinCompleteBtn = new JButton("�Ϸ�");
		JoinCompleteBtn.setBounds(206, 363, 139, 29);
		contentPane.add(JoinCompleteBtn);
		
		setVisible(true);
		//ȸ�����ԿϷ� �׼�
		JoinCompleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, IdField.getText()+" ������ ȸ�������� �Ϸ�Ǿ����ϴ�.");
				dispose();
				
			}
		});

	}
}