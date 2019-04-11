package com.job.server.crontab;

import com.api.common.utils.ApiResponse;
import com.job.server.entity.AreaEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient 指定定时任务
 * @author 三多
 * @Time 2019/3/27
 */
@FeignClient(value = "permit-control",fallback = CrontabFallback.class)
public interface FeignCrontabClient {

    /**
     * 定时查询地区表
     * @param areaId
     * @return
     */
    @GetMapping(value = "/area/findByAreaId")
    public ApiResponse<AreaEntity> findByAreaId(@RequestParam("areaId") Long areaId);


}
