package com.example.demo.user.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "fileinfo", uniqueConstraints = {@UniqueConstraint(columnNames = {"file_seq"})})
@NoArgsConstructor
@Builder
public class FileInfo implements Serializable {
  //파일 시퀀스
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="file_seq")
  private int fileSeq;

  // 오리지날 파일명
  @Column(name="original_file_name", length = 200)
  private String originalFileName;
  // 변경된 파일명
  @Column(name="stored_file_name", length = 200)
  private String storedFileName;

  // 게시글 시퀀스
  @Column(name="board_seq")
  private int boardSeq;

  // 파일 크기
  @Column(name="file_size", length = 100)
  private String fileSize;
  // 파일 확장자
  @Column(name="file_ext", length = 100)
  private String fileExt;
  // 사용유무
  @Column(name="use_yn", length = 3)
  private String useYn;
  // 등록자 아이디
  @Column(name="reg_id", length = 50)
  private String regId;
  // 등록자 아이디
  @Column(name="file_path", length = 50)
  private String filePath;


  @Builder
  public FileInfo(int fileSeq, String originalFileName, String storedFileName, int boardSeq, String fileSize, String fileExt, String useYn, String regId, String filePath) {
    this.fileSeq = fileSeq;
    this.originalFileName = originalFileName;
    this.storedFileName = storedFileName;
    this.boardSeq = boardSeq;
    this.fileSize = fileSize;
    this.fileExt = fileExt;
    this.useYn = useYn;
    this.regId = regId;
    this.filePath = filePath;
  }
}
