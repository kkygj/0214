package service.impl;

import dao.OrdersDao;
import dao.impl.OrdersDaoImpl;
import model.Orders;
import service.OrdersService;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    private OrdersDao ordersDao;

    public OrdersServiceImpl() {
        this.ordersDao = new OrdersDaoImpl();
    }

    @Override
    public void createOrder(Orders order) {
        ordersDao.addOrder(order);
    }

    @Override
    public Orders getOrderById(int id) {
        return ordersDao.getOrderById(id);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersDao.getAllOrders();
    }

    @Override
    public void updateOrder(Orders order) {
        ordersDao.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) {
        ordersDao.deleteOrder(id);
    }
}
