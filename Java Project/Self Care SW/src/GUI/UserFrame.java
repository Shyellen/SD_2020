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
		
		//////////////////////////// 기본 컨텐트팬 ////////////////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		idx = UserEventProcess.checkEveCnt(conn, stmt, ID, name);
		
		//////////////////////////// 카테고리 이름 출력 패널  ////////////////////////////
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.white);
		contentPane.add(northPanel, BorderLayout.NORTH);
		JLabel catName = new JLabel(name);
		catName.setFont(new Font("굴림", Font.BOLD, 50));
		catName.setBackground(new Color(204, 255, 255));
		northPanel.add(catName);
		
		//////////////////////////// 광고 출력 패널 ////////////////////////////
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
		
		RoundedButton3 PaymentBtn = new RoundedButton3("결제하기");
		if (payTF == true) {
			PaymentBtn.setEnabled(!payTF);
			PaymentBtn.setText("결제완료");
		}
		southPanel.add(PaymentBtn);
		
		contentPane.add(southPanel, BorderLayout.SOUTH);
		//////////////////////////// 중앙 패널: 버튼+데이터 ////////////////////////////
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.white);
		centerPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		//////////////////////////// 중앙-왼쪽 버튼 패널 ////////////////////////////
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
		
		RoundedButton3 btnBackButton = new RoundedButton3("←");
		btnPanel.add(btnBackButton);
		
		//////////////////////////// 중앙-중앙 데이터 출력 패널 ////////////////////////////
		JPanel MainArea = new JPanel(new GridBagLayout());
		MainArea.setBackground(Color.white);
		centerPanel.add(new JScrollPane(MainArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		
		RoundedButton writeDataBtn[] = new RoundedButton[10];
		RoundedButton readDataBtn[] = new RoundedButton[10];
		RoundedButton Button2[] = new RoundedButton[10];
		RoundedButton Button3[] = new RoundedButton[10];
		RoundedButton Button4[] = new RoundedButton[10];
		
		for (i = 0; i < 10; i++) {
			writeDataBtn[i] = new RoundedButton("기록하기");
			readDataBtn[i] = new RoundedButton("기록열람");
			Button2[i] = new RoundedButton("차트화");
			Button3[i] = new RoundedButton("기록전송");
			Button4[i] = new RoundedButton(" X ");
		}
			
		Font f1 = new Font("돋움", Font.BOLD, 20);
		JPanel panel[] = new JPanel[10];
		if (idx > 0) {
			String EveName[] = UserEventProcess.checkEveCname(conn, stmt, ID, idx, name);
    		for (int i=0; i < idx; i++) {
    			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            	panel[i].setBackground(Color.ORANGE);
            	panel[i].setPreferredSize(new Dimension(970, 70));
            	
                gbc.gridy = i;
                gbc.gridx = 0;
                
                panel[i].add(new JLabel(EveName[i])).setFont(f1);
                Component horizontalStrut6 = Box.createHorizontalStrut(50);
                panel[i].add(horizontalStrut6);
                
                panel[i].add(writeDataBtn[i]);
                panel[i].add(readDataBtn[i]);
                panel[i].add(Button2[i]);
                panel[i].add(Button3[i]);
                panel[i].add(Button4[i]);
                
                MainArea.add(panel[i], gbc);
    		}
        }

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
				AddEventFrame frame = new AddEventFrame(conn, stmt, ID, name);
			}
        });
		PaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "PAYMENT", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                	Payment.paymentRequest(conn,stmt,ID);
                	JOptionPane.showMessageDialog(null, "결제완료");
                	dispose();
    				UserFrame frame = new UserFrame(conn, stmt, ID, name);
                }
                else {
                	JOptionPane.showMessageDialog(null, "결제취소");
                }
            }
        });
		
		writeDataBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String EveName[] = UserEventProcess.checkEveCname(conn, stmt, ID, idx, name);
				String EveType = UserEventProcess.checkEveType(conn, stmt, EveName[0]);
				if (EveType.equals("bool")) {
					RecordBoolean RecordBoolean1 = new RecordBoolean(conn, stmt, ID, EveName[0], name);
				}
				else if(EveType.equals("num")) {
					//RecordNumber[0] = new RecordNumber(conn, stmt, ID, EveName[0]);
				}
				else if(EveType.equals("text")) {
					//RecordText[0] = new RecordText(conn, stmt, ID, EveName[0]);
				}
			}
		});
		
		writeDataBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String EveName[] = UserEventProcess.checkEveCname(conn, stmt, ID, idx, name);
				String EveType = UserEventProcess.checkEveType(conn, stmt, EveName[1]);
				
				if (EveType.equals("bool")) {
					RecordBoolean RecordBoolean1 = new RecordBoolean(conn, stmt, ID, EveName[1], name);
				}
				else if(EveType.equals("num")) {
					//RecordNumber[1] = new RecordNumber(conn, stmt, ID, EveName[1]);
				}
				else if(EveType.equals("text")) {
					//RecordText[1] = new RecordText(conn, stmt, ID, EveName[1]);
				}
			}
		});
		
		writeDataBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String EveName[] = UserEventProcess.checkEveCname(conn, stmt, ID, idx, name);
				String EveType = UserEventProcess.checkEveType(conn, stmt, EveName[2]);
				
				if (EveType.equals("bool")) {
					RecordBoolean RecordBoolean1 = new RecordBoolean(conn, stmt, ID, EveName[2], name);
				}
				else if(EveType.equals("num")) {
					//RecordNumber[2] = new RecordNumber(conn, stmt, ID, EveName[2]);
				}
				else if(EveType.equals("text")) {
					//RecordText[2] = new RecordText(conn, stmt, ID, EveName[2]);
				}
			}
			
		});
		
		readDataBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String EveName[] = UserEventProcess.checkEveCname(conn, stmt, ID, idx, name);
				DataFrame frame = new DataFrame(conn, stmt, ID, EveName[0], name);
			}
			
		});
	}

}

