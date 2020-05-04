package com.example.demo.user.controller;

import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.service.BoardService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class BoardController {

  private BoardService boardService;

  @GetMapping("/board/list")
  public String list(){

    return "board/list";
  }


  @GetMapping("/board/write")
  public String write(){

    return "board/write";
  }

  @PostMapping("/board/save")
  @ResponseBody
  public JSONObject boardSave(BoardDto boardDto, @RequestParam(value="file1", required = false) MultipartFile file1,
      @RequestParam (value="file2", required = false) MultipartFile file2,
      @RequestParam (value="file3", required = false) MultipartFile file3,
      @RequestParam (value="file4", required = false) MultipartFile file4,
      @RequestParam (value="file5", required = false) MultipartFile file5) {
    JSONObject json = new JSONObject();

    String boardSeq = boardService.boardSave(boardDto);


    return json;
  }



}
