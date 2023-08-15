package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.initializers.ServicesInit;


public class ServerApp {
    public static void main(String[] args){
        ServicesInit.init();
        Socket client;
        int count = 0;
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try(ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Listening on port 8000...");
            while(true){
                client = server.accept();
                count++;
                System.out.println("Client " + count + " accepted!");
                Runnable clientHandler = new ClientHandler(client);
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}