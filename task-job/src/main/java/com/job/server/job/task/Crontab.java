package com.job.server.job.task;

import com.api.common.utils.ApiResponse;
import com.job.server.crontab.FeignCrontabClient;
import com.job.server.entity.AreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定时任务
 * @author 三多
 * @Time 2019/3/27
 */
@Component
public class Crontab {

    @Autowired(required = false)
    private FeignCrontabClient feignCrontabClient;

    public void addUser(){
        ApiResponse<AreaEntity> areaId = feignCrontabClient.findByAreaId(610000L);
        if (areaId != null) {
            System.out.println("测试feign框架调用任务..." + areaId.getData().getRegionName() + ":" + areaId.getData().getCityCode());
        }
    }

}
