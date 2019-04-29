package com.springboot.function.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 权限AOP
 * @Aspect 定义切面
 * @author 三多
 * @Time 2019/4/28
 */
@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private HttpServletRequest request;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.springboot.function.annotation.DataFilter)")
    public void  dataFilterCut() {
    }

    /**
     * 前置通知
     * @param point
     */
    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point){
        try {
            Signature signature = point.getSignature();
            System.out.println("getName:"+signature.getName());
            System.out.println("getModifiers:"+signature.getModifiers());
            System.out.println("getDeclaringTypeName:"+signature.getDeclaringTypeName());
            System.out.println("getDeclaringType:"+signature.getDeclaringType());
            Object[] args = point.getArgs();
            if (args != null) {
                for (Object arg : args) {
                    System.out.println("arg"+ arg);
                }
            }
            String kind = point.getKind();
            System.out.println("kind:"+kind);
            Object target = point.getTarget();
            System.out.println(""+target);
            JoinPoint.StaticPart staticPart = point.getStaticPart();

            //获取用户名和密码
            Object params = point.getArgs()[0];
            if (params != null && params instanceof Map){
                StringBuffer requestURL = request.getRequestURL();
                System.out.println("requestURL:"+requestURL);
                Map map = (Map)params;
                map.put("username","sanduo");
                map.put("password","sanduo@123");
            }
            //*========控制台输出=========*//
            System.out.println("=====前置通知开始=====");
            System.out.println("###请求方法:" + (point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()"));
            System.out.println("=====前置通知结束=====");
        }catch(Exception e){
            //记录本地异常日志
            e.printStackTrace();
        }

    }

    @After("dataFilterCut()")
    public void doAfterTask(JoinPoint joinPoint){
        System.out.println("拦截成功");
        System.out.println("=====后置通知=====");
        System.out.println("###请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
        System.out.println("=====后置通知end=====");
    }

}
