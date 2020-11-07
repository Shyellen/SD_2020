package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class User_Category extends JFrame {
	private static int idx = 0;
	
	public User_Category() {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel TitleLabel = new JLabel("카테고리");
		Font f1 = new Font("돋움", Font.BOLD, 50);
		TitleLabel.setFont(f1);
    	
    	JPanel BtnPanel = new JPanel();
    	BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	
    	JPanel LogoutPanel = new JPanel();
    	LogoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton AddBtn = new JButton("+");
    	AddBtn.setPreferredSize(new Dimension(150, 150));
    	
    	JButton LogoutBtn = new JButton("로그아웃");
    	LogoutBtn.setPreferredSize(new Dimension(100, 50));
    	
    	// 배치
    	setLayout(null);
    	TitleLabel.setBounds(420, 130, 300, 50);
    	BtnPanel.setBounds(10, 300, 1045, 180);
    	LogoutPanel.setBounds(10, 600, 1045, 70);
    	
        
    	// 추가
    	BtnPanel.add(AddBtn);
    	LogoutPanel.add(LogoutBtn);
        add(TitleLabel);
        add(BtnPanel);
        add(LogoutPanel);
        
        setVisible(true);
        
        // 버튼 ActionListener
        JButton btn[] = new JButton[6];
        for (int i = 0; i < 5; i++) {
        	btn[i] = new JButton();
        }
        AddBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane aa = new JOptionPane();
				String CatName = aa.showInputDialog("추가할 카테고리 이름 입력");
				if (CatName != null) {
					btn[idx].setText(CatName);
					btn[idx].setPreferredSize(new Dimension(150, 150));
					BtnPanel.add(btn[idx]);
					idx = idx + 1;
					setVisible(true);
					if (idx == 5) {
						AddBtn.setEnabled(false);
					}
				}
			}
		});
        
        LogoutBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame frame = new LoginFrame();
			}
        });
        btn[0].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[0].getText());
			}
		});
        
        btn[1].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[1].getText());
			}
		});
        
        btn[2].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[2].getText());
			}
		});
        
        btn[3].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[3].getText());
			}
		});
        
        btn[4].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[4].getText());
			}
		});
	}
}
