package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    Schedule findScheduleByIdOrElseThrow(Long id);
    List<ScheduleResponseDto> findAllSchedules(String updatedAt, String author);
    int updateSchedule(Long id, String task, String author);
    int deleteSchedule(Long id);
}
