package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

  private int boardSeq;
  // 제목
  private String title;
  // 내용
  private String content;
  // 조회수
  private int readCnt;
  // 글쓴이
  private String writer;
  // 게시글 패스워드
  private String boardPW;
  // 게시글 부서
  private String department;
  // 게시글 부서
  private String contact;

  public Board toEntity(){
    Board board = Board.builder()
        .boardSeq(boardSeq)
        .boardPW(boardPW)
        .title(title)
        .content(content)
        .readCnt(readCnt)
        .writer(writer)
        .department(department)
        .contact(contact)
        .build();
    return board;
  }
  @Builder
  public BoardDto(int boardSeq, String boardPW, String title, String content, int readCnt,
      String writer, String department, String contact) {
    this.boardSeq = boardSeq;
    this.boardPW = boardPW;
    this.title = title;
    this.content = content;
    this.readCnt = readCnt;
    this.writer = writer;
    this.department = department;
    this.contact = contact;
  }
}
