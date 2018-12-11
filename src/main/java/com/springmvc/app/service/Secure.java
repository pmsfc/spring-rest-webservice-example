package com.springmvc.app.service;

import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Using aspects to verify access to all controllers
 * @author Pedro Caldeira
 */
@Aspect
public class Secure {

    /**
     * Before all execution of any controller class this method will run
     * @param joinPoint
     */
    @Before("execution(* com.springmvc.app.controllers.*.*(..))")
    public void checkLogin(JoinPoint joinPoint) {
        System.out.println("ASPECT: Checking login: " + joinPoint.getTarget().getClass());
        HttpSession s = (HttpSession) RequestContextHolder
                    .currentRequestAttributes()
                    .resolveReference(RequestAttributes.REFERENCE_SESSION);
        
        if(s != null){
            System.out.println("ASPECT: Session found");            
        }
    }
}
