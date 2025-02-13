package controller.orders;

import javax.swing.*;

import controller.customer.LoginUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersMainUI extends JFrame {
    private JButton btnCreate;
    private JButton btnManage;
    private JButton btnLogout;

    public OrdersMainUI() {
        setTitle("訂單管理");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrdersMainUI().setVisible(true));
    }
}