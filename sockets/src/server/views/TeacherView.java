package server.views;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import server.endpoints.Endpoints;

public class TeacherView implements RoleView {
    private final DataOutputStream outStream;
    private final ObjectInputStream objectInStream;
    private final BufferedReader br;

    public TeacherView(DataOutputStream outStream, ObjectInputStream objectInStream, BufferedReader br) {
        this.outStream = outStream;
        this.objectInStream = objectInStream;
        this.br = br;
    }

    @Override
    public void listen() {
        Scanner sc = new Scanner(System.in);

        System.out.print("[1] Enter marks");
        int choice = sc.nextInt();

        try {
            switch (choice) {
                case 1 -> {
                    outStream.writeUTF(Endpoints.ENTER_MARK.toString());
                    enterMark();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void enterMark() throws IOException {
        System.out.println("Enter the course name");
        String courseName = br.readLine();
        outStream.writeUTF(courseName);

        System.out.println("Enter the student ID");
        int studentId = Integer.parseInt(br.readLine());
        outStream.writeInt(studentId);

        System.out.println("Enter the mark");
        int mark = Integer.parseInt(br.readLine());
        outStream.writeInt(mark);
    }
}
