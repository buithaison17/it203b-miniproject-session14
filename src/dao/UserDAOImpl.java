package dao;

import models.User;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    
    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("full_name"),
                    rs.getString("address")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin user: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("full_name"),
                    rs.getString("address")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách user: " + e.getMessage());
        }
        return list;
    }
    
    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO Users(full_name, address) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getAddress());
            
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm user: " + e.getMessage());
        }
        return false;
    }

    public List<User> getTop5Users() {
        List<User> list = new ArrayList<>();
        String sql =
                "SELECT u.user_id, u.full_name, u.address, " +
                        "SUM(oi.quantity * oi.price) AS total " +
                        "FROM Users u " +
                        "JOIN Orders o ON u.user_id = o.user_id " +
                        "JOIN OrderItems oi ON o.order_id = oi.order_id " +
                        "GROUP BY u.user_id, u.full_name, u.address " +
                        "ORDER BY total DESC " +
                        "LIMIT 5";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("address")
                );
                list.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy top 5 user");
            e.printStackTrace();
        }
        return list;
    }
}
