package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import PROCESS.UserEventProcess;

public class AddEventFrame extends JFrame implements FocusListener{

	private Container c;
	public JRadioButton booleanRB;
	public JRadioButton numberRB;
	public JRadioButton textRB;
	public JTextField NameField;
	public JButton ConfirmBtn;
	
	public AddEventFrame(Connection conn, Statement stmt, String ID, String catName) {
		setTitle("Add Event");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		addFocusListener(this);
		
		c = getContentPane();
		c.setLayout(null);
		
		//////////////////// Name Panel
		JPanel LabelPanel = new JPanel();
		LabelPanel.setLayout(new GridLayout(2, 1, 5, 5));
		LabelPanel.setBounds(50, 20, 100, 100);
		
		JLabel NameLabel = new JLabel("Name");
		Font f1 = new Font("돋움", Font.BOLD, 20); //궁서 바탕 돋움
		NameLabel.setFont(f1);
		LabelPanel.add(NameLabel);
		
		JLabel TypeLabel = new JLabel("Type");
		TypeLabel.setFont(f1);
		LabelPanel.add(TypeLabel);
		
		//////////////////// Input Panel
		JPanel InputPanel = new JPanel();
		InputPanel.setLayout(new GridLayout(2, 1, 5, 5));
		InputPanel.setBounds(150, 20, 250, 100);
		
		NameField = new JTextField();
		NameField.setFont(f1);
		InputPanel.add(NameField);
		
		JPanel TypePanel = new JPanel();
		JRadioButton booleanRB = new JRadioButton("OX", true);
		JRadioButton numberRB = new JRadioButton("수치");
		JRadioButton textRB = new JRadioButton("텍스트");
		ButtonGroup TG = new ButtonGroup();
		TG.add(booleanRB);
		TG.add(numberRB);
		TG.add(textRB);
		TypePanel.add(booleanRB).setFont(f1);
		TypePanel.add(numberRB).setFont(f1);
		TypePanel.add(textRB).setFont(f1);
		InputPanel.add(TypePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		buttonPanel.setBounds(20, 140, 400, 50);
		
		ConfirmBtn = new JButton("확인");
		ConfirmBtn.setFont(f1);
		ConfirmBtn.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(ConfirmBtn);
		
		c.add(LabelPanel);
		c.add(InputPanel);
		c.add(buttonPanel);
		
		setVisible(true);
		
		////////////////////Add ActionListener  ////////////////////
		ConfirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(NameField.getText().length() > 2 ) {
					if (booleanRB.isSelected()) {
						JOptionPane.showMessageDialog(null, NameField.getText()+", "+ booleanRB.getText());
						UserEventProcess.insertEve(conn, stmt, ID, NameField.getText().toString(), "bool", catName);
					}
					else if (numberRB.isSelected()) {
						JOptionPane.showMessageDialog(null, NameField.getText()+", "+ numberRB.getText());
						UserEventProcess.insertEve(conn, stmt, ID, NameField.getText().toString(), "num", catName);
					}
					else {
						JOptionPane.showMessageDialog(null, NameField.getText()+", "+ textRB.getText());
						UserEventProcess.insertEve(conn, stmt, ID, NameField.getText().toString(), "text", catName);
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "2글자 이상 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		dispose();
		
	}

}
