package com.sxgokit.rdf.util.imgUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * 根据图片绝对路径获取图片的大小、宽高、横图或竖图
 */
public class ImgInfoUtil
{

    /**
     * 
     * Description: 本地获取图片的尺寸<br>
     * Time：2018年5月31日 下午3:10:27<br>
     * @author wgl
     * @param path
     * @return imgSize 图片大小
     * @return imgWidth 图片宽度
     * @return imgHeight 图片高度
     * @return imgType 图片类型(0方图、1横图、2竖图)
     */
    public static Map<String, Object> getImgInfo(String path)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        File picture = new File(path);
        BufferedImage sourceImg;
        try
        {
            sourceImg = ImageIO.read(new FileInputStream(picture));
            // 源图宽度
            int imgWidth = sourceImg.getWidth();
            // 源图高度
            int imgHeight = sourceImg.getHeight();
            // 源图大小
            map.put("imgSize", String.format("%.1f", picture.length() / 1024.0));
            map.put("imgWidth", imgWidth);
            map.put("imgHeight", imgHeight);
            if (imgWidth > imgHeight)
            {
                map.put("imgType", 1);
            }
            else if (imgHeight > imgWidth)
            {
                map.put("imgType", 2);
            }else{
                map.put("imgType", 0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args)
    {
        String path = "/uploadfile/otherfile/file_1527748439602.jpg";
        Map<String, Object> map = getImgInfo(path);
        System.out.println(map.get("size"));
        System.out.println(map.get("width"));
        System.out.println(map.get("height"));
    }

}
