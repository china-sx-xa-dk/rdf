/**
 * Copyright &copy; 2016-2020 <a href="http://www.sxgokit.com">SXGOK</a> All rights reserved.
 */
package com.sxgokit.rdf.util;

import java.security.KeyStore;
import java.util.List;
import java.util.Map;

import com.sxgokit.rdf.mapper.system.SysDictDao;
import com.sxgokit.rdf.model.domain.system.SysDict;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 字典工具类
 * @author SXGOK
 * @version 2013-5-29
 */
@Component
public class DictUtils {

	@Autowired
	private SysDictDao sysDictDao;

	private static SysDictDao dictDao;

	@Autowired
	private RedisUtil sysRedisUtil;

	private static RedisUtil redisUtil;

	public static final String redis_dict_list_key = "rdf-dict-list-key";

	@PostConstruct
	public void init() {
		dictDao = sysDictDao;
		redisUtil = sysRedisUtil;
	}

	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (SysDict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (SysDict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<SysDict> getDictList(String type){
		Map<String, List<SysDict>> dictMap = Maps.newHashMap();
		List<SysDict> dictArray = null;

		//redis的使用
		//如果redis中没有对应字典类
		//从数据库中查询并放入redis
		//有对应数据字典数据则直接使用
		List<Object> ls = redisUtil.lGet(redis_dict_list_key, 0, -1);
		if(ls != null && ls.size() > 0){
			Object obj = (Object) ls.get(0);
			dictArray = (List<SysDict>)obj;
		}else{
			dictArray = dictDao.findAllList(new SysDict());
			//拿到值以后放入redis中
			redisUtil.lSet(redis_dict_list_key, dictArray);
		}
		for (SysDict dict : dictArray){
			List<SysDict> dictList = dictMap.get(dict.getType());
			if (dictList != null){
				dictList.add(dict);
			}else{
				dictMap.put(dict.getType(), Lists.newArrayList(dict));
			}
		}
		List<SysDict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
}
