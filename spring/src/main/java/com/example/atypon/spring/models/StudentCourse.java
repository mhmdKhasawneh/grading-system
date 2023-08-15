package com.example.atypon.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StudentCourse {
    private int studentId;
    private String courseName;
    private int mark;
}
