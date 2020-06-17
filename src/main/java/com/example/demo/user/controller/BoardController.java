package com.example.demo.user.controller;

import com.example.demo.common.CommonUtils;
import com.example.demo.common.FileUtils;
import com.example.demo.user.domain.entity.Board;
import com.example.demo.user.domain.entity.FileInfo;
import com.example.demo.user.dto.BoardDto;
import com.example.demo.user.dto.FileDto;
import com.example.demo.user.service.BoardService;
import com.example.demo.user.service.FileService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

  private FileService fileService;

  @Resource(name = "fileUtils")
  private FileUtils fileUtils;

  //게시글 목록 보기
  @GetMapping("/board/list")
  public String list(@PageableDefault Pageable pageable, Model model
      , @RequestParam(value = "searchType", required = false) String searchType
      , @RequestParam(value = "searchKeyword", required = false) String searchKeyword
      , @RequestParam(value = "viewLow", required = false) String viewLow) {

    Map<String, String> searchMap = new HashMap<String, String>();

    if (searchType != null && searchKeyword != null && searchKeyword != "") {
      searchMap.put(searchType, searchKeyword);
    }
    if(viewLow == null || viewLow == ""){
      viewLow = "10";
    }

    Page<Board> boardList = boardService.findBoardList(pageable, searchMap, Integer.parseInt(viewLow));

  //  List<Board> qBoardList = boardService.qFindBoardList(pageable, searchType, searchKeyword);

    List<BoardDto> board = new ArrayList<BoardDto>();

    for (Board list : boardList.getContent()) {
      BoardDto boardDto = new BoardDto();
      boardDto.setBoardSeq(list.getBoardSeq());
      boardDto.setBoardPW(list.getBoardPW());
      boardDto.setContact(list.getContact());
      boardDto.setContent(list.getContent());
      boardDto.setCreatedDate(list.getCreatedDate());
      boardDto.setDelDate(list.getDelDate());
      boardDto.setDepartment(list.getDepartment());
      boardDto.setFileInfoList(list.getFileInfoList());
      boardDto.setIsDel(list.getIsDel());
      boardDto.setModifiedDate(list.getModifiedDate());
      boardDto.setReadCnt(list.getReadCnt());
      boardDto.setTitle(list.getTitle());
      boardDto.setWriter(list.getWriter());
      board.add(boardDto);
    }

    int pageNum = pageable.getPageNumber();
    if (pageNum == 0) {
      pageNum = 1;
    }
    int totalCnt = (int) boardList.getTotalElements();
    int getSize = board.size();
    int dataNum = CommonUtils.getDataNum(Integer.parseInt(viewLow), pageNum, totalCnt);

    for (int i = 0; i < getSize; i++) {
      board.get(i).setRowNum(dataNum);
      dataNum--;
    }
    model.addAttribute("viewLow", viewLow);
    model.addAttribute("searchType", searchType);
    model.addAttribute("searchKeyword", searchKeyword);
    model.addAttribute("board", board);
    model.addAttribute("boardList", boardList);

    return "board/list";
  }

  //글쓰기 화면 이동
  @GetMapping("/board/write")
  public String write() {
    return "board/write";
  }

  //글 등록
  @PostMapping("/board/save")
  public String boardSave(BoardDto boardDto, HttpServletRequest request) throws Exception {

    //게시판 종류에 따른 분기처리 진행예정
    //게시판 로그 기록 처리 예정

    FileDto fileDto = new FileDto();
    List<Map<String, Object>> file_list = fileUtils
        .parseInsertFileInfo(request, "files[]", "board", true);
    String boardSeq = boardService.boardSave(boardDto);
    boardDto.setBoardSeq(Integer.parseInt(boardSeq));

    for (int i = 0; i < file_list.size(); i++) {
      fileDto.setBoardSeq(Integer.parseInt(boardSeq));
      //file.setGidx(searchVO.getGidx());
      fileDto.setOriginalFileName((String) file_list.get(i).get("ORIGINAL_FILE_NAME"));
      fileDto.setStoredFileName((String) file_list.get(i).get("STORED_FILE_NAME"));
      fileDto.setFileSize(file_list.get(i).get("FILE_SIZE") + "");
      fileDto.setFilePath((String) file_list.get(i).get("FILE_PATH"));
      fileService.fileSave(fileDto);
    }

    return "redirect:/board/list";
  }

  //상세보기 화면 이동
  @GetMapping("/board/detail")
  public String detail(@RequestParam String boardSeq, Model model) {

    Board detailBoard = boardService.findByBoardSeq(Integer.parseInt(boardSeq));
    Board prevBoard = boardService.findPrevBoardSeq(Integer.parseInt(boardSeq));
    Board nextBoard = boardService.findNextBoardSeq(Integer.parseInt(boardSeq));

    model.addAttribute("detailBoard", detailBoard);
    model.addAttribute("prevBoard", prevBoard);
    model.addAttribute("nextBoard", nextBoard);

    return "board/detail";
  }

  //글 수정 화면 이동
  @PostMapping("/board/boardEdit")
  public String boardEdit(@RequestParam String boardSeq, Model model) {

    Board detailBoard = boardService.findByBoardSeq(Integer.parseInt(boardSeq));
    model.addAttribute("detailBoard", detailBoard);
    model.addAttribute("fileFlag", "edit");

    return "board/write";
  }

  //글 수정
  @PostMapping("/board/editProc")
  @ResponseBody
  public JSONObject editProc(BoardDto boardDto, HttpServletRequest request) throws Exception {
    JSONObject json = new JSONObject();

    //게시판 종류에 따른 분기처리 진행예정
    //게시판 로그 기록 처리 예정

    FileDto fileDto = new FileDto();

    boardService.boardUpdate(boardDto);

    List<Map<String, Object>> file_list = fileUtils
        .parseInsertFileInfo(request, "files[]", "board", true);

    for (int i = 0; i < file_list.size(); i++) {
      fileDto.setBoardSeq(boardDto.getBoardSeq());
      fileDto.setOriginalFileName((String) file_list.get(i).get("ORIGINAL_FILE_NAME"));
      fileDto.setStoredFileName((String) file_list.get(i).get("STORED_FILE_NAME"));
      fileDto.setFileSize(file_list.get(i).get("FILE_SIZE") + "");
      fileDto.setFilePath((String) file_list.get(i).get("FILE_PATH"));
      fileService.fileSave(fileDto);
    }

    json.put("success", "true");

    return json;
  }

  //다운로드 삭제
  @PostMapping("/board/deleteFile")
  @ResponseBody
  public String deleteFile(@RequestParam int fileSeq) {
    fileService.deleteFile(fileSeq);
    String result = "{\"result\":\"1\"}";
    return result;
  }

  //게시글 삭제
  @PostMapping("/board/deleteBoard")
  @ResponseBody
  public String deleteBoard(@RequestParam int boardSeq) {

    boardService.deleteBoard(boardSeq);
    String result = "{\"result\":\"1\"}";
    return result;
  }

  //게시글 다운로드
  @GetMapping("/board/download")
  public void boardDownload(@RequestParam int fileSeq, HttpServletRequest request, Model model,
      HttpServletResponse response) throws Exception {
    FileInfo fileInfo = fileService.selectFile(fileSeq);

    fileUtils.DownloadFile("board", fileInfo, response, request);
  }

}
