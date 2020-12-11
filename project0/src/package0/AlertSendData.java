package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AlertSendData {
	public AlertSendData() {		
		JFrame Frame = new JFrame();
		Frame.setSize(315, 200);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame.getContentPane().setLayout(null);
		JLabel Label = new JLabel("Do you really want to send these records?", JLabel.CENTER);
		Label.setBounds(0, 0, 300, 100);
		Frame.getContentPane().add(Label);
		
		JButton Button0 = new JButton("Yes");
		Button0.setBounds(40, 105, 90, 40);
		Frame.getContentPane().add(Button0);
		
		JButton Button1 = new JButton("No");
		Button1.setBounds(170, 105, 90, 40);
		Frame.getContentPane().add(Button1);
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Alert Alert = new Alert("", "Your records have successfully sent.");
				Frame.dispose();
			}
		});
		
		Button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Frame.dispose();
			}
		});
		
		Frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AlertSendData();
	}
}
