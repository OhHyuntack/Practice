package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.ImageFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageFileDto {

  private int imageFileSeq;
  // 오리지날 파일명
  private String originalFileName;
  // 변경된 파일명
  private String storedFileName;
  // 파일 크기
  private String fileSize;
  // 파일 확장자
  private String fileExt;
  // 사용유무
  private String useYn;
  // 등록자 아이디
  private String regId;

  private String filePath;

  public ImageFile toEntity(){
    ImageFile imageFile = ImageFile.builder()
        .imageFileSeq(imageFileSeq)
        .originalFileName(originalFileName)
        .storedFileName(storedFileName)
        .fileSize(fileSize)
        .fileExt(fileExt)
        .useYn(useYn)
        .regId(regId)
        .filePath(filePath)
        .build();
    return imageFile;
  }

  @Builder
  public ImageFileDto(int imageFileSeq, String originalFileName, String storedFileName, String fileSize,
      String fileExt, String useYn, String regId, String filePath) {
    this.imageFileSeq = imageFileSeq;
    this.originalFileName = originalFileName;
    this.storedFileName = storedFileName;
    this.fileSize = fileSize;
    this.fileExt = fileExt;
    this.useYn = useYn;
    this.regId = regId;
    this.filePath = filePath;
  }
}
