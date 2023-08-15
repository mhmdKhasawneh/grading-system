package server.data;

import server.models.CourseStatistics;

public interface CourseStatisticsDao {
    void save(CourseStatistics courseStatistics);
    CourseStatistics findByCourseName(String courseName);
    void updateCourseStatistics(CourseStatistics courseStatistics);
}
