package service;

import model.Orders;
import java.util.List;

public interface OrdersService {
    void createOrder(Orders order);
    Orders getOrderById(int id);
    List<Orders> getAllOrders();
    void updateOrder(Orders order);
    void deleteOrder(int id);
}
