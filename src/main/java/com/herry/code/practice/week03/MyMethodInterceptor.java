package com.herry.code.practice.week03;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * CGLIB 实现动态代理
 * @author herry
 */
public class MyMethodInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyMethodInterceptor.class);

    private final Enhancer enhancer = new Enhancer();

    private final Object bean;

    public MyMethodInterceptor(Object bean) {
        this.bean = bean;
    }

    public Object getProxy(){
        //设置需要创建子类的类
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    /**
     * 在读取数据前后打印日志
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String methodName = method.getName();
        if ("readExcel".equals(methodName)){
            logger.info("开始读取数据");
        }
        Object result = method.invoke(bean, args);
        logger.info("数据读取结束");

        return result;
    }
}