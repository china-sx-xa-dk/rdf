package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.mapper.system.SysDictDao;
import com.sxgokit.rdf.model.domain.system.SysDict;
import com.sxgokit.rdf.service.system.SysDictService;
import com.sxgokit.rdf.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 数据字典service接口实现
 * @author liwei
 */

@Service
public class SysDictServiceImpl implements SysDictService {

    /**
     * 数据字典持续化接口
     */
    @Autowired
    private SysDictDao sysDictDao;

    /**
     * 分页查询数据字典列表
     */
    @Override
    public List findPageList(Page page, SysDict sysDict){
        return sysDictDao.findPageList(page,sysDict);
    }

    @Override
    public List<SysDict> findAll(){
        return sysDictDao.findAllList(new SysDict());
    }

    /**
     * 根据主键id删除数据字典
     */
    @Override
    public void deleteById(Integer id){
        sysDictDao.deleteByDictId(id);
    }

    /**
     * 根据主键id查找数据字典
     */
    @Override
    public SysDict selectById(Integer id)
    {
        return sysDictDao.selectById(id);
    }

    /**
     * 查询数据字典中的数据类型（去重）
     */
    @Override
    public List selectType(){
        return sysDictDao.selectType();
    }

    /**
     * 新增数据字典
     * @Param sysDict
     */
    @Override
    public  void insertSysDict(SysDict sysDict)
    {
        sysDictDao.insertSysDict(sysDict);
    }


    /**
     * 编辑数据字典
     * @param sysDict
     */
    @Override
    public void updateSysDict(SysDict sysDict)
    {
        sysDictDao.updateSysDict(sysDict);
    }

}
