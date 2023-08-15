package server.data;

import server.models.StudentCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDaoImpl implements StudentCourseDao{
    @Override
    public void save(StudentCourse studentCourse) {
        String sql = "INSERT INTO student_course(student_id, course_name, mark) VALUES(?, ?, 0)";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, studentCourse.getStudentId());
            stmt.setString(2, studentCourse.getCourseName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudentCourse(StudentCourse studentCourse) {
        String sql = "UPDATE student_course SET mark=? WHERE student_id=? AND course_name=?";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, studentCourse.getMark());
            stmt.setInt(2, studentCourse.getStudentId());
            stmt.setString(3, studentCourse.getCourseName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentCourse> getGradesFor(int studentId) {
        List<StudentCourse> studentCourse = new ArrayList<>();
        String sql = "SELECT student_id, course_name, mark FROM student_course WHERE student_id=?";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                studentCourse.add(new StudentCourse(rs.getInt(1),rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentCourse;
    }

    @Override
    public int findMaxMarkFor(String courseName) {
        String sql = "SELECT MAX(mark) AS max_mark FROM student_course WHERE course_name=?";
        int max = -1;
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                max = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }

    @Override
    public int findMinMarkFor(String courseName) {
        String sql = "SELECT MIN(mark) AS min_mark FROM student_course WHERE course_name=?";
        int min = -1;
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                min = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return min;
    }

    @Override
    public int findAvgMarkFor(String courseName) {
        String sql = "SELECT AVG(mark) AS avg_mark FROM student_course WHERE course_name=?";
        int avg = -1;
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                avg = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avg;
    }
}
