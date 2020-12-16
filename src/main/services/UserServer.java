package main.services;

import main.dao.UserDao;
import main.model.Admin;
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

    // （登录）传入user对象 判断用户密码是否正确
    public boolean Login(int id,String password)
    {
        User user = new User();
        user.setUserID(id);
        user.setUserPassword(password);
        try {
            boolean result=userDao.IdentifyAdministrator(user);
            return result;
        } catch (SQLException e) {
            System.out.println("UserServer-Login Error:"+e);
            return false;
        }
    }

    // 获取所有user信息
    public List<User> getAllUser()
    {
        try {
            return userDao.getAllUser();
        } catch (SQLException e) {
            System.out.println("UserServer-getAllUser Error:"+e);
            return null;
        }
    }

    // 创建新用户
    public boolean createUser(User user)
    {
        try {
            return userDao.createUser(user);
        } catch (SQLException e) {
            System.out.println("UserServer-createUser Error:"+e);
            return false;
        }
    }

    // 删除用户
    public boolean deleteUser(int userID)
    {
        try {
            return userDao.deleteUser(userID);
        } catch (SQLException e) {
            System.out.println("UserServer-deleteUser Error:"+e);
            return false;
        }
    }

    // 查询用户
    public User queryUserByUserID(int userID)
    {
        try {
            return userDao.getUserByUserID(userID);
        } catch (SQLException e) {
            System.out.println("UserServer-queryUserByUserID Error:"+e);
            return null;
        }
    }

    // 修改用户信息
    public boolean updateUser(User user)
    {
        try {
            return userDao.updateUser(user);
        } catch (SQLException e) {
            System.out.println("UserServer-updateUser Error:"+e);
            return false;
        }
    }
}
