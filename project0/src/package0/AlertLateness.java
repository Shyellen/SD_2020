package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AlertLateness {
	public AlertLateness() {		
		JFrame Frame = new JFrame("Alert");
		Frame.setSize(315, 200);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame.getContentPane().setLayout(null);
		
		JLabel Label = new JLabel("It is time to write record, don't be late.", JLabel.CENTER);
		Label.setBounds(0, 0, 300, 100);
		Frame.getContentPane().add(Label);
		
		JButton Button = new JButton("Write Record");
		Button.setBounds(90, 105, 120, 40);
		Frame.getContentPane().add(Button);
		
		Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Frame.dispose();
			}
		});
		
		Frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		AlertLateness AlertLateness = new AlertLateness();
	}
}
