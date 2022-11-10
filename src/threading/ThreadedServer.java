package threading;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadedServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int PORT = 12345;
        try (
                var ss = new ServerSocket(PORT);
        ) {
           while (true) {
               try (
                       var s = ss.accept();
                       var sc = new Scanner(s.getInputStream());
                       var pw = new PrintWriter(s.getOutputStream());
                       ) {
                   int waitSeconds = 1;
                   Thread.sleep(1000 * waitSeconds);

                   var msg = "sdasd";
                   System.out.println("Sending the message");

                   pw.println("echo " + msg);
                   pw.flush();

                   String answer = sc.nextLine();
                   System.out.println(answer);

                   //talkToClient(s, sc, pw);
               }
           }
        }
    }

    private static void talkToClient(Socket s, Scanner sc, PrintWriter pw) {

    }
}
