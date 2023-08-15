package client;

import java.io.IOException;

public class ClientTwo {
    public static void main(String[] args) throws IOException {
        ClientApp clientApp = new ClientApp();
        try {
            clientApp.serve();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
