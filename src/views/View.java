package views;

import dao.UserDAOImpl;
import services.UserService;
import utils.Input;


public class View {
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Quản lý User");
            System.out.println("2. Quản lý Sản phẩm");
            System.out.println("3. Quản lý Đơn hàng");
            System.out.println("4. Đặt hàng");
            System.out.println("0: Thoát");
            System.out.print("Lựa chọn: ");
            int choice = Input.inputIntegerPositive("");

            switch (choice) {
                case 1:
                    UserService userService = new UserService(new UserDAOImpl());
                    UserView userView = new UserView(userService);
                    userView.menu();
                    break;
                case 2:
                    ProductView productView = new ProductView();
                    productView.menu();
                    break;
                case 3:
                    OrderView orderView = new OrderView();
                    orderView.menu();
                    break;
                case 4:
//                    placeOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

//    private static void placeOrder() {
//        System.out.print("Nhập mã sản phẩm (ví dụ: 1, 2, 3,...): ");
//        String productIdsString = Input.inputString("");
//        List<Integer> productIds = new ArrayList<>();
//        for (String id : productIdsString.split(",")) {
//            productIds.add(Integer.parseInt(id.trim()));
//        }
//
//        int userId = Input.inputIntegerPositive("Nhập mã User: ");
//
//        OrderService orderService = new OrderService();
//        List<OrderItem> orderItems = new ArrayList<>();
//        for (int productId : productIds) {
//            int quantity = Input.inputIntegerPositive("Nhập số lượng sản phẩm " + productId + ": ");
//            orderItems.add(new OrderItem(0, 0, productId, quantity));
//        }
//
//        orderService.placeOrder(userId, orderItems);
//    }

}