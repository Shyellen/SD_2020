package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddEventFrame extends JFrame implements FocusListener{

	private Container c;
	public JRadioButton booleanRB;
	public JRadioButton numberRB;
	public JRadioButton textRB;
	public JTextField NameField;
	public JButton ConfirmBtn;
	
	public AddEventFrame() {
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
		Font f1 = new Font("µ¸¿ò", Font.BOLD, 20); //±Ã¼­ ¹ÙÅÁ µ¸¿ò
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
		JRadioButton numberRB = new JRadioButton("¼öÄ¡");
		JRadioButton textRB = new JRadioButton("ÅØ½ºÆ®");
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
		
		ConfirmBtn = new JButton("È®ÀÎ");
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
				if (booleanRB.isSelected()) {
					JOptionPane.showMessageDialog(null, NameField.getText()+", "+ booleanRB.getText());
				}
				else if (numberRB.isSelected()) {
					JOptionPane.showMessageDialog(null, NameField.getText()+", "+ numberRB.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, NameField.getText()+", "+ textRB.getText());
				}
				dispose();
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
