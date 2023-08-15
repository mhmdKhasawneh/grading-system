package data;

import models.Course;

public interface CourseDao {
    void save(Course course);
    Course findByName(String courseName);
}
