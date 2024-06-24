package dengluzhuceGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;;

public class Jdbczhuceadmin {
	private String driver = "com.mysql.cj.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/newsql?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8&serverTimezone=UTC";
	 static String username = "root";
	 static String password = "root";

	public Jdbczhuceadmin() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		 }
	
	public static void zhuceadmin(String username1, String password1,String password2, String tel1) throws SQLException {
	    String sql = "INSERT INTO admin (zhanghao, mima, miyao) VALUES (?, ?, ?)";
	    try (Connection c = DriverManager.getConnection(url, username, password);
	         PreparedStatement pstmt = c.prepareStatement(sql)) {
	         pstmt.setString(1, username1);
	         pstmt.setString(2, password1);
	         pstmt.setString(3, tel1);
	  	     pstmt.executeUpdate();
	  	   JOptionPane.showMessageDialog(null, "注册成功！");
	        }catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, "注册失败，用户已存在");
	        	
	        }
	   
		
	}
}

