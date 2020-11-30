package package0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class RecordBoolean extends JFrame {	
	private EtchedBorder Border = new EtchedBorder(EtchedBorder.LOWERED);
	
	public RecordBoolean() {	
		setTitle("Write Record");
		setSize(400, 75);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
					AlertRecord AlertRecord = new AlertRecord("Your record has successfully saved.");
					dispose();
				}
				else if (RadioButton1.isSelected() == true) {
					System.out.printf("No\n");
					AlertRecord AlertRecord = new AlertRecord("Your record has successfully saved.");
					dispose();
				}
				else {
					AlertRecord AlertRecord = new AlertRecord("You should select one of yes or no.");
				}
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
		RecordBoolean RecordBoolean = new RecordBoolean();
	}
}
