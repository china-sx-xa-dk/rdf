package com.sxgokit.rdf.util.imgUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

/**
 * 图片加水印工具类
 */
public class WaterMarkUtils
{
    /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     */
    public static void addWaterMark(String srcImgPath, String tarImgPath,String waterMarkContent)
    {
        try
        {
            Color color = new Color(0,0,0); //水印图片色彩以及透明度
            Font font = new Font("微软雅黑", Font.PLAIN, 16); //水印字体
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight,
                BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(color); //根据图片的背景设置水印颜色
            g.setFont(font); //设置字体

            //设置水印的坐标
            int x = 40;
            int strLength = waterMarkContent.length();
            if(strLength!=20){
                x = 40+8*(20-strLength);
            }
            int y = 390;
            g.drawString(waterMarkContent, x, y); //画出水印
            g.dispose();
            // 输出图片  
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            //System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g)
    {
        return g.getFontMetrics(g.getFont()).charsWidth(
            waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void main(String[] args)
    {
        String srcImgPath = "D:/1.jpg"; //源图片地址
        String tarImgPath = "D:/1.jpg.jpg"; //待存储的地址
        String waterMarkContent = "测试商品名测试商品名测试商品名测试商品名"; //水印内容
        WaterMarkUtils.addWaterMark(srcImgPath, tarImgPath,waterMarkContent);

    }
}
