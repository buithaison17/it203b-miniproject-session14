package dao;

import models.Order;
import models.OrderItem;

import java.sql.Connection;
import java.util.List;

public interface OrderDAO {
    void placeOrder(int userId, List<OrderItem> items);
    void insertOrderItemDetails(Connection connection, int orderId, List<OrderItem> items);
    List<Order> getAllOrders();
    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
