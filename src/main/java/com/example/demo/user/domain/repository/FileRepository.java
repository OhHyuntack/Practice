package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileInfo, Long> {

  void deleteByFileSeq(int fileSeq);

  FileInfo findByFileSeq(int fileSeq);

}
