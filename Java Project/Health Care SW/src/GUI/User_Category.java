package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class User_Category extends JFrame {
	private static int idx = 0;
	private static boolean delete = false;
	
	
	public User_Category() {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//////////////////// Title Label ////////////////////
		JLabel TitleLabel = new JLabel("카테고리");
		Font f1 = new Font("돋움", Font.BOLD, 50);
		TitleLabel.setFont(f1);
		TitleLabel.setBounds(420, 130, 300, 50);
		add(TitleLabel);
    	
		//////////////////// Category Button Panel ////////////////////
    	JPanel BtnPanel = new JPanel();
    	BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	BtnPanel.setBounds(10, 300, 1045, 160);
    	
    	JButton AddBtn = new JButton("+");
    	AddBtn.setPreferredSize(new Dimension(150, 150));
    	BtnPanel.add(AddBtn);
    	
    	add(BtnPanel);
    	
		//////////////////// Logout Button Panel ////////////////////
    	JPanel LogoutPanel = new JPanel();
    	LogoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	LogoutPanel.setBounds(10, 600, 1045, 70);
		
    	JButton LogoutBtn = new JButton("로그아웃");
    	LogoutBtn.setPreferredSize(new Dimension(100, 50));
    	LogoutPanel.add(LogoutBtn);
    	
    	add(LogoutPanel);
    	
		//////////////////// Delete Button Panel ////////////////////
    	JPanel DeletePanel = new JPanel();
    	DeletePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	DeletePanel.setBounds(10, 455, 1045, 180);
    	
    	JLabel SpaceLabel = new JLabel("");
    	SpaceLabel.setPreferredSize(new Dimension(150, 30));
    	DeletePanel.add(SpaceLabel);
    	
    	add(DeletePanel);
    	DeletePanel.setVisible(false);
    	
		//////////////////// Start Deletion Button ////////////////////
    	JButton StartDeletionBtn = new JButton("-");
    	if (idx == 0) {
    		StartDeletionBtn.setEnabled(false);
		}
    	StartDeletionBtn.setBounds(945, 250, 50, 50);
    	add(StartDeletionBtn);

        setVisible(true);
        
		//////////////////// 동적 생성 및 삭제 버튼들 ////////////////////
        JButton btn[] = new JButton[5];
        for (int i = 0; i < 5; i++) {
        	btn[i] = new JButton();
        }
        
        JButton RemoveBtn[] = new JButton[5];
        for (int i = 0; i < 5; i++) {
        	RemoveBtn[i] = new JButton();
        }
        
		//////////////////// 버튼 액션 ////////////////////
        AddBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane popup = new JOptionPane();
				String CatName = popup.showInputDialog("추가할 카테고리 이름 입력");
				if (CatName != null) {
					btn[idx].setText(CatName);
					btn[idx].setPreferredSize(new Dimension(150, 150));
					BtnPanel.add(btn[idx]);
					RemoveBtn[idx].setText("Remove");
					RemoveBtn[idx].setPreferredSize(new Dimension(150, 30));
					DeletePanel.add(RemoveBtn[idx]);
					idx = idx + 1;
					setVisible(true);
					if (idx == 5) {
						AddBtn.setEnabled(false);
					}
					if (idx > 0) {
						StartDeletionBtn.setEnabled(true);
					}
				}
			}
		});
        
        StartDeletionBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (delete == false) {
					delete = true;
					DeletePanel.setVisible(true);
					AddBtn.setEnabled(false);
				}
				else {
					delete = false;
					DeletePanel.setVisible(false);
					AddBtn.setEnabled(true);
				}
				setVisible(true);
			}
        });
        
        LogoutBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				idx = 0;
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
