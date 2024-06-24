package dengluzhuceGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Dengluxiaoshou{
	
	static JFrame a ;
	public static void denglu() {
		 a = new JFrame("主界面");
	     a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     a.setSize(500,400);
	     a.setLocation(500,200);
	     a.setLayout(null);
	     
	     JLabel b = new JLabel("销售登录");
	     b.setBounds(160, 30, 300, 100);
	     a.getContentPane().add(b);
	     
	     JMenu file = new JMenu("其他用户登录");
	     JMenuBar menuBar = new JMenuBar();
	     menuBar.add(file);
	     JMenuItem admin = new JMenuItem("管理员登录");
	     file.add(admin);
	     JMenuItem user = new JMenuItem("顾客登录");
	     file.add(user);
	     a.setJMenuBar(menuBar);
	     
	     JLabel r = new JLabel("用户名:");
	     r.setBounds(110, 100, 200, 30);
	     a.getContentPane().add(r);
	     
	     JTextField username = new JTextField(20);
	     username.setBounds(160, 100, 200, 30);
	     a.getContentPane().add(username);
	     
	     JLabel s = new JLabel("密码:");
	     s.setBounds(110, 140, 200, 30);
	     a.getContentPane().add(s);
	     
	     JTextField password = new JTextField(20);
	     password.setBounds(160, 140, 200, 30);
	     a.getContentPane().add(password);
//	     password.setEchoChar('*');
	     
	     JButton button1 = new JButton("登录");
	     button1.setBounds(160, 180, 100, 30);
	     a.getContentPane().add(button1);
	     JButton button2 = new JButton("注册");
	     button2.setBounds(260, 180, 100, 30);
	     a.getContentPane().add(button2);
	     a.setVisible(true);
	     
	     button1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = username.getText();
					String mima = password.getText();
					try {
						Jdbcdengluxiaoshou.dengluxiaoshou(name,mima);
						
					} catch (SQLException e1) {
		
						e1.printStackTrace();
					}
					
				}
			});
	     
	     button2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//销毁当前页面
					a.dispose();
					Zhucexiaoshou.zhuce();
				}
			});
	     admin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//销毁当前页面
					a.dispose();
					Dengluadmin.denglu();
				}
			});
	     
	     user.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//销毁当前页面
					a.dispose();
					Dengluuser.denglu();
				}
			});
	}
	
	public static void main(String[] args) {
	 denglu();    
}
}

