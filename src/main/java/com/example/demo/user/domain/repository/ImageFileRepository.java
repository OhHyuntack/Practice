package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {


  @Query(value = "SELECT max(file_seq) FROM fileinfo", nativeQuery = true)
  int findByMaxSeq();


  ImageFile findByImageFileSeq(int imageFileSeq);
}
