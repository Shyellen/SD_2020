package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*; // import JDBC package

public class User_Category extends JFrame implements ActionListener {
	private JButton InsertBtn;
	private JButton DeleteBtn;
	
	private static int idx = 0;
	private static boolean delete = false;
	public static String name=null;
	
	public User_Category(Connection conn, Statement stmt, String Id) {
		setTitle("Self Care SW");
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
    	
    	InsertBtn = new JButton("+");
    	InsertBtn.setPreferredSize(new Dimension(150, 150));
    	BtnPanel.add(InsertBtn);
    	
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
    	JButton DeleteBtn = new JButton("-");
    	if (idx == 0) {
    		DeleteBtn.setEnabled(false);
		}
    	DeleteBtn.setBounds(945, 250, 50, 50);
    	add(DeleteBtn);
        
		//////////////////// 동적 생성 및 삭제 버튼들 ////////////////////
    	JButton btn[] = new JButton[5];
    	for (int i = 0; i < 5; i++)
    		btn[i] = new JButton();
    	
    	idx = checkCatCnt(conn, stmt, Id);
    	System.out.println("카테고리 수: "+idx);
    	
    	if (idx > 0) {
    		String name[] = checkCatCname(conn, stmt, Id, idx);
    		for (int i=0; i < idx; i++) {
    			btn[i].setText(name[i]);
    			BtnPanel.add(btn[i]);
    		}
        }
    	
        JButton RemoveBtn[] = new JButton[5];
        for (int i = 0; i < 5; i++) {
        	RemoveBtn[i] = new JButton();
        }
        
        setVisible(true);
        
		//////////////////// 버튼 액션 ////////////////////
        InsertBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane popup = new JOptionPane();
				String CatName = popup.showInputDialog("추가할 카테고리 이름 입력(2글자 이상)");
				if(CatName.length() >= 2) {
					insertCat(conn, stmt, Id, CatName);
					btn[idx].setText(CatName);
					btn[idx].setPreferredSize(new Dimension(150, 150));
					BtnPanel.add(btn[idx]);
					RemoveBtn[idx].setText("Remove");
					RemoveBtn[idx].setPreferredSize(new Dimension(150, 30));
					DeletePanel.add(RemoveBtn[idx]);
					idx = idx + 1;
					setVisible(true);
					if (idx == 5) {
						InsertBtn.setEnabled(false);
					}
					if (idx > 0) {
						DeleteBtn.setEnabled(true);
					}
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
				MainFrame frame = new MainFrame();
			}
		});
        
        btn[1].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[1].getText());
				name = btn[1].getText();
				dispose();
				MainFrame frame = new MainFrame();
			}
		});

        btn[2].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[2].getText());
				name = btn[2].getText();
				dispose();
				MainFrame frame = new MainFrame();
			}
		});
        
        btn[3].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[3].getText());
				name = btn[3].getText();
				dispose();
				MainFrame frame = new MainFrame();
			}
		});
        
        btn[4].addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, btn[4].getText());
				name = btn[4].getText();
				dispose();
				MainFrame frame = new MainFrame();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static int checkCatCnt(Connection conn, Statement stmt, String Id) {	// 해당 ID로 생성된 카테고리 수를 확인한다.
		ResultSet rs = null;
		String DBcnt = "";
		try {
			String sql = "SELECT COUNT(*) FROM CMAKE WHERE Uid = '"+Id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				DBcnt = rs.getString(1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		return Integer.parseInt(DBcnt);
	}
	
	public static String[] checkCatCname(Connection conn, Statement stmt, String Id, int cnt) { // 해당 ID로 생성된 카테고리 이름을 확인한다.
		ResultSet rs = null;
		String[] DBname = new String[cnt];
		try {
			String sql = "SELECT Cname FROM Category"
					+ " WHERE Cnum = (SELECT Cnum FROM Cmake WHERE Uid='"+Id+"')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()) {
				DBname[i] = (String) rs.getString(i+1);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.out.println("error");
			e.printStackTrace();
		}
		return DBname;
	}
	
	public static int insertCat(Connection conn, Statement stmt, String Id, String name) { // 새로운 카테고리를 생성해 넣는다.
		ResultSet rs = null;
		String max = "";
		int num = 0;
		
		try {
			String sql;
			sql = "SELECT MAX(cnum) FROM category";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
				max = rs.getString(1);
			rs.close();
		} catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		
		System.out.println(max);
		/*
		
			
			//else
			//	num = Integer.parseInt(max) + 1;
			//System.out.println(num);
			/*
			sql2 = "INSERT INTO category(cnum, cname) VALUES ("+num+", " + name+")";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			System.out.println("Row inserted.");
			conn.commit();
			
			sql3 = "INSERT INTO cmake(Uid, Cnum) VALUES ("+Id+", " + num+")";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql3);
			System.out.println("Row inserted.");
			conn.commit();
			*/
		return 1;
	}
}
