package com.example.demo.user.dto;

import com.example.demo.user.domain.entity.Schedule;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScheduleDto {

    private int scheduleSeq;
    private String title;
    private String description;
    private String userName;
    private String userId;
    private LocalDateTime start;
    private LocalDateTime end;
    private String type;
    private String textColor;
    private LocalDateTime regDate;
    private String isDel;
    private LocalDateTime delDate;
    private LocalDateTime modifiedDate;
    private String modifiedId;
    private String backgroundColor;
    private String allDay;
    private String editStart;
    private String editEnd;
    private int _id;

  public Schedule toEntity(){
    Schedule schdule = Schedule.builder()
        .scheduleSeq(scheduleSeq)
        .title(title)
        .description(description)
        .userName(userName)
        .userId(userId)
        .start(start)
        .end(end)
        .type(type)
        .textColor(textColor)
        .regDate(regDate)
        .isDel(isDel)
        .delDate(delDate)
        .modifiedDate(modifiedDate)
        .modifiedId(modifiedId)
        .backgroundColor(backgroundColor)
        .allDay(allDay)
        .build();
    return schdule;
  }
  @Builder
  public ScheduleDto(int scheduleSeq, String title, String description, String userName, String userId,
      LocalDateTime start, LocalDateTime end, String type, String textColor, LocalDateTime regDate, String isDel
      , LocalDateTime delDate, LocalDateTime modifiedDate, String modifiedId, String backgroundColor, String allDay) {
    this.scheduleSeq = scheduleSeq;
    this.title = title;
    this.description = description;
    this.userName = userName;
    this.userId = userId;
    this.start = start;
    this.end = end;
    this.type = type;
    this.textColor = textColor;
    this.regDate = regDate;
    this.isDel = isDel;
    this.delDate = delDate;
    this.modifiedDate = modifiedDate;
    this.modifiedId = modifiedId;
    this.backgroundColor = backgroundColor;
    this.allDay = allDay;
  }

}
