package com.sxgokit.rdf.config.component;

import com.google.gson.Gson;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import com.sxgokit.rdf.web.interceptor.AppAuthInterceptor;
import com.sxgokit.rdf.model.domain.app.AppUserModel;
import com.sxgokit.rdf.util.Digest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Author: token容器
 * Created: 2018/3/19 0019
 */
@Component
@Slf4j
public class TokenComponent {

    @Autowired
    private Gson gson;
    //暂未使用,如需对时间有要求请自行完成时间判断
    @Value("${app.token-alive-time}")
    private int tokenAliveTime = 7200;
    @Autowired
    private RedisUtil redisUtil;

    public String createToken(AppUserModel model) {
        String token = Digest.digest(model.getId() + RandomStringUtils.randomAscii(10));
        redisUtil.set(token, model, tokenAliveTime);
        return token;
    }

    public boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        return redisUtil.get(token) != null;
    }

    /**
     * 更新数据库中对应token的时间戳
     */
    public void keepaliveToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        redisUtil.set(token, redisUtil.get(token) , tokenAliveTime);
//        log.debug("token alive time is : {}M", tokenAliveTime);
    }

    /**
     * 获取当前用户
     */
    public AppUserModel getCurrentAppUser(HttpServletRequest request) {
        AppUserModel model = this.getInfoByToken(request);
        if (model != null) {
            return model;
        }
        return null;
    }

    /**
     * 通过token获取登陆信息
     */
    private AppUserModel getInfoByToken(HttpServletRequest request) {
        String authToken = request.getHeader(AppAuthInterceptor.HEAD_AUTH_TOKEN);
        AppUserModel model = (AppUserModel)redisUtil.get(authToken);
        if (model == null || StringUtils.isEmpty(authToken)) {
            return null;
        }
        return model;
    }

    /**
     * 验证用户
     */
    public boolean validAppUser(HttpServletRequest request, String appUserId) {
        AppUserModel model = this.getCurrentAppUser(request);
        boolean effect = false;
        if (model != null) {
            effect = model.getId().equals(appUserId);
        }
        return effect;
    }

    public void clearToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        redisUtil.del(token);
    }

    /**
     * 当用户信息发生改变后,更新缓存
     */
    public void updateToken(String token, AppUserModel model) {
        redisUtil.set(token, model, tokenAliveTime);
    }



    //用于用户首次登陆返回
    @Data
    public static class Token {

        private final String authToken;

        private final String appUser;

    }
}
