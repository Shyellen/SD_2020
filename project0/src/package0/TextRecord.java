package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class TextRecord extends JFrame {	
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	
	public TextRecord() {	
		JFrame Frame = new JFrame("Write Record");
		Frame.setBounds(550, 150, 414, 493);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setLayout(null);
		Frame.setResizable(false);
		
		JPanel Panel0 = new JPanel();			
		Panel0.setBounds(0, 0, 400, 500);
		Panel0.setLayout(null);
		Frame.add(Panel0);	
		
		JTextArea TextArea = new JTextArea();
		TextArea.setBounds(5, 5, 390, 390);
		TextArea.setBorder(Border);
		TextArea.setLineWrap(true);
		Panel0.add(TextArea);
		
		JButton Button0 = new JButton("Finish");
		JButton Button1 = new JButton("Cancel");
		Button0.setBounds(20, 405, 160, 40);
		Button1.setBounds(220, 405, 160, 40);
		Panel0.add(Button0);
		Panel0.add(Button1);	
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String String = TextArea.getText();
				System.out.printf("%s\n", String);
				Recorded Recorded = new Recorded();
			}
		});
		
		Button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		Frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		TextRecord TextRecord = new TextRecord();
	}
}
