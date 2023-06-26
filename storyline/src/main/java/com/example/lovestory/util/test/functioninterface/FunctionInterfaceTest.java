package com.example.lovestory.util.test.functioninterface;

import java.util.function.Function;

/**
 * 函数式接口作为参数
 */
public class FunctionInterfaceTest {
    public static void main(String[] args) {
        FunctionInterfaceTest interfaceTest = new FunctionInterfaceTest();

        funMethod funMethod = new FunctionInterfaceTest.funMethodService();
        String s = interfaceTest.functionTest(funMethod, "s");
        System.out.println(s);
    }

    public String functionTest(Function function, String s) {
        Object apply = function.apply(s);
        return apply.toString();
    }

    interface funMethod extends Function {

    }

    static class funMethodService implements funMethod {
        @Override
        public Object apply(Object o) {
            return o.toString();
        }
    }
}
