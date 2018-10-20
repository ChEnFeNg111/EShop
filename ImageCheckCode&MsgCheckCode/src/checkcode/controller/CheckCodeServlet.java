package checkcode.controller;

import checkcode.utils.CheckCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 将生成的验证码图片返回响应给浏览器，显示出来
 * @author chen
 */
@WebServlet(urlPatterns = "/image")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置图片格式
        resp.setContentType("png");

        String str = CheckCode.random();

        // 把图片作为响应 字节流 resp.getOutputStream()
        CheckCode.outputImage(str,resp.getOutputStream());

    }
}
