package com.sxgokit.rdf.web.controller;


import com.sxgokit.rdf.config.file.ResponseInfo;
import com.sxgokit.rdf.model.domain.file.FileInfo;
import com.sxgokit.rdf.service.system.FileInfoService;
import com.sxgokit.rdf.util.fileUtil.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuhao
 * @date 2019/6/4 17:39
 */
@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 文件上传
     * 1. 文件上传后的文件名
     * 2. 上传后的文件路径 , 当前年月日时 如:2018060801  2018年6月8日 01时
     * 3. file po 类需要保存文件信息 ,旧名 ,大小 , 上传时间 , 是否删除 ,
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseInfo<?> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return ResponseInfo.error("文件不能为空");
        }
        try {
            return fileInfoService.upload(file);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 文件下载
     * @param fileName
     * @param res
     */
    @RequestMapping(value = "/downloadFile/{fileName}")
    @ResponseBody
    public void downloadFile(@PathVariable("fileName") String fileName, HttpServletResponse res) {
        try {
            fileInfoService.downloadFile(fileName, res);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 文件查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> view(@RequestParam("fileName") String fileName){
        FileInfo fileInfo = null;
        try {
            fileInfo = fileInfoService.getImage(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders header = new HttpHeaders();
        if (FileUtils.match(fileInfo.getFileName(), "jpg", "png", "gif", "bmp", "tif")) {
            header.setContentType(MediaType.IMAGE_JPEG);
        } else if (FileUtils.match(fileInfo.getFileName(), "html", "htm")) {
            header.setContentType(MediaType.TEXT_HTML);
        } else if (FileUtils.match(fileInfo.getFileName(), "pdf")) {
            header.setContentType(MediaType.APPLICATION_PDF);
        } else {
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        header.add("X-Filename", fileInfo.getFileName());
        header.add("X-MD5", fileInfo.getMd5());

        return new ResponseEntity<>(new InputStreamResource(fileInfo.getContent()), header, HttpStatus.OK);
    }

    /**
     * 文件列表查询
     */
    @RequestMapping(value = "/find")
    @ResponseBody
    public ResponseInfo<?> findList(@RequestParam("resourceId") String resourceId) {
        try {
            return fileInfoService.findFileList(resourceId);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 逻辑删除文件
     */
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public ResponseInfo<?> deleteFile(@RequestParam("fileName") String fileName) {
        try {
            return fileInfoService.deleteFile(fileName);
        }catch (Exception e){
            return null;
        }
    }
}
