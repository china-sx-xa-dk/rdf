package com.sxgokit.rdf.config.generator;

import com.sxgokit.rdf.mapper.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Dao配置注入
 * @author dolyw.com
 * @date 2019/4/5 17:56
 */
@Configuration
public class DataBaseConfig {

    @Autowired
    private MySQLGeneratorDao mySqlGeneratorDao;

    /**
     * 根据驱动判断注入那个类型数据库
     * @param
     * @throws
     * @return com.example.dao.GeneratorDao
     * @author dolyw.com
     * @date 2019/4/5 17:59
     */
    @Bean
    @Primary
    public GeneratorDao getGeneratorDao() {
        return mySqlGeneratorDao;
    }
}
