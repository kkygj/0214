package controller.orders;

import model.Orders;
import model.OrderItems;
import dao.CustomerDao;
import dao.OrdersDao;
import dao.OrderItemsDao;
import dao.impl.CustomerDaoImpl;
import dao.impl.OrdersDaoImpl;
import dao.impl.OrderItemsDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersCreateUI extends JFrame {
    private JComboBox<String> customerDropdown;
    private JLabel lblTotalAmount;
    private JTable orderItemsTable;
    private DefaultTableModel tableModel;
    private JButton btnAddItem, btnSubmit, btnCancel;
    private List<OrderItems> orderItemsList;
    private OrdersDao ordersDao;
    private OrderItemsDao orderItemsDao;
    private CustomerDao customerDao;
    private Timer timer; // 計時器

    public OrdersCreateUI() {
        setTitleWithTime(); // ✅ 設置標題時間
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        customerDao = new CustomerDaoImpl();
        ordersDao = new OrdersDaoImpl();
        orderItemsDao = new OrderItemsDaoImpl();
        orderItemsList = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        customerDropdown = new JComboBox<>(customerDao.getAllCustomerNames().toArray(new String[0]));
        lblTotalAmount = new JLabel("0.00");

        inputPanel.add(new JLabel("客戶:"));
        inputPanel.add(customerDropdown);
        inputPanel.add(new JLabel("總金額:"));
        inputPanel.add(lblTotalAmount);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"產品名稱", "數量", "價格", "小計"}, 0);
        orderItemsTable = new JTable(tableModel);
        add(new JScrollPane(orderItemsTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnAddItem = new JButton("新增商品");
        btnSubmit = new JButton("提交訂單");
        btnCancel = new JButton("取消");
        buttonPanel.add(btnAddItem);
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);

        btnAddItem.addActionListener(e -> addOrderItem());
        btnSubmit.addActionListener(e -> submitOrder());
        btnCancel.addActionListener(e -> dispose());

        // ✅ 設置計時器，每秒更新標題
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();
    }

    private void addOrderItem() {
        String productName = JOptionPane.showInputDialog(this, "輸入產品名稱:");
        String quantityStr = JOptionPane.showInputDialog(this, "輸入數量:");
        String priceStr = JOptionPane.showInputDialog(this, "輸入單價:");

        if (productName != null && quantityStr != null && priceStr != null) {
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            double total = quantity * price;
            orderItemsList.add(new OrderItems(0, 0, productName, quantity, price));
            tableModel.addRow(new Object[]{productName, quantity, price, total});
            updateTotalAmount();
        }
    }

    private void updateTotalAmount() {
        double total = orderItemsList.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
        lblTotalAmount.setText(String.format("%.2f", total));
    }

    private void submitOrder() {
        int customerId = customerDropdown.getSelectedIndex() + 1;
        double totalAmount = Double.parseDouble(lblTotalAmount.getText());
        String status = "Pending"; // 預設新訂單為 Pending

        Orders newOrder = new Orders(0, customerId, totalAmount, new Date(), status);
        ordersDao.addOrder(newOrder);

        if (newOrder.getId() > 0) {
            for (OrderItems item : orderItemsList) {
                item.setOrderId(newOrder.getId());
                orderItemsDao.addOrderItem(item);
            }
            JOptionPane.showMessageDialog(this, "訂單提交成功!");
        } else {
            JOptionPane.showMessageDialog(this, "訂單提交失敗!", "錯誤", JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }

    // ✅ 獲取當前時間並更新標題
    private void setTitleWithTime() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = DATE_FORMAT.format(new Date());
        setTitle("新增訂單 - " + currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrdersCreateUI().setVisible(true));
    }
}
