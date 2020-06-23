package com.example.demo.user.controller;

import com.example.demo.user.domain.entity.Schedule;
import com.example.demo.user.dto.ScheduleDto;
import com.example.demo.user.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class ScheduleController {

  private ScheduleService scheduleService;

  @GetMapping("/schedule/calendar")
  public String schedule(){ return "schedule/calendar"; }


  @GetMapping("/schedule/data.json")
  @ResponseBody
    public Object scheduleSelect(){
    JSONParser parser = new JSONParser();
    Object obj = null;

    /*try {*/
      //obj = parser.parse(
        //  new FileReader("C:/Users/htoh/demo/src/main/resources/templates/schedule/data.json"));

      obj = scheduleService.findSchduleList();


      System.out.println("1");
 /*  } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }*/
    return obj;
  }


  @GetMapping("/schedule/createSchedule")
  @ResponseBody
  public void createSchedule(ScheduleDto scheduleDto, HttpServletRequest request,  HttpSession session){
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    loginInfo = (HashMap<String, String>)  session.getAttribute("sessionLoginInfo");

    String userId = "";

    scheduleDto.setRegDate(LocalDateTime.now());

    if(loginInfo != null){
      userId = loginInfo.get("sessionUserId");
      scheduleDto.setUserId(userId);
      scheduleDto.setUserName(loginInfo.get("sessionUserName"));
    }

    scheduleDto.setStart(LocalDateTime.parse(scheduleDto.getEditStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // 2010-11-25T12:30
    scheduleDto.setEnd(LocalDateTime.parse(scheduleDto.getEditEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // 2010-11-25T12:30


    scheduleService.save(scheduleDto);

  }
}

