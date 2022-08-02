package com.example.demo.base.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.common.Constants;
import com.example.demo.utils.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/18 8:19 下午
 * @hope: The newly created file will not have a bug
 */
@Aspect
@Component
@Slf4j
public class TraceIdConfig {

    private final ThreadLocal<String> traceTag = new ThreadLocal<>();

    /* controller的切点*/
    @Pointcut("execution(public * com.example.demo..*.*(..))")
    public void controllerTraceId() {
    }

  @Before(value = "controllerTraceId()")
    public void doBefore(JoinPoint joinPoint){
        //添加日志打印
        if (StringUtils.isBlank(MDC.get(Constants.TRACE_ID))) {
            MDC.put(Constants.TRACE_ID,TraceIdUtil.getTraceId() );
        }
      // 获得方法名称
      String className = joinPoint.getTarget().getClass().getName();
      String[] classNamePackage = className.split("\\.");
      className = classNamePackage[classNamePackage.length - 1];
      String methodName = joinPoint.getSignature().getName();
      String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
      Object[] args = joinPoint.getArgs();
//      String requestParm = "方法入参 : (无入参)";
//      if (args.length != 0) {
//          StringBuffer buffer = new StringBuffer();
//          for (int i = 0; i < args.length; i++) {
//              if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
//                  continue;
//              }
//              if (argNames == null || argNames.length == 0) {
//                  buffer.append(String.format("%s ", JSON.toJSONString(args[i])));
//              } else if(args[i] == null){
//                  buffer.append(String.format("%s ", argNames[i]));
//              }else {
//                  buffer.append(String.format("%s=%s ", argNames[i], JSON.toJSONString(args[i])));
//              }
//              if (i != args.length - 1) {
//                  buffer.append(",");
//              }
//          }
//          requestParm = buffer.toString();
//      }
      log.info("<=== 调用类名.方法 = {}.{} ;请求参数:{}", className, methodName, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "controllerTraceId()",returning="returnValue")
    public void  doAfterReturning(JoinPoint joinPoint,Object returnValue){
        String className = joinPoint.getTarget().getClass().getName();
        String[] classNamePackage = className.split("\\.");
        className = classNamePackage[classNamePackage.length - 1];
        String methodName = joinPoint.getSignature().getName();
        log.info("<=== 调用类名.方法 = {}.{} ; 方法出参 : {}", className, methodName, JSON.toJSONString(returnValue));
    }


    @Around(value="controllerTraceId()")
    public Object timeAround(ProceedingJoinPoint joinPoint) {
        //获取开始执行的时间
        long startTime = System.currentTimeMillis();

        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("=====>统计某方法执行耗时环绕通知出错", e);
        }

        String className = joinPoint.getTarget().getClass().getName();
        String[] classNamePackage = className.split("\\.");
        className = classNamePackage[classNamePackage.length - 1];
        String methodName = joinPoint.getSignature().getName();

        // 获取执行结束的时间
        long endTime = System.currentTimeMillis();
        // 打印耗时的信息
        log.info("<=== 调用类名.方法 = {}.{} ;请求共耗时：{} ms",className, methodName,endTime-startTime);
        MDC.remove(Constants.TRACE_ID);
        return obj;
    }


    /**
     *
     @Aspect：声明该类为一个注解类；
     @Pointcut：定义一个切点，后面跟随一个表达式，表达式可以定义为切某个注解，也可以切某个 package 下的方法；

     @Before: 在切点之前，织入相关代码；
     @After: 在切点之后，织入相关代码;
     @AfterReturning: 在切点返回内容后，织入相关代码，一般用于对返回值做些加工处理的场景；
     @AfterThrowing: 用来处理当织入的代码抛出异常后的逻辑处理;
     @Around: 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点  只能用 ProceedingJoinPoint 其余用JoinPoint
     */
}
