package com.example.demo.common.interceptor;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    System.out.println(
        "Interceptor preHandle!!!!!========================================================="+request.getContextPath()+"/user/login.html");

   HttpSession session = request.getSession(false);
    if(session != null) {
      Object obj = session.getAttribute("sessionLoginInfo");
      if(obj != null)
        return true;
    }

    response.sendRedirect(request.getContextPath() + "/user/login");
    return false;
  }


}
