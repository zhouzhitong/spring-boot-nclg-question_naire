package com.nclg.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 13:01
 **/
@Configuration
public class DruidConfig {

    /**
     * 1、绑定application.yml配置文件的连接数据
     * 作用：将application.yml的相关属性进行绑定
     *
     * @return Druid 数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druid() {
        return new DruidDataSource();
    }


    /**
     * 2、配置Druid的监控
     * 1、配置一个管理后台的Servlet
     * //黑名单
     * //initParams.put("deny","localhost");
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>(5);
        //用户名
        initParams.put("loginUsername", "admin");
        //密码
        initParams.put("loginPassword", "123456");
        //白名单
        //默认就是允许所有访问
        initParams.put("allow", "");
        //黑名单

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 功能描述: <br>2、配置一个web监控的filter
     * <>
     *
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @author 周志通
     * @date 2020/5/29
     **/
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        // 所有请求进行监控处理
        bean.setUrlPatterns(Collections.singletonList("/*"));
        Map<String, String> initParams = new HashMap<>(2);
        // 排除名单
        initParams.put("exclusions", "*.js,*.css,*.jpg,/druid/*");

        bean.setInitParameters(initParams);
        return bean;
    }


}
