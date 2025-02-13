package controller.customer;

import javax.swing.*;

public class RegisterSuccessUI extends JFrame {
    public RegisterSuccessUI() {
        setTitle("註冊成功");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblMessage = new JLabel("註冊成功，請重新登入", SwingConstants.CENTER);
        add(lblMessage);

        JButton btnOk = new JButton("確定");
        add(btnOk, "South");

        btnOk.addActionListener(e -> {
            new LoginUI().setVisible(true);
            dispose();
        });
    }
}
