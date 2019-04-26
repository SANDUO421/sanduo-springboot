package com.redis.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author 三多
 * @Time 2019/4/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tes_reporter")
public class Reporter {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("code")
    private String code;

    @Field("description")
    private String description;
}
