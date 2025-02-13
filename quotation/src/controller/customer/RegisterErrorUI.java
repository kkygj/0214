package controller.customer;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterErrorUI extends JFrame {
    private Timer timer;

    public RegisterErrorUI() {
        setTitleWithTime();
        setSize(373, 208);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblMessage = new JLabel("帳號重複，請重新註冊", SwingConstants.CENTER);
        lblMessage.setBounds(109, 48, 136, 15);
        lblMessage.setAlignmentX(CENTER_ALIGNMENT);
        getContentPane().add(lblMessage);

        JButton btnRetry = new JButton("返回註冊");
        btnRetry.setBounds(125, 102, 102, 23);
        btnRetry.setAlignmentX(CENTER_ALIGNMENT);
        getContentPane().add(btnRetry);

        btnRetry.addActionListener(e -> {
            new RegisterUI().setVisible(true);
            dispose();
        });

        // 啟動計時器，每秒更新標題
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();
    }

    // 設定標題為 "註冊失敗 - 當前時間"
    private void setTitleWithTime() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = DATE_FORMAT.format(new Date());
        setTitle("註冊失敗 - " + currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterErrorUI().setVisible(true));
    }
}
