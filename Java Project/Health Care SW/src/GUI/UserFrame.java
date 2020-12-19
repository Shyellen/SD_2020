package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import PROCESS.start;
import package0.*;

import java.sql.*; // import JDBC package

@SuppressWarnings("unused")
public class UserFrame extends JFrame {
	private JPanel contentPane;
	private boolean payTF = false;
	
	private int i = 0;

	public UserFrame(Connection conn, Statement stmt, String ID) {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//////////////////////////// 기본 컨텐트팬 ////////////////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//////////////////////////// 카테고리 이름 출력 패널  ////////////////////////////
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		JLabel catName = new JLabel(CategoryFrame.name);
		catName.setFont(new Font("굴림", Font.BOLD, 50));
		catName.setBackground(new Color(204, 255, 255));
		northPanel.add(catName);
		
		//////////////////////////// 광고 출력 패널 ////////////////////////////
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(1080, 50));
		southPanel.setLayout(new GridLayout(1, 3, 5, 5));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		southPanel.add(horizontalStrut);
		
		JPanel ADpanel = new JPanel();
		ADpanel.setBackground(Color.black);
		southPanel.add(ADpanel);
		JButton PaymentBtn = new JButton("결제하기");
		PaymentBtn.setEnabled(!payTF);
		southPanel.add(PaymentBtn);
		
		contentPane.add(southPanel, BorderLayout.SOUTH);
		//////////////////////////// 중앙 패널: 버튼+데이터 ////////////////////////////
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		//////////////////////////// 중앙-왼쪽 버튼 패널 ////////////////////////////
		JPanel btnPanel = new JPanel();
		centerPanel.add(btnPanel, BorderLayout.WEST);
		btnPanel.setLayout(new GridLayout(7, 1, 5, 5));
		
		JButton btnAddEvent = new JButton("Add");
		btnPanel.add(btnAddEvent);
		
		JButton btnDelEvent = new JButton("Del");
		btnPanel.add(btnDelEvent);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut_2);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		btnPanel.add(verticalStrut_3);
		
		JButton btnBackButton = new JButton("←");
		btnPanel.add(btnBackButton);
		
		//////////////////////////// 중앙-중앙 데이터 출력 패널 ////////////////////////////
		JPanel MainArea = new JPanel(new GridBagLayout());
		centerPanel.add(new JScrollPane(MainArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		
		Boolean exist[] = new Boolean[99];
		int types[] = new int[99];
		JButton Button0[] = new JButton[99];
		JButton Button1[] = new JButton[99];
		JButton Button2[] = new JButton[99];
		JButton Button3[] = new JButton[99];
		JButton Button4[] = new JButton[99];
		RecordBoolean RecordBoolean[] = new RecordBoolean[99];
		RecordNumber RecordNumber[] = new RecordNumber[99];
		RecordText RecordText[] = new RecordText[99];
		
		for (i = 0; i < 99; i++) {
			exist[i] = false;
			Button0[i] = new JButton("기록 생성/편집");
			Button1[i] = new JButton("기록 보기");
			Button2[i] = new JButton("기록 삭제");
			Button3[i] = new JButton("이미지화");
			Button4[i] = new JButton("기록 전송");
			
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
		
		Font f1 = new Font("돋움", Font.BOLD, 20);
		JPanel panel[] = new JPanel[99];
		
		for (i = 0; i < 3; i++) {
        	panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	panel[i].setBackground(Color.ORANGE);
        	panel[i].setPreferredSize(new Dimension(970, 70));
        	
            gbc.gridy = i;
            gbc.gridx = 0;
            panel[i].add(new JLabel("Name of Event")).setFont(f1);
            Component horizontalStrut6 = Box.createHorizontalStrut(50);
            panel[i].add(horizontalStrut6);
            
            panel[i].add(Button0[i]);
            panel[i].add(Button1[i]);
            panel[i].add(Button2[i]);
            panel[i].add(Button3[i]);
            panel[i].add(Button4[i]);
            
            MainArea.add(panel[i], gbc);
		}
        
		btnAddEvent.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if (i < 99) {					
		        	panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        	panel[i].setBackground(Color.ORANGE);
		        	panel[i].setPreferredSize(new Dimension(970, 70));
		        	
		            gbc.gridy = i;
		            gbc.gridx = 0;
		            panel[i].add(new JLabel("Name of Event")).setFont(f1);
		            Component horizontalStrut6 = Box.createHorizontalStrut(50);
		            panel[i].add(horizontalStrut6);
		            panel[i].add(new JButton("기록 보기"));
		            panel[i].add(new JButton("기록 추가"));
		            panel[i].add(new JButton("기록 이미지"));
		            panel[i].add(new JButton("전송"));
		            
		            MainArea.add(panel[i], gbc);
		        	i++;
				}
				else {
					new Alert("Error", "You cannot create more than 99 records.");
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
		
		//////////////////// 버튼 동작 ///////////////////////
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
				//AddEventFrame frame = new AddEventFrame();
				
			}
        });
		PaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "PAYMENT", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                	//Payment.paymentRequest(conn,stmt,ID,);
                	PaymentBtn.setEnabled(!payTF);
                	JOptionPane.showMessageDialog(null, "결제완료");
                }
                else {
                	JOptionPane.showMessageDialog(null, "결제취소");
                }
            }
        });
		
	}

}
