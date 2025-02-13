package service.impl;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import model.Customer;
import service.CustomerService;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl(); 

    @Override
    public void addCustomer(Customer customer) {
        // 檢查帳號是否已存在
        if (customerDao.selectByUsername(customer.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        customerDao.add(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.selectAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.selectById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDao.delete(id);
    }

    @Override
    public Customer login(String username, String password) {
        return customerDao.selectByUsernameAndPassword(username, password);
    }

    @Override
    public boolean register(Customer customer) {
        // 檢查帳號是否已存在
        if (customerDao.selectByUsername(customer.getUsername()) != null) {
            return false; // 註冊失敗，帳號已存在
        }
        customerDao.add(customer);
        return true; // 註冊成功
    }

    @Override
    public Customer selectByUsername(String username) {
        return customerDao.selectByUsername(username);
    }
}