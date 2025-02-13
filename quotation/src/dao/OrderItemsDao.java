package dao;

import model.OrderItems;
import java.util.List;

public interface OrderItemsDao {
    void addOrderItem(OrderItems orderItem);
    OrderItems getOrderItemById(int id);
    List<OrderItems> getAllOrderItemsByOrderId(int orderId);
    void updateOrderItem(OrderItems orderItem);
    void deleteOrderItem(int id);
}
