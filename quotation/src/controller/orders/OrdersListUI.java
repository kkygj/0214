package controller.orders;

import model.Orders;
import model.OrderItems;
import dao.OrdersDao;
import dao.OrderItemsDao;
import dao.impl.OrdersDaoImpl;
import dao.impl.OrderItemsDaoImpl;
import model.Customer;
import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdersListUI extends JFrame {
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> statusFilter;
    private JButton btnViewDetails, btnEditStatus, btnDelete, btnRefresh;
    private OrdersDao ordersDao;
    private OrderItemsDao orderItemsDao;
    private CustomerDao customerDao;
    private Timer timer;

    public OrdersListUI() {
        setTitleWithTime();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ordersDao = new OrdersDaoImpl();
        orderItemsDao = new OrderItemsDaoImpl();
        customerDao = new CustomerDaoImpl();

        System.out.println("OrdersListUI initialized");

        // 頂部搜尋和篩選面板
        JPanel topPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        statusFilter = new JComboBox<>(new String[]{"All", "Pending", "Completed", "Canceled"});
        btnRefresh = new JButton("刷新");
        topPanel.add(new JLabel("搜尋 (ID/客戶名稱):"));
        topPanel.add(searchField);
        topPanel.add(new JLabel("篩選狀態:"));
        topPanel.add(statusFilter);
        topPanel.add(btnRefresh);
        add(topPanel, BorderLayout.NORTH);

        // 訂單表格
        tableModel = new DefaultTableModel(new Object[]{"訂單 ID", "客戶名稱", "總金額", "狀態", "訂單日期"}, 0);
        ordersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        // 底部操作按鈕
        JPanel bottomPanel = new JPanel(new FlowLayout());
        btnViewDetails = new JButton("查看詳情");
        btnEditStatus = new JButton("修改狀態");
        btnDelete = new JButton("刪除訂單");
        bottomPanel.add(btnViewDetails);
        bottomPanel.add(btnEditStatus);
        bottomPanel.add(btnDelete);
        add(bottomPanel, BorderLayout.SOUTH);

        // 載入訂單資料
        loadOrders();

        // 設置監聽器
        btnRefresh.addActionListener(e -> loadOrders());
        btnViewDetails.addActionListener(e -> viewOrderDetails());
        btnEditStatus.addActionListener(e -> editOrderStatus());
        btnDelete.addActionListener(e -> deleteOrder());
        searchField.addActionListener(e -> loadOrders());
        statusFilter.addActionListener(e -> loadOrders());

        // 設定計時器，每秒更新標題時間
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();
    }

    /**
     * 讀取訂單資料並更新表格
     */
    private void loadOrders() {
        tableModel.setRowCount(0);
        List<Orders> ordersList = ordersDao.getAllOrders();
        String searchText = searchField.getText().trim().toLowerCase();
        String selectedStatus = statusFilter.getSelectedItem().toString();

        for (Orders order : ordersList) {
            Customer customer = customerDao.selectById(order.getCustomerId());
            String customerName = (customer != null) ? customer.getName() : "未知";

            if (!searchText.isEmpty() && !String.valueOf(order.getId()).contains(searchText) && !customerName.toLowerCase().contains(searchText)) {
                continue; // 跳過不匹配的訂單
            }

            if (!selectedStatus.equals("All") && !order.getStatus().equals(selectedStatus)) {
                continue; // 跳過不匹配狀態的訂單
            }

            tableModel.addRow(new Object[]{order.getId(), customerName, order.getTotalAmount(), order.getStatus(), order.getDate()});
        }
    }

    /**
     * 顯示訂單詳情
     */
    private void viewOrderDetails() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "請選擇一筆訂單!", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        Orders order = ordersDao.getOrderById(orderId);
        if (order == null) {
            JOptionPane.showMessageDialog(this, "無法找到該訂單!", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<OrderItems> items = orderItemsDao.getAllOrderItemsByOrderId(orderId);
        StringBuilder details = new StringBuilder();
        details.append("訂單 ID: ").append(order.getId()).append("\n");
        details.append("客戶 ID: ").append(order.getCustomerId()).append("\n");
        details.append("總金額: ").append(order.getTotalAmount()).append("\n");
        details.append("狀態: ").append(order.getStatus()).append("\n");
        details.append("訂單日期: ").append(order.getDate()).append("\n");
        details.append("商品列表:\n");

        for (OrderItems item : items) {
            details.append("- ").append(item.getProductName())
                    .append(" x ").append(item.getQuantity())
                    .append(" (").append(item.getPrice()).append(")\n");
        }

        JOptionPane.showMessageDialog(this, details.toString(), "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 修改訂單狀態
     */
    private void editOrderStatus() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "請選擇一筆訂單!", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int orderId = (int) tableModel.getValueAt(selectedRow, 0);

        String[] statusOptions = {"Pending", "Completed", "Canceled"};
        String newStatus = (String) JOptionPane.showInputDialog(this, "選擇新的狀態:", "修改狀態",
                JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

        if (newStatus != null) {
            Orders order = ordersDao.getOrderById(orderId);
            if (order != null) {
                order.setStatus(newStatus);
                ordersDao.updateOrder(order);
                loadOrders();
                JOptionPane.showMessageDialog(this, "訂單狀態已更新!");
            }
        }
    }

    /**
     * 刪除訂單
     */
    private void deleteOrder() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "請選擇一筆訂單!", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "確定要刪除此訂單嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            ordersDao.deleteOrder(orderId);
            loadOrders();
            JOptionPane.showMessageDialog(this, "訂單已刪除!");
        }
    }

    /**
     * 設定視窗標題，包含系統時間
     */
    private void setTitleWithTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setTitle("訂單管理 - " + sdf.format(new Date()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrdersListUI().setVisible(true));
    }
}
