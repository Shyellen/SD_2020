package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class RecordText extends JFrame {	
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	
	public RecordText() {	
		JFrame Frame = new JFrame("Write Record");
		Frame.setSize(414, 493);
		Frame.setLocationRelativeTo(null);		
		Frame.setResizable(false);
		Frame.setLayout(null);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
				
				if (String.length() > 0) {
					System.out.printf("%s\n", String);
					AlertRecord AlertRecord = new AlertRecord("Your record has successfully saved.");
					Frame.dispose();
				}
				else {
					AlertRecord AlertRecord = new AlertRecord("You should input at least one character.");
				}
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
		RecordText RecordText = new RecordText();
	}
}
