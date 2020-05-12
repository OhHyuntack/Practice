package com.example.demo.user.controller;

import com.example.demo.common.FileUtils;
import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.dto.FileDto;
import com.example.demo.user.service.BoardService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class BoardController {

  private BoardService boardService;

  @Resource(name="fileUtils")
  private FileUtils fileUtils;

  @GetMapping("/board/list")
  public String list(@PageableDefault Pageable pageable, Model model){
    Page<Board> boardList = boardService.findBoardList(pageable);
    model.addAttribute("boardList", boardList);

    return "board/list";
  }

  @GetMapping("/board/write")
  public String write(){ return "board/write"; }

  @PostMapping("/board/save")
  public String boardSave(BoardDto boardDto, HttpServletRequest request) throws Exception {
    System.out.println("1");

    FileDto fileDto = new FileDto();
    List<Map<String, Object>> file_list = fileUtils.parseInsertFileInfo(request, "files[]", "board", true);
    String boardSeq = boardService.boardSave(boardDto);
    boardDto.setBoardSeq(Integer.parseInt(boardSeq));

    for(int i=0; i<file_list.size();i++){
      fileDto.setBoardSeq(Integer.parseInt(boardSeq));
      //file.setGidx(searchVO.getGidx());
      fileDto.setOriginalFileName((String) file_list.get(i).get("ORIGINAL_FILE_NAME"));
      fileDto.setStoredFileName((String) file_list.get(i).get("STORED_FILE_NAME"));
      fileDto.setFileSize(file_list.get(i).get("FILE_SIZE")+"");
      fileDto.setFilePath((String) file_list.get(i).get("FILE_PATH"));
      boardService.fileSave(fileDto);
    }

    return "redirect:/board/list";
  }

  @GetMapping("/board/detail")
  public String detail(@RequestParam String boardSeq, Model model){

   Board detailBoard = boardService.findByBoardSeq(Integer.parseInt(boardSeq));
   Board prevBoard = boardService.findPrevBoardSeq(Integer.parseInt(boardSeq));
   Board nextBoard = boardService.findNextBoardSeq(Integer.parseInt(boardSeq));

    model.addAttribute("detailBoard", detailBoard);
    model.addAttribute("prevBoard", prevBoard);
    model.addAttribute("nextBoard", nextBoard);

   return "board/detail";
  }

  @PostMapping("/board/boardEdit")
  public String boardEdit(@RequestParam String boardSeq, Model model){

    Board detailBoard = boardService.findByBoardSeq(Integer.parseInt(boardSeq));
    model.addAttribute("detailBoard", detailBoard);
    model.addAttribute("fileFlag", "edit");

    return "board/write";
  }

  @PostMapping("/board/editProc")
  @ResponseBody
  public JSONObject editProc(BoardDto boardDto, HttpServletRequest request)throws Exception {
    JSONObject json = new JSONObject();

    //List<Map<String, Object>> file_list = fileUtils.parseInsertFileInfo(request, "files[]", "board", true);


    return json;
  }

  @PostMapping("/board/deleteFile")
  @ResponseBody
  public String deleteFile(@RequestParam int fileSeq){
    boardService.deleteFile(fileSeq);
    String result="{\"result\":\"1\"}";
    return result;
  }

}
