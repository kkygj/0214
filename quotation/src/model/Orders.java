package model;

import java.util.Date;

public class Orders {
    private int id;
    private int customerId;
    private double totalAmount;
    private Date date;
    private String status; // ✅ 新增欄位

    public Orders() {}

    public Orders(int id, int customerId, double totalAmount, Date date, String status) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status; // ✅ 初始化 status
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStatus() { return status; } // ✅ 新增 getStatus()
    public void setStatus(String status) { this.status = status; } // ✅ 新增 setStatus()
}
