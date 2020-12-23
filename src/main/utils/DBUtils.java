package main.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:12/12/2020 21:26
 */

public class DBUtils {
    // 创建数据库连接对象
    private static DataSource ds=new ComboPooledDataSource();// 会自动去加载配置文件

    public static DataSource getDataSource()
    {
        return  ds;
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();//获取连接对象
    }
}
