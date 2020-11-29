package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*; // import JDBC package


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private boolean payTF = false;

	public MainFrame() {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//////////////////////////// �⺻ ����Ʈ�� ////////////////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//////////////////////////// ī�װ� �̸� ��� �г�  ////////////////////////////
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		JLabel catName = new JLabel(User_Category.name);
		catName.setFont(new Font("����", Font.BOLD, 50));
		catName.setBackground(new Color(204, 255, 255));
		northPanel.add(catName);
		
		//////////////////////////// ���� ��� �г� ////////////////////////////
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(1080, 50));
		southPanel.setLayout(new GridLayout(1, 3, 5, 5));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		southPanel.add(horizontalStrut);
		
		JPanel ADpanel = new JPanel();
		ADpanel.setBackground(Color.black);
		southPanel.add(ADpanel);
		JButton PaymentBtn = new JButton("�����ϱ�");
		PaymentBtn.setEnabled(!payTF);
		southPanel.add(PaymentBtn);
		
		contentPane.add(southPanel, BorderLayout.SOUTH);
		//////////////////////////// �߾� �г�: ��ư+������ ////////////////////////////
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		//////////////////////////// �߾�-���� ��ư �г� ////////////////////////////
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
		
		JButton btnBackButton = new JButton("��");
		btnPanel.add(btnBackButton);
		
		//////////////////////////// �߾�-�߾� ������ ��� �г� ////////////////////////////
		JPanel MainArea = new JPanel(new GridBagLayout());
		centerPanel.add(new JScrollPane(MainArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4,4,4,4);
		
		JPanel panel[] = new JPanel[10];
        for (int i = 0; i < 10; i++) {
        	panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	panel[i].setBackground(Color.ORANGE);
        	panel[i].setPreferredSize(new Dimension(970, 70));
        }
        Font f1 = new Font("����", Font.BOLD, 20);
        for (int ii=0; ii<10; ii++) {
            gbc.gridy = ii;
            gbc.gridx = 0;
            panel[ii].add(new JLabel("Name of Event")).setFont(f1);
            Component horizontalStrut6 = Box.createHorizontalStrut(50);
            panel[ii].add(horizontalStrut6);
            panel[ii].add(new JButton("��� ����"));
            panel[ii].add(new JButton("��� �߰�"));
            panel[ii].add(new JButton("��� �̹���"));
            panel[ii].add(new JButton("����"));
            
            MainArea.add(panel[ii], gbc);
        }

		setVisible(true);
		
		//////////////////// ��ư ���� ///////////////////////
		btnBackButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				User_Category frame = new User_Category();
			}
        });
		btnAddEvent.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEventFrame frame = new AddEventFrame();
				
			}
        });
		PaymentBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "PAYMENT", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                	payTF = true;
                	PaymentBtn.setEnabled(!payTF);
                	JOptionPane.showMessageDialog(null, "�����Ϸ�");
                }
                else {
                	JOptionPane.showMessageDialog(null, "�������");
                }
            }
        });
		
	}

}
