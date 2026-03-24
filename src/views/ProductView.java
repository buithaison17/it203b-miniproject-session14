package views;

import models.Product;
import services.ProductService;
import utils.Input;

import java.util.List;

public class ProductView {
    private ProductService productService = new ProductService();

    public void menu() {
        while (true) {
            System.out.println("\n=== Quản lý Sản phẩm ===");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("0. Thoát");
            int choice = Input.inputIntegerPositive("Chọn: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showProducts();
                case 0 -> {
                    System.out.println("Thoát...");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void addProduct() {
        String name = Input.inputString("Tên sản phẩm: ");
        int quantity = Input.inputIntegerPositive("Số lượng: ");
        double stock = Input.inputDoublePositive("Giá: ");

        Product p = new Product(0, name, quantity, stock);
        productService.addProduct(p);
    }

    private void showProducts() {
        List<Product> products = productService.showProducts();
        System.out.println("\n--- Danh sách sản phẩm ---");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
