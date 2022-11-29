package solutions.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BookStoreServer {
	public static void main(String[] args) throws IOException {
		var bookStore = new BookStore();
		bookStore.add(new Book("a", 123));
		bookStore.add(new Book("b", 321));

		try (
			var ss = new ServerSocket(12345);
		) {
			while (true) {
				var s = ss.accept();
				new Thread(() -> handleClient(s, bookStore)).start();
			}
		}
	}

	private static void handleClient(Socket s, BookStore bookStore) {
		try (
			s;
			var ois = new ObjectInputStream(s.getInputStream());
			var oos = new ObjectOutputStream(s.getOutputStream());
		) {
			var bookTitle = (String)ois.readObject();
			Book book = bookStore.get(bookTitle);

			oos.writeObject(book != null);
			if (book != null)   oos.writeObject(book);
			oos.flush();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
