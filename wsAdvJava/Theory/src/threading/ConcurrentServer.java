package threading;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConcurrentServer {
	public static void main(String[] args) throws IOException {
		int PORT = 12345;
		try (
			var ss = new ServerSocket(PORT);
		) {
			while (true) {
				// dispatcher thread
				var s = ss.accept();
				new Thread(() -> {
					talkToClient(s);
				}).start();
			}
		}

	}

	private static void talkToClient(Socket s) {
		try (s) {
			var sc = new Scanner(s.getInputStream());
			var pw = new PrintWriter(s.getOutputStream());

			String line = sc.nextLine();
			pw.println(line);
			pw.flush();
		} catch (IOException e) {
			// not happening
		}
	}
}
