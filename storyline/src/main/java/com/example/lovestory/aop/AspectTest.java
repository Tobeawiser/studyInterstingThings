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
public class AspectTest {
    /**
     * 切点，表示要进行增强的哪些类和方法
     */
    @Pointcut("execution(* com.example.lovestory.aop.TestBean.test(..))")
    public void test() {
    }

    @Around("test()")
    public void aroundTest(ProceedingJoinPoint proceedingJoinPoint) {
        String s = proceedingJoinPoint.getArgs()[0].toString();
        System.out.println("before Test..." + s);
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("AspectTest.aroundTest", e);
        }

        System.out.println("after test ..." + s);

    }
}
