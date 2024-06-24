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

public class Admin_enter_goods {
	
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

        JLabel secend = new  JLabel("进货管理系统");
        secend.setSize(140, 15);
        secend.setLocation(180, 0);
        b.add(secend);

       

        JButton third = new JButton("销售管理系统");
        third.setSize(140, 15);
        third.setLocation(280, 0);
        b.add(third);
        
        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.dispose();               
                Admin_enter_xiaoshou.AdminGUI();
            }
        });
        
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
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"商品编号","商品名称","进价","售价","销量","库存"});
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
    	
    	JFrame add1 = new JFrame("添加商品");
    	add1.setSize(200,300);
    	 add1.setLocation(300, 200);
         add1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         add1.setLayout(null);
         
         JLabel r = new JLabel("商品名称:");
	     r.setBounds(10, 10, 100, 30);
	     add1.getContentPane().add(r);
	     
	     JTextField name1 = new JTextField(20);
	     name1.setBounds(60, 20, 100, 15);
	     add1.getContentPane().add(name1);
	     
	     JLabel s = new JLabel("进价:");
	     s.setBounds(10, 30, 100, 30);
	     add1.getContentPane().add(s);
         
	     JTextField jj1 = new JTextField(20);
	     jj1.setBounds(60, 40, 100, 15);
	     add1.getContentPane().add(jj1);
	     
	     JLabel sj = new JLabel("售价:");
	     sj.setBounds(10, 50, 100, 30);
	     add1.getContentPane().add(sj);
         
	     JTextField sj1 = new JTextField(20);
	     sj1.setBounds(60, 60, 100, 15);
	     add1.getContentPane().add(sj1);
	     
	     JLabel kc = new JLabel("库存");
	     kc.setBounds(10, 70, 100, 30);
	     add1.getContentPane().add(kc);
         
	     JTextField kc1 = new JTextField(20);
	     kc1.setBounds(60, 80, 100, 15);
	     add1.getContentPane().add(kc1);
	     
	     JLabel bh = new JLabel("商品编号");
	     bh.setBounds(10, 90, 100, 30);
	     add1.getContentPane().add(bh);
         
	     JTextField bh1 = new JTextField(20);
	     bh1.setBounds(60, 100, 100, 15);
	     add1.getContentPane().add(bh1);
	     
	     JLabel xl = new JLabel("销量");
	     xl.setBounds(10, 110, 100, 30);
	     add1.getContentPane().add(xl);
         
	     JTextField xl1 = new JTextField(20);
	     xl1.setBounds(60, 120, 100, 15);
	     add1.getContentPane().add(xl1);
	     
	     
	     
	     
	     JButton p = new JButton("保存");
	     p.setSize(80,20);
	     p.setLocation(10,180);
	     add1.add(p);
	     
	     p.addActionListener(new ActionListener() {
				
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  				
	  				String num = bh1.getText();
	  				String name = name1.getText();
	  				String jj = jj1.getText();
	  			    String sj = sj1.getText();
	  			    String xl = xl1.getText();
	  			    String kc = kc1.getText();

	 					try {
	 						
	 						Addgoods.goods(num,name,jj,sj,xl,kc);
	 						
	 					} catch (SQLException e1) {
	 						e1.printStackTrace();
	 					}
	  		}
	  		});
	     
	     JButton l = new JButton("退出");
	     l.setSize(80,20);
	     l.setLocation(100,180);
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
            String sql = "SELECT num,name,jinjia,shoujia,xiaoliang,kucun FROM goods";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 读取数据并添加到表格模型中
                model.addRow(new Object[]{rs.getString("num"), rs.getString("name"), rs.getString("jinjia"), rs.getString("shoujia"), rs.getString("xiaoliang"), rs.getString("kucun")});
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
             String qq0 = (String) model.getValueAt(selectedRow, 0);
             String qq1 = (String) model.getValueAt(selectedRow, 1);
             String qq2 = (String) model.getValueAt(selectedRow, 2);
             String qq3 = (String) model.getValueAt(selectedRow, 3);
             String qq4 = (String) model.getValueAt(selectedRow, 4);
             String qq5 = (String) model.getValueAt(selectedRow, 5);
             

             JFrame modifyFrame = new JFrame("修改商品");
             modifyFrame.setSize(200, 300);
             modifyFrame.setLocation(300, 200);
             modifyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             modifyFrame.setLayout(null);

             JLabel r = new JLabel("商品编号:");
             r.setBounds(10, 10, 100, 30);
             modifyFrame.getContentPane().add(r);

             JTextField num1 = new JTextField(qq0, 20);
             num1.setBounds(60, 20, 100, 15);
             modifyFrame.getContentPane().add(num1);

             JLabel s = new JLabel("商品名称:");
             s.setBounds(10, 30, 100, 30);
             modifyFrame.getContentPane().add(s);

             JTextField name1 = new JTextField(qq1, 20);
             name1.setBounds(60, 40, 100, 15);
             modifyFrame.getContentPane().add(name1);

             JLabel w = new JLabel("进价:");
             w.setBounds(10, 50, 100, 30);
             modifyFrame.getContentPane().add(w);

             JTextField jj1 = new JTextField(qq2, 20);
             jj1.setBounds(60, 60, 100, 15);
             modifyFrame.getContentPane().add(jj1);
             
             JLabel w1 = new JLabel("售价:");
             w1.setBounds(10, 70, 100, 30);
             modifyFrame.getContentPane().add(w1);

             JTextField sj1 = new JTextField(qq3, 20);
             sj1.setBounds(60, 80, 100, 15);
             modifyFrame.getContentPane().add(sj1);
             
             JLabel w2 = new JLabel("销量:");
             w2.setBounds(10, 90, 100, 30);
             modifyFrame.getContentPane().add(w2);

             JTextField xl1 = new JTextField(qq4, 20);
             xl1.setBounds(60, 100, 100, 15);
             modifyFrame.getContentPane().add(xl1);
             
             JLabel w3 = new JLabel("库存:");
             w3.setBounds(10, 110, 100, 30);
             modifyFrame.getContentPane().add(w3);

             JTextField kc1 = new JTextField(qq5, 20);
             kc1.setBounds(60, 120, 100, 15);
             modifyFrame.getContentPane().add(kc1);

             JButton p = new JButton("保存");
             p.setSize(70, 20);
             p.setLocation(10, 150);
             modifyFrame.add(p);

             p.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // 在这里获取修改后的值并执行数据库更新操作
                	 String num = num1.getText();
 	  				String name = name1.getText();
 	  				String jj = jj1.getText();
 	  			    String sj = sj1.getText();
 	  			    String xl = xl1.getText();
 	  			    String kc = kc1.getText();

                     // 执行数据库更新
                     try {
                    	 Xiugaigoods.xiugai(num,name,jj,sj,xl,kc);
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
         if (selectedRow!= -1) {
             String qq0 = (String) model.getValueAt(selectedRow, 0);
             String qq1 = (String) model.getValueAt(selectedRow, 1);
             String qq2 = (String) model.getValueAt(selectedRow, 2);
             String qq3 = (String) model.getValueAt(selectedRow, 3);
             String qq4 = (String) model.getValueAt(selectedRow, 4);
             String qq5 = (String) model.getValueAt(selectedRow, 5);
             

            // 显示确认对话框，询问用户是否确认删除
            String confirmMessage = "确认删除商品？编号：" + qq0 + "，名称：" + qq1;
            int confirm = JOptionPane.showConfirmDialog(b, confirmMessage, "删除确认", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // 删除数据库中的记录
                try {
                	deleteFromDatabase(qq0,qq1,qq2,qq3,qq4,qq5);
                    // 从表格中删除该行
                    model.removeRow(selectedRow);
                    // 更新表格
                    table.repaint();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "删除商品失败，请检查网络连接或数据库权限！" + e.getMessage());
                }
            }
        }
    }

    private static void deleteFromDatabase(String num1, String name1,String jinjia1,String shoujia1,String xiaoliang1,String kucun1) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsql", "root", "root");
        String sql = "DELETE FROM goods WHERE num=? AND name=? AND jinjia=? AND shoujia=? AND xiaoliang=? AND kucun=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, num1);
    	pstmt.setString(2, name1);
        pstmt.setString(3, jinjia1); // 'tel' should be passed from the event handler
        pstmt.setString(4, shoujia1);
        pstmt.setString(5, xiaoliang1);
        pstmt.setString(6,kucun1);
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

        JLabel accountLabel = new JLabel("商品名称：");
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
            String sql = "SELECT num,name,jinjia,shoujia,xiaoliang,kucun FROM goods WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountToSearch);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("num"), rs.getString("name"), rs.getString("jinjia"), rs.getString("shoujia"), rs.getString("xiaoliang"), rs.getString("kucun")});
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
