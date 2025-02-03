package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    //의존성 주입
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTask(), dto.getAuthor(), dto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String updatedAt, String author) {
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules(updatedAt, author);
        return allSchedules;
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String task, String author, String password) {
        if (task == null || author == null) {
            System.out.println("task = " + task + ", author = " + author + ", password = " + password);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task와 author 모두 입력되어야 합니다.");
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        if(!schedule.getPassword().equals(password)) {
            System.out.println("2");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password가 일치하지 않습니다.");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, task, author);
        if (updatedRow == 0) {
            System.out.println("3");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id가 존재하지 않습니다.");
        }

        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(updatedSchedule);
    }
}
