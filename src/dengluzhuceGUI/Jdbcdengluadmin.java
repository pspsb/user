package dengluzhuceGUI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.Admin_enter_goods;;

public class Jdbcdengluadmin extends Dengluadmin{
	private String driver = "com.mysql.cj.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/newsql?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8&serverTimezone=UTC";
	 static String username = "root";
	 static String password = "root";

	public Jdbcdengluadmin() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		 }
	
	public static void dengluadmin(String username1, String password1) throws SQLException {
	    String sql = "SELECT * FROM admin WHERE zhanghao=? AND mima=?";
	    try (Connection c = DriverManager.getConnection(url, username, password);
	         PreparedStatement pstmt = c.prepareStatement(sql)) {
	         pstmt.setString(1, username1);
	         pstmt.setString(2, password1);
	  	     ResultSet jieguo =  pstmt.executeQuery();
	  	     
	  	   if (jieguo.next()) {
	  		 Admin_enter_goods.main(null);
	  		 a.dispose();
           } 
	  	   else {
        	   JOptionPane.showMessageDialog(null, "登录失败，请检查账号密码");
	  	   } 
	    }
	  	   catch (SQLException e) {
	  		 JOptionPane.showMessageDialog(null, "登录失败，请检查数据库连接");
	       } 
	    
	       catch (Exception e) {
	    	   JOptionPane.showMessageDialog(null, "登录失败，未知错误");
	       }
   
	}
	}