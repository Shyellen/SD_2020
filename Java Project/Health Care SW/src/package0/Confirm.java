package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Confirm {
	public Confirm(String Title, String Text, String Message) {		
		JFrame Frame = new JFrame(Title);
		Frame.setSize(315, 200);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame.getContentPane().setLayout(null);
		
		JLabel Label = new JLabel(Text, JLabel.CENTER);
		Label.setBounds(0, 0, 300, 100);
		Frame.getContentPane().add(Label);
		
		JButton Button0 = new JButton("Yes");
		JButton Button1 = new JButton("No");
		Button0.setBounds(40, 105, 90, 40);
		Button1.setBounds(170, 105, 90, 40);
		Frame.getContentPane().add(Button0);
		Frame.getContentPane().add(Button1);
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Alert("", Message);
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
		new Confirm("Title", "Text", "Message");
	}
}
