package com.example.demo.user.controller;

import com.example.demo.common.UserSha256;
import com.example.demo.user.domain.entity.User;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/user/join")
  public String join() {
    return "member/join";
  }

  @GetMapping("/user/login")
  public String login(@RequestParam(value="rUrl", required=false) String rUrl, Model model) {

    if(rUrl != null){
      model.addAttribute("rUrl", rUrl);
    }

    return "member/login";
  }

  @PostMapping("/user/insertMember")
  @ResponseBody
  public JSONObject insertMember(@Valid UserDto userDto, Errors errors) {
    JSONObject json = new JSONObject();
    //유효성 검사 실패시
    if (errors.hasErrors()) {
      json.put("userDto", userDto);
      // 유효성 통과 못한 필드와 메시지를 핸들링
      Map<String, String> validatorResult = userService.validateHandling(errors);
      for (String key : validatorResult.keySet()) {
        json.put(key, validatorResult.get(key));
      }
      return json;
    }

    //비밀번호 암호화
    userDto.setUserPW(UserSha256.encrypt(userDto.getUserPW()));
    //검증된 data 인써트
    userService.insertMember(userDto);
    json.put("msg", "가입을 환영합니다.");

    return json;
  }

  @PostMapping("/user/idCheck")
  @ResponseBody
  public JSONObject idCheck(User user) {
    String userId = user.getUserId();
    JSONObject json = new JSONObject();
    int chkValue = 0;
    chkValue = userService.idCheck(userId);
    json.put("chkValue", chkValue);
    return json;
  }

  /**
   * @param user
   * @param session
   * @return
   */
  @PostMapping("/user/login")
  @ResponseBody
  public JSONObject login(User user, HttpSession session, @RequestParam(value = "rUrl", required=false) String rUrl) {
    JSONObject json = new JSONObject();
    String result = "";
    String encryPassword = UserSha256.encrypt(user.getUserPW());
    User userInfo = userService.findByUserId(user.getUserId());

    User userTest = userService.findById(user.getUserId());

    if (userInfo != null) {
      if (userInfo.getUserPW().equals(encryPassword)) {
        // 세션정보 매핑
        //세션 타임아웃 시간 설정 60*60 1시간 * 24 시간
        session.setMaxInactiveInterval(60 * 60 * 24);
        HashMap<String, String> loginInfo = new HashMap<String, String>();
        loginInfo.put("sessionUserId", userInfo.getUserId());
        loginInfo.put("sessionUserName", userInfo.getUserName());
        loginInfo.put("sessionMobileNo", userInfo.getMobileNo());
        loginInfo.put("sessionAddr", userInfo.getAddr());
        loginInfo.put("sessionEmail", userInfo.getEmail());

        session.setAttribute("sessionUserId", userInfo.getUserId());
        session.setAttribute("sessionUserName", userInfo.getUserName());
        session.setAttribute("sessionMobileNo", userInfo.getMobileNo());
        session.setAttribute("sessionAddr", userInfo.getAddr());
        session.setAttribute("sessionEmail", userInfo.getEmail());
        session.setAttribute("sessionLoginInfo", loginInfo);

        result = "success";
        json.put("rUrl", rUrl);
        json.put("result", result);
        return json;

      } else {
        result = "idFail";
      }
    } else {
      result = "pwFail";
    }
    json.put("rUrl", rUrl);
    json.put("result", result);

    return json;
  }

  @GetMapping("/user/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("sessionUserId");
    session.removeAttribute("sessionUserName");
    session.removeAttribute("sessionMobileNo");
    session.removeAttribute("sessionAddr");
    session.removeAttribute("sessionEmail");
    session.removeAttribute("sessionLoginInfo");

    return "index";
  }

  @GetMapping("/member/info")
  public String memberInfo(){ return "member/info"; }

  @GetMapping("/member/myInfo")
  public String memberMyinfo(){

    return "member/myinfo";
  }

  @GetMapping("/member/find")
  public String memberFind(){return "member/find";}

  @GetMapping("/member/network")
  public String memberNetwork(){return "member/network";}

}