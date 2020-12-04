package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AlertFeedback {
	public AlertFeedback() {		
		JFrame Frame = new JFrame("Alert");
		Frame.setSize(315, 200);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.getContentPane().setLayout(null);
		
		JLabel Label = new JLabel("<html>There is a feedback from your expert about<br>your record.<html>", JLabel.CENTER);
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
		AlertFeedback AlertEmergency = new AlertFeedback();
	}
}
