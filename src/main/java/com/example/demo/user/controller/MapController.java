package com.example.demo.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MapController {

  //지도보기
  @GetMapping("/map/main")
  public String mapMain() {
    return "map/main";
  }



}
