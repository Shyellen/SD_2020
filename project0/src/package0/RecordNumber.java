package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class RecordNumber extends JFrame {	
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	private int Datum;
	
	public RecordNumber() {	
		JFrame Frame0 = new JFrame("Write Record");
		Frame0.setSize(315, 188);
		Frame0.setLocationRelativeTo(null);
		Frame0.setResizable(false);
		Frame0.setLayout(null);
		Frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Panel0 = new JPanel();
		Panel0.setBounds(0, 0, 300, 150);
		Panel0.setLayout(null);
		Frame0.add(Panel0);	
		
		JTextArea TextArea = new JTextArea();
		TextArea.setBounds(25, 25, 250, 25);
		TextArea.setBorder(Border);
		TextArea.setLineWrap(true);
		Panel0.add(TextArea);
		
		TextArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c0 = e.getKeyChar();
                if (c0 < '0' || c0 > '9') {
                    e.consume();
                }
            }
        });
		
		JButton Button0 = new JButton("Save");
		JButton Button1 = new JButton("Cancel");
		Panel0.add(Button0);
		Panel0.add(Button1);
		Button0.setBounds(25, 80, 100, 40);
		Button1.setBounds(175, 80, 100, 40);		
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String String = TextArea.getText();
				
				if (String.length() > 0) {
					Datum = Integer.parseInt(String);
					Alert Alert = new Alert("", "Your record has successfully saved.");
					Frame0.dispose();
				}
				else {
					Alert Alert = new Alert("Error", "<html>You should input a number greater than or<br>equal to 0.<html>");
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
	
	public int GetDatum() {
		return Datum;
	}
	
	public static void main(String[] args) {
		RecordNumber RecordNumber = new RecordNumber();
	}
}
