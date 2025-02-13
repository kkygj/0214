package dao.impl;

import dao.OrderItemsDao;
import model.OrderItems;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDaoImpl implements OrderItemsDao {

    @Override
    public void addOrderItem(OrderItems orderItem) {
        String sql = "INSERT INTO order_items (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setString(2, orderItem.getProductName());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getPrice());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItems getOrderItemById(int id) {
        OrderItems orderItem = null;
        String sql = "SELECT * FROM order_items WHERE id = ?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    orderItem = new OrderItems();
                    orderItem.setId(rs.getInt("id"));
                    orderItem.setOrderId(rs.getInt("order_id"));
                    orderItem.setProductName(rs.getString("product_name"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setPrice(rs.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItems> getAllOrderItemsByOrderId(int orderId) {
        List<OrderItems> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderItems item = new OrderItems();
                    item.setId(rs.getInt("id"));
                    item.setOrderId(rs.getInt("order_id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void updateOrderItem(OrderItems orderItem) {
        String sql = "UPDATE order_items SET product_name=?, quantity=?, price=? WHERE id=?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, orderItem.getProductName());
            pstmt.setInt(2, orderItem.getQuantity());
            pstmt.setDouble(3, orderItem.getPrice());
            pstmt.setInt(4, orderItem.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int id) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
