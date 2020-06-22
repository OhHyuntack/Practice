package com.example.demo.user.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "schedule", uniqueConstraints = {@UniqueConstraint(columnNames = {"schedule_seq"})})
public class Schedule {

  // seq
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="schedule_seq")
  private int schduleSeq;

  // 제목
  @Column(name="title", length = 200)
  private String title;
  // 내용
  @Column(name="description", length = 900)
  private String description;
  // 글쓴이
  @Column(name="user_name", length = 50)
  private String userName;
  // 등록아이디
  @Column(name="user_id", length = 50)
  private String userId;

  @Column(name="start")
  private LocalDateTime start;

  @Column(name="end")
  private LocalDateTime end;

  @Column(name="type", length = 10)
  private String type;

  @Column(name="text_color", length = 10)
  private String textColor;

  @Column(name="regdate")
  private LocalDateTime regDate;

  @Column(name="modified_id", length = 50)
  private String modifiedId;

  @Column(name="modified_date")
  private LocalDateTime modifiedDate;

  @Column(name="is_del", length = 2)
  private String isDel;

  @Column(name="del_date")
  private LocalDateTime delDate;

  @Column(name="background_color")
  private String backgroundColor;

  @Column(name="all_day", length = 10)
  private String allDay;


  @Builder
  public Schedule(int schduleSeq, String title, String description, String userName, String userId, LocalDateTime start, LocalDateTime end, String type, String textColor, LocalDateTime regDate, String isDel, LocalDateTime delDate,
      String modifiedId, LocalDateTime modifiedDate,String backgroundColor, String allDay) {
    this.schduleSeq = schduleSeq;
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
