package controller.orders;

import javax.swing.*;

import controller.customer.LoginUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersMainUI extends JFrame {
    private JButton btnCreate;
    private JButton btnManage;
    private JButton btnLogout;
    private Timer timer;

    public OrdersMainUI() {
        setTitleWithTime(); // 設置標題（包含時間）
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // 設定計時器，每秒更新標題時間
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        btnCreate = new JButton("新增訂單");
        btnManage = new JButton("管理訂單");
        btnLogout = new JButton("登出");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnCreate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnManage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnLogout, gbc);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrdersCreateUI().setVisible(true);
            }
        });

        btnManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrdersListUI().setVisible(true);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUI().setVisible(true);
                dispose();
            }
        });
    }

    /**
     * 設置標題，包含當前時間
     */
    private void setTitleWithTime() {
        setTitle("訂單管理  |  " + getCurrentTime());
    }

    /**
     * 獲取當前時間的方法
     */
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrdersMainUI().setVisible(true));
    }
}
