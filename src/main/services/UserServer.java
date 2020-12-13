package main.services;

import main.dao.UserDao;
import main.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 13:21
 */
public class UserServer {
    private UserDao userDao = new UserDao();

    public List<User> getAllUser()
    {
        try {
            return userDao.getAllUser();
        } catch (SQLException e) {
            System.out.println("UserServer-getAllUser Error:"+e);
            return null;
        }
    }
}
