package filter;


import entity.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            // 1、转换两个对象HttpServletRequest，HttpServletResponse
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            String uri = req.getRequestURI();

            String path = req.getContextPath();


            path = uri.substring(path.length());


            //如果请求的资源不是login.jsp，也不是/servlet/loginServlet，才往下执行
            if (!("/login.jsp".equals(path) || "/login".equals(path))) {
                User user = (User) req.getSession().getAttribute("user");



                //如果session得到了user对象，说明已经登录过或自动登录过。
                //那么请求下一个资源时就不用执行自动登录了。
                //用户没有登录过，我们才执行自动登录
                if (user == null) {

                    // 2、处理业务
                    // 得到cookies数组
                    Cookie[] cookies = req.getCookies();
                    String username = "";
                    String password = "";
                    // 从数组中找到想要的user对象的信息
                    for (int i = 0; cookies != null && i < cookies.length; i++) {
                        if ("user".equals(cookies[i].getName())) {
                            // tom&123
                            String value = cookies[i].getValue();
                            String[] values = value.split("&");
                            username = values[0];
                            password = values[1];
                        }
                    }

                    // 登录操作
                    User u = UserService .findUser(username, password);
                    // 如果登录成功，把用户信息存到session中
                    if (u != null) {

                        System.out.println(u.getUsername()+"=="+u.getPassword());

                        req.getSession().setAttribute("user", u);
                    }
                }
            }
            // 3、放行
            filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
