package com.jkevin.gradle_springboot_aop.aop;

import com.jkevin.gradle_springboot_aop.dto.HelloDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author mid2098
 * @version 1.0
 * @description: TODO
 * @date 2024/10/14 13:29
 */
@Slf4j
@Aspect
@Component
public class ClassAdvice {

    // 设置织面
    // 第一个* 指返回值
    // 后跟路径匹配所有controller 包下所有类 或者可以直接写具体的controller 类名
    @Pointcut("execution(* com.jkevin.gradle_springboot_aop.controller.*.*(..))")
    public void targetClass() {}

    // 1、针对方法执行表达式切入
    @Before("targetClass()")
    public void before(JoinPoint joinPoint) {
        log.info("before1方法， {}方法被调用",joinPoint.getSignature().getName());
    }

    // 2、针对特殊类注解切入
    @Before("@within(org.springframework.stereotype.Service)")
    public void before2(JoinPoint joinPoint) {
        log.info("before2方法， {}方法被调用",joinPoint.getSignature().getName());
    }

    // 3、针对自定义注解切入
    @Before("@annotation(com.jkevin.gradle_springboot_aop.aop.MyAnnotation)")
    public void before3(JoinPoint joinPoint) {
        log.info("before3方法， {}方法被调用",joinPoint.getSignature().getName());
    }

    // 4、针对自定义对象类型切入
    // this和target的区别需要对比代理的区别
    // 建议直接搜索jdk动态代理和cglib代理的区别，实现方式不一样导致扫描用的this和target不同。另外，自己写个简单接口测试一下就懂了，把proxyTargetClass属性值改一下，看看返回的bean对象是proxy还是cglib，很容易看懂
    // 4.1、代理对象类型
    //      指所有实现该接口的类中所有的方法，包括不属于该接口的方法
    @Before("target(com.jkevin.gradle_springboot_aop.service.MyService)")
    public void before4(JoinPoint joinPoint) {
        log.info("before4方法， {}方法被调用",joinPoint.getSignature().getName());
    }

    // 4.2、目标对象类型
    //      指所有实现该接口的类中所有的方法，包括不属于该接口的方法
//    @Before("this(com.jkevin.gradle_springboot_aop.service.MyService)")
//    public void before5(JoinPoint joinPoint) {
//        log.info("before5方法， {}方法被调用",joinPoint.getSignature().getName());
//    }

    // 5、针对参数切入
    // args无法被单独引入，只能和target一起使用
    @Before("@within(org.springframework.stereotype.Service) && args(dto)")
    public void before6(JoinPoint joinPoint, HelloDto dto) {
        // 在参数类型为 String 的方法之前执行
        log.info("before6方法， {}方法被调用，{}",joinPoint.getSignature().getName(),dto.toString());
    }

    // 6、通过组合表达式引入 && || !
    // 可参照第5点
}
