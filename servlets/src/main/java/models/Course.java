package models;
public class Course {
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
