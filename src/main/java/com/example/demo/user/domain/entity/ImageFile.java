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
@Table(name = "imagefile", uniqueConstraints = {@UniqueConstraint(columnNames = {"image_file_seq"})})
@NoArgsConstructor
@Builder
public class ImageFile implements Serializable {
  //파일 시퀀스
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="image_file_seq")
  private int imageFileSeq;

  // 오리지날 파일명
  @Column(name="original_file_name", length = 200)
  private String originalFileName;
  // 변경된 파일명
  @Column(name="stored_file_name", length = 200)
  private String storedFileName;

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
  public ImageFile(int imageFileSeq, String originalFileName, String storedFileName, String fileSize, String fileExt, String useYn, String regId, String filePath) {
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
