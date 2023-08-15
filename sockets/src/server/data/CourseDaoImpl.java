package server.data;

import server.models.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDaoImpl implements CourseDao{
    @Override
    public void save(Course course) {
        String sql = "INSERT INTO course(name, teacher_id) VALUES(?, ?)";
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getTeacherId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course findByName(String courseName) {
        String sql = "SELECT * from course WHERE name=?";
        Course course = null;
        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                course = new Course(courseName, rs.getInt(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return course;
    }
}
