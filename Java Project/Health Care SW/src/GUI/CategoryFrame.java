package GUI;

import PROCESS.CategoryProcess;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*; // import JDBC package

public class CategoryFrame extends JFrame {
	private JButton InsertBtn;
	private JButton DeleteBtn;
	private JPanel contentPane;
	
	private static int idx = 0;
	private static boolean delete = false;
	public static String name=null;
	private boolean payTF = false;
	
	public CategoryFrame(Connection conn, Statement stmt, String Id) {
		setTitle("Self Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		idx = CategoryProcess.checkCatCnt(conn, stmt, Id);
    	System.out.println("ī�װ� ��: "+idx);
		
		//////////////////////////////////////// Title Label
		JLabel TitleLabel = new JLabel("ī�װ�");
		Font f1 = new Font("����", Font.BOLD, 50);
		TitleLabel.setFont(f1);
		TitleLabel.setBounds(420, 130, 300, 50);
		add(TitleLabel);
    	
		//////////////////////////////////////// Category Button Panel
    	JPanel BtnPanel = new JPanel();
    	BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	BtnPanel.setBounds(10, 300, 1045, 160);
    	
    	InsertBtn = new JButton("+");
    	InsertBtn.setPreferredSize(new Dimension(150, 150));
    	BtnPanel.add(InsertBtn);
    	if (idx == 5) {
			InsertBtn.setEnabled(false);
    	}
    	
    	add(BtnPanel);
    	
    	//////////////////////////////////////// Logout Button Panel
    	JPanel LogoutPanel = new JPanel();
    	LogoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	LogoutPanel.setBounds(10, 600, 1045, 70);
		
    	JButton LogoutBtn = new JButton("�α׾ƿ�");
    	LogoutBtn.setPreferredSize(new Dimension(100, 50));
    	LogoutPanel.add(LogoutBtn);
    	
    	add(LogoutPanel);
    	
    	////////////////////////////���� ��� �г� ////////////////////////////
    	JPanel payPanel = new JPanel();
    	payPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	payPanel.setBounds(400, 600, 1045, 70);
    	
    	JButton PaymentBtn = new JButton("�����ϱ�");
    	PaymentBtn.setEnabled(!payTF);
    	PaymentBtn.setPreferredSize(new Dimension(100, 50));
    	payPanel.add(PaymentBtn);
    	
    	add(payPanel);
    	
    	//////////////////////////////////////// Delete Button Panel
    	JPanel DeletePanel = new JPanel();
    	DeletePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	DeletePanel.setBounds(10, 455, 1045, 180);
    	
    	JLabel SpaceLabel = new JLabel("");
    	SpaceLabel.setPreferredSize(new Dimension(150, 30));
    	DeletePanel.add(SpaceLabel);
    	
    	add(DeletePanel);
    	DeletePanel.setVisible(false);
    	
    	//////////////////////////////////////// Start Deletion Button
    	JButton DeleteBtn = new JButton("-");
    	if (idx == 0) {
    		DeleteBtn.setEnabled(false);
		}
    	if (idx == 5) {
			InsertBtn.setEnabled(false);
    	}
    	DeleteBtn.setBounds(945, 250, 50, 50);
    	add(DeleteBtn);
        
    	//////////////////////////////////////// ���� ���� �� ���� ��ư��
    	JButton btn[] = new JButton[5];
    	for (int i = 0; i < 5; i++)
    		btn[i] = new JButton();
    	
        JButton RemoveBtn[] = new JButton[5];
        for (int i = 0; i < 5; i++) {
        	RemoveBtn[i] = new JButton();
        }
		
        if (idx > 0) {
        	String CatName[] = CategoryProcess.checkCatCname(conn, stmt, Id, idx);
    		for (int i=0; i < idx; i++) {
    			btn[i].setText(CatName[i]);
    			btn[i].setPreferredSize(new Dimension(150, 150));
    			RemoveBtn[i].setText("Remove");
    			RemoveBtn[i].setPreferredSize(new Dimension(150, 30));
    			BtnPanel.add(btn[i]);
    			DeletePanel.add(RemoveBtn[i]);
    		}
        }
        
        setVisible(true);
        
		//////////////////// ��ư �׼� ////////////////////
        InsertBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane popup = new JOptionPane();
				String CatName = popup.showInputDialog("�߰��� ī�װ� �̸� �Է�(2���� �̻�)");
				if(CatName.length() >= 2) {
					boolean check = true;
					if (idx > 0) {
						for (int i=0; i<idx; i++) {
							if (CatName.equals(btn[i].getText())) {
								JOptionPane.showMessageDialog(null, "�̹� �����ϴ� �̸��Դϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
								check = false;
							}
						}
					}
					if(check == true) {
						CategoryProcess.insertCat(conn, stmt, Id, CatName);
						dispose();
						CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "2���� �̻� �Է����ּ���.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        
        DeleteBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (delete == false) {
					delete = true;
					DeletePanel.setVisible(true);
					InsertBtn.setEnabled(false);
				}
				else {
					delete = false;
					DeletePanel.setVisible(false);
					if (idx == 5) {
						InsertBtn.setEnabled(false);
					}
					else
						InsertBtn.setEnabled(true);
				}
				setVisible(true);
			}
        });
        
        LogoutBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				idx = 0;
				dispose();
				LoginFrame frame = new LoginFrame(conn, stmt);
			}
        });
        
        btn[0].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[0].getText());
				name = btn[0].getText();
				dispose();
				UserFrame frame = new UserFrame(conn, stmt, Id);
			}
		});
        
        btn[1].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[1].getText());
				name = btn[1].getText();
				dispose();
				UserFrame frame = new UserFrame(conn, stmt, Id);
			}
		});

        btn[2].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[2].getText());
				name = btn[2].getText();
				dispose();
				UserFrame frame = new UserFrame(conn, stmt, Id);
			}
		});
        
        btn[3].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[3].getText());
				name = btn[3].getText();
				dispose();
				UserFrame frame = new UserFrame(conn, stmt, Id);
			}
		});
        
        btn[4].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[4].getText());
				name = btn[4].getText();
				dispose();
				UserFrame frame = new UserFrame(conn, stmt, Id);
			}
		});
        
        RemoveBtn[0].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				CategoryProcess.removeCat(conn, stmt, Id, btn[0].getText());
				JOptionPane.showMessageDialog(null, btn[0].getText()+"���� �Ϸ�");
				dispose();
				CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
			}
		});
        
        RemoveBtn[1].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				CategoryProcess.removeCat(conn, stmt, Id, btn[1].getText());
				JOptionPane.showMessageDialog(null, btn[1].getText()+"���� �Ϸ�");
				dispose();
				CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
			}
		});
        
        RemoveBtn[2].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				CategoryProcess.removeCat(conn, stmt, Id, btn[2].getText());
				JOptionPane.showMessageDialog(null, btn[2].getText()+"���� �Ϸ�");
				dispose();
				CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
			}
		});
        
        RemoveBtn[3].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				CategoryProcess.removeCat(conn, stmt, Id, btn[3].getText());
				JOptionPane.showMessageDialog(null, btn[3].getText()+"���� �Ϸ�");
				dispose();
				CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
			}
		});
        
        RemoveBtn[4].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				CategoryProcess.removeCat(conn, stmt, Id, btn[4].getText());
				JOptionPane.showMessageDialog(null, btn[4].getText()+"���� �Ϸ�");
				dispose();
				CategoryFrame frame = new CategoryFrame(conn, stmt, Id);
			}
		});
        
	}
}
