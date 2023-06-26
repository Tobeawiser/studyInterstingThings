package com.example.lovestory.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验指定枚举注解
 * TYPE：表示类、接口（包括注解），或者枚举声明
 * FIELD：字段，包括枚举常量
 * METHOD：方法
 * PARAMETER：方法中的参数
 * CONSTRUCTOR：构造方法
 * LOCAL_VARIABLE：本地变量
 * ANNOTATION_TYPE：注解类型
 * PACKAGE：包
 *
 * @author liaochengyuan
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {EnumValidtor.class})
public @interface EnumValid {

    String message() default "{枚举类型不正确}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> value();


}
