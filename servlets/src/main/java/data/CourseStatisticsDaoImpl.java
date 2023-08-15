package data;

import models.CourseStatistics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStatisticsDaoImpl implements CourseStatisticsDao{
    @Override
    public void save(CourseStatistics courseStatistics) {
        String sql = "INSERT INTO course_statistics(course_name) VALUES(?)";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, courseStatistics.getCourseName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CourseStatistics findByCourseName(String courseName) {
        CourseStatistics courseStatistics = null;
        String sql = "SELECT max, min, average FROM course_statistics WHERE course_name=?";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1));
                courseStatistics = new CourseStatistics(courseName, rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseStatistics;
    }

    @Override
    public void updateCourseStatistics(CourseStatistics courseStatistics) {
        String sql = "UPDATE course_statistics SET max=?, min=?, average=? WHERE course_name=?";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, courseStatistics.getMax());
            stmt.setInt(2, courseStatistics.getMin());
            stmt.setInt(3, courseStatistics.getAvg());
            stmt.setString(4, courseStatistics.getCourseName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
