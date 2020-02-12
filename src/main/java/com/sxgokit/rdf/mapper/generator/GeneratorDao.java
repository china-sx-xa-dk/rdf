package com.sxgokit.rdf.mapper.generator;

import com.sxgokit.rdf.common.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * GeneratorDao
 * @author dolyw.com
 * @date 2019/4/5 17:51
 */
public interface GeneratorDao {

    /**
     * 查询数据库所有表
     * @param map
     * @throws
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author dolyw.com
     * @date 2019/4/5 17:51
     */
    List<Map<String, Object>> findPageList(Page page, @Param("tableName")String tableName);

    /**
     * 查询表信息
     * @param tableName
     * @throws
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author dolyw.com
     * @date 2019/4/5 17:52
     */
    Map<String, String> queryTable(@Param("tableName")String tableName);

    /**
     * 查询列信息
     * @param tableName
     * @throws
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @author dolyw.com
     * @date 2019/4/5 17:52
     */
    List<Map<String, String>> queryColumns(@Param("tableName")String tableName);
}
