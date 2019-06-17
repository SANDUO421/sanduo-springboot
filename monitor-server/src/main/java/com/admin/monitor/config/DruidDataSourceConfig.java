package com.admin.monitor.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
* @Description:    druid的配置类
* @Author:         sanduo
* @CreateDate:     2019/6/6 13:29
* @Version:        1.0
*/
@SpringBootConfiguration
public class DruidDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.druid")
    @Bean(destroyMethod = "close", initMethod = "init")
    public DruidDataSource dataSource(Filter statFilter) {
        DruidDataSource dataSource = new DruidDataSource();
        //  添加慢日志功能Lists.newArrayList添加guava工具集
        dataSource.setProxyFilters(Lists.newArrayList(statFilter));
        return dataSource;
    }

    @Bean
    public Filter statFilter(){
        StatFilter filter = new StatFilter();
        // 设置慢sql时间
        filter.setSlowSqlMillis(3000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

   /* @Bean // 注册StatViewServlet
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }*/

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        //禁用HTML页面上的“Rest All”功能
        initParameters.put("resetEnable", "false");
        //ip白名单（没有配置或者为空，则允许所有访问）
        initParameters.put("allow", "");
        //++监控页面登录用户名
        initParameters.put("loginUsername", "admin");
        //++监控页面登录用户密码
        initParameters.put("loginPassword", "admin@lydsj");
        //ip黑名单
        initParameters.put("deny", "");
        //如果某个ip同时存在，deny优先于allow
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidWebStatViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addInitParameter("urlPatterns", "/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }

}
