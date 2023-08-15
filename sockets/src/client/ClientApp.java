package client;

import java.io.*;
import java.net.Socket;
import java.util.Map;

import server.views.*;
import server.models.Role;

public class ClientApp {
    private final Socket client;
    private final DataOutputStream outStream;
    private final ObjectOutputStream objectOutStream;
    private final ObjectInputStream objectInStream;
    private final BufferedReader br;

    public ClientApp() throws IOException {
        client = new Socket("localhost", 8000);
        outStream = new DataOutputStream(client.getOutputStream());
        objectOutStream = new ObjectOutputStream(client.getOutputStream());
        objectInStream = new ObjectInputStream(client.getInputStream());
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void serve() throws IOException, ClassNotFoundException {
        LoginView loginView = new LoginView(outStream, br);
        loginView.show();

        Map<String,String> data = attemptLogin(loginView);

        System.out.println("Welcome " + data.get("user_name"));

        String role = data.get("role_name");
        int id = Integer.parseInt(data.get("user_id"));
        renderViewFor(role ,id);

        client.close();
        outStream.close();
        objectOutStream.close();
        objectInStream.close();
        br.close();
    }

    private Map<String,String> attemptLogin(LoginView loginView) throws IOException, ClassNotFoundException {
        Map<String,String> data = (Map<String, String>) objectInStream.readObject();

        while (data.containsKey("Error")){
            System.out.println("Incorrect credentials");
            loginView.show();
            data = (Map<String, String>) objectInStream.readObject();
        }
        return data;
    }

    private void renderViewFor(String role, int id){
        if(role.equalsIgnoreCase(Role.ADMIN.toString())){
            AdminView adminView = new AdminView(outStream, objectOutStream, br);
            while(true) {
                adminView.listen();
            }
        }
        else if(role.equalsIgnoreCase(Role.STUDENT.toString())){
            StudentView studentClient = new StudentView(id, outStream, objectInStream, br);
            while(true){
                studentClient.listen();
            }
        }
        else if(role.equalsIgnoreCase(Role.TEACHER.toString())){
            TeacherView teacherClient = new TeacherView(outStream, objectInStream, br);
            while(true){
                teacherClient.listen();
            }
        }
    }
}