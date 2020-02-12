package com.sxgokit.rdf.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxgokit.rdf.mapper.system.SysConfigDao;
import com.sxgokit.rdf.model.domain.system.SysConfigModel;
import com.sxgokit.rdf.service.system.SysConfigService;
import com.sxgokit.rdf.util.dateUtil.DateUtil;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import com.sxgokit.rdf.util.sensitiveWordUtil.SensitiveWordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.unit.DataUnit;

import java.util.List;

/**
 * @author:Dukang
 * @createDate:2018年10月30日
 * @desc:此类中完成系统启动时需加载的数据
 */
@Configuration
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * 项目启动加载系统配置表数据到redis的开关
	 */
	@Value("${rdf.loadConfigWhenStart}")
	private boolean loadConfigWhenStart = false;

	@Autowired
	private SysConfigDao sysConfigDao;

	@Autowired
	private RedisUtil redisUtil;

	Logger logger = LoggerFactory.getLogger(StartupConfig.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//启动时初始化敏感词检索库
		SensitiveWordUtil.init();
		logger.info("敏感词库加载成功,系统时间:" + DateUtil.formatNowDate());

		if(loadConfigWhenStart){
			logger.info("启动加载了系统配置" + loadSysConfig() + "到redis缓存中。");
		}
	}

	/**
	 * 加载系统配置数据表
	 */
	private int loadSysConfig(){
		List<SysConfigModel> models = sysConfigDao.selectList(new QueryWrapper<>());
		for (SysConfigModel model:models) {
			redisUtil.set(model.getConfigKey(), model.getConfigValue());
		}
		return models.size();
	}
}
