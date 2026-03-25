package services;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import models.Order;
import models.OrderItem;
import models.Product;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import models.User;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;

    public OrderService() {
        this.orderDAO = new OrderDAOImpl();
        this.productDAO = new ProductDAOImpl();
        this.userDAO = new UserDAOImpl();
    }
//
//    public void placeOrder() {
//        orderDAO.placeOrder();
//    }

    public void viewAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào.");
            return;
        }

        System.out.println("\n=== DANH SÁCH ĐƠN HÀNG ===");
        System.out.printf("%-10s %-15s %-15s %-15s%n", "Mã ĐH", "Mã KH", "Ngày đặt", "Tổng tiền");
        System.out.println("------------------------------------------------------------");

        for (Order order : orders) {
            User user = userDAO.getUserById(order.getUserId());
            String userName = user != null ? user.getFullName() : "Unknown";
            System.out.printf("%-10d %-15d %-15s %-15.0f VNĐ%n",
                    order.getOrderId(),
                    order.getUserId(),
                    order.getOrderDate(),
                    order.getTotalAmount());
        }
    }

    public void viewOrderDetails(int orderId) {
        List<OrderItem> orderItems = orderDAO.getOrderItemsByOrderId(orderId);
        if (orderItems.isEmpty()) {
            System.out.println("Không tìm thấy chi tiết đơn hàng hoặc đơn hàng không tồn tại.");
            return;
        }

        Order order = null;
        List<Order> orders = orderDAO.getAllOrders();
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                order = o;
                break;
            }
        }

        if (order == null) {
            System.out.println("Không tìm thấy đơn hàng.");
            return;
        }

        User user = userDAO.getUserById(order.getUserId());
        System.out.println("\n=== CHI TIẾT ĐƠN HÀNG ===");
        System.out.println("Mã đơn hàng: " + order.getOrderId());
        System.out.println("Khách hàng: " + (user != null ? user.getFullName() : "Unknown"));
        System.out.println("Ngày đặt: " + order.getOrderDate());
        System.out.println("Tổng tiền: " + order.getTotalAmount() + " VNĐ");
        System.out.println("\nChi tiết sản phẩm:");
        System.out.printf("%-10s %-25s %-10s %-15s %-15s%n", "Mã SP", "Tên sản phẩm", "SL", "Đơn giá", "Thành tiền");
        System.out.println("--------------------------------------------------------------------------");

        double total = 0;
        for (OrderItem item : orderItems) {
            Product product = productDAO.getProductById(item.getProductId());
            if (product != null) {
                double lineTotal = product.getPrice() * item.getQuantity();
                total += lineTotal;
                System.out.printf("%-10d %-25s %-10d %-15.0f %-15.0f VNĐ%n",
                        product.getProductId(),
                        product.getProductName(),
                        item.getQuantity(),
                        product.getPrice(),
                        lineTotal);
            }
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Tổng cộng: %.0f VNĐ%n", total);
    }
}
