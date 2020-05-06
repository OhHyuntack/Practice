package com.example.demo.user.controller;

import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.service.BoardService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class BoardController {

  private BoardService boardService;

  @GetMapping("/board/list")
  public String list(@PageableDefault Pageable pageable, Model model){
    Page<Board> boardList = boardService.findBoardList(pageable);
    model.addAttribute("boardList", boardList);

    return "board/list";
  }


  @GetMapping("/board/write")
  public String write(){

    return "board/write";
  }

  @PostMapping("/board/save")
  public String boardSave(BoardDto boardDto, @RequestParam(value="file1", required = false) MultipartFile file1,
      @RequestParam (value="file2", required = false) MultipartFile file2,
      @RequestParam (value="file3", required = false) MultipartFile file3,
      @RequestParam (value="file4", required = false) MultipartFile file4,
      @RequestParam (value="file5", required = false) MultipartFile file5) {
    JSONObject json = new JSONObject();

    String boardSeq = boardService.boardSave(boardDto);
    boardDto.setBoardSeq(Integer.parseInt(boardSeq));

   // boardService.fileSave();

    return "redirect:/board/list";

  }



}
