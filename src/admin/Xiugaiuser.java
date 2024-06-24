package admin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Xiugaiuser {
    
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/newsql";
    private static final String user = "root";
    private static final String password = "root";
    
    // JDBC variables for opening and managing connection
    private static Connection con;
    
    public static void xiugai(String name, String mima,String mima2,String tel) throws SQLException {
        // SQL statement for updating password
        String sql = "UPDATE guke SET mima = ? ,tel=?WHERE zhanghao = ?";
        
        try {
            // Open a connection
            con = DriverManager.getConnection(url, user, password);

            // Execute the query
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, mima);
                pstmt.setString(2, tel); // 'tel' should be passed from the event handler
                pstmt.setString(3, name);
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
