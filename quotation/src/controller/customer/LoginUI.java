package controller.customer;

import model.Customer;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private Timer timer;

    private CustomerService customerService = new CustomerServiceImpl();

    // 密碼正規表示法：至少8位，包含大小寫字母和數字
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public LoginUI() {
        setTitleWithTime();
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3, 2, 5, 5));

        getContentPane().add(new JLabel("帳號:"));
        txtUsername = new JTextField();
        txtUsername.setText("admin");
        getContentPane().add(txtUsername);

        getContentPane().add(new JLabel("密碼: *至少8位，包含大小寫字母與數字"));
        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("密碼必須至少8位，包含大小寫字母和數字");
        getContentPane().add(txtPassword);

        btnLogin = new JButton("登入");
        btnRegister = new JButton("註冊");
        getContentPane().add(btnLogin);
        getContentPane().add(btnRegister);

        // 每秒更新標題時間
        timer = new Timer(1000, e -> setTitleWithTime());
        timer.start();

        // 登入按鈕監聽
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            // 檢查密碼格式
            if (!password.matches(PASSWORD_REGEX)) {
                JOptionPane.showMessageDialog(this, "密碼必須至少8位，包含大小寫字母和數字！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                return; // 停止登入流程
            }

            Customer customer = customerService.login(username, password);
            if (customer != null) {
                new LoginSuccessUI(customer.getName()).setVisible(true);
                dispose();
            } else {
                // 檢查帳號是否存在
                if (customerService.selectByUsername(username) != null) {
                    new RegisterErrorUI().setVisible(true); // 帳號存在但密碼錯誤
                } else {
                    new LoginErrorUI().setVisible(true); // 帳號不存在，提示先註冊
                }
                dispose();
            }
        });

        // 註冊按鈕監聽
        btnRegister.addActionListener(e -> {
            new RegisterUI().setVisible(true);
            dispose();
        });
    }

    // 設定標題為 "登入系統" + 當前時間
    private void setTitleWithTime() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = DATE_FORMAT.format(new Date());
        setTitle("登入系統 - " + currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
