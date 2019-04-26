package com.redis.application.controller;

import com.api.common.utils.ApiResponse;
import com.mongodb.client.result.UpdateResult;
import com.redis.application.dao.AddressRepository;
import com.redis.application.entity.Address;
import com.redis.application.entity.Reporter;
import com.redis.application.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * MongoDB 的控制器
 * @author 三多
 * @Time 2019/4/25
 */
@Api(description = "MongoDB 的控制器")
@RestController
@EnableCaching
@RequestMapping("/mongoDb")
public class MongoDbController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 仓储
     */
    @Autowired
    private AddressRepository repository;

    /**
     * 用户业务.
      */
    @Autowired(required = false)
    private UserService userService;

    /**
     * 保存列表.
     *
     * @return
     */
    @ApiOperation(value = "保存地址")
    @PostMapping("/save")
    public Address save(@RequestBody Address address) {

        return userService.save(address);
    }

    /**
     * 得到地址列表.
     *
     * @return
     */
    @ApiOperation(value = "根据province获取信息")
    @GetMapping("/address/{province}")
    public Address getAddress(@PathVariable("province") String province) {
        System.out.println("1,province=" + province);
        return userService.getAddress(province);
    }

    /**
     * 添加
     * @param reporter
     * @return
     */
    @ApiOperation(value = "添加报告者")
    @PutMapping("/add")
    public ApiResponse add(@RequestBody Reporter reporter){
        mongoTemplate.insert(reporter);
        return ApiResponse.ofSuccess(true);

    }

    /**
     * 查询 分页
     * @param name
     * @param code
     * @param offset
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页查询报告者")
    @GetMapping("/select")
    public List<Reporter> queryReporter(String name, String code, Integer offset, Integer limit) {

        Query query = new Query();
        if(StringUtils.isNotEmpty(name)){
            query.addCriteria(Criteria.where("name").is(name));
        }
        if (StringUtils.isNotEmpty(code)) {
            query.addCriteria(Criteria.where("code").is(code));
        }
        query.with(Sort.by(Sort.Direction.DESC, "creatTime"));
        int skip = (offset - 1) * limit;
        // 从那条记录开始
        query.skip(skip);
        // 取多少条记录
        query.limit(limit);
        List<Reporter> list = mongoTemplate.find(query,Reporter.class);
        return list;
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询报告者")
    @GetMapping("/selectById")
    public Reporter queryReporterById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Reporter reporter = mongoTemplate.findOne(query,Reporter.class);
        return reporter;
    }


    /**
     * 修改
     * @param reporter
     * @return
     */
    @ApiOperation(value = "修改报告者")
    @PutMapping("/update")
    public UpdateResult updateReporter(Reporter reporter) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(reporter.getId()));
        Update update = new Update();
        if(StringUtils.isNotEmpty(reporter.getName())) {
            update.set("name",reporter.getName());
        }
        if(StringUtils.isNotEmpty(reporter.getCode())){
            update.set("code",reporter.getCode());
        }
        if(StringUtils.isNotEmpty(reporter.getDescription())){
            update.set("description",reporter.getDescription());
        }
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Reporter.class);
        return updateResult;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除报告者")
    @DeleteMapping("/delete")
    public int delReporter(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Reporter.class);
        return 1;
    }

    public  <T> List<T> get(Class<T> t,String collectionName) throws Exception {
        Query query=new Query();
        Criteria criteria=new Criteria();
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if(field.getType().equals(String.class) && !"id".equals(fieldName) && !"serialVersionUID".equals(fieldName)){
                String property = BeanUtils.getProperty(t, field.getName());
                if(StringUtils.isNoneBlank(property)){
                    criteria.and(fieldName).is(property);
                }
            }
        }
        query.addCriteria(criteria);

        //使用模板对象执行查询
        return mongoTemplate.find(query, t , collectionName);
    }

}
