package com.zjts.broadband.common.log;

import com.mongodb.BasicDBObject;
import com.zjts.broadband.system.model.SysLog;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台系统日志 - 切点类
 */
@Aspect
@Component
public class LogAOP {

    @Autowired
    MongoTemplate mongoTemplate;

    private String collectionName = "broadbandLog";

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(LogAOP.class);

    //Service层切点
    @Pointcut("execution (* com.zjts.broadband.*.service.*.*(..)) @annotation(com.zjts.broadband.common.log.ServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.zjts.broadband.common.log.ControllerLog)")
    public void controllerAspect() {
    }


    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        try {
            //请求的IP
            String ip = request.getRemoteAddr();
            //*========数据库日志=========*//
            SysLog sysLog = new SysLog();
            sysLog.setUsername((String) session.getAttribute("username"));
            sysLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            sysLog.setCreateTime(new Date());
            sysLog.setRequestip(ip);
            sysLog.setType(0);
            sysLog.setOperation(getControllerMethodDescription(joinPoint));
            //保存数据库
            mongoTemplate.save(sysLog);
        } catch (Exception e) {
            //记录本地异常日志
            logger.error("记录本地系统日志异常");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        try {
            //读取headUAC中的用户
            String username = request.getHeader("head_uac");
            if (StringUtils.isBlank(username)) {
                username = "未知用户";
            }
//            if (idcard != null) {
//                username = idcard;
//            }
            //获取请求ip
            String ip = request.getRemoteAddr();
            //获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += joinPoint.getArgs()[i] + ";";
                }
            }
            /*==========数据库日志=========*/
            SysLog sysLog = new SysLog();
            sysLog.setUsername((String) session.getAttribute("username"));
            sysLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            sysLog.setCreateTime(new Date());
            sysLog.setRequestip(ip);
            sysLog.setType(1);
            sysLog.setOperation(getServiceMthodDescription(joinPoint));
            sysLog.setExceptioncode(e.getClass().getName());
            sysLog.setExceptiondetail(e.getMessage());
            sysLog.setParams(params);
            //保存数据库
            mongoTemplate.save(sysLog);
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("记录系统异常信息异常");
            logger.error("异常信息:{}", ex.getMessage());
        }

    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
