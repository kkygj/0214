package service.impl;

import dao.OrderItemsDao;
import dao.impl.OrderItemsDaoImpl;
import model.OrderItems;
import service.OrderItemsService;
import java.util.List;

public class OrderItemsServiceImpl implements OrderItemsService {
    private OrderItemsDao orderItemsDao;

    public OrderItemsServiceImpl() {
        this.orderItemsDao = new OrderItemsDaoImpl();
    }

    @Override
    public void createOrderItem(OrderItems orderItem) {
        orderItemsDao.addOrderItem(orderItem);
    }

    @Override
    public OrderItems getOrderItemById(int id) {
        return orderItemsDao.getOrderItemById(id);
    }

    @Override
    public List<OrderItems> getAllOrderItemsByOrderId(int orderId) {
        return orderItemsDao.getAllOrderItemsByOrderId(orderId);
    }

    @Override
    public void updateOrderItem(OrderItems orderItem) {
        orderItemsDao.updateOrderItem(orderItem);
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemsDao.deleteOrderItem(id);
    }
}
