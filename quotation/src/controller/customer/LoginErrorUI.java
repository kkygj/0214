package controller.customer;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginErrorUI extends JFrame {
    private JButton btnRegister;
    private Timer timer;

    public LoginErrorUI() {
        setTitleWithTime();
        setSize(372, 177);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JLabel lblMessage = new JLabel("登入失敗！", SwingConstants.CENTER);
        getContentPane().add(lblMessage, BorderLayout.CENTER);

        btnRegister = new JButton("請先註冊");
        getContentPane().add(btnRegister, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> {
            new RegisterUI().setVisible(true);
            dispose();
        });

        // 啟動計時器，每秒更新標題時間
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();
    }

    // 設定標題為 "登入失敗" + 當前時間
    private void setTitleWithTime() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = DATE_FORMAT.format(new Date());
        setTitle("登入失敗 - " + currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginErrorUI().setVisible(true));
    }
}
