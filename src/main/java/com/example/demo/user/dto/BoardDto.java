package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.domain.entity.FileInfo;
import java.time.LocalDateTime;
import java.util.List;
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

  private String boardType;

  private List<FileInfo> fileInfoList;

  private String isDel;

  private LocalDateTime delDate;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;

  private int rowNum;

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
        .boardType(boardType)
        .fileInfoList(fileInfoList)
        .isDel(isDel)
        .delDate(delDate)
        .modifiedDate(modifiedDate)
        .createdDate(createdDate)
        .build();
    return board;
  }
  @Builder
  public BoardDto(int boardSeq, String boardPW, String title, String content, int readCnt,
      String writer, String department, String contact, String boardType, List<FileInfo> fileInfoList, String isDel, LocalDateTime delDate
      , LocalDateTime createdDate, LocalDateTime modifiedDate) {
    this.boardSeq = boardSeq;
    this.boardPW = boardPW;
    this.title = title;
    this.content = content;
    this.readCnt = readCnt;
    this.writer = writer;
    this.department = department;
    this.contact = contact;
    this.boardType = boardType;
    this.fileInfoList = fileInfoList;
    this.isDel = isDel;
    this.delDate = delDate;
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
  }
}
