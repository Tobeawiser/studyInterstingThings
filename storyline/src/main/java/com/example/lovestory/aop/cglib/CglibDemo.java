package com.example.lovestory.aop.cglib;

public class CglibDemo {
    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        //设置父类好调用方法
//        enhancer.setSuperclass(CglibDemo.class);
//        //拦截器
//        enhancer.setCallback(new MethodInterceptorImp());
//        CglibDemo cglibDemo = (CglibDemo) enhancer.create();
//        //调用此代理方法  类中所有方法都被拦截代理
//        cglibDemo.test("iam args1");
//        cglibDemo.test1();
        ProxyStrengthen.stengthen();

    }
//    springAop

//    @Override
//    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
//
//        if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
//            Class<?> targetClass = config.getTargetClass();
//            if (targetClass == null) {
//                throw new AopConfigException("TargetSource cannot determine target class: " +
//                        "Either an interface or a target is required for proxy creation.");
//            }
//            //如果
//            if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
//                return new JdkDynamicAopProxy(config);
//            }
//            return new ObjenesisCglibAopProxy(config);
//        }
//        else {
//            return new JdkDynamicAopProxy(config);
//        }
//    }

}
