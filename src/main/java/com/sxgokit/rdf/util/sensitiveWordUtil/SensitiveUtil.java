/**
 * Copyright &copy; 2016-2020 <a href="http://www.sxgokit.com">SXGOK</a> All rights reserved.
 */
package com.sxgokit.rdf.util.sensitiveWordUtil;

import com.google.common.collect.Maps;
import com.sxgokit.rdf.mapper.system.SensitiveInfoMapper;
import com.sxgokit.rdf.model.condition.system.SensitiveInfoCondition;
import com.sxgokit.rdf.model.vo.system.SensitiveInfoVo;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 敏感词处理工具类
 * @author SXGOK
 * @version 2013-5-29
 */
@Component
public class SensitiveUtil {

	@Autowired
	private SensitiveInfoMapper sensitiveInfoMapper;

	private static SensitiveInfoMapper sensitiveMapper;

	@Autowired
	private RedisUtil sysRedisUtil;

	private static RedisUtil redisUtil;

	public static final String redis_sensitive_list_key = "redis_sensitive_list_key";

	@PostConstruct
	public void init() {
		sensitiveMapper = sensitiveInfoMapper;
		redisUtil = sysRedisUtil;
	}

	/**
	 * 获取敏感词map
	 * @return
	 */
	public static Map<String, String> getSensitiveMap(){
		Map<String, String> sensitiveMap = Maps.newHashMap();
		List<SensitiveInfoVo> sensitiveArray = null;

		//redis的使用
		//如果redis中没有对应敏感词数据
		//从数据库中查询并放入redis
		//有对应敏感词数据则直接使用
		List<Object> ls = redisUtil.lGet(redis_sensitive_list_key, 0, -1);
		if(ls != null && ls.size() > 0){
			Object obj = (Object) ls.get(0);
			sensitiveArray = (List<SensitiveInfoVo>)obj;
		}else{
			sensitiveArray = sensitiveMapper.findList(new SensitiveInfoCondition());
			//拿到值以后放入redis中
			redisUtil.lSet(redis_sensitive_list_key, sensitiveArray);
		}
		for (SensitiveInfoVo sensitiveInfo : sensitiveArray){
			sensitiveMap.put(sensitiveInfo.getSensitiveContent(),sensitiveInfo.getReplaceContent());
		}
		return sensitiveMap;
	}
	
}
