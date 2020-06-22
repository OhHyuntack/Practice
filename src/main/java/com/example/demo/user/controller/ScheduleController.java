package com.example.demo.user.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class ScheduleController {

  @GetMapping("/schedule/calendar")
  public String schedule(){ return "schedule/calendar"; }


  @GetMapping("/schedule/data.json")
  @ResponseBody
    public Object scheduleSelect(){
    JSONParser parser = new JSONParser();
    Object obj = null;
    try {
      obj = parser.parse(
          new FileReader("C:/Users/htoh/demo/src/main/resources/templates/schedule/data.json"));

     /* ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
      testDtoList =
          mapper.convertValue(obj, TypeFactory
              .defaultInstance().constructCollectionType(java.util.List.class, obj.getClass()));*/
      System.out.println("1");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return obj;
  }
}

