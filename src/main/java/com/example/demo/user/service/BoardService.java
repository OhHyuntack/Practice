package com.example.demo.user.service;

import com.example.demo.common.BoardSpecification;
import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.domain.entity.FileInfo;
import com.example.demo.user.domain.repository.BoardRepository;
import com.example.demo.user.domain.repository.FileRepository;
import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.dto.FileDto;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
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

  //리스트 목록 보기
  public Page<Board> findBoardList(Pageable pageable, Map<String, String> searchMap) {

    String del = String.valueOf('N');
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        pageable.getPageSize(), Sort.by("boardSeq").descending());

    if(searchMap.size() == 0){
      return boardRepository.findAll(pageable);
    }else{
      return boardRepository.findAll(BoardSpecification.searchBoard(searchMap), pageable);
    }
  }

  //파일 저장
  public void fileSave(FileDto fileDto) { fileRepository.save(fileDto.toEntity()); }

  //상세보기
  public Board findByBoardSeq(int boardSeq) { return boardRepository.findByBoardSeq(boardSeq); }

  //이전글
  public Board findPrevBoardSeq(int boardSeq){
    return boardRepository.findPrevBoardSeq(boardSeq);
  }

  //다음글
  public Board findNextBoardSeq(int boardSeq){ return boardRepository.findNextBoardSeq(boardSeq); }

  //다운로드 삭제
  @Transactional
  public void deleteFile(int fileSeq){ fileRepository.deleteByFileSeq(fileSeq); }

  //게시글 삭제
  @Transactional
  public void deleteBoard(int boardSeq) { boardRepository.deleteUpdate(boardSeq); }

  //게시글 수정
  public void boardUpdate(BoardDto boardDto) { boardRepository.boardUpdate(boardDto); }

  public FileInfo selectFile(int fileSeq) {
    return fileRepository.findByFileSeq(fileSeq);
  }
}
