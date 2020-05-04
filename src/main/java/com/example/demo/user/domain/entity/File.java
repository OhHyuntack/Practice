package com.example.demo.user.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;

public class File implements Serializable {
  //파일 시퀀스
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="fileSeq")
  private int fileSeq;
  // 오리지날 파일명
  @Column(name="originalfilename", length = 200)
  private String originalfilename;
  // 변경된 파일명
  @Column(name="logicalfilename", length = 200)
  private String logicalfilename;
  // 게시글 시퀀스
  @Column(name="boardSeq")
  private int boardSeq;
  // 파일 크기
  @Column(name="fileSize", length = 100)
  private String fileSize;
  // 파일 확장자
  @Column(name="fileExt", length = 100)
  private String fileExt;
  // 사용유무
  @Column(name="useYn", length = 3)
  private String useYn;
  // 등록자 아이디
  @Column(name="regId", length = 50)
  private String regId;


  @Builder
  public File(int fileSeq, String originalfilename, String logicalfilename, int boardSeq, String fileSize, String fileExt, String useYn, String regId) {
    this.fileSeq = fileSeq;
    this.originalfilename = originalfilename;
    this.logicalfilename = logicalfilename;
    this.boardSeq = boardSeq;
    this.fileSize = fileSize;
    this.fileExt = fileExt;
    this.useYn = useYn;
    this.regId = regId;
  }
}
