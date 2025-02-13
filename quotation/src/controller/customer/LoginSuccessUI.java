package controller.customer;

import javax.swing.*;
import controller.orders.OrdersMainUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginSuccessUI extends JFrame {
    private JLabel lblMessage;
    private JButton btnManageOrder;
    private JButton btnLogout;
    private Timer timer;

    public LoginSuccessUI(String name) {
        setTitleWithTime();
        setSize(331, 174);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        lblMessage = new JLabel(name + " 歡迎您!", SwingConstants.CENTER);
        getContentPane().add(lblMessage, BorderLayout.CENTER);

        JPanel panelBtn = new JPanel(new FlowLayout());
        btnManageOrder = new JButton("管理訂單");
        btnLogout = new JButton("登出");
        panelBtn.add(btnManageOrder);
        panelBtn.add(btnLogout);
        getContentPane().add(panelBtn, BorderLayout.SOUTH);

        btnManageOrder.addActionListener(e -> {
            new OrdersMainUI().setVisible(true);
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new LoginUI().setVisible(true);
            dispose();
        });

        // 啟動計時器，每秒更新標題
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();
    }

    // 設定標題為 "登入成功" + 當前時間
    private void setTitleWithTime() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = DATE_FORMAT.format(new Date());
        setTitle("登入成功 - " + currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSuccessUI("User").setVisible(true));
    }
}
