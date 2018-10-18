package service;

import dao.UserDao;
import entity.User;

public class UserService {


    public static User findUser(String username,String password){
        UserDao u = new UserDao();
        return  u.Login(username,password);
    }
}
