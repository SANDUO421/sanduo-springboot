package com.auth2.permit.client;

import com.api.common.utils.ApiResponse;
import com.auth2.permit.model.RoleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("permit-control")
public interface RoleClientService {
    @GetMapping(value = "/role/{userId}")
    ApiResponse<List<RoleEntity>> getRoleByUserId(@PathVariable("userId")Long userId);
}
