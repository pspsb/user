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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Zhucexiaoshou{
	
	public static void zhuce() {
	 JFrame a = new JFrame("销售注册界面");
     a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     a.setSize(500,400);
     a.setLocation(500,200);
     a.setLayout(null);
     
     JMenu file = new JMenu("其他用户注册");
     JMenuBar menuBar = new JMenuBar();
     menuBar.add(file);
     JMenuItem admin = new JMenuItem("管理员注册");
     file.add(admin);
     JMenuItem zhuce = new JMenuItem("普通用户注册");
     file.add(zhuce);
     
     JLabel b = new JLabel("销售注册界面");
     b.setBounds(160, 30, 300, 100);
     a.getContentPane().add(b);
     
     JLabel r = new JLabel("用户名:");
     r.setBounds(110, 100, 200, 30);
     a.getContentPane().add(r);
     
     JTextField user = new JTextField(20);
     user.setBounds(160, 100, 200, 30);
     a.getContentPane().add(user);
     
     JLabel s = new JLabel("密码:");
     s.setBounds(110, 140, 200, 30);
     a.getContentPane().add(s);
     
     JTextField password1 = new JTextField(20);
     password1.setBounds(160, 140, 200, 30);
     a.getContentPane().add(password1);
     
     JLabel t = new JLabel("确认密码:");
     t.setBounds(110, 180, 200, 30);
     a.getContentPane().add(t);
     
     JTextField password2 = new JTextField(20);
     password2.setBounds(160, 180, 200, 30);
     a.getContentPane().add(password2);
     
     JLabel u = new JLabel("请输入密钥:");
     u.setBounds(95, 220, 200, 30);
     a.getContentPane().add(u);
     
     JTextField tel1 = new JTextField(20);
     tel1.setBounds(160, 220, 200, 30);
     a.getContentPane().add(tel1);
     
     JButton button1 = new JButton("确认");
     button1.setBounds(200, 260, 100, 30);
     a.getContentPane().add(button1);
     
     JButton button2 = new JButton("⇦返回登录");
     button2.setBounds(10, 320, 100, 20);
     a.getContentPane().add(button2);
     a.setJMenuBar(menuBar);
     a.setVisible(true);
     
     button1.addActionListener(new ActionListener() {
			
   		@Override
   		public void actionPerformed(ActionEvent e) {
   				
   				String name = user.getText();
   				String mima = password1.getText();
   				String m = password2.getText();
   			    String tel = tel1.getText();
   			    String ww = "111";

  					try {
  						if(mima.equals(m) && tel.equals(ww)) 
  						Jdbczhucexiaoshou.zhucexiaoshou(name,mima,m,tel);
  						else JOptionPane.showMessageDialog(null, "密钥错误或密码不一致！"); 

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
				//打开一个新的页面
				Dengluxiaoshou.denglu();
			}
		});
     
	admin.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				//销毁当前页面
				a.dispose();
				//打开一个新的页面
				Zhuceadmin.zhuce();
			}
		});
	zhuce.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				//销毁当前页面
				a.dispose();
				//打开一个新的页面
				Zhuceuser.zhuce();
			}
		});
     }
	public static void main(String[] args) {
		zhuce();
	}

}
	

