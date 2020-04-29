package com.example.demo.user.controller;

import com.example.demo.common.UserSha256;
import com.example.demo.user.domain.entity.User;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @GetMapping("/user/join")
  public String join(){
    return "member/join";
  }

  @GetMapping("/user/login")
  public String login(){
    return "member/login";
  }

  @PostMapping("/user/insertMember")
  @ResponseBody
  public JSONObject insertMember(@Valid UserDto userDto, Errors errors, Model model){
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
  public JSONObject idCheck(User user){
    String userId = user.getUserId();
    JSONObject json = new JSONObject();
    int chkValue = 0;
    chkValue = userService.idCheck(userId);
    json.put("chkValue", chkValue);
    return json;
  }

  @PostMapping("/user/login")
  @ResponseBody
  public JSONObject login(User user){
    JSONObject json = new JSONObject();
    String msg = "";
    String encryPassword = UserSha256.encrypt(user.getUserPW());
    UserDto userDto = userService.getByUserId(user.getUserId());

    if(userDto != null){
      if(userDto.getUserPW().equals(encryPassword)){
        HttpSession session = new HttpSession();
      }else{
        msg = "아이디 또는 비밀번호가 다릅니다";
      }
      msg = "아이디 또는 비밀번호가 다릅니다";
    }else{
      msg = "가입된 아이디가 없습니다";
    }

    json.put("msg", msg);


    return json;
  }

  @GetMapping("/user/logout")
  public void logout(){

  }

}