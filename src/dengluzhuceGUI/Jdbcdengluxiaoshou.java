package dengluzhuceGUI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import xiaoshouGUI.XiaoshouGUI;;

public class Jdbcdengluxiaoshou extends Dengluxiaoshou {
	private String driver = "com.mysql.cj.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/newsql?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8&serverTimezone=UTC";
	 static String username = "root";
	 static String password = "root";

	public Jdbcdengluxiaoshou() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		 }
	
	public static void dengluxiaoshou(String username1, String password1) throws SQLException {
	    String sql = "SELECT * FROM xiaoshou WHERE zhanghao=? AND mima=?";
	    try (Connection c = DriverManager.getConnection(url, username, password);
	         PreparedStatement pstmt = c.prepareStatement(sql)) {
	         pstmt.setString(1, username1);
	         pstmt.setString(2, password1);
	  	     ResultSet jieguo =  pstmt.executeQuery();
	  	   if (jieguo.next()) {
	  		 XiaoshouGUI.main(null);
	  		 a.dispose();
           } else {
        	   JOptionPane.showMessageDialog(null, "登录失败，请重新登录！"); 
           }
       } catch (SQLException e) {
    	   JOptionPane.showMessageDialog(null, "数据库连接失败或查询出错！");
       } catch (Exception e) {
    	   JOptionPane.showMessageDialog(null, "登录失败，未知错误！");
       }
   }
}