package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.Board;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query(value = "SELECT MAX(board_seq) FROM board",
      nativeQuery = true)
  String findByMaxSeq();

  @Query(value = "select DISTINCT bo from Board bo left join fetch bo.fileList where bo.boardSeq = :boardSeq")
  Board findByBoardSeq(int boardSeq);

  @Query(value="select bo.* from Board bo where bo.board_seq < :boardSeq order by bo.board_seq desc LIMIT 1", nativeQuery = true)
  Board findPrevBoardSeq(int boardSeq);

  @Query(value="select bo.* from Board bo where bo.board_seq > :boardSeq order by bo.board_seq desc LIMIT 1", nativeQuery = true)
  Board findNextBoardSeq(int boardSeq);

}
