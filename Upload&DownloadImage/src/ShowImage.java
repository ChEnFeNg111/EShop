
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 在浏览器上显示本地磁盘下，images文件夹下的任意图片
 *      http://localhost:8080/images/7227f4a1040c3478.jpg
 * @author chen
 */

@WebServlet(urlPatterns = "/images/*")
public class ShowImage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将 响应的 content—type 设置为 image， 浏览器就会显示图片
        resp.setContentType("image/jpg");

        // 获取到浏览器的访问路径，再将【浏览器的访问路径】 ==》【本地磁盘下的图片路径】
        String uri = req.getRequestURI();
        String path = "G:"+uri;

        //判断图片是否存在于 G:/images 文件夹下
        if(!new File(path).exists()){
            resp.sendError(404);
            return;
        }

        // 若果存在，则用 响应流 的方式将图片显示到浏览器上

        // 获取响应输出流
        OutputStream os = resp.getOutputStream();

        // 将图片用流的方式读入输入流
        FileInputStream is = new FileInputStream(path);

        // 利用上传工具类将输入流转换为输出流 返回响应
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);


    }
}
