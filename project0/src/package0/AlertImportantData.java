package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AlertImportantData {
	public AlertImportantData() {		
		JFrame Frame = new JFrame("Alert");
		Frame.setSize(315, 200);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);
		
		JLabel Label = new JLabel("There is an important record from your patient.", JLabel.CENTER);
		Label.setBounds(0, 0, 300, 100);
		Frame.getContentPane().add(Label);
		
		JButton Button = new JButton("View it now");
		Button.setBounds(100, 105, 100, 40);
		Frame.getContentPane().add(Button);
		
		Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Frame.dispose();
			}
		});
		
		Frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		AlertImportantData AlertEmergency = new AlertImportantData();
	}
}
