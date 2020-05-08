package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query(value = "SELECT MAX(board_seq) FROM board",
      nativeQuery = true)
  String findByMaxSeq();
}
