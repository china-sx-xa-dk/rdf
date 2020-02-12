package com.sxgokit.rdf.model.domain.file;

import com.alibaba.fastjson.annotation.JSONField;
import com.sxgokit.rdf.util.dateUtil.DateUtil;
import lombok.*;

import java.io.InputStream;
import java.util.Date;

@Data
public class FileInfo {

    private Integer fileId;

    //文件资源ID ，绑定业务来源的ID
    private String resourceId;

    //上传后的文件名
    private String fileName;

    //原文件名
    private String fileOriginName;

    //文件路径
    private String filePath;

    private String md5;

    //文件类型
    private String fileType;

    private Long size; //文件大小

    // 文件是否后效 true/1: 有效 ; false/0 : 无效
    private Integer valid = 1;

    // 是否删除文件
    private Integer isDelete = 0;

    //图片上传时间
    private String uploadTime = DateUtil.getDateString(new Date(),"yyyy-MM-dd HH:mm:ss");

    //图片删除时间
    private String deleteTime;

    private String createdBy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    private String updatedBy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

    private InputStream content;
}
