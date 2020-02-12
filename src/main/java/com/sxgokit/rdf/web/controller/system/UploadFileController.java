package com.sxgokit.rdf.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.sxgokit.rdf.util.common.StringUtils;
import com.sxgokit.rdf.util.fileUtil.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UploadFileController {

    /**
     * 上传单个文件到服务器
     *
     * @param request
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public String uploadFile(HttpServletRequest request, @Param("file") MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        Map<String, Object> map = new HashMap<String, Object>();
        String result = "";
        try {
            String res = sdf.format(new Date());
            //服务器上使用
            String rootPath = request.getSession().getServletContext().getRealPath(
                    "/");
            rootPath = rootPath + "WEB-INF/classes/";

            Calendar date = Calendar.getInstance();
            String realFilePath =
                    "static/common/uploadFile/" + date.get(Calendar.YEAR)
                            + File.separator + (date.get(Calendar.MONTH) + 1);
            //原始名称
            String originalFilename = file.getOriginalFilename();
            //新的文件名称
            String newFileName = res + originalFilename.substring(
                    originalFilename.lastIndexOf("."));

            //创建年月文件夹
            File dateDirs = new File(realFilePath);
            //新文件
            File newFile = new File(rootPath + "/" + dateDirs + "/" + newFileName);
            //判断目标文件所在的目录是否存在
            if (!newFile.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }
            //将内存中的数据写入磁盘
            file.transferTo(newFile);
            //完整的url
            String projectUrl = request.getRequestURL().toString();
            projectUrl = projectUrl.substring(0,
                    projectUrl.indexOf("/uploadFile"));
            String fileUrl = projectUrl + "/" + realFilePath + "/" + newFileName;
            Map<String, Object> map2 = new HashMap<String, Object>();
            map.put("code", 0);//0表示成功，1失败
            map.put("msg", "上传文件成功！");//提示消息
            map.put("data", map2);
            map2.put("src", fileUrl);//图片url
            map2.put("title", newFileName);//图片名称，这个会显示在输入框里
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "上传文件失败！");//提示消息
        } finally {
            result = new JSONObject(map).toString();
        }
        return result;
    }

    /**
     * 删除服务器单个文件
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteFile")
    public String deleteFile(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String result = "";
        String path = request.getParameter("path");
        try {
            if (StringUtils.isNotBlank(path)) {
                //获取文件在服务器上路径
                String rootPath = request.getSession().getServletContext().getRealPath(
                        "/");
                rootPath = rootPath + "WEB-INF/classes/static/common/";
                String filePath = path.substring(path.indexOf("/uploadFile/") + 1, path.length());
                filePath = rootPath + filePath;
                filePath = FileUtils.path(filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                    map.put("code", 0);//0表示成功，1失败
                    map.put("msg", "删除文件成功！");//提示消息
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "删除文件失败！");//提示消息
        } finally {
            result = new JSONObject(map).toString();
        }
        return result;
    }

    /**
     * 批量删除服务器文件
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAllFile")
    public String deleteAllFile(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String result = "";
        String pathArrayStr = request.getParameter("pathArrayStr");
        try {
            if (StringUtils.isNotBlank(pathArrayStr)) {
                String[] pathArray = pathArrayStr.split(",");
                //获取文件在服务器上路径
                String rootPath = request.getSession().getServletContext().getRealPath(
                        "/");
                rootPath = rootPath + "WEB-INF/classes/static/common/";
                for (String path : pathArray) {
                    String filePath = path.substring(path.indexOf("/uploadFile/") + 1, path.length());
                    filePath = rootPath + filePath;
                    filePath = FileUtils.path(filePath);
                    File file = new File(filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                map.put("code", 0);//0表示成功，1失败
                map.put("msg", "批量删除文件成功！");//提示消息
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);//0表示成功，1失败
            map.put("msg", "批量删除文件失败！");//提示消息
        } finally {
            result = new JSONObject(map).toString();
        }
        return result;
    }
}
