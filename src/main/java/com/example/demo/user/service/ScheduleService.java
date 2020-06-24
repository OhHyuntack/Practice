package com.example.demo.user.service;


import com.example.demo.user.domain.entity.Schedule;
import com.example.demo.user.domain.repository.ScheduleRepository;
import com.example.demo.user.dto.ScheduleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ScheduleService {

  private ScheduleRepository scheduleRepository;


  public void save(ScheduleDto scheduleDto) {

    scheduleRepository.save(scheduleDto.toEntity());

  }

  public Object findSchduleList() {

    Object obj = null;
    List<Schedule> list = new ArrayList<Schedule>();
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    cal.add(Calendar.MONTH, -12);
    String searchStartDate = df.format(cal.getTime());
    cal.add(Calendar.MONTH, 24);
    String searchEndDate = df.format(cal.getTime());

    list = scheduleRepository.findByStartBetween(searchStartDate, searchEndDate);

    String jsonString = "[";
    int i = 0;
    JSONArray jsonArray = new JSONArray();
    for(Schedule schedule : list){

      /*JSONObject jsonObject = new JSONObject();
      jsonObject.put("_id", schdule.getScheduleSeq());
      jsonObject.put("title", schdule.getTitle());
      jsonObject.put("description", schdule.getDescription());
      jsonObject.put("start", schdule.getStart());
      jsonObject.put("end", schdule.getEnd());
      jsonObject.put("type", schdule.getType());
      jsonObject.put("username", schdule.getUserName());
      jsonObject.put("backgroundColor", schdule.getBackgroundColor());
      jsonObject.put("textColor", schdule.getTextColor());
      jsonObject.put("allDay", schdule.getAllDay());

      jsonArray.add(jsonObject);*/

      if(i >= 1){
        jsonString += ",";
      }

      int scheduleSeq = schedule.getScheduleSeq();
      String title = schedule.getTitle();
      String description = schedule.getDescription();
      LocalDateTime start = schedule.getStart();
      LocalDateTime end = schedule.getEnd();
      String type = schedule.getType();
      String username = schedule.getUserName();
      String backgroundColor = schedule.getBackgroundColor();
      String textColor = schedule.getTextColor();
      String allDay = schedule.getAllDay();

      jsonString += "{\"_id\":"+scheduleSeq+",\"title\":\""+title+"\",\"description\":\""+description+"\",\"start\":\""+start+"\",\"end\":\""+end+"\""
          + ",\"type\":\""+type+"\",\"username\":\""+username+"\",\"backgroundColor\":\""+backgroundColor+"\",\"textColor\":\""+textColor+"\""
          + ",\"allDay\":"+allDay+"}";
      i++;
    }

    jsonString += "]";

    JSONParser parser = new JSONParser();

    try {
       obj = parser.parse(jsonString);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return obj;
  }

  public void editSchedule(ScheduleDto scheduleDto) {

    scheduleDto.setStart(LocalDateTime.parse(scheduleDto.getEditStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // 2010-11-25T12:30
    scheduleDto.setEnd(LocalDateTime.parse(scheduleDto.getEditEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); // 2010-11-25T12:30

    int seq = scheduleDto.getScheduleSeq();

    Schedule selectSchedule = scheduleRepository.findByScheduleSeq(seq);

    selectSchedule.setUserName(scheduleDto.getUserName());
    selectSchedule.setStart(scheduleDto.getStart());
    selectSchedule.setUserName(scheduleDto.getUserName());
    selectSchedule.setBackgroundColor(scheduleDto.getBackgroundColor());
    selectSchedule.setDescription(scheduleDto.getDescription());
    selectSchedule.setModifiedDate(LocalDateTime.now());
    selectSchedule.setModifiedId(scheduleDto.getUserId());

    scheduleRepository.save(selectSchedule);

  }
}
