package admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dengluzhuceGUI.Dengluadmin;
import dengluzhuceGUI.Jdbczhucexiaoshou;

public class Admin_enter_xiaoshou {
	
public static JFrame b;
static DefaultTableModel model;
static JTable table;
static JButton three;
static String accountId;
static String password;
static String phoneNumber;

    public static void AdminGUI() {
        b = new JFrame("管理员登录界面");
        b.setSize(500, 500);
        b.setLocation(500, 200);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setLayout(null);

        JButton first = new JButton("用户管理系统");
        first.setSize(140, 15);
        first.setLocation(0, 0);
        b.add(first);

        JButton secend = new JButton("进货管理系统");
        secend.setSize(140, 15);
        secend.setLocation(140, 0);
        b.add(secend);

        secend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.dispose();
            
                Admin_enter_goods.AdminGUI();
            }
        });

        JLabel third = new JLabel("销售管理系统");
        third.setSize(140, 15);
        third.setLocation(300, 0);
        b.add(third);

        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.dispose();
                Admin_enter_constmer.AdminGUI();
            }
        });
        
        JButton forth = new JButton("↻");
        forth.setSize(70, 15);
        forth.setLocation(420, 0);
        b.add(forth);
        
        forth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.dispose();
                main(null);
            }
        });
        
        JButton hui  = new JButton("⇦");
        hui.setSize(50, 30);
        hui.setLocation(0, 430);
        b.add(hui);
        
        hui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Dengluadmin.denglu();
               b.dispose();
            }
        });
        
        // 从数据库获取用户信息
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"销售账号", "销售密码", "密钥"});
         table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 15, 500, 400);
        b.add(scrollPane);

        JButton one = new JButton("增加");
        one.setSize(80, 30);
        one.setLocation(50, 430);
        b.add(one);

        JButton two = new JButton("修改");
        two.setSize(80, 30);
        two.setLocation(150, 430);
        b.add(two);

       three = new JButton("删除");
        three.setSize(80, 30);
        three.setLocation(250, 430);
        b.add(three);

        JButton four = new JButton("查询");
        four.setSize(80, 30);
        four.setLocation(350, 430);
        b.add(four);

        // 填充表格数据
        fillTableData(model);
        
        one.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           add1();
	            
	        }
	    });
        
        two.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        modifyAction(); // 添加这个方法来处理修改操作
		    }
		});
        
        three.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 deleteAction() ; // 添加这个方法来处理修改操作
		    }
		});

        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchDialog();
            }
        });
        
        b.setVisible(true);

    }
    //增加用户
    
    private static void add1() {
    	
    	JFrame add1 = new JFrame("添加用户");
    	add1.setSize(200,160);
    	 add1.setLocation(300, 200);
         add1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         add1.setLayout(null);
         
         JLabel r = new JLabel("用户名:");
	     r.setBounds(10, 10, 100, 30);
	     add1.getContentPane().add(r);
	     
	     JTextField username = new JTextField(20);
	     username.setBounds(55, 20, 100, 15);
	     add1.getContentPane().add(username);
	     
	     JLabel s = new JLabel("密码:");
	     s.setBounds(10, 30, 100, 30);
	     add1.getContentPane().add(s);
         
	     JTextField mima = new JTextField(20);
	     mima.setBounds(55, 40, 100, 15);
	     add1.getContentPane().add(mima);
	     
	     JLabel w = new JLabel("密钥:");
	     w.setBounds(10, 50, 100, 30);
	     add1.getContentPane().add(w);
         
	     JTextField q = new JTextField(20);
	     q.setBounds(55, 60, 100, 15);
	     add1.getContentPane().add(q);
	     
	     JButton p = new JButton("保存");
	     p.setSize(70,20);
	     p.setLocation(10,80);
	     add1.add(p);
	     
	     p.addActionListener(new ActionListener() {
				
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  				
	  				String name = username.getText();
	  				String mima1 = mima.getText();
	  				String m = mima1;
	  			    String miyao = q.getText();
	  			    String ww ="111";

	 					try {
	 						if(mima1.equals(m)&&miyao.equals(ww))
	 						Jdbczhucexiaoshou.zhucexiaoshou(name,mima1,m,miyao);
	 						else JOptionPane.showMessageDialog(null, "添加用户失败！");
	 					} catch (SQLException e1) {
	 						e1.printStackTrace();
	 					}
	  		}
	  		});
	     
	     JButton l = new JButton("退出");
	     l.setSize(70,20);
	     l.setLocation(90,80);
	     add1.add(l);
	     l.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	  add1.dispose();
		              b.dispose();
		          main(null);
		            
		        }
		    });
         add1.setVisible(true);
         
         
    	
    }

    
    private static void fillTableData(DefaultTableModel model) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 连接数据库
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsql", "root", "root");
            // 查询用户信息
            String sql = "SELECT zhanghao, mima, miyao FROM xiaoshou";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 读取数据并添加到表格模型中
                model.addRow(new Object[]{rs.getString("zhanghao"), rs.getString("mima"), rs.getString("miyao")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库连接失败或查询出错！");
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //修改用户
    
    private static void modifyAction() {
    	 int selectedRow = table.getSelectedRow();
         if (selectedRow!= -1) {
             String account = (String) model.getValueAt(selectedRow, 0);
             String password = (String) model.getValueAt(selectedRow, 1);
             String tel = (String) model.getValueAt(selectedRow, 2);

             JFrame modifyFrame = new JFrame("修改用户");
             modifyFrame.setSize(200, 160);
             modifyFrame.setLocation(300, 200);
             modifyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             modifyFrame.setLayout(null);

             JLabel r = new JLabel("用户名:");
             r.setBounds(10, 10, 100, 30);
             modifyFrame.getContentPane().add(r);

             JTextField username = new JTextField(account, 20);
             username.setBounds(55, 20, 100, 15);
             modifyFrame.getContentPane().add(username);

             JLabel s = new JLabel("密码:");
             s.setBounds(10, 30, 100, 30);
             modifyFrame.getContentPane().add(s);

             JTextField mima = new JTextField(password, 20);
             mima.setBounds(55, 40, 100, 15);
             modifyFrame.getContentPane().add(mima);

             JLabel w = new JLabel("密钥:");
             w.setBounds(10, 50, 100, 30);
             modifyFrame.getContentPane().add(w);

             JTextField q = new JTextField(tel, 20);
             q.setBounds(55, 60, 100, 15);
             modifyFrame.getContentPane().add(q);

             JButton p = new JButton("保存修改");
             p.setSize(70, 20);
             p.setLocation(10, 80);
             modifyFrame.add(p);

             p.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // 在这里获取修改后的值并执行数据库更新操作
                     String newAccount = username.getText();
                     String newPassword = mima.getText();
                     String ppp = newPassword;
                     String newmiyao = "111";

                     // 执行数据库更新
                     try {
                    	 Xiugaixiaoshou.xiugai(newAccount,newPassword,ppp,newmiyao);
                         // 你的更新数据库代码
                     } catch (SQLException ex) {
                         ex.printStackTrace();
                     }
                     modifyFrame.dispose();
                     b.dispose();
                     main(null);
                 }
             });

             modifyFrame.setVisible(true);  
         }
     }
    
    private static void deleteAction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String accountId = (String) model.getValueAt(selectedRow, 0);
            String password = (String) model.getValueAt(selectedRow, 1);
            String phoneNumber = (String) model.getValueAt(selectedRow, 2);

            // 显示确认对话框，询问用户是否确认删除
            String confirmMessage = "确认删除用户？账号：" + accountId + "，密码：" + password + "，电话：" + phoneNumber;
            int confirm = JOptionPane.showConfirmDialog(b, confirmMessage, "删除确认", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // 删除数据库中的记录
                try {
                    deleteFromDatabase(accountId, password, phoneNumber);
                    // 从表格中删除该行
                    model.removeRow(selectedRow);
                    // 更新表格
                    table.repaint();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "删除用户失败，请检查网络连接或数据库权限！" + e.getMessage());
                }
            }
        }
    }

    private static void deleteFromDatabase(String accountId, String password, String phoneNumber) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsql", "root", "root");
        String sql = "DELETE FROM xiaoshou WHERE zhanghao = ? AND mima = ? AND miyao = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, accountId);
        pstmt.setString(2, password);
        pstmt.setString(3, phoneNumber);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    
    private static void showSearchDialog() {
        JFrame searchDialog = new JFrame("查询用户");
        searchDialog.setSize(200, 120);
        searchDialog.setLocationRelativeTo(b);
        searchDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchDialog.setLayout(new FlowLayout());

        JLabel accountLabel = new JLabel("账号：");
        JTextField accountField = new JTextField(15);
        JButton searchButton = new JButton("查询");

        searchDialog.add(accountLabel);
        searchDialog.add(accountField);
        searchDialog.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountToSearch = accountField.getText();
                searchAccount(accountToSearch);
                searchDialog.dispose();
            }
        });

        searchDialog.setVisible(true);
    }

    private static void searchAccount(String accountToSearch) {
        model.setRowCount(0); // 清空表格
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsql", "root", "root");
            String sql = "SELECT zhanghao, mima, miyao FROM xiaoshou WHERE zhanghao = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountToSearch);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("zhanghao"), rs.getString("mima"), rs.getString("miyao")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(b, "查询失败，请检查网络连接或数据库权限！" + e.getMessage());
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
 public static void main(String[] args) {
        AdminGUI();
    }
}
