package views;

import services.OrderService;
import utils.Input;

public class OrderView {
    private OrderService orderService;

    public OrderView() {
        this.orderService = new OrderService();
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ ĐƠN HÀNG ===");
            System.out.println("1. Xem danh sách đơn hàng");
            System.out.println("2. Xem chi tiết đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = Input.inputIntegerPositive("");

            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    viewOrderDetails();
                    break;
                case 0:
                    System.out.println("Thoát quản lý đơn hàng.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private void viewAllOrders() {
        orderService.viewAllOrders();
    }

    private void viewOrderDetails() {
        System.out.println("\n=== XEM CHI TIẾT ĐƠN HÀNG ===");
        int orderId = Input.inputIntegerPositive("Nhập mã đơn hàng: ");
        orderService.viewOrderDetails(orderId);
    }
}
