package com.example.demo.common;

public class CommonUtils {

  /**
   * 데이터의 번호를 주기위해 번호를 계산해오는 Method <br>
   * : 게시판 목록 같은 경우 페이지별 데이터의 번호를 계산해 주는 메소드. <br>
   *
   */
  public static int getDataNum(int token, int page, int allPage){
    if(allPage<=token){
      return allPage;
    }
    int num = allPage - (token*page) + token;
    return num;
  }

}
