package com.sxgokit.rdf.mapper.file;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.model.domain.file.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dreamhai
 * @desc
 * @date 2018-06-08 12:33
 */
@Repository
public interface FileInfoDao extends BaseMapper<FileInfo> {

    FileInfo findByFileName(String fileName);

    List<FileInfo> findByValid(int valid);

    List<FileInfo> findByResourceId(String resourceId);

    void deleteAll(List<FileInfo> fileInfos);

    void insertFile(FileInfo fileInfo);
}
