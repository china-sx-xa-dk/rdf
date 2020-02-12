package com.sxgokit.rdf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Author: Dukang
 * @Date: 2019/6/5/0005 11:32
 * @Description: 通过配置一次运行所有测试类
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JunitTestDemo.class})
//@Suite.SuiteClasses({EhCacheTest.class,EhCacheTest1.class})
public class TestSuits {

    //不用写代码，只需要注解加好需要一次运行的测试类
}
