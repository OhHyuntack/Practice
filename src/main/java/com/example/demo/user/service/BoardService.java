package com.example.demo.user.service;

import com.example.demo.user.domain.repository.BoardRepository;
import com.example.demo.user.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardService {

  private BoardRepository boardRepository;

  // 글등록
  public String boardSave(BoardDto boardDto){

    boardRepository.save(boardDto.toEntity());
    String boardSeq = boardRepository.findByMaxSeq();

    return boardSeq;
  }
}
