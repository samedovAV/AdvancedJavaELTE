package solutions.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BookStoreClient {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		try (
			var s = new Socket("localhost", 12345);
			var oos = new ObjectOutputStream(s.getOutputStream());
			var ois = new ObjectInputStream(s.getInputStream());
		) {
			Thread.sleep(1000);
			oos.writeObject("c");
			oos.flush();

			var gotTheBook = (Boolean)ois.readObject();
			if (gotTheBook) {
				var book = (Book)ois.readObject();
				System.out.println(book);
			}
		}
	}
}
