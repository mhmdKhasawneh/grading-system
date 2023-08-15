package com.example.atypon.spring.controllers;

import com.example.atypon.spring.models.Course;
import com.example.atypon.spring.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        try {
            courseService.addCourse(course);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to add course. Double check the entered ID",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Successfully added course", HttpStatus.OK);
    }
}
