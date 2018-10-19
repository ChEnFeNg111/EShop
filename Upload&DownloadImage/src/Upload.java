import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 上传图片
 *
 *  *      1: 导入 commons-fileupload-1.3.3.jar  和  commons-io-2.2.jar  两个jar包
 *  *
 *  *      2: 获取到一个  将上传文件存入服务器磁盘的  工具类
 *  *              DiskFileItemFactory factory = new DiskFileItemFactory();
 *  *
 *  *         再获取到一个 核心文件上传 的工具类
 *  *              ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
 *  *
 *  *      3: 获取表单提交的多部分数据
 *  *              List<FileItem> fileItems = servletFileUpload.parseRequest(req);
 *  *
 *  *      4: 遍历集合,得到各部分数据
 *  *
 * @author chen
 */

@WebServlet(urlPatterns = "/upload")
public class Upload extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 上传文件存入磁盘的 工具类
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 再获取一个 核心文件上传  的工具类
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        try {
            // 获取表单提交的 多部分数据，提交方式必须是 ：post  enctype= "multipart/form-data"
            List<FileItem> items = servletFileUpload.parseRequest(req);

            // 遍历表单数据
            for (FileItem item : items) {
                // 如果是 普通的表单项
                if(item.isFormField()){
                    System.out.println("获取参数名:"+item.getFieldName());

                    // utf-8 : 防止乱码
                    System.out.println("获取参数值:"+item.getString("utf-8"));
                }else{
                    // 如果不是普通表单项
                    System.out.println("获取文件的大小："+item.getSize());
                    if(item.getSize()>0){
                        // 将上传的图片写入本地磁盘 G:\\img 下
                        item.write(new File("G:\\img\\"+item.getName()));
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
