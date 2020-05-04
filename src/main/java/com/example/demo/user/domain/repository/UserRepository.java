package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(
      value = "SELECT COUNT(*) FROM user WHERE user_id = ?1",
      nativeQuery = true)
  int getByUserCnt(String userId);

  User findByUserId(String userId);
}

