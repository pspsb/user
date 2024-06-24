package dengluzhuceGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Xiugaimima {
	public static void xiugaimima() {
		JFrame a = new JFrame("修改密码");
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     a.setSize(500,400);
	     a.setLocation(500,200);
	     a.setLayout(null);
	     
	     JLabel r = new JLabel("用户名:");
	     r.setBounds(110, 100, 200, 30);
	     a.getContentPane().add(r);
	     
	     JTextField user = new JTextField();
	     user.setBounds(160, 100, 200, 30);
	     a.getContentPane().add(user);
	     
	     JLabel s = new JLabel("新密码:");
	     s.setBounds(110, 140, 200, 30);
	     a.getContentPane().add(s);
	     
	     JTextField password1 = new JTextField();
	     password1.setBounds(160, 140, 200, 30);
	     a.getContentPane().add(password1);
	     
	     JLabel t = new JLabel("确认密码:");
	     t.setBounds(110, 180, 200, 30);
	     a.getContentPane().add(t);
	     
	     JTextField password2 = new JTextField();
	     password2.setBounds(160, 180, 200, 30);
	     a.getContentPane().add(password2);
	     
	     JLabel u = new JLabel("手机号:");
	     u.setBounds(110, 220, 200, 30);
	     a.getContentPane().add(u);
	     
	     JTextField tel1 = new JTextField();
	     tel1.setBounds(160, 220, 200, 30);
	     a.getContentPane().add(tel1);
	     
	     JButton button1 = new JButton("确认");
	     button1.setBounds(200, 260, 100, 30);
	     a.getContentPane().add(button1);
	     
	     JButton button2 = new JButton("⇦返回登录");
	     button2.setBounds(10, 320, 100, 20);
	     a.getContentPane().add(button2);
	    
	     a.setVisible(true);
	     
	     button1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = user.getText();
					String mima = password1.getText();
					String mima2 = password2.getText();
					String tel = tel1.getText();
					
					try {
						Jdbcxiugaiuser.xiugai(name,mima,mima2,tel);
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
	 				Dengluuser.denglu();
	 			}
	 		});
	     
	     
	}
	public static void main(String[] args) {
		xiugaimima();
	}
}
