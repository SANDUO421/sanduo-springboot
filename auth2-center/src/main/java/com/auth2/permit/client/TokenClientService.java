package com.auth2.permit.client;

import com.api.common.utils.ApiResponse;
import com.auth2.permit.model.OauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "permit-control")
public interface TokenClientService {
    @GetMapping("/oauthClient/getAllClient")
    ApiResponse<List<OauthClientDetails>>  getAllClient();
}
