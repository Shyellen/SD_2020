package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Calendar;

@SuppressWarnings("serial")
public class SetRecordType extends JFrame {
	public int type = 0;
	
	public SetRecordType() {		
		JFrame Frame0 = new JFrame("Write Record");
		Frame0.setSize(265, 163);
		Frame0.setLocationRelativeTo(null);
		Frame0.setResizable(false);
		Frame0.setLayout(null);
		Frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Panel0 = new JPanel();
		Panel0.setBounds(0, 25, 250, 50);		
		Panel0.setLayout(new FlowLayout());
		Frame0.add(Panel0);
		
		Container Container = getContentPane();
		Container.setLayout(new FlowLayout());
		
		JRadioButton RadioButton0 = new JRadioButton("Boolean");
		JRadioButton RadioButton1 = new JRadioButton("Number");
		JRadioButton RadioButton2 = new JRadioButton("Text");
		ButtonGroup ButtonGroup = new ButtonGroup();
		ButtonGroup.add(RadioButton0);
		ButtonGroup.add(RadioButton1);
		ButtonGroup.add(RadioButton2);
		Panel0.add(RadioButton0);
		Panel0.add(RadioButton1);
		Panel0.add(RadioButton2);
		
		JPanel Panel1 = new JPanel();
		Panel1.setBounds(0, 75, 250, 50);
		Panel1.setLayout(null);
		Frame0.add(Panel1);	
		
		JButton Button0 = new JButton("Save");
		JButton Button1 = new JButton("Cancel");
		Panel1.add(Button0);
		Panel1.add(Button1);
		Button0.setBounds(30, 10, 80, 30);
		Button1.setBounds(140, 10, 80, 30);	
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {				
				if (RadioButton0.isSelected() == true) {
					Frame0.dispose();
					type = 0;
				}
				else if (RadioButton1.isSelected() == true) {
					Frame0.dispose();
					type = 1;					
				}
				else if (RadioButton2.isSelected() == true) {
					Frame0.dispose();
					type = 2;
				}
				else {
					new Alert("Error", "You should choose one of three types.");
				}
			}
		});
		
		Button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Frame0.dispose();
			}
		});
		
		Frame0.setVisible(true);
	}
	public int GetType() {
		return type;
	}
	public static void main(String[] args) {
		new SetRecordType();
	}
}
