package com.security.application.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;

/**
 * 请求返回json类
 * @author 三多
 * @Time 2019/3/22
 */
public class JsonResultUtils {

    public  static String returnJsonString(Integer status,String msg,Object result){

        JSONObject jsonObject =new JSONObject();
        jsonObject.put("status",status);
        jsonObject.put("msg",msg);
        jsonObject.put("result",result);

        return jsonObject.toJSONString();
    }
}
