package server.views;

import server.endpoints.Endpoints;
import server.models.CourseStatistics;
import server.models.StudentCourse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

public class StudentView implements RoleView {
    private final int studentId;
    private final DataOutputStream outStream;
    private final ObjectInputStream objectInStream;
    private final BufferedReader br;

    public StudentView(int studentId, DataOutputStream outStream, ObjectInputStream objectInStream, BufferedReader br) {
        this.studentId = studentId;
        this.outStream = outStream;
        this.objectInStream = objectInStream;
        this.br = br;
    }

    @Override
    public void listen() {
        Scanner sc = new Scanner(System.in);

        System.out.print("[1] View grades\n[2] Enroll in course");
        int choice = sc.nextInt();

        try {
            switch (choice) {
                case 1:
                    outStream.writeUTF(Endpoints.GET_GRADES.toString());
                    try {
                        getGrades(studentId);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    outStream.writeUTF(Endpoints.ENROLL.toString());
                    try {
                        enroll();
                    } catch (IOException | IllegalArgumentException e) {
                        e.printStackTrace();
                        System.out.println("Course does not exist");
                    }
                    break;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void getGrades(int studentId) throws IOException, ClassNotFoundException{
        outStream.writeInt(studentId);
        List<StudentCourse> studentCourses = (List<StudentCourse>) objectInStream.readObject();
        List<CourseStatistics> courseStatistics = (List<CourseStatistics>) objectInStream.readObject();
        int i = 0;
        for(StudentCourse studentCourse : studentCourses){
            System.out.println("For course " + studentCourse.getCourseName());
            System.out.println("Mark: " + studentCourse.getMark());
            System.out.println("Max mark: " + courseStatistics.get(i).getMax());
            System.out.println("Min mark: " + courseStatistics.get(i).getMin());
            System.out.println("Avg mark: " + courseStatistics.get(i).getAvg());
            i++;
        }
    }

    private void enroll() throws IOException {
        System.out.println("Enter course name");
        String course = br.readLine();
        outStream.writeUTF(course);
    }
}
