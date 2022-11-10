package sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        int PORT = 12345;
        try (
                var serverSocket = new ServerSocket(PORT);
                var s = serverSocket.accept();
        ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
