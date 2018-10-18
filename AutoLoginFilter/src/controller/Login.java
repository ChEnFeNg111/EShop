package controller;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user =UserService.findUser(username,password);

        System.out.println(user.getUsername()+"===="+user.getPassword());

        // 验证成功
        if(user!=null){

            String autologin = req.getParameter("autologin");
            Cookie cookie = new Cookie("user", user.getUsername()+"&"+user.getPassword());
            cookie.setPath("/");

            System.out.println("cookie: "+cookie.getValue());
            System.out.println("autologin: "+autologin);

            //要把用户信息保存到cookie中
            if(autologin!=null){
                cookie.setMaxAge(60*60*24*7);
            }else{//要清除cookie对象的数据
                cookie.setMaxAge(0);
            }

            //把cookie对象保存到客户端
            resp.addCookie(cookie);

            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }else{
            req.setAttribute("msg", "用户名或密码错误,请重新登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
