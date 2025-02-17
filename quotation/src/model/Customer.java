package model;

public class Customer {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String vat;

    // 無參數建構子
    public Customer() {}

    // 這是用於完整的 Customer 物件
    public Customer(int id, String name, String username, String password, String email, String phone, String address, String vat) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.vat = vat;
    }

    // **這是新加入的建構子，讓 `RegisterUI` 可以使用**
    public Customer(String name, String username, String password, String email, String phone, String address, String vat) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.vat = vat;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getVat() { return vat; }
    public void setVat(String vat) { this.vat = vat; }
}
