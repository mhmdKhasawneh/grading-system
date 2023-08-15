package com.example.atypon.spring.controllers;

import com.example.atypon.spring.security.CustomUserDetails;
import com.example.atypon.spring.models.StudentCourse;
import com.example.atypon.spring.services.CourseService;
import com.example.atypon.spring.services.StudentCourseService;
import com.example.atypon.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/studentcourse")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody StudentCourse studentCourse){
        int studentId = userDetails.getUserId();
        studentCourse.setStudentId(studentId);

        try {
            studentCourseService.enroll(studentCourse);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.BAD_REQUEST);
        } catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Successfully enrolled into course", HttpStatus.OK);
    }

    @GetMapping("/grades")
    public ResponseEntity<?> getGrades(@AuthenticationPrincipal CustomUserDetails userDetails){
        int studentId = userDetails.getUserId();

        List<StudentCourse> grades = new ArrayList<>();
        try{
            grades.addAll(studentCourseService.getGrades(studentId));
        } catch(SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }
    @PostMapping("/update-mark")
    public ResponseEntity<String> updateMark(@RequestBody StudentCourse studentCourse){
        if(!userService.isIdValid(studentCourse.getStudentId())){
            return new ResponseEntity<>("Provided ID not found in the system", HttpStatus.BAD_REQUEST);
        }
        if(!userService.isIdStudent(studentCourse.getStudentId())) {
            return new ResponseEntity<>("Provided ID does not belong to a student", HttpStatus.BAD_REQUEST);
        }
        if(courseService.getCourse(studentCourse.getCourseName()) == null){
            return new ResponseEntity<>("Provided course name does not exist", HttpStatus.BAD_REQUEST);
        }
        int mark = studentCourse.getMark();
        if(mark < 0 || mark > 100){
            return new ResponseEntity<>("Mark is not in the typical range.", HttpStatus.BAD_REQUEST);
        }
        try {
            studentCourseService.updateMark(studentCourse);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>("Course doesn't exist or student Id is incorrect", HttpStatus.BAD_REQUEST);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully updated mark", HttpStatus.OK);
    }
}
