package com.sxgokit.rdf.service.system;


import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.config.file.BusinessException;
import com.sxgokit.rdf.config.file.ResponseInfo;
import com.sxgokit.rdf.model.domain.file.FileInfo;
import com.sxgokit.rdf.model.domain.system.SysDict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件service接口
 * @author wuhao
 */

public interface FileInfoService {

    ResponseInfo<?> upload(MultipartFile file) throws BusinessException;

    void downloadFile(String fileName, HttpServletResponse res) throws BusinessException;

    FileInfo getImage(String fileName) throws Exception;

    ResponseInfo<?> findFileList(String resourceId) throws BusinessException;

    ResponseInfo<?> deleteFile(String fileName) throws BusinessException;

}