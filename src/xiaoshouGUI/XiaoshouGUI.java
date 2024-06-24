package xiaoshouGUI;

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

import dengluzhuceGUI.Dengluxiaoshou;

public class XiaoshouGUI {
	
public static JFrame b;
static DefaultTableModel model;
static JTable table;
static JButton three;
static String accountId;
static String password;
static String phoneNumber;

    public static void AdminGUI() {
        b = new JFrame("销售界面");
        b.setSize(500, 500);
        b.setLocation(500, 200);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setLayout(null);

        JButton forth = new JButton("刷新");
        forth.setSize(70, 30);
        forth.setLocation(300, 430);
        b.add(forth);
        
        forth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.dispose();
                main(null);
            }
        });
        
        // 从数据库获取用户信息
        model = new DefaultTableModel(new Object[][]{}, new Object[]{"商品编号","商品名称","进价","售价","销量","库存"});
         table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 15, 500, 400);
        b.add(scrollPane);

        JButton four = new JButton("查询");
        four.setSize(80, 30);
        four.setLocation(200, 430);
        b.add(four);
        
        
        // 填充表格数据
        fillTableData(model);
 
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchDialog();
            }
        });
        
        
        JButton hui  = new JButton("退出");
        hui.setSize(60, 30);
        hui.setLocation(120, 430);
        b.add(hui);
        
        hui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Dengluxiaoshou.denglu();
               b.dispose();
            }
        });
        
    
        b.setVisible(true);
 
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
