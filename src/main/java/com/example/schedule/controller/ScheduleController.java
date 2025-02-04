package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    //의존성 주입
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    //일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    //일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam(required = false) String updatedAt,
            @RequestParam(required = false) String author
    ) {
        return new ResponseEntity<>(scheduleService.findAllSchedules(updatedAt, author), HttpStatus.OK);
    }

    //일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getTask(), dto.getAuthor(), dto.getPassword()), HttpStatus.OK);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody
    ) {
        String password = requestBody.get("password"); // "password" 키로 값 추출
        scheduleService.deleteSchedule(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
