package admin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Xiugaigoods {
    
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/newsql";
    private static final String user = "root";
    private static final String password = "root";
    
    // JDBC variables for opening and managing connection
    private static Connection con;
    
    public static void xiugai(String num1, String name1,String jinjia1,String shoujia1,String xiaoliang1,String kucun1) throws SQLException {
        // SQL statement for updating password
        String sql = "UPDATE goods SET name=?,jinjia=?,shoujia=?,xiaoliang=?,kucun=? WHERE num=?";
        
        try {
            // Open a connection
            con = DriverManager.getConnection(url, user, password);

            // Execute the query
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, name1);
                pstmt.setString(2, jinjia1); // 'tel' should be passed from the event handler
                pstmt.setString(3, shoujia1);
                pstmt.setString(4, xiaoliang1);
                pstmt.setString(5,kucun1);
                pstmt.setString(6, num1);
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                	JOptionPane.showMessageDialog(null, "修改成功！");
                } else {
                	JOptionPane.showMessageDialog(null, "修改失败！");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
