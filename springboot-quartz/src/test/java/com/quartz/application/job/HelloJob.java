package com.quartz.application.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * demo
 * @author 三多
 * @Time 2019/3/26
 */
@Slf4j
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 打印当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前系统时间："+dateFormat.format(date));
        //执行具体的业务逻辑
        System.out.println("Hello Quartz..........");
        System.out.println("开始生成任务报表 或 开始发送邮件...........");

        JobKey key = context.getJobDetail().getKey();
        System.out.println("jobDetail 的name ： "+key.getName());     //打印jobDetail 的name
        System.out.println("jobDetail 的group ： "+key.getGroup());    //打印jobDetail 的group
        JobDataMap jobDetailDataMap = context.getJobDetail().getJobDataMap();
        String message = jobDetailDataMap.getString("message"); //
        float floatJobValue = jobDetailDataMap.getFloat("FloatJobValue");
        System.out.println("jobDataMap定义的message的值 : "+message );  //打印jobDataMap定义的message的值
        System.out.println("jobDataMap定义的floatJobValue的值 : "+floatJobValue );   //jobDataMap定义的floatJobValue的值
    }
}
