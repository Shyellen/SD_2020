package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import PROCESS.CategoryProcess;
import PROCESS.start;
import package0.*;
import PROCESS.UserEventProcess;

import java.sql.*; // import JDBC package

@SuppressWarnings("unused")
public class UserFrame extends JFrame {
	private JPanel contentPane;
	private boolean payTF;
	
	int idx = 0;
	private int i = 0;

	public UserFrame(Connection conn, Statement stmt, String ID, String name) {
		setTitle("Self Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//////////////////////////// �⺻ ����Ʈ�� ////////////////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		idx = UserEventProcess.checkEveCnt(conn, stmt, ID, name);
		
		//////////////////////////// ī�װ� �̸� ��� �г�  ////////////////////////////
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.white);
		contentPane.add(northPanel, BorderLayout.NORTH);
		JLabel catName = new JLabel(name);
		catName.setFont(new Font("����", Font.BOLD, 50));
		catName.setBackground(new Color(204, 255, 255));
		northPanel.add(catName);
		
		//////////////////////////// ���� ��� �г� ////////////////////////////
		payTF = Payment.checkPayment(conn, stmt, ID);
		
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(1080, 50));
		southPanel.setLayout(new GridLayout(1, 3, 5, 5));
		southPanel.setBackground(Color.white);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		southPanel.add(horizontalStrut);
		
		JPanel ADpanel = new JPanel();
		ImageIcon ad = new ImageIcon("images/m-logo.png");
		Image img = ad.getImage();
		Image changeImg = img.getScaledInstance(340, 50, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel imgLabel = new JLabel(changeIcon);
		ADpanel.add(imgLabel);
		southPanel.add(ADpanel);
		
		ADpanel.setVisible(!payTF);
		
		RoundedButton3 PaymentBtn = new RoundedButton3("�����ϱ�");
		if (payTF == true) {
			PaymentBtn.setEnabled(!payTF);
			PaymentBtn.setText("�����Ϸ�");
		}
		southPanel.add(PaymentBtn);
		
		contentPane.add(southPanel, BorderLayout.SOUTH);
		//////////////////////////// �߾� �г�: ��ư+������ ////////////////////////////
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.white);
		centerPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		//////////////////////////// �߾�-���� ��ư �г� ////////////////////////////
		JPanel btnPanel = new JPanel();
		centerPanel.add(btnPanel, BorderLayout.WEST);
		btnPanel.setLayout(new GridLayout(7, 1, 5, 5));
		btnPanel.setBackground(Color.white);
		
		RoundedButton3 btnAddEvent = new RoundedButton3("Add");
		btnPanel.add(btnAddEvent);
		
