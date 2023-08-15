package server.models;
import java.io.Serializable;

public class Course implements Serializable {
    private String name;
    private int teacherId;

    public Course(String name, int teacherId) {
        this.name = name;
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public int getTeacherId() {
        return teacherId;
    }
}
