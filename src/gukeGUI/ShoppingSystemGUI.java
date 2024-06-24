package gukeGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dengluzhuceGUI.Dengluuser;

@SuppressWarnings("serial")
public class ShoppingSystemGUI extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JPanel productPanel;
    private JPanel orderPanel;
    private JPanel resultPanel;

    private JLabel productNameLabel;
    private JTextField productNameField;

    private JLabel quantityLabel;
    private JComboBox<Integer> quantityComboBox;

    private JButton queryProductButton;

    private JButton orderProductButton;

    private JButton cancelOrderButton;

    private JButton refreshButton;
    private JButton freshButton;

    private JTable productTable;

    private DefaultTableModel productModel;

    private JLabel totalPriceLabel;
    private JTextField totalPriceField;

    private JLabel paymentLabel;
    private JTextField paymentField;

    private JLabel changeLabel;
    private JTextField changeField;

    private JButton checkoutButton;

    private JLabel resultLabel;
    Product product;
    ArrayList<Product> productList;

    boolean isDataFullyLoaded = false;

    public ShoppingSystemGUI() {
        initComponents();
        // 连接数据库并获取商品数据
        connectToDatabase();
        productList = new ArrayList<>();
        for (int i = 0; i < productModel.getRowCount(); i++) {
            int id = (int) productModel.getValueAt(i, 0);
            String name = (String) productModel.getValueAt(i, 1);
            double price = (double) productModel.getValueAt(i, 2);
            int stock = (int) productModel.getValueAt(i, 3);
            productList.add(new Product(id, name, price, stock));
        }
    }

    private void initComponents() {
        setTitle("购物系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));

        mainPanel = new JPanel(new BorderLayout());

        productPanel = new JPanel(new FlowLayout());

        productNameLabel = new JLabel("商品名称：");
        productPanel.add(productNameLabel);

        productNameField = new JTextField(20);
        productPanel.add(productNameField);

        quantityLabel = new JLabel("数量：");
        productPanel.add(quantityLabel);

        quantityComboBox = new JComboBox<>();
        for (int i = 1; i <= 50; i++) {
            quantityComboBox.addItem(i);
        }
        productPanel.add(quantityComboBox);

        queryProductButton = new JButton("查询商品");
        queryProductButton.addActionListener(this);
        productPanel.add(queryProductButton);

        orderProductButton = new JButton("订购商品");
        orderProductButton.addActionListener(this);
        productPanel.add(orderProductButton);

        cancelOrderButton = new JButton("取消订购");
        cancelOrderButton.addActionListener(this);
        productPanel.add(cancelOrderButton);

        refreshButton = new JButton("刷新");
        refreshButton.addActionListener(this);
        productPanel.add(refreshButton);
        
        freshButton = new JButton("退出");
        freshButton.addActionListener(this);
        productPanel.add(freshButton);
        
//        freshButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               Dengluxiaoshou.denglu();
//               b.dispose();
//            }
//        });

        mainPanel.add(productPanel, BorderLayout.CENTER);

        productModel = new DefaultTableModel(new Object[]{"商品编号", "商品", "单价", "库存"}, 0);
        productTable = new JTable(productModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        orderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        totalPriceLabel = new JLabel("总价：");
        orderPanel.add(totalPriceLabel, gbc);

        gbc.gridx = 1;
        totalPriceField = new JTextField(10);
        totalPriceField.setEditable(false);
        orderPanel.add(totalPriceField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        paymentLabel = new JLabel("支付金额：");
        orderPanel.add(paymentLabel, gbc);

        gbc.gridx = 1;
        paymentField = new JTextField(10);
        orderPanel.add(paymentField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        changeLabel = new JLabel("找零：");
        orderPanel.add(changeLabel, gbc);

        gbc.gridx = 1;
        changeField = new JTextField(10);
        changeField.setEditable(false);
        orderPanel.add(changeField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        checkoutButton = new JButton("结账");
        checkoutButton.addActionListener(this);
        orderPanel.add(checkoutButton, gbc);

        mainPanel.add(orderPanel, BorderLayout.EAST);

        resultPanel = new JPanel(new FlowLayout());
        resultLabel = new JLabel("");
        resultPanel.add(resultLabel);

        add(mainPanel);
         pack();
         //居中设置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

       
        setVisible(true);
    }

    private void connectToDatabase() {
        // 替换为你的数据库连接信息
        String url = "jdbc:mysql://localhost:3306/newsql";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // 创建查询商品数据的语句
            String sql = "SELECT num, name, shoujia, kucun FROM goods";
            PreparedStatement statement = connection.prepareStatement(sql);

            // 执行查询并获取结果集
            ResultSet rs = statement.executeQuery();

            // 将结果集添加到产品模型中
            while (rs.next()) {
                int id = rs.getInt("num");
                String name = rs.getString("name");
                double price = rs.getDouble("shoujia");
                int stock = rs.getInt("kucun");

                productModel.addRow(new Object[]{id, name, price, stock});
            }
            

            isDataFullyLoaded = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "连接数据库失败：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void queryProduct() {
        String name = productNameField.getText();
        List<Product> foundProducts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsql", "root", "root")) {
            // 创建根据商品名称查询商品数据的语句
            String sql = "SELECT num, name, shoujia, kucun FROM goods WHERE name =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            // 执行查询并获取结果集
            ResultSet rs = statement.executeQuery();

            // 将结果集添加到产品模型中
            while (rs.next()) {
                int id = rs.getInt("num");
                String foundName = rs.getString("name");
                double price = rs.getDouble("shoujia");
                int stock = rs.getInt("kucun");

                foundProducts.add(new Product(id, foundName, price, stock));
            }

            // 更新产品表格
            updateProductTable(foundProducts);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "查询商品失败：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProductTable(List<Product> foundProducts) {
        if (foundProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "没有找到该商品。", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        productModel.setRowCount(0);
        for (Product product : foundProducts) {
            Object[] rowData = {product.getId(), product.getName(), product.getPrice(), product.getStock()};
            productModel.addRow(rowData);
        }
    }

    private void orderProduct() {
        String name = productNameField.getText();
        int quantity = (int) quantityComboBox.getSelectedItem();
         product = null;

        for (Product p : productList) {
            if (p.getName().equals(name)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            JOptionPane.showMessageDialog(this, "未找到商品！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (product.getStock() < quantity) {
            JOptionPane.showMessageDialog(this, "库存不足！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 获取选中商品的单价
        double price = product.getPrice();

        // 计算总价
        double totalPrice = price * quantity;

        // 将总价显示在总价文本框中
        totalPriceField.setText(String.valueOf(totalPrice));
    }

    private void cancelOrder() {
        totalPriceField.setText("");
        quantityComboBox.setSelectedIndex(0);
        
        
    }

    private void checkout() {
        double totalPrice = Double.parseDouble(totalPriceField.getText());
        double payment = Double.parseDouble(paymentField.getText());

        if (payment < totalPrice) {
            JOptionPane.showMessageDialog(this, "支付不足！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double change = payment - totalPrice;
        changeField.setText(String.valueOf(change));

        String name = productNameField.getText();
        int quantity = (int) quantityComboBox.getSelectedItem();

        // 此处结账逻辑
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsql", "root", "root")) {
        	 String sql = "UPDATE goods SET kucun = kucun - ? WHERE num = ?";
        	    PreparedStatement updateStatement = connection.prepareStatement(sql);

        	    // 设置参数，第一个参数是购买的数量，第二个参数是商品编号
        	    updateStatement.setInt(1, quantity);
        	    updateStatement.setInt(2, product.getId());

        	    // 执行更新操作
        	    int rowsAffected = updateStatement.executeUpdate();

        	    if (rowsAffected > 0) {
        	        // 如果更新成功，库存减少，说明操作成功
        	        JOptionPane.showMessageDialog(this, "购买成功，库存已更新。");
        	    } else {
        	        // 如果更新失败，可能是因为库存不足或其他问题
        	        JOptionPane.showMessageDialog(this, "购买失败，库存不足。");
        	    }
        	} catch (SQLException e) {
        	    JOptionPane.showMessageDialog(this, "更新库存失败：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        	}
        
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        resultLabel.setText("购买详情：\n" + "商品编号：" + product.getId() + "\n" + "商品：" + name + "\n" + "单价：" + totalPrice / quantity + "\n" + "购买数量：" + quantity + "\n" + "时间：" + currentTime + "\n" + "应付：" + totalPrice + "\n" + "实付：" + payment + "\n" + "找零：" + change + "\n" + "欢迎下次光临！");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == queryProductButton) {
            queryProduct();
        } else if (e.getSource() == orderProductButton) {
            orderProduct();
        } else if (e.getSource() == cancelOrderButton) {
            cancelOrder();
        } else if (e.getSource() == checkoutButton) {
            checkout();
        } else if (e.getSource() == refreshButton) {
        	dispose();
        	new ShoppingSystemGUI();
        } else if (e.getSource() == freshButton) {
        	dispose();
        	Dengluuser.denglu();
        }
    }

    
    public static void main(String[] args) {
        new ShoppingSystemGUI();
    }

    class Product {
        private int id;
        private String name;
        private double price;
        private int stock;

        public Product(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }
    }
}