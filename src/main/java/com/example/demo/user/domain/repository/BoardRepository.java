package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query(value = "SELECT MAX(boardSeq) FROM board",
      nativeQuery = true)
  String findByMaxSeq();
}
