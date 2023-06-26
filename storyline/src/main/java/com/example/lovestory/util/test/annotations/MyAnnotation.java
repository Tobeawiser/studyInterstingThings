package com.example.lovestory.util.test.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 把注解的值和这个类给联系起来
 * 直接用对象的class对象进行反射
 * 需要获取到当前对象，用反射进行处理
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String hello() default "hello";
}