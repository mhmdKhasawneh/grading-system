package data;

import models.CourseStatistics;

public interface CourseStatisticsDao {
    void save(CourseStatistics courseStatistics);
    CourseStatistics findByCourseName(String courseName);
    void updateCourseStatistics(CourseStatistics courseStatistics);
}
