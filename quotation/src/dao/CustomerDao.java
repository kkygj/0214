package dao;

import model.Customer;
import model.OrderItems;

import java.util.List;

public interface CustomerDao {
    // Create
    void add(Customer customer);

    // Read
    List<Customer> selectAll();
    Customer selectById(int id);
    Customer selectByUsernameAndPassword(String username, String password);
    Customer selectByUsername(String username); // 查詢帳號是否已存在    

    // Update
    void update(Customer customer);

    // Delete
    void delete(int id);

	List<OrderItems> getAllCustomerNames();
}
