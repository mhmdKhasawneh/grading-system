package com.example.atypon.spring.controllers;

import com.example.atypon.spring.security.CustomUserDetails;
import com.example.atypon.spring.models.CourseStatistics;
import com.example.atypon.spring.services.CourseStatisticsService;
import com.example.atypon.spring.services.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/coursestatistics")
public class CourseStatisticsController {
    @Autowired
    private CourseStatisticsService courseStatisticsService;
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/get/{courseName}")
    public ResponseEntity<?> getCourseStatistics(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable String courseName){
        int studentId = userDetails.getUserId();

        if(studentCourseService.isEnrolled(studentId, courseName)){
            CourseStatistics courseStatistics = null;
            try {
                courseStatistics = courseStatisticsService.getCourseStatistics(courseName);
            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch(EmptyResultDataAccessException e){
                e.printStackTrace();
                return new ResponseEntity<>("Course not found", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(courseStatistics, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
