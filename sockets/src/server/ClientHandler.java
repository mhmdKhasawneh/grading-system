package server;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import server.initializers.ServicesInit;
import server.models.*;
import server.services.*;
import server.endpoints.Endpoints;

public class ClientHandler implements Runnable {
    private Socket client;
    private UserService userService;
    private CourseService courseService;
    private StudentCourseService studentCourseService;
    private CourseStatisticsService courseStatisticsService;
    ObjectOutputStream objectOutStream;
    DataInputStream inStream;
    ObjectInputStream objectInStream;
    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.userService = ServicesInit.getUserService();
        this.courseService = ServicesInit.getCourseService();
        this.studentCourseService = ServicesInit.getStudentCourseService();
        this.courseStatisticsService = ServicesInit.getCourseStatisticsService();
        this.objectOutStream = new ObjectOutputStream(client.getOutputStream());
        this.inStream = new DataInputStream(client.getInputStream());
        this.objectInStream = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run(){
        Map<String, String> data;
        try {
            String email = inStream.readUTF();
            String password = inStream.readUTF();
            data = userService.login(email, password);
            objectOutStream.writeObject(data);

            while(data.containsKey("Error")){
                email = inStream.readUTF();
                password = inStream.readUTF();
                data = userService.login(email, password);
                objectOutStream.writeObject(data);
            }
            listenForRequests(Integer.parseInt(data.get("user_id")));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void listenForRequests(int fromId) throws IOException, ClassNotFoundException, SQLException {
        while(true){
            String action = inStream.readUTF();
            if(action.equalsIgnoreCase(Endpoints.ADD_USER.toString())){
                User user = (User) objectInStream.readObject();
                userService.addUser(user);
            }
            else if(action.equalsIgnoreCase(Endpoints.ADD_COURSE.toString())){
                String courseName = inStream.readUTF();
                int teacherId = inStream.readInt();
                courseService.addCourse(new Course(courseName, teacherId));
            }
            else if(action.equalsIgnoreCase(Endpoints.ENROLL.toString())) {
                String courseName = inStream.readUTF();
                try {
                    studentCourseService.enroll(new StudentCourse(fromId, courseName, 0));
                } catch (IllegalArgumentException e){
                    System.out.println("Course does not exist");
                }
            }
            else if(action.equalsIgnoreCase(Endpoints.GET_GRADES.toString())){
                int studentId = inStream.readInt();
                List<StudentCourse> studentCourse = studentCourseService.getGrades(studentId);
                List<CourseStatistics> courseStatistics = new ArrayList<>();
                for(StudentCourse st : studentCourse){
                    courseStatistics.add(courseStatisticsService.getCourseStatistics(st.getCourseName()));
                }
                objectOutStream.writeObject(studentCourse);
                objectOutStream.writeObject(courseStatistics);
            }
            else if(action.equalsIgnoreCase(Endpoints.ENTER_MARK.toString())){
                String courseName = inStream.readUTF();
                int studentId = inStream.readInt();
                int mark = inStream.readInt();
                studentCourseService.updateMark(new StudentCourse(studentId, courseName, mark));
            }
        }
    }
}
