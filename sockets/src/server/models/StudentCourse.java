package server.models;
import java.io.Serializable;

public class StudentCourse implements Serializable {
    private int studentId;
    private String courseName;
    private int mark;

    public StudentCourse(int studentId, String courseName, int mark) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.mark = mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMark() {
        return mark;
    }
}
