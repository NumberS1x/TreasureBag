package com.example.six.core.aspect;


import com.example.six.core.annotion.WebLog;
import com.example.six.core.utils.IPUtils;
import com.example.six.modules.weblog.entity.WebLogEntity;
import com.example.six.modules.weblog.service.WebLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @Aspect：标识切面
 *
 * @Pointcut：设置切点，这里以自定义注解为切点，定义切点有很多其它种方式，自定义注解是比较常用的一种。
 *
 * @Before：在切点之前织入，打印了一些入参信息
 *
 * @Around：环绕切点，打印返回参数和接口执行时间
 */


@Aspect
@Component
public class WebLogAspect {
    @Autowired
    private WebLogService webLogService;


    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);


    /**
     * Pointcut自定义切点
     */
    @Pointcut("@annotation(com.example.six.core.annotion.WebLog)")
    public void webLog(){
    }


    /**
     * 在切点之前织入
     * @param joinPoint
     */

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        WebLog webLog = method.getAnnotation(WebLog.class);
        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
//        获取WebLog注解中的值，表示操作
        String operation;
        if (webLog!=null){
            operation=webLog.value();
        }else{
            operation=null;
        }
//        获取httpMethod
        String httpMethod = request.getMethod();
//        打印请求的url
        logger.info("URL            : {}", request.getRequestURL().toString());
        String httpUrl = request.getRequestURL().toString();
//      获取classMethod
        String classMethod = signature.getDeclaringTypeName() + signature.getName();
//        获取请求的参数
        Object[] args = joinPoint.getArgs();
        String params = null;
        try{
            params = Arrays.toString(args);
        }catch (Exception e){
            System.out.println(e);
        }
//        获取Ip
        String ip = IPUtils.getIpAddr(request);

        WebLogEntity webLogEntity = WebLogEntity.builder()
                .userName(request.getRemoteUser())
                .operation(operation)
                .httpMethod(httpMethod)
                .httpUrl(httpUrl)
                .classMethod(classMethod)
                .param(params)
                .ip(ip)
                .createTime(new Date())
                .build();
        webLogService.insertWebLog(webLogEntity);
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 结束后打个分隔线，方便查看
        logger.info("=========================================== End ===========================================");
    }

    /**
     *环绕处理
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开始时间
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
//        // 打印出参
//        logger.info("Response Args  : {}", new ObjectMapper().writeValueAsString(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


}
