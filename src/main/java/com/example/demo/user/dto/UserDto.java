package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.User;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

  // 사용자ID
  @NotBlank(message = "아이디는 필수 입력 값입니다.")
  private String userId;

  // 패스워드
  //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
  //    message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
  @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
  private String userPW;

  // 패스워드
  private String addr;
  // 사용자명
  private String userName;
  // 이메일주소
  private String email;
  // 연락처
  private String mobileNo;

  public User toEntity(){
    User user = User.builder()
        .userId(userId)
        .userPW(userPW)
        .addr(addr)
        .userName(userName)
        .email(email)
        .mobileNo(mobileNo)
        .build();
    return user;
  }
  @Builder
  public UserDto(String userId, String userPW, String addr, String userName, String email,
      String mobileNo) {
    this.userId = userId;
    this.userPW = userPW;
    this.addr = addr;
    this.userName = userName;
    this.email = email;
    this.mobileNo = mobileNo;
  }
}
