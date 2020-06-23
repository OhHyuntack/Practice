package com.example.demo.user.service;


import com.example.demo.user.domain.repository.ScheduleRepository;
import com.example.demo.user.dto.ScheduleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ScheduleService {

  private ScheduleRepository scheduleRepository;


  public void save(ScheduleDto scheduleDto) {

    scheduleRepository.save(scheduleDto.toEntity());

  }
}
