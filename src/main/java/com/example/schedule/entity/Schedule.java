package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String task;
    private String author;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Schedule(String task, String author, String password) {
        this.task = task;
        this.author = author;
        this.password = password;
    }
}
