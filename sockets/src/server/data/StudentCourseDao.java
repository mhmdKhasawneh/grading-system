package server.data;

import server.models.StudentCourse;

import java.util.List;

public interface StudentCourseDao {
    void save(StudentCourse studentCourse);
    void updateStudentCourse(StudentCourse studentCourse);
    List<StudentCourse> getGradesFor(int studentId);
    int findMaxMarkFor(String courseName);
    int findMinMarkFor(String courseName);
    int findAvgMarkFor(String courseName);
}
