package com.elp.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by NWJ on 2017/7/3.
 */

@Aspect
@Component
public class TestAspect {

    private final static Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Pointcut("execution(public * com.elp.controller.TestController.*(..))")
    public void point(){}

    @Before("point()")
    public void doBefore() {
        logger.info("before!!!!");
    }

    @After("point()")
    public void doAfter() {
        logger.info("after!!!!");
    }
}
