package views;

import models.User;
import services.UserService;
import utils.Input;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private UserService userService;
    private Scanner sc = new Scanner(System.in);

    public UserView(UserService userService) {
        this.userService = userService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Thêm user");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Hiển thị top 5 user mua nhiều nhất");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = Input.inputIntegerPositive("Nhập chức năng: ");
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    showUsers();
                    break;
                case 3:
                    showTop5Users();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Sai lựa chọn");
            }
        }
    }

    // nhập dữ liệu từ người dùng rồi gọi service
    private void addUser() {
        try {
            System.out.print("Tên: ");
            String name = sc.nextLine().trim();

            // validate tên
            if (name.isEmpty()) {
                System.out.println("Tên không được để trống!");
                return;
            }
            if (name.length() < 3 || name.length() > 50) {
                System.out.println("Tên phải từ 3 đến 50 ký tự!");
                return;
            }
            if (!name.matches("[a-zA-ZÀ-ỹ\\s]+")) {
                System.out.println("Tên không hợp lệ (không chứa số/ký tự đặc biệt)!");
                return;
            }

            System.out.print("Địa chỉ: ");
            String address = sc.nextLine().trim();

            // validate địa chỉ
            if (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống!");
                return;
            }
            if (address.length() > 100) {
                System.out.println("Địa chỉ tối đa 100 ký tự!");
                return;
            }

            // tạo object
            User user = new User(0, name, address);

            // gọi service
            if (userService.addUser(user)) {
                System.out.println("Thêm thành công");
            } else {
                System.out.println("Thêm thất bại");
            }

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        }
    }

    //lấy danh sách từ service in ra màn hình
    private void showUsers() {
        List<User> list = userService.getAllUsers();
        System.out.println("\nDanh sách user:");
        // duyệt list và in ra
        for (User u : list) {
            System.out.println(
                    u.getUserId() + " - " + u.getFullName() + " - " + u.getAddress()
            );
        }
    }

    private void showTop5Users() {
        List<User> list = userService.getTop5Users();
        System.out.println("\nTop 5 user mua nhiều nhất:");
        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu");
            return;
        }
        for (User u : list) {
            System.out.println(
                    u.getUserId() + " - " + u.getFullName() + " - " + u.getAddress()
            );
        }
    }
}