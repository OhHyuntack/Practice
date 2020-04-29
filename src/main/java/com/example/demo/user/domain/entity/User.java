package com.example.demo.user.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"})})
public class User extends Time implements Serializable {
  // 사용자ID
  @Id
  @Column(name="user_id", length = 50)
  private String userId;
  // 패스워드
  @Column(name="userPW", length = 50)
  private String userPW;
  // 패스워드
  @Column(name="addr", length = 100)
  private String addr;
  // 사용자명
  @Column(name="user_name", length = 50)
  private String userName;
  // 이메일주소
  @Column(name="email", length = 100)
  private String email;
  // 연락처
  @Column(name="mobile_no", length = 50)
  private String mobileNo;

  @Builder
  public User(String userId, String userPW, String addr, String userName, String email,
      String mobileNo) {
    this.userId = userId;
    this.userPW = userPW;
    this.addr = addr;
    this.userName = userName;
    this.email = email;
    this.mobileNo = mobileNo;

  }
}


