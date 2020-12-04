package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AlertLateness {
	public AlertLateness(int UserData) {		
		JFrame Frame = new JFrame("Alert");
		Frame.setSize(315, 200);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);
		
		JLabel Label0 = new JLabel("", JLabel.CENTER);
		Label0.setBounds(0, 0, 300, 100);
		Frame.getContentPane().add(Label0);
		
		JButton Button0 = new JButton();
		Frame.getContentPane().add(Button0);
		
		if (UserData == 0) {
			Label0.setText("You haven't written any record for 48 hours.");
			Button0.setText("Write a record now");
			Button0.setBounds(80, 105, 140, 40);
		}
		else {
			Label0.setText("<html>There is a record for which no feedback has<br>been written for 48 hours.<html>");
			Button0.setText("View it now");
			Button0.setBounds(100, 105, 100, 40);
		}
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Frame.dispose();
			}
		});
		
		Frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		AlertLateness AlertLateness = new AlertLateness(1);
	}
}
