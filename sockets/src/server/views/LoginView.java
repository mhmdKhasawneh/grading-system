package server.views;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class LoginView {
    private DataOutputStream outStream;
    private BufferedReader br;

    public LoginView(DataOutputStream outStream, BufferedReader br){
        this.outStream = outStream;
        this.br = br;
    }
    public void show() throws IOException {
        System.out.println("Enter email:");
        String email = br.readLine();
        outStream.writeUTF(email);
        System.out.println("Enter password:");
        String password = br.readLine();
        outStream.writeUTF(password);
    }
}
