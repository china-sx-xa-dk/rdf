package com.sxgokit.rdf;

import com.sxgokit.rdf.service.app.AppUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * junit测试类demo
 * 可直接通过@Autowired完成service等的注入
 * 开发时建议分包进行编写单元测试
 */
public class JunitTestDemo extends RdfApplicationTests {

    @Autowired
    private AppUserService appUserService;

    @Test
    public void junitTestDemoOK(){
        Assert.assertTrue(true);
    }

    @Test
    public void AppUser_findList(){
        Assert.assertTrue(appUserService.findList(null).size() > 0);
    }
}
