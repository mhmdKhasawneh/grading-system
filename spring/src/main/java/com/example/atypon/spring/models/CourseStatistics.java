package com.example.atypon.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CourseStatistics {
    private String courseName;
    private int max;
    private int min;
    private int avg;
}
