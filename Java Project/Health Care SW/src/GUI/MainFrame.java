package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*; // import JDBC package

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table1;
	DefaultTableModel model1,model2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("Health Care SW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//////////////////////////// 상단 메뉴 바 ////////////////////////////
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu optionMenu = new JMenu("Option");
		menuBar.add(optionMenu);
		JMenuItem logoutMenuItem = new JMenuItem("logout");
		optionMenu.add(logoutMenuItem);
		
		//////////////////////////// 기본 컨텐트팬 ////////////////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//////////////////////////// 카테고리 이름 출력 패널  ////////////////////////////
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		JLabel catName = new JLabel("Category Name");
		catName.setFont(new Font("굴림", Font.BOLD, 50));
		catName.setBackground(new Color(204, 255, 255));
		northPanel.add(catName);
		
		//////////////////////////// 광고 출력 패널 ////////////////////////////
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setPreferredSize(new Dimension(1080, 50));
		southPanel.setLayout(new GridLayout(1, 3, 5, 5));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		southPanel.add(horizontalStrut);
		
		JPanel ADpanel = new JPanel();
		ADpanel.setBackground(Color.black);
		southPanel.add(ADpanel);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		southPanel.add(horizontalStrut_1);
		
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
		
		JButton btnNewButton = new JButton("←");
		btnPanel.add(btnNewButton);
		
		//////////////////////////// 중앙-중앙 데이터 출력 패널 ////////////////////////////
		JPanel mainPanel = new JPanel();
		centerPanel.add(mainPanel, BorderLayout.CENTER);
		
		String col1[]={"종목", "보기","전송"};
    	String row1[][]=new String[0][3];
    	model1=new DefaultTableModel(row1,col1) {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	};
		table1 = new JTable(model1);
		table1.setColumnSelectionAllowed(true);
		table1.setShowVerticalLines(false);
		JScrollPane js1=new JScrollPane(table1);
		table1.getTableHeader().setReorderingAllowed(false);
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.add(js1);
	}

}
