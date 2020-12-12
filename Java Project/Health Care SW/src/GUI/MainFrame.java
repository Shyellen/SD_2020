package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*; // import JDBC package
import package0.*;

@SuppressWarnings("unused")
public class MainFrame extends JFrame {
	private JPanel contentPane;
	private boolean payTF = false;
	
	private int i = 0;
	
	public MainFrame() {
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
		JLabel catName = new JLabel(User_Category.name);
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
		// JButton Button0[] = new JButton[99];
		// JButton Button1[] = new JButton[99];
		// JButton Button2[] = new JButton[99];
		// JButton Button3[] = new JButton[99];
		// RecordBoolean RecordBoolean[] = new RecordBoolean("USERNAME")[];
		// RecordNumber RecordNumber[] = new RecordNumber("USERNAME")[];
		// RecordText RecordText[] = new RecordText("USERNAME")[];
		JPanel panel[] = new JPanel[99];
        for (; i < 3; i++) {
        	panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	panel[i].setBackground(Color.ORANGE);
        	panel[i].setPreferredSize(new Dimension(970, 70));
        	exist[i] = true;
        }
        
		btnAddEvent.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if (i < 99) {
		        	panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        	panel[i].setBackground(Color.ORANGE);
		        	panel[i].setPreferredSize(new Dimension(970, 70));
		        	exist[i] = true;
		        	i++;
				}
				else {
					new Alert("Error", "You cannot create more than 99 records.");
				}
			}
        });
		btnDelEvent.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if (i > 1) {
					panel[i] = null;
					exist[i] = false;
					i--;
				}
				else {
					new Alert("Error", "There is currently no record to delete.");
				}
			}
        });
		
        JButton Button0, Button1, Button2, Button3;
        
        Font f1 = new Font("돋움", Font.BOLD, 20);
        for (int ii = 0; ii < 10; ii++) {
            gbc.gridy = ii;
            gbc.gridx = 0;
            panel[ii].add(new JLabel("Name of Event")).setFont(f1);
            Component horizontalStrut6 = Box.createHorizontalStrut(50);
            panel[ii].add(horizontalStrut6);
            
            Button0 = new JButton("기록 보기");
            Button1 = new JButton("기록 추가");
            Button2 = new JButton("이미지화");
            Button3 = new JButton("기록 전송");
            
            
    		Button0.addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
    				if (true) {
    					
    				}
    				else {
    					new Alert("Error", "There is already a record in that location.");
    				}
    			}
            });
    		Button1.addActionListener(new ActionListener() {	
    			public void actionPerformed(ActionEvent e) {
    				if (true) {
    					new Alert("Error", "There is no record in that location.");
    				}
    				else {
    					SetRecordType SetRecordType = new SetRecordType();
    					if (SetRecordType.type == 0) {
    						RecordBoolean RecordBoolean = new RecordBoolean("USERNAME");
    					}
    					else if (SetRecordType.type == 1) {
    						RecordNumber RecordNumber = new RecordNumber("USERNAME");
    					}
    					else {
    						RecordText RecordText = new RecordText("USERNAME");
    					}
    				}
    			}
            });
    		Button2.addActionListener(new ActionListener() {	
    			public void actionPerformed(ActionEvent e) {
    				if (true) {
    					
    				}
    				else {
    					new Alert("Error", "There is no record in that location.");
    				}
    			}
            });
    		Button3.addActionListener(new ActionListener() {	
    			public void actionPerformed(ActionEvent e) {
    				if (true) {
    					new AlertSendData();
    				}
    				else {
    					new Alert("Error", "There is no record in that location.");
    				}
    			}
            });
            
            panel[ii].add(Button0);
            panel[ii].add(Button1);
            panel[ii].add(Button2);
            panel[ii].add(Button3);
            
            MainArea.add(panel[ii], gbc);
        }

		setVisible(true);
		
		//////////////////// 버튼 동작 ///////////////////////
		btnBackButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				//User_Category frame = new User_Category();
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
                	payTF = true;
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
