package com.example.lovestory.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TimeCostAop {
    /**
     * 切点，表示要进行增强的哪些类和方法
     */
    @Pointcut("execution(* com.example.lovestory.service.impl.UserServiceImpl.*(..))")
    public void timeCostAop() {
    }

    @Around("timeCostAop()")
    public void aroundTest(ProceedingJoinPoint proceedingJoinPoint) {


        try {
            System.out.println("进入aop记时统计");
            long start = System.currentTimeMillis();
            proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            System.out.println("执行耗时:" + (end - start) / 1000);
        } catch (Throwable e) {
            log.error("AspectTest.aroundTest", e);
        }


    }
}
