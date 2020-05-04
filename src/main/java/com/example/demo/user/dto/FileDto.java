package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {

  private int fileSeq;
  // 오리지날 파일명
  private String originalfilename;
  // 변경된 파일명
  private String logicalfilename;
  // 게시글 시퀀스
  private int boardSeq;
  // 파일 크기
  private String fileSize;
  // 파일 확장자
  private String fileExt;
  // 사용유무
  private String useYn;
  // 등록자 아이디
  private String regId;

  public File toEntity(){
    File file = File.builder()
        .boardSeq(fileSeq)
        .originalfilename(originalfilename)
        .logicalfilename(logicalfilename)
        .boardSeq(boardSeq)
        .fileSize(fileSize)
        .fileExt(fileExt)
        .useYn(useYn)
        .regId(regId)
        .build();
    return file;
  }
  @Builder
  public FileDto(int fileSeq, String originalfilename, String logicalfilename, int boardSeq, String fileSize,
      String fileExt, String useYn, String regId) {
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
