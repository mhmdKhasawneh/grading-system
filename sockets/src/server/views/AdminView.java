package server.views;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import server.endpoints.Endpoints;
import server.models.User;
import server.models.Role;

public class AdminView implements RoleView {
    DataOutputStream outStream;
    ObjectOutputStream objectOutStream;
    BufferedReader br;

    public AdminView(DataOutputStream outStream, ObjectOutputStream objectOutStream, BufferedReader br){
        this.outStream = outStream;
        this.objectOutStream = objectOutStream;
        this.br = br;
    }
    @Override
    public void listen() {
        Scanner sc = new Scanner(System.in);

        System.out.print("[1] Add student\n[2] Add teacher\n[3] Add course");
        int choice = sc.nextInt();

        try {
            switch (choice) {
                case 1 -> {
                    outStream.writeUTF(Endpoints.ADD_USER.toString());
                    addUser(Role.STUDENT);
                }
                case 2 -> {
                    outStream.writeUTF(Endpoints.ADD_USER.toString());
                    addUser(Role.TEACHER);
                }
                case 3 -> {
                    outStream.writeUTF(Endpoints.ADD_COURSE.toString());
                    addCourse();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addUser(Role role) throws IOException {
        System.out.println("Name:");
        String name = br.readLine();
        System.out.println("Email:");
        String email = br.readLine();
        System.out.println("Password:");
        String password = br.readLine();

        objectOutStream.writeObject(new User(-1, name, email, password, role));
    }

    private void addCourse() throws IOException {
        System.out.println("Enter the course name: ");
        String name = br.readLine();
        outStream.writeUTF(name);
        System.out.println("Enter the course teacher ID: ");
        int teacherId = Integer.parseInt(br.readLine());
        outStream.writeInt(teacherId);
    }

}
