package com.example.demo.user.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "board", uniqueConstraints = {@UniqueConstraint(columnNames = {"board_seq"})})
public class Board extends Time implements Serializable {
  // seq
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="board_seq")
  private int boardSeq;
  // 제목
  @Column(name="title", length = 200)
  private String title;
  // 내용
  @Column(name="content", length = 2000)
  private String content;
  // 조회수
  @Column(name="read_cnt")
  private int readCnt;
  // 글쓴이
  @Column(name="writer", length = 100)
  private String writer;
  // 게시글 패스워드
  @Column(name="boardPW", length = 50)
  private String boardPW;

  @Column(name="department", length = 100)
  private String department;

  @Column(name="contact", length = 30)
  private String contact;

  @Column(name="board_type", length = 50)
  private String boardType;


  @Builder
  public Board(int boardSeq, String title, String content, int readCnt, String writer, String boardPW, String department, String contact, String boardType) {
    this.boardSeq = boardSeq;
    this.title = title;
    this.content = content;
    this.readCnt = readCnt;
    this.writer = writer;
    this.boardPW = boardPW;
    this.department = department;
    this.contact = contact;
    this.boardType = boardType;
  }
}
