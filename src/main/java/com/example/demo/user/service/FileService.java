package com.example.demo.user.service;

import com.example.demo.user.domain.entity.FileInfo;
import com.example.demo.user.domain.entity.ImageFile;
import com.example.demo.user.domain.repository.FileRepository;
import com.example.demo.user.domain.repository.ImageFileRepository;
import com.example.demo.user.dto.FileDto;
import com.example.demo.user.dto.ImageFileDto;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FileService {

  private FileRepository fileRepository;
  private ImageFileRepository imageFileRepository;

  //파일 저장
  public void fileSave(FileDto fileDto) {
    fileRepository.save(fileDto.toEntity());
  }

  //다운로드 삭제
  @Transactional
  public void deleteFile(int fileSeq) {
    fileRepository.deleteByFileSeq(fileSeq);
  }

  public FileInfo selectFile(int fileSeq) {
    return fileRepository.findByFileSeq(fileSeq);
  }

  public int getFileSeq() { return imageFileRepository.findByMaxSeq();}

  public void imageFileSave(ImageFileDto imageFileDto) {imageFileRepository.save(imageFileDto.toEntity());}


  public ImageFile selectImageFile(int imageFileSeq){
    return imageFileRepository.findByImageFileSeq(imageFileSeq);
  }
}
