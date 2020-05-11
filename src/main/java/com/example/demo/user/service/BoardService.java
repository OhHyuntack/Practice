package com.example.demo.user.service;

import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.domain.repository.BoardRepository;
import com.example.demo.user.domain.repository.FileRepository;
import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.dto.FileDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardService {

  private BoardRepository boardRepository;

  private FileRepository fileRepository;

  // 글등록
  public String boardSave(BoardDto boardDto){

    boardRepository.save(boardDto.toEntity());
    String boardSeq = boardRepository.findByMaxSeq();

    return boardSeq;
  }


  public Page<Board> findBoardList(Pageable pageable) {
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        pageable.getPageSize(), Sort.by("boardSeq").descending());
    return boardRepository.findAll(pageable);
  }

  public void fileSave(FileDto fileDto) {
    fileRepository.save(fileDto.toEntity());
  }

  public Board findByBoardSeq(int boardSeq) {
    return boardRepository.findByBoardSeq(boardSeq);
  }

  public Board findPrevBoardSeq(int boardSeq){
    return boardRepository.findPrevBoardSeq(boardSeq);
  }

  public Board findNextBoardSeq(int boardSeq){
    return boardRepository.findNextBoardSeq(boardSeq);
  }
}
