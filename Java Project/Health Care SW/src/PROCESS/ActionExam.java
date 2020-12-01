package PROCESS;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ActionExam extends Frame implements ActionListener
{
	// ��ư ����
	JButton btn1 = new JButton("Ŭ��1");
	JButton btn2 = new JButton("Ŭ��2");

	//������ ����
	public ActionExam()
	{
		// ���̾ƿ� ����
		this.setLayout(new FlowLayout());

		// ��ư �߰�
		this.add(btn1);
		this.add(btn2);

		// ũ�� �� ���� ����
		this.setSize(300, 200);
		this.setVisible(true);

		// �̺�Ʈ ����ϱ�
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}

	public static void main(String[] args)
	{
		// ����
		new ActionExam();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(this, e.getID());

	}
}