package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Recorded {
	public Recorded() {
		JFrame Frame = new JFrame("Successfully Recorded");
		Frame.setBounds(600, 200, 315, 118);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setLayout(null);
		Frame.setResizable(false);
		
		JButton Button = new JButton("OK");
		Button.setBounds(100, 20, 100, 40);
		Frame.add(Button);
		
		Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		Frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Recorded Recorded = new Recorded();
	}
}
