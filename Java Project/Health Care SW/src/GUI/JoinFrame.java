package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JoinFrame extends JFrame {

	public JoinFrame() {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 530);
		setResizable(false);
		setLocationRelativeTo(null);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		//////////////////// Title Label ////////////////////
		JLabel TitleLabel = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20); //궁서 바탕 돋움
		TitleLabel.setFont(f1); 
		TitleLabel.setBounds(150, 40, 100, 20);
		
		//////////////////// Label Panel ////////////////////
		JPanel LabelPanel = new JPanel();
		LabelPanel.setLayout(new GridLayout(7, 1, 5, 5));
		LabelPanel.setBounds(70, 100, 70, 280);
		
		JLabel IdLabel = new JLabel("아이디");
		LabelPanel.add(IdLabel);
		JLabel PwLabel = new JLabel("비밀번호");
		LabelPanel.add(PwLabel);
		JLabel NameLabel = new JLabel("이름");
		LabelPanel.add(NameLabel);
		JLabel SexLabel = new JLabel("성별");
		LabelPanel.add(SexLabel);
		JLabel BirthLabel = new JLabel("생년월일");
		LabelPanel.add(BirthLabel);
		JLabel PhoneLabel = new JLabel("전화번호");
		LabelPanel.add(PhoneLabel);
		JLabel TypeLabel = new JLabel("이용타입");
		LabelPanel.add(TypeLabel);
		
		//////////////////// DataPanel //////////////////// 
		JPanel DataPanel = new JPanel();
		DataPanel.setLayout(new GridLayout(7, 1, 5, 5));
		DataPanel.setBounds(150, 100, 150, 280);
		
		JTextField IdField = new JTextField();
		DataPanel.add(IdField);
		
		JPasswordField PwField = new JPasswordField();
		DataPanel.add(PwField);
		
		JTextField NameField = new JTextField();
		DataPanel.add(NameField);
		
		JPanel SexPanel = new JPanel();
		JRadioButton ManRB = new JRadioButton("남성", true);
		JRadioButton WomanRB = new JRadioButton("여성");
		ButtonGroup SG = new ButtonGroup();
		SG.add(ManRB);
		SG.add(WomanRB);
		SexPanel.add(ManRB);
		SexPanel.add(WomanRB);
		DataPanel.add(SexPanel);
		
		JTextField BirthField = new JTextField();
		BirthField.setText("yyyymmdd");
		DataPanel.add(BirthField);
		
		JTextField PhoneField = new JTextField();
		PhoneField.setText("000-0000-0000");
		DataPanel.add(PhoneField);
		
		JPanel TypePanel = new JPanel();
		JRadioButton UserRB = new JRadioButton("일반인", true);
		JRadioButton ExpertRB = new JRadioButton("전문가");
		ButtonGroup TG = new ButtonGroup();
		TG.add(UserRB);
		TG.add(ExpertRB);
		TypePanel.add(UserRB);
		TypePanel.add(ExpertRB);
		DataPanel.add(TypePanel);
		
        //////////////////// Button Panel ////////////////////
		JPanel BtnPanel = new JPanel();
		BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		BtnPanel.setBounds(0, 430, 400, 50);
		
		JButton CancelBtn = new JButton("취소");
		BtnPanel.add(CancelBtn);
		
		JButton CompleteBtn = new JButton("완료");
		BtnPanel.add(CompleteBtn);
		
        //////////////////// Add to Container ////////////////////
		c.add(TitleLabel);
		c.add(LabelPanel);
		c.add(DataPanel);
		c.add(BtnPanel);
		
		setVisible(true);
		
		//////////////////// Add ActionListener  ////////////////////
		CompleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, IdField.getText()+" 계정의 회원가입이 완료되었습니다.");
				dispose();
			}
		});
		
		CancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}