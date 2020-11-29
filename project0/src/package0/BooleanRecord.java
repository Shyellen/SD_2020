package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class BooleanRecord extends JFrame {	
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	
	public BooleanRecord() {		
		setBounds(550, 150, 400, 75);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Write Record");
		
		Container Container = getContentPane();
		Container.setLayout(new FlowLayout());
		
		JRadioButton RadioButton0 = new JRadioButton("Yes");
		JRadioButton RadioButton1 = new JRadioButton("No");
		ButtonGroup ButtonGroup = new ButtonGroup();
		ButtonGroup.add(RadioButton0);
		ButtonGroup.add(RadioButton1);
		Container.add(RadioButton0);
		Container.add(RadioButton1);
		
		JButton Button0 = new JButton("Finish");
		JButton Button1 = new JButton("Cancel");
		add(Button0);
		add(Button1);		
		
		Button0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (RadioButton0.isSelected() == true) {
					System.out.printf("Yes\n");
				}
				else {
					System.out.printf("No\n");
				}
				
				Recorded Recorded = new Recorded();
			}
		});
		
		Button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		BooleanRecord BooleanRecord = new BooleanRecord();
	}
}
