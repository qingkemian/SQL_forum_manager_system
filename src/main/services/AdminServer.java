package main.services;

import main.dao.AdminDao;
import main.model.Admin;

import java.sql.SQLException;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 15:04
 */
public class AdminServer {
    //  需要调用AdminDao中的方法
    private AdminDao adminDao = new AdminDao();

    // （登录）将id password传入admin中 判断用户密码是否正确
    public boolean adminLogin(int id,String password)
    {
        Admin admin = new Admin();
        admin.setAdminID(id);
        admin.setAdminPassword(password);
        try {
            boolean result = adminDao.IdentifyAdministrator(admin);
            return result;
        } catch (SQLException e) {
            System.out.println("AdminServer Error:"+e);
            return false;
        }
    }
}
