package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class NumberRecord extends JFrame {	
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	
	public NumberRecord() {	
		JFrame Frame = new JFrame("Write Record");
		Frame.setBounds(550, 150, 315, 188);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setLayout(null);
		Frame.setResizable(false);
		
		JPanel Panel0 = new JPanel();
		Panel0.setBounds(0, 0, 300, 150);
		Panel0.setLayout(null);
		Frame.add(Panel0);	
		
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
		
		JButton Button0 = new JButton("Finish");
		JButton Button1 = new JButton("Cancel");
		Panel0.add(Button0);
		Panel0.add(Button1);
		Button0.setBounds(25, 80, 100, 40);
		Button1.setBounds(175, 80, 100, 40);		
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String String = TextArea.getText();
				
				if (String.length() > 0) {
					int i0 = Integer.parseInt(String);
					System.out.printf("%d\n", i0);
					Recorded Recorded = new Recorded();
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
		NumberRecord NumberRecord = new NumberRecord();
	}
}
