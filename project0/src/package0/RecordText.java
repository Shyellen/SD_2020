package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class RecordText extends JFrame {
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	private String Datum;
	
	public RecordText() {	
		JFrame Frame0 = new JFrame("Write Record");
		Frame0.setSize(414, 493);
		Frame0.setLocationRelativeTo(null);		
		Frame0.setResizable(false);
		Frame0.setLayout(null);
		Frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Panel0 = new JPanel();			
		Panel0.setBounds(0, 0, 400, 500);
		Panel0.setLayout(null);
		Frame0.add(Panel0);	
		
		JTextArea TextArea = new JTextArea();
		TextArea.setBounds(5, 5, 390, 390);
		TextArea.setBorder(Border);
		TextArea.setLineWrap(true);
		Panel0.add(TextArea);
		
		JButton Button0 = new JButton("Save");
		JButton Button1 = new JButton("Cancel");
		Button0.setBounds(20, 405, 160, 40);
		Button1.setBounds(220, 405, 160, 40);
		Panel0.add(Button0);
		Panel0.add(Button1);
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String String = TextArea.getText();
				
				if (String.length() > 0) {
					Datum = String;
					Alert Alert = new Alert("Error", "Your record has successfully saved.");
					Frame0.dispose();
				}
				else {
					Alert Alert = new Alert("Error", "You should input at least one character.");
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
	
	public String GetDatum() {
		return Datum;
	}
	
	public static void main(String[] args) {
		RecordText RecordText = new RecordText();
	}
}
