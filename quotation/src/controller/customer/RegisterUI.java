package controller.customer;

import model.Customer;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

import javax.swing.*;
import java.awt.*;

public class RegisterUI extends JFrame {
    private JTextField txtUsername, txtName, txtEmail, txtPhone, txtAddress, txtVat;
    private JPasswordField txtPassword;
    private JButton btnRegister;

    private CustomerService customerService = new CustomerServiceImpl();

    // 正規表示法
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9]{5,15}$"; // 5-15 英數字元
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // 至少8位，包含大小寫+數字
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Email格式
    private static final String PHONE_REGEX = "^09\\d{8}$"; // 台灣手機號碼格式 09xxxxxxxx
    private static final String VAT_REGEX = "^\\d{8}$"; // 台灣統編 8 碼數字

    public RegisterUI() {
        setTitle("註冊");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("姓名:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("帳號 (5-15 位英數):"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("密碼 (至少8位，包含大小寫與數字):"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("電話 (台灣格式 09xxxxxxxx):"));
        txtPhone = new JTextField();
        panel.add(txtPhone);

        panel.add(new JLabel("地址:"));
        txtAddress = new JTextField();
        panel.add(txtAddress);

        panel.add(new JLabel("統編 (8 碼數字):"));
        txtVat = new JTextField();
        panel.add(txtVat);

        btnRegister = new JButton("註冊");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRegister);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 設置按鈕事件
        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();
            String vat = txtVat.getText().trim();

            // 驗證輸入格式
            if (!username.matches(USERNAME_REGEX)) {
                JOptionPane.showMessageDialog(this, "帳號需為 5-15 位英數組合，不可包含特殊符號！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.matches(PASSWORD_REGEX)) {
                JOptionPane.showMessageDialog(this, "密碼需至少 8 位，包含大小寫字母與數字！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!email.matches(EMAIL_REGEX)) {
                JOptionPane.showMessageDialog(this, "請輸入有效的 Email！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!phone.matches(PHONE_REGEX)) {
                JOptionPane.showMessageDialog(this, "電話需為台灣手機格式 (09xxxxxxxx)！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!vat.matches(VAT_REGEX)) {
                JOptionPane.showMessageDialog(this, "統編需為 8 碼數字！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 註冊邏輯
            if (customerService.register(new Customer(name, username, password, email, phone, address, vat))) {
                new RegisterSuccessUI().setVisible(true);
                dispose();
            } else {
                new RegisterErrorUI().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterUI().setVisible(true));
    }
}
