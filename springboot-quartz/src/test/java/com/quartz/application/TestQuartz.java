package com.quartz.application;

import com.quartz.application.job.HelloJob;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试quartz
 *
 * @author 三多
 * @Time 2019/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuartzApp.class})
@Slf4j
public class TestQuartz {
    //@BeforeClass
    //public static void init() {
    //    System.out.println("................init.......");
    //}

    /**
     * 测试Quartz
     */
    @Test
    public void quartzTest() {
        log.debug("测试quartz.....");
        try {
            //获取任务调度器实例
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            StdSchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();
            // 开始任务
            scheduler.start();
            //执行任务调度
            executeTask(scheduler);
            //关闭
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /**
     * 执行任务：每隔40分钟执行一次
     */
    private void executeTask(Scheduler scheduler) throws SchedulerException {
        //执行任务调度
        // 定义任务调度器JobDetail并绑定HelloJob
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        // 创建Trigger实例，每隔40分钟触发调度
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }

    /**
     * 每日的9点40分触发任务打印HelloQuartz
     * 1.每日10点15分触发      0 15 10 ？* *
     * 2.每天下午的2点到2点59分（正点开始，隔5分触发）       0 0/5 14 * * ?
     * 3.从周一到周五每天的上午10点15触发      0 15 10 ? MON-FRI
     * 4.每月的第三周的星期五上午10点15触发     0 15 10 ? * 6#3
     * 5.2016到2017年每月最后一周的星期五的10点15分触发   0 15 10 ? * 6L 2016-2017
     */
    @Test
    public void cronTest() throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        //创建jobDetail
        JobDetail detail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("cornJob","group2")
                .build();
        //创建Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2","group2")
                .startNow()
                //每天9点40触发
                .withSchedule(CronScheduleBuilder.cronSchedule("0 27 15 * * ?"))
                .build();
        // 开始任务
        scheduler.start();
        //执行任务调度
        executeTask(scheduler);
        //关闭
        scheduler.shutdown();
    }

    /**
     * JobDataMap 测试
     */
    @Test
    public void jobDataMapTest() throws SchedulerException {
        //1.创建一个jobDetail的实例，将该实例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class) //定义Job类为HelloJob类，真正的执行逻辑所在
                .withIdentity("myJob", "group1") //定义name 和 group
                .usingJobData("message","hello myJob1") //加入属性到jobDataMap
                .usingJobData("FloatJobValue",8.88f) //加入属性到jobDataMap
                .build();

        //2.创建一个Trigger触发器的实例，定义该job立即执行，并且每2秒执行一次，一直执行
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        //3.创建schedule实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start(); //启动
        scheduler.scheduleJob(jobDetail,trigger); // jobDetail和trigger加入调度
        //关闭
        scheduler.shutdown();
    }

    //@AfterClass
    //public static void close() {
    //    System.out.println("...................close....................");
    //}
}
