package main.dao;

import main.model.Admin;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-13 14:57
 */
public class AdminDao {
    // 在数据库中对管理员登录身份验证 如果id和密码正确就返回true 否则返回false
    public boolean IdentifyAdministrator(Admin admin) throws SQLException {
        QueryRunner runner= new QueryRunner(DBUtils.getDataSource());
        String sql="select * from admin where adminID=? and adminPassword=?";
        Admin temp=runner.query(sql, new BeanHandler<Admin>(Admin.class),admin.getAdminID(),admin.getAdminPassword());
        if(temp!=null)
            return true;
        else
            return false;
    }
}
