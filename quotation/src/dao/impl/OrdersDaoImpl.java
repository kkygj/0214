package dao.impl;

import dao.OrdersDao;
import model.Orders;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public void addOrder(Orders order) {
        String sql = "INSERT INTO orders (customer_id, total_price, order_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDouble(2, order.getTotalAmount()); // total_amount -> total_price
            pstmt.setTimestamp(3, new java.sql.Timestamp(order.getDate().getTime())); // date -> order_date
            pstmt.setString(4, order.getStatus()); // 新增 status 欄位

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            // 獲取自動生成的訂單 ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders getOrderById(int id) {
        Orders order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    order = new Orders();
                    order.setId(rs.getInt("id"));
                    order.setCustomerId(rs.getInt("customer_id"));
                    order.setTotalAmount(rs.getDouble("total_price")); // total_amount -> total_price
                    order.setDate(rs.getTimestamp("order_date")); // date -> order_date
                    order.setStatus(rs.getString("status")); // 新增 status
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmount(rs.getDouble("total_price")); // total_amount -> total_price
                order.setDate(rs.getTimestamp("order_date")); // date -> order_date
                order.setStatus(rs.getString("status")); // 新增 status
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public void updateOrder(Orders order) {
        String sql = "UPDATE orders SET customer_id=?, total_price=?, order_date=?, status=? WHERE id=?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDouble(2, order.getTotalAmount()); // total_amount -> total_price
            pstmt.setTimestamp(3, new java.sql.Timestamp(order.getDate().getTime())); // date -> order_date
            pstmt.setString(4, order.getStatus()); // 更新 status
            pstmt.setInt(5, order.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DbConnection.getDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
