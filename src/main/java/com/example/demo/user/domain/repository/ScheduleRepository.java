package com.example.demo.user.domain.repository;

import com.example.demo.user.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
