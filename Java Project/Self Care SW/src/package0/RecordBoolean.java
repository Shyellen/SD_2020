package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Calendar;

@SuppressWarnings("serial")
public class RecordBoolean extends JFrame {	
	public String username;
	public int year, month, date, hour, minute, second;
	public boolean datum;
	
	public RecordBoolean(String Username) {
		Calendar Calendar0 = Calendar.getInstance();
		
		JFrame Frame0 = new JFrame("Write Record");
		Frame0.setSize(215, 163);
		Frame0.setLocationRelativeTo(null);
		Frame0.setResizable(false);
		Frame0.setLayout(null);
		Frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Panel0 = new JPanel();
		Panel0.setBounds(0, 25, 200, 50);
		Panel0.setLayout(new FlowLayout());
		Frame0.add(Panel0);
		
		Container Container = getContentPane();
		Container.setLayout(new FlowLayout());
		
		JRadioButton RadioButton0 = new JRadioButton("Yes");
		JRadioButton RadioButton1 = new JRadioButton("No");
		ButtonGroup ButtonGroup = new ButtonGroup();
		ButtonGroup.add(RadioButton0);
		ButtonGroup.add(RadioButton1);
		Panel0.add(RadioButton0);
		Panel0.add(RadioButton1);
		
		JPanel Panel1 = new JPanel();
		Panel1.setBounds(0, 75, 200, 50);
		Panel1.setLayout(null);
		Frame0.add(Panel1);	
		
		JButton Button0 = new JButton("Save");
		JButton Button1 = new JButton("Cancel");
		Panel1.add(Button0);
		Panel1.add(Button1);
		Button0.setBounds(17, 10, 75, 30);
		Button1.setBounds(108, 10, 75, 30);	
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (RadioButton0.isSelected() == false && RadioButton1.isSelected() == false) {
					new Alert("Error", "You should select one of yes or no.");
				}
				else {
					username = Username;
					year = Calendar0.get(Calendar.YEAR);
					month = Calendar0.get(Calendar.MONTH) + 1;
					date = Calendar0.get(Calendar.DAY_OF_MONTH);
					hour = Calendar0.get(Calendar.HOUR_OF_DAY);
					minute = Calendar0.get(Calendar.MINUTE);
					second = Calendar0.get(Calendar.SECOND);
					
					if (RadioButton0.isSelected() == true) {
						datum = true;
						new Alert("", "Your record has successfully saved.");
						Frame0.dispose();
					}
					else if (RadioButton1.isSelected() == true) {
						datum = false;
						new Alert("", "Your record has successfully saved.");
						Frame0.dispose();
					}
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
	
	public static void main(String[] args) {
		new RecordBoolean("");
	}
}
