# springCloud 基本架构操作

## 参考
1. [Springboot和SpringCloud 整合全集](https://github.com/heibaiying/spring-samples-for-all)

## 开发规范
1. [敏捷/DevOps实践规范参考](https://choerodon.io/zh/docs/practice-specification-reference/)
2. [数据库设计规范](https://choerodon.io/zh/docs/practice-specification-reference/database-design-specification/)
3. [开发规范](https://choerodon.io/zh/docs/practice-specification-reference/development/)

## Api 文档
1. [Spring For All 社区 Spring 官方教程翻译](http://www.spring4all.com/article/558)
2. [官方文档](https://spring.io/guides)
## 微服务架构
1. [微服务落地，我们在考虑什么？](https://www.infoq.cn/article/D-cBEauI4oTVqwUsx5Fk)
2. [主流分布式架构的风流韵事...](https://www.cnblogs.com/hafiz/p/9236664.html)
3. [微服务六大设计原则](https://www.jianshu.com/p/4e582616d565)
4. [微服务的4个设计原则和19个解决方案](https://www.cnblogs.com/HigginCui/p/10460807.html)
5. [微服务开发规范](https://choerodon.io/zh/docs/practice-specification-reference/development/developmen-to-micro-services/)
6. [领域驱动设计：服务边界划分](https://qinnnyul.github.io/2018/08/13/ddd-concept/#more)
## stream 参考
1. [IBM Stream的参考](https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/)

## 数据库
1. [数据库索引分类](https://www.cnblogs.com/zsc1/p/9230096.html)
2. [Mysql SSL问题](https://blog.csdn.net/oopsangle/article/details/59529969)
3. [MySql 关闭ssl](https://blog.csdn.net/weixin_34273481/article/details/87240943)
##配置文件动态刷新
1. [springCloud config refresh刷新actuator踩坑](https://blog.csdn.net/sdrfengmi/article/details/86622556)

## 文件上传
1. [springboot附件上传超限](https://blog.csdn.net/qq_25446311/article/details/78600354)
```
1.项目使用的是Spring Boot + Spring Cloud，上传附件报超出自带tomacat限制大小（默认1M）

"Maximum upload size exceeded; nested exception is java.lang.IllegalStateException: org.apache.tomcat.util.http.fileupload.FileUploadBase$FileSizeLimitExceededException: 
The field file exceeds its maximum permitted size of 1048576 bytes
2.解决方案 
（1）在配置文件（application.properties）加入如下代码
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 100MB

maxFileSize 单个数据大小 
maxRequestSize 是总数据大小

（2）把如下代码放在启动类上，并在类上加入@Configuration

    /**
     * 文件上传配置
     * 
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("10MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
PS：若是有网关之类的中转，需在网关中也加入如上配置。
```
## mybatisPlus
1. [springboot + mybatis plus强大的条件构造器queryWrapper、updateWrapper](https://blog.csdn.net/m0_37034294/article/details/82917234)
2. [官方文档](https://mp.baomidou.com/guide/wrapper.html#abstractwrapper)

## java 专题
1. [Java 信任所有SSL证书(解决PKIX path building failed问题)](https://blog.csdn.net/zziamalei/article/details/46520797)