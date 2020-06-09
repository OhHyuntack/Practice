package com.example.demo.common.aop;

import com.example.demo.common.SessionMng;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AOPChkLogin {

  @Resource(name = "sessionMng")
  private SessionMng sessionMng;

  @SuppressWarnings("unchecked")
  @Around("execution(* com.example.demo.user.controller.BoardController.*())")
  public Object aroundAdminProcess(ProceedingJoinPoint joinPoint) throws Throwable {

    String className = joinPoint.getTarget().getClass().getSimpleName();
    String methodName = joinPoint.getSignature().getName();
    System.out.println(
        "------------------------------------------------------------\nAOP AOPChkAdminLogin 시작 : "
            + className + "." + methodName + " executed.");

    if (!className.equals("UserController")) {
      HttpServletRequest request = null;
      for (Object o : joinPoint.getArgs()) {
        if (o instanceof HttpServletRequest) {
          request = (HttpServletRequest) o;
        }
      }
      try {
        HttpSession session = request.getSession();
        HashMap<String, String> loginInfo = new HashMap<String, String>();
        loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");

        if (loginInfo == null || loginInfo.get("S_MGR_ID").equals("")) {
          System.out.println("AOP AOPChkAdminLogin 비로그인 : " + className + "." + methodName
              + "\n------------------------------------------------------------");
          return sessionMng.goLogin();
        }
      } catch (Exception e) {
        System.out.println("AOP AOPChkAdminLogin 세션없음 : " + className + "." + methodName
            + "\n------------------------------------------------------------");
        return sessionMng.goLogin();
      }
    } else {
      System.out.println("AOP AOPChkAdminLogin 체크 비대상 : " + className + "." + methodName);
    }

    Object result = joinPoint.proceed();
    return result;
  }

}