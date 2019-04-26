package com.redis.application.entity;

import com.redis.application.utils.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author 三多
 * @Time 2019/4/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    /**
     * 编号.
     */
    @Id
    private String id;
    /**
     * 省.
     */
    private String province;
    /**
     * 市.
     */
    private String city;
    /**
     * 区.
     */
    private String district;
    /**
     * 状态.
     */
    private ApiResponse.Status status;

    public enum Status {
        SUCCESS(200, "OK"),
        ERROR(-1, "操作失败！");

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }
        private int code;
        private String standardMessage;

        public int getCode() {
            return code;
        }
        public String getStandardMessage() {
            return standardMessage;
        }
    }
}
