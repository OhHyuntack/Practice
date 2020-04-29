package com.example.demo.user.service;

import com.example.demo.user.domain.entity.User;
import com.example.demo.user.domain.repository.UserRepository;
import com.example.demo.user.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Service
public class UserService {

  private UserRepository userRepository;

  // 회원가입 시, 유효성 체크
  public Map<String, String> validateHandling(Errors errors) {
    Map<String, String> validatorResult = new HashMap<>();

    for (FieldError error : errors.getFieldErrors()) {
      String validKeyName = String.format("valid_%s", error.getField());
      validatorResult.put(validKeyName, error.getDefaultMessage());
    }

    return validatorResult;
  }
  // 회원가입
  public void insertMember(UserDto userDto){
      userRepository.save(userDto.toEntity());
  }

  // 아이디체크
  public int idCheck(String userId) {
    return userRepository.findByUserId(userId);
  }

  public UserDto getByUserId(String userId) {
    return userRepository.getByUserId(userId);
  }
}
