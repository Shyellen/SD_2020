package PROCESS;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ActionExam extends Frame implements ActionListener
{
	// 버튼 생성
	JButton btn1 = new JButton("클릭1");
	JButton btn2 = new JButton("클릭2");

	//생성자 설정
	public ActionExam()
	{
		// 레이아웃 설정
		this.setLayout(new FlowLayout());

		// 버튼 추가
		this.add(btn1);
		this.add(btn2);

		// 크기 및 보기 설정
		this.setSize(300, 200);
		this.setVisible(true);

		// 이벤트 등록하기
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}

	public static void main(String[] args)
	{
		// 실행
		new ActionExam();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(this, e.getID());

	}
}