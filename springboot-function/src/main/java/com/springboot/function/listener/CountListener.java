package com.springboot.function.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器主要是对对象自身的创建和销毁进行监听
 * 主要有ServletContextListener、HttpSessionListener、ServletRequestListener，用法基本一致
 * 下面以HttpSessionListener为例，用来监听 统计当前访问人数。
 *
 * @author 三多
 * @Time 2019/7/17
 */
public class CountListener implements HttpSessionListener {

    private volatile int count = 0;

    /**
     * 创建
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count++;
        se.getSession().getServletContext().setAttribute("count", count);
        System.out.println("新增在线人数，当前在线人数："+count);

    }

    /**
     * 销毁
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
        se.getSession().getServletContext().setAttribute("count", count);
        System.out.println("删减在线人数，当前在线人数："+count);

    }
}
