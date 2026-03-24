package dao;

import models.OrderItem;

import java.util.List;

public interface OrderDAO {
    void placeOrder(int userId, List<OrderItem> items);

    void insertOrderItemDetails(int orderId, List<OrderItem> items);
}
