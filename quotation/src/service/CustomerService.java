package service;

import model.Customer;
import java.util.List;

public interface CustomerService {

    // ===========================
    // CREATE (新增)
    // ===========================
    
    /**
     * 新增客戶
     * @param customer 要新增的客戶對象
     */
    void addCustomer(Customer customer);

    /**
     * 註冊客戶
     * @param customer 要註冊的客戶對象
     * @return 註冊成功與否
     */
    boolean register(Customer customer);

    // ===========================
    // READ (查詢)
    // ===========================

    /**
     * 獲取所有客戶列表
     * @return 所有客戶的列表
     */
    List<Customer> getAllCustomers();

    /**
     * 根據 ID 查找客戶
     * @param id 客戶 ID
     * @return 客戶對象，若不存在則返回 null
     */
    Customer getCustomerById(int id);

    /**
     * 根據用戶名查找客戶
     * @param username 用戶名
     * @return 查詢到的客戶對象
     */
    Object selectByUsername(String username);

    // ===========================
    // UPDATE (更新)
    // ===========================

    /**
     * 更新客戶資料
     * @param customer 包含更新資料的客戶對象
     */
    void updateCustomer(Customer customer);

    // ===========================
    // DELETE (刪除)
    // ===========================

    /**
     * 根據 ID 刪除客戶
     * @param id 客戶 ID
     */
    void deleteCustomer(int id);

    // ===========================
    // 其他業務邏輯
    // ===========================

    /**
     * 客戶登入
     * @param username 用戶名
     * @param password 密碼
     * @return 登入成功的客戶對象，若失敗則返回 null
     */
    Customer login(String username, String password);
}