		RoundedButton3 btnDelEvent = new RoundedButton3("Del");
		btnPanel.add(btnDelEvent);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut_2);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut_3);
		
		RoundedButton3 btnBackButton = new RoundedButton3("��");
		btnPanel.add(btnBackButton);
		
		//////////////////////////// �߾�-�߾� ������ ��� �г� ////////////////////////////
		JPanel MainArea = new JPanel(new GridBagLayout());
		MainArea.setBackground(Color.white);
		centerPanel.add(new JScrollPane(MainArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		
		Boolean exist[] = new Boolean[10];
		int types[] = new int[10];
		JButton Button0[] = new JButton[10];
		JButton Button1[] = new JButton[10];
		JButton Button2[] = new JButton[10];
		JButton Button3[] = new JButton[10];
		JButton Button4[] = new JButton[10];
		RecordBoolean RecordBoolean[] = new RecordBoolean[10];
		RecordNumber RecordNumber[] = new RecordNumber[10];
		RecordText RecordText[] = new RecordText[10];
		
		for (i = 0; i < 10; i++) {
			exist[i] = false;
			Button0[i] = new JButton("��� ����/����");
			Button1[i] = new JButton("��� ����");
			Button2[i] = new JButton("��� ����");
			Button3[i] = new JButton("�̹���ȭ");
			Button4[i] = new JButton("��� ����");
			
			Button0[i].addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					if (exist[i] == true) {
						int result = JOptionPane.showConfirmDialog(null, "<html>There is already a record at that.<br>Do you want to overwrite this record?<html>");
						if (result == JOptionPane.YES_OPTION) {
							
						}
						else {
							
						}
					}
					else {						
						String[] TypeList = { "Boolean", "Number", "Text"};
						String type = JOptionPane.showInputDialog("<html>Enter one of the following three:<br>Boolean, Number, Text<html>");
						
						if (type.equals("Boolean")) {
							RecordBoolean[i] = new RecordBoolean(start.USER_ID);
							types[i] = 0;
							exist[i] = true;
						}
						else if (type.equals("Number")) {
							RecordNumber[i] = new RecordNumber(start.USER_ID);
							types[i] = 1;
							exist[i] = true;
						}
						else if (type.equals("Text")) {
							RecordText[i] = new RecordText(start.USER_ID);
							types[i] = 2;
							exist[i] = true;
						}
						else {
							new Alert("Error", "Incorrect type input.");
						}
					}
				}
	        });
			Button1[i].addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					if (exist[i] == true) {
						if (types[i] == 0) {

						}
						else if (types[i] == 1) {

						}
						else {

						}
					}
					else {
						new Alert("Error", "There is no record at this.");
					}
				}
	        });
			Button2[i].addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					if (exist[i] == true) {
						int result = JOptionPane.showConfirmDialog(null, "Do your really want to delete this data?");
						if (result == JOptionPane.YES_OPTION) {
							
						}
						else {
							
						}
						
						if (true) {
							if (types[i] == 0) {
								RecordBoolean[i] = null;
							}
							else if (types[i] == 1) {
								RecordNumber[i] = null;
							}
							else {
								RecordText[i] = null;
							}
							exist[i] = false;
						}
					}
					else {
						new Alert("Error", "There is no record at this.");
					}
				}
	        });
			Button3[i].addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					if (exist[i] == true) {
						
					}
					else {
						new Alert("Error", "There is no record at this.");
					}
				}
	        });
			Button4[i].addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					if (exist[i] == true) {
						int result = JOptionPane.showConfirmDialog(null, "Do your really want to send this data?");
						if (result == JOptionPane.YES_OPTION) {
							
						}
						else {
							
						}
					}
					else {
						new Alert("Error", "There is no record at this.");
					}
				}
	        });
		}
		
		Font f1 = new Font("����", Font.BOLD, 20);
		JPanel panel[] = new JPanel[10];
		if (idx > 0) {
        	String EveName[] = CategoryProcess.checkCatCname(conn, stmt, ID, idx);
    		for (int i=0; i < idx; i++) {
    			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            	panel[i].setBackground(Color.ORANGE);
            	panel[i].setPreferredSize(new Dimension(970, 70));
            	
                gbc.gridy = i;
                gbc.gridx = 0;
                
                panel[i].add(new JLabel(EveName[i])).setFont(f1);
                Component horizontalStrut6 = Box.createHorizontalStrut(50);
                panel[i].add(horizontalStrut6);
                
                panel[i].add(Button0[i]);
                panel[i].add(Button1[i]);
                panel[i].add(Button2[i]);
                panel[i].add(Button3[i]);
                panel[i].add(Button4[i]);
                
                MainArea.add(panel[i], gbc);
    		}
        }
        
		btnAddEvent.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if (idx < 10) {					
		        	panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        	panel[i].setBackground(Color.ORANGE);
		        	panel[i].setPreferredSize(new Dimension(970, 70));
		        	
		            gbc.gridy = i;
		            gbc.gridx = 0;
		            panel[i].add(new JLabel("Name of Event")).setFont(f1);
		            Component horizontalStrut6 = Box.createHorizontalStrut(50);
		            panel[i].add(horizontalStrut6);
		            panel[i].add(new JButton("��� ����"));
		            panel[i].add(new JButton("��� �߰�"));
		            panel[i].add(new JButton("��� �̹���"));
		            panel[i].add(new JButton("����"));
		            
		            MainArea.add(panel[i], gbc);
		        	i++;
				}
				else {
					new Alert("Error", "You cannot create more than 10 records.");
				}
			}
        });
		btnDelEvent.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if (i > 0) {
					panel[i] = null;
					i--;
				}
				else {
					new Alert("Error", "There is currently no record to delete.");
				}
			}
        });

		setVisible(true);
		
		//////////////////// ��ư ���� ///////////////////////
		btnBackButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				CategoryFrame frame = new CategoryFrame(conn, stmt, ID);
			}
        });
		btnAddEvent.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEventFrame frame = new AddEventFrame(conn, stmt, ID);
			}
        });
		PaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "PAYMENT", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                	Payment.paymentRequest(conn,stmt,ID);
                	JOptionPane.showMessageDialog(null, "�����Ϸ�");
                	dispose();
    				UserFrame frame = new UserFrame(conn, stmt, ID, name);
                }
                else {
                	JOptionPane.showMessageDialog(null, "�������");
                }
            }
        });
		
	}

}
