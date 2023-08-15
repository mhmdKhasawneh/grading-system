package server.models;

import java.io.Serializable;

public class CourseStatistics implements Serializable {
    private String courseName;
    private int max;
    private int min;
    private int avg;

    public CourseStatistics(String courseName, int max, int min, int avg) {
        this.courseName = courseName;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "CourseStatistics{" +
                "courseName='" + courseName + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                '}';
    }
}
