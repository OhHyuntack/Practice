package com.example.demo.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ScheduleController {

  @GetMapping("/schedule/calendar")
  public String schedule(){ return "/schedule/calendar"; }
}
