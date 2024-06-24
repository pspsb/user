package admin;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;;

public class Addgoods {
	private String driver = "com.mysql.cj.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/newsql?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8&serverTimezone=UTC";
	 static String username = "root";
	 static String password = "root";

	public Addgoods() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		 }
	
	public static void goods(String num1, String name1,String jinjia1,String shoujia1,String xiaoliang1,String kucun1) throws SQLException {
	    String sql = "INSERT INTO goods (num,name,jinjia,shoujia,xiaoliang,kucun) VALUES (?,?,?,?,?,?)";
	    try (Connection c = DriverManager.getConnection(url, username, password);
	         PreparedStatement pstmt = c.prepareStatement(sql)) {
	    	pstmt.setString(1, num1);
	    	pstmt.setString(2, name1);
            pstmt.setString(3, jinjia1); // 'tel' should be passed from the event handler
            pstmt.setString(4, shoujia1);
            pstmt.setString(5, xiaoliang1);
            pstmt.setString(6,kucun1);
            
	  	     pstmt.executeUpdate();
	  	   JOptionPane.showMessageDialog(null, "商品添加成功！");
	        }catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, "商品添加失败！");
	        	
	        }
	   
		
	}
}


