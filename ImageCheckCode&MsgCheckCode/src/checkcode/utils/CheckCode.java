package checkcode.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 *  生成验证码
 */
public class CheckCode {

    private static String[] arr = {"a","b","c","d","e","f","g","0","1","2","3","4","5","6","7","8","9"};

    /**
     * 生成 4 位随机数
     */

    public static String random(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(4);

        // 在该数组中随机选出 4 位验证码
        for (int i = 0; i < 4; i++) {
            int id = random.nextInt(arr.length);

            sb.append(arr[id]);
        }

        return sb.toString();
    }

    /**
     *生成包含验证码的图片
     */
    public static void outputImage(String str , OutputStream os){
        // 获取图片对象
        BufferedImage image = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);

        // 获取画布
        Graphics g = image.getGraphics();

        //设置背景色
        g.setColor(Color.white);
        //填充背景色
        g.fillRect(0,0,100,50);

        //设置字体颜色
        g.setColor(Color.BLACK);
        //设置字体样式
        g.setFont(new Font("宋体",Font.BOLD,25));

        //将 文字  写入画布中
        g.drawString(str,20,40);

        //加上干扰线
        g.drawLine(0,0,100,50);
        g.drawLine(100,0,0,50);
        g.drawLine(50,0,50,100);
        g.drawLine(0,25,100,25);

        try {
            // 将 生成的 图片 返回
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
