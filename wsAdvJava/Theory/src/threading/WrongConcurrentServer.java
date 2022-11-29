package threading;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WrongConcurrentServer {
	public static void main(String[] args) throws IOException {
		int PORT = 12345;
		try (
			var ss = new ServerSocket(PORT);
		) {
			while (true) {
				new Thread(() -> {
					try (
						var s = ss.accept();
						var sc = new Scanner(s.getInputStream());
						var pw = new PrintWriter(s.getOutputStream());
					) {
						talkToClient(s, sc, pw);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
			}
		}

	}

	private static void talkToClient(Socket s, Scanner sc, PrintWriter pw) {
		String line = sc.nextLine();
		pw.println(line);
		pw.flush();
	}
}
