package com.example.demo.user.service;

import com.example.demo.common.BoardSpecification;
import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.domain.entity.FileInfo;
import com.example.demo.user.domain.entity.QBoard;
import com.example.demo.user.domain.repository.BoardRepository;
import com.example.demo.user.domain.repository.FileRepository;
import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.dto.FileDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardService {

  private BoardRepository boardRepository;

  @PersistenceContext
  private EntityManager em;


  // 글등록
  public String boardSave(BoardDto boardDto) {
    boardRepository.save(boardDto.toEntity());
    String boardSeq = boardRepository.findByMaxSeq();
    return boardSeq;
  }

  //리스트 목록 보기
  public Page<Board> findBoardList(Pageable pageable, Map<String, String> searchMap,int viewLow) {

    String del = String.valueOf('N');
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        viewLow, Sort.by("boardSeq").descending());

    if (searchMap.size() == 0) {
      return boardRepository.findAll(pageable);
    } else {
      return boardRepository.findAll(BoardSpecification.searchBoard(searchMap), pageable);
    }
  }



  //상세보기
  public Board findByBoardSeq(int boardSeq) {
    return boardRepository.findByBoardSeq(boardSeq);
  }

  //이전글
  public Board findPrevBoardSeq(int boardSeq) {
    return boardRepository.findPrevBoardSeq(boardSeq);
  }

  //다음글
  public Board findNextBoardSeq(int boardSeq) {
    return boardRepository.findNextBoardSeq(boardSeq);
  }

  //게시글 삭제
  @Transactional
  public void deleteBoard(int boardSeq) {
    boardRepository.deleteUpdate(boardSeq);
  }

  //게시글 수정
  public void boardUpdate(BoardDto boardDto) {
    boardRepository.boardUpdate(boardDto);
  }


  //querydsl
  public List<Board> qFindBoardList(Pageable pageable, String searchType, String searchKeyword) {
    JPAQuery<Board> query = new JPAQuery<>(em);
    QBoard qBoard = QBoard.board;
    BooleanBuilder builder = new BooleanBuilder();

    /*if (searchKeyword != null) {
      if (searchType.equals("title")) {
        builder.and(qBoard.title.eq(searchKeyword));
      } else if (searchType.equals("content")) {
        builder.and(qBoard.content.eq(searchKeyword));
      } else if (searchType.equals("all")) {
        builder.and(qBoard.title.eq(searchKeyword));
        builder.and(qBoard.content.eq(searchKeyword));
      }
    }*/
    //.where(builder)
    query.from(QBoard.board)
        .where(qBoard.isDel.eq("N"))
        .orderBy(qBoard.boardSeq.desc())
        .offset(0).limit(10);
    return query.fetch();
  }

}
