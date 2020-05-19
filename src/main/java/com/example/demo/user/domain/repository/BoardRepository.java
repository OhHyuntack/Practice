package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.dto.BoardDto;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>,
    JpaSpecificationExecutor<Board> {

  @Query(value = "SELECT MAX(board_seq) FROM board",
      nativeQuery = true)
  String findByMaxSeq();

  @Query(value = "select DISTINCT bo from Board bo left join fetch bo.fileInfoList where bo.boardSeq = :boardSeq")
  Board findByBoardSeq(int boardSeq);

  @Query(value="select bo.* from Board bo where bo.board_seq < :boardSeq and is_del = 'N' order by bo.board_seq desc LIMIT 1", nativeQuery = true)
  Board findPrevBoardSeq(int boardSeq);

  @Query(value="select bo.* from Board bo where bo.board_seq > :boardSeq and is_del = 'N' order by bo.board_seq asc LIMIT 1", nativeQuery = true)
  Board findNextBoardSeq(int boardSeq);

  @Transactional
  @Modifying
  @Query(value="update Board "
      + "SET is_del = 'Y', del_date = SYSDATE()"
      + "WHERE board_seq = :boardSeq", nativeQuery = true)
  void deleteUpdate(int boardSeq);

  //Page<Board> findAllByIsDel(Pageable pageable, String n);

  @Transactional
  @Modifying
  @Query(value= "UPDATE board "
      + " SET title = :#{#boardDto.title}"
      + ", content = :#{#boardDto.content}"
      + ", writer = :#{#boardDto.writer}"
      + ", department = :#{#boardDto.department}"
      + ", contact = :#{#boardDto.contact}"
      + ", modified_date = SYSDATE()"
      + " WHERE board_seq = :#{#boardDto.boardSeq}", nativeQuery = true)
  Integer boardUpdate(@Param("boardDto") BoardDto boardDto);


  Page<Board> findAll(Specification<Board> searchBoard, Pageable pageable);


}
