package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
