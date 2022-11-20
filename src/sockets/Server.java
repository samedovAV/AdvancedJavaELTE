package sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        int PORT = 12345;
        try (
                var serverSocket = new ServerSocket(PORT);
                Socket s = serverSocket.accept();
                var sc = new Scanner(s.getInputStream());
                var pw = new PrintWriter(s.getOutputStream());
        ) {
            //sc.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
