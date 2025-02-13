package dao;

import model.Orders;
import java.util.*;

public interface OrdersDao {
    void addOrder(Orders order);
    Orders getOrderById(int id);
    List<Orders> getAllOrders();
    void updateOrder(Orders order);
    void deleteOrder(int id);
}