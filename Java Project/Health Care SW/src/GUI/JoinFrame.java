package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*; // import JDBC package

public class JoinFrame extends JFrame {

	public JoinFrame(Connection conn, Statement stmt) {
		String[] values;
		values = new String[7];
		setTitle("Self Care SW");
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
				int cnt=0;
				values[cnt++]=IdField.getText();
				values[cnt++]=String.valueOf(PwField.getPassword());
				values[cnt++]=NameField.getText();
				if(ManRB.isSelected()) {
					values[cnt++]="남";
				}
				else if (WomanRB.isSelected()) {
					values[cnt++]="여";
				}
				
				values[cnt++]=BirthField.getText();
				values[cnt++]=PhoneField.getText();
				if(UserRB.isSelected()) {
					values[cnt++]="user";
				}
				else if(ExpertRB.isSelected()) {
					values[cnt++]="expert";
				}
				//for(int i=0;i<7;i++)
				//	System.out.println(values[i]);
				
				int statesignup = signup(conn, stmt, values);
				if( statesignup == 1) {
				JOptionPane.showMessageDialog(null, values[0]+" 계정의 회원가입이 완료되었습니다.");
				dispose();
				}
				else if (statesignup ==0) {		// 이미 있는 아이디 입니다.를 배너로 띄움.
					JOptionPane.showMessageDialog(null, "[실패] 이미 존재하는 아이디입니다.");
				}
			}
		});
		
		CancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public static int signup(Connection conn, Statement stmt, String[] values) {
		ResultSet rs = null;
		
		try {
			String sql;
			String sql2 = "select Id, name from PEOPLE as p \n" + 
					"where p.Id = " + "'" + values[0] + "'" + " or p.name = " + "'" + values[2] + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				if (values[0].equals(rs.getString(1))) return 0;
				if (values[2].equals(rs.getString(2))) return 0;
			}
			rs.close();
			
			sql = "INSERT INTO people(id, pw, name, sex, birth, phone, type) \n"
					+ "VALUES (" + "'" + values[0] + "', " + "'" + values[1] + "', " 
					+ "'" + values[2] + "', " + "'" + values[3] + "', " 
					+ "'" + values[4] + "', " + "'" + values[5] + "', "
					+ "'" + values[6] + "'" + ")";
			//System.out.println(sql);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(" row inserted.");
			conn.commit();
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		return 1;
	}
}