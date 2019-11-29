package com.auth2.permit.client;
import com.api.common.utils.ApiResponse;
import com.auth2.permit.model.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "permit-control")
public interface UserClientService {
    @GetMapping(value = "user/getOne")
    ApiResponse<UserEntity>  getUser(@RequestParam("username") String username);
}
