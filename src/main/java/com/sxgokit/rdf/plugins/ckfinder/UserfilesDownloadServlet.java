package com.sxgokit.rdf.plugins.ckfinder;

import com.ckfinder.connector.ServletContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 查看CK上传的图片
 * @author ThinkGem
 * @version 2014-06-25
 */
public class UserfilesDownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String USERFILES_BASE_URL = "/static/common/userfiles/";
    public void fileOutputStream(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String filepath = req.getRequestURI();
        int index = filepath.indexOf(USERFILES_BASE_URL);

        if(index >= 0) {
            filepath = filepath.substring(index + USERFILES_BASE_URL.length());
        }
        try {
            filepath = UriUtils.decode(filepath, "UTF-8");
        } catch (Exception e1) {
            logger.error(String.format("解释文件路径失败，URL地址为%s", filepath), e1);
        }
//        File file = new File(getUserfilesBaseDir() + USERFILES_BASE_URL + filepath);
        File file = new File(getUserfilesBaseDir() + USERFILES_BASE_URL + filepath);
        try {
            FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            resp.setHeader("Content-Type", "application/octet-stream");
            return;
        } catch (FileNotFoundException e) {
            req.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
            req.getRequestDispatcher("/WEB-INF/jsp/page_404.jsp").forward(req, resp);
        }
    }

    private String getUserfilesBaseDir() {
        String path = "";
        try {
            path = ServletContextFactory.getServletContext().getRealPath("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        fileOutputStream(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        fileOutputStream(req, resp);
    }
}