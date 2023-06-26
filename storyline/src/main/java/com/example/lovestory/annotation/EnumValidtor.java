package com.example.lovestory.annotation;

import com.example.lovestory.constants.BizConstant;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class EnumValidtor implements ConstraintValidator<EnumValid, String> {

    private EnumValid enumValid;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.enumValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(String val, ConstraintValidatorContext constraintValidatorContext) {
        //  enumValid 获取枚举类型
        Class<?> enumClass = enumValid.value();
        Method valMethod = null;
        try {
            //获取默认自定义校验注解方法名
            valMethod = enumClass.getMethod(BizConstant.DEFAULT_ENUM_METHOD);
            Field[] fields = enumClass.getFields();

            if (fields.length == 0) {
                return false;
            }
            //遍历enum域类 激活查找指定值 对比
            for (Field field : fields) {
                String enumVal = valMethod.invoke(field.get(field)).toString();
                if (enumVal.equals(val)) {
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException{}", BizConstant.DEFAULT_ENUM_METHOD);
        } catch (Exception e) {
            log.error("ReflectiveOperationException", e);
        }
        return false;
    }
}
