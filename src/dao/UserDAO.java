package dao;

import models.User;
import java.util.List;

public interface UserDAO {
    User getUserById(int userId);
    List<User> findAll();
    boolean insert(User user);
    List<User> getTop5Users();
}
