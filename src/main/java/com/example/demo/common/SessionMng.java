package com.example.demo.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service("sessionMng")
public class SessionMng {

  private String URI_LOGIN = "/user/login";


  @SuppressWarnings("unchecked")
  public Boolean isLogin(HttpSession session) {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");
    //System.out.println("Session admin_id : " + sessionInfoValue);
//		if (loginInfo==null || loginInfo.get("admin_id").length()==0){
//		if (loginInfo==null || loginInfo.get("admin_id")==null || loginInfo.get("admin_id").length()==0){
//		if (loginInfo==null || loginInfo.get("S_MGR_ID")==null || loginInfo.get("S_MGR_ID").length()==0){
    if ("".equals(getSessionUserId(session))) { // 2020.02.05.c9
      return false;
    } else {
      //System.out.println("Session SungnamAdmin admin_id : " + loginInfo.get("admin_id"));
      return true;
    }
  }

  // 로그인 페이지로 강제 이동
  // return 값 있는 method에서 사용
  public String goLogin() {
    return "redirect:" + URI_LOGIN;
  }

  // 로그인 페이지로 강제 이동
  // return 값 없는 void method에서 사용
  public void goLoginVoid(HttpServletResponse response) throws IOException {
    PrintWriter writer = response.getWriter();
    writer.println(
        "<script type='text/javascript'>window.location.href='" + URI_LOGIN + "';</script>");
  }

  @SuppressWarnings("unchecked")
  public String getSessionUserId(HttpSession session) {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");
    return loginInfo.get("sessionUserId");
  }

  @SuppressWarnings("unchecked")
  public String getSessionUserName(HttpSession session) {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");
    return loginInfo.get("sessionUserName");
  }

  @SuppressWarnings("unchecked")
  public String getSessionMobbileNo(HttpSession session) {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");
    return loginInfo.get("sessionMobileNo");
  }

  @SuppressWarnings("unchecked")
  public String getSessionAddr(HttpSession session) {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");
    return loginInfo.get("sessionAddr");
  }

  @SuppressWarnings("unchecked")
  public String getSessionEmail(HttpSession session) {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>) session.getAttribute("sessionLoginInfo");
    return loginInfo.get("sessionEmail");
  }

}

