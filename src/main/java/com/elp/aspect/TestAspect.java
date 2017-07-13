package com.elp.aspect;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by NWJ on 2017/7/3.
 */

@Aspect
@Component
public class TestAspect {

    private final static Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Pointcut("execution(public * com.elp.controller.*.*(..))")
    public void point(){}

    @Before("point()")
    public void doBefore() {
        logger.info("before!!!!");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("ID");
        /*之后登陆通过这个返回sessionid*/
        logger.info(session.getId());
        logger.info(id);
    }

    @After("point()")
    public void doAfter() {
        logger.info("after!!!!");
    }
}
