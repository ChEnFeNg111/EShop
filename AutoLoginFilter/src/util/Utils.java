package util;


import java.sql.*;

/**
 * JDBC连接数据库
 *
 * @author chen
 */
public class Utils {
     static final String URL="jdbc:mysql://localhost:3306/users?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
     static final String USERNAME="root";
     static final String PASSWORD="123";

    /* 注册驱动 */
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    /**
     * 关闭资源
     */

    public static void close(ResultSet rs, Connection conn, PreparedStatement stat){
        /**
         *关闭  ResultSet
         */
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * 关闭 Connection
         */
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        /**
         * 关闭 PreparedStatement
         */
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, PreparedStatement stat){
        Utils.close(null,conn,stat);
    }
}
