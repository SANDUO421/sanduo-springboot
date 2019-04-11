package com.job.server.crontab;

import com.api.common.utils.ApiResponse;
import com.job.server.entity.AreaEntity;
import org.springframework.stereotype.Component;

/**
 * @author 三多
 * @Time 2019/3/27
 */
@Component
public class CrontabFallback implements FeignCrontabClient{
    @Override
    public ApiResponse<AreaEntity> findByAreaId(Long areaId) {
        return ApiResponse.ofMessage(5404,"地区查询失败");
    }
}
