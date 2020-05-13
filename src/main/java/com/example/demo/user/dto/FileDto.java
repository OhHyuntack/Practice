package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.FileInfo;
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
  private String originalFileName;
  // 변경된 파일명
  private String storedFileName;
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

  private String filePath;

  public FileInfo toEntity(){
    FileInfo fileInfo = FileInfo.builder()
        .fileSeq(fileSeq)
        .originalFileName(originalFileName)
        .storedFileName(storedFileName)
        .boardSeq(boardSeq)
        .fileSize(fileSize)
        .fileExt(fileExt)
        .useYn(useYn)
        .regId(regId)
        .filePath(filePath)
        .build();
    return fileInfo;
  }
  @Builder
  public FileDto(int fileSeq, String originalFileName, String storedFileName, int boardSeq, String fileSize,
      String fileExt, String useYn, String regId, String filePath) {
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
