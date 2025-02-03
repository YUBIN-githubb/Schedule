package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter

public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String author;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    public ScheduleResponseDto(Long id, String task, String author, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.task = task;
        this.author = author;
        this.password = password;
        this.createdAt = createdAt.toLocalDate();  // LocalDate로 변환
        this.updatedAt = updatedAt.toLocalDate();  // LocalDate로 변환
    }

    public ScheduleResponseDto (Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.author = schedule.getAuthor();
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

}
