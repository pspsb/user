package dengluzhuceGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;;

public class Jdbczhucexiaoshou {
	private String driver = "com.mysql.cj.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/newsql?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8&serverTimezone=UTC";
	 static String username = "root";
	 static String password = "root";

	public Jdbczhucexiaoshou() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		 }
	
	public static void zhucexiaoshou(String username1, String password1,String password2, String tel1) throws SQLException {
	    String sql = "INSERT INTO xiaoshou (zhanghao, mima, miyao) VALUES (?, ?, ?)";
	    try (Connection c = DriverManager.getConnection(url, username, password);
	         PreparedStatement pstmt = c.prepareStatement(sql)) {
	         pstmt.setString(1, username1);
	         pstmt.setString(2, password1);
	         pstmt.setString(3, tel1);
	  	     pstmt.executeUpdate();
	  	   JOptionPane.showMessageDialog(null, "添加成功！");
	        }catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, "添加用户失败！");
	        	
	        }
	   
		
	}
}

