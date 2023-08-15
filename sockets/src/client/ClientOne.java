package client;

import java.io.IOException;

public class ClientOne {
    public static void main(String[] args) throws IOException {
        ClientApp clientApp = new ClientApp();
        try {
            clientApp.serve();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
