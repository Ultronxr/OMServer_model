package utils.databases;

import java.sql.*;
import java.util.ResourceBundle;


/**
 * @Name: Mysql的静态方法类
 * @Description: 从资源文件中获取mysql配置资料，数据库连接和关闭操作
 *
 */
public class MyMysql {
    private static final String url;
    private static final String usr;
    private static final String pwd;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("mysql_config");

    static {
        url = resourceBundle.getString("jdbc.url");
        usr = resourceBundle.getString("jdbc.username");
        pwd = resourceBundle.getString("jdbc.password");
        String driverClassName = resourceBundle.getString("jdbc.driverClassName");

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    public static void closeConnection(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
