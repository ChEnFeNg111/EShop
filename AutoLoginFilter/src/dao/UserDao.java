package dao;


import entity.User;
import util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    /**
     * 登陆业务处理
     * @return 返回是否成功登陆
     */

    public User Login(String username, String password){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        User u = new User();

        try {
            conn = Utils.getConnection();
            stat = conn.prepareStatement("select username,password from user where username=? and password=?");
            stat.setString(1,username);
            stat.setString(2,password);

            rs = stat.executeQuery();

            if(rs.next()){
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }

            return u ;

        } catch (SQLException e) {

           throw new RuntimeException(e);

        }finally {
            Utils.close(rs,conn,stat);
        }

    }

}