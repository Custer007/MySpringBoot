package com.mr.aop;

import com.mr.interfaces.TrackPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitorAop {

    @Around("execution(* com.mr.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        // 1. 获取当前执行方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 2. 获取方法上的自定义注解
        TrackPoint monitor = method.getAnnotation(TrackPoint.class);

        // 判断是否标注了该注解
        if (monitor != null) {
            String pointCode = monitor.value();
            String desc = monitor.desc();
            boolean needPrint = monitor.printParam();
            System.out.println("埋点标识：" + pointCode + "，描述：" + desc);
        }

        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            // 异常埋点逻辑
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - start;
            System.out.println("接口耗时：" + cost + "ms");
        }
        return result;
    }
}