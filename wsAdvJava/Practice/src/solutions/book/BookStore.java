package solutions.book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BookStore implements Serializable {
	private Map<String, Book> titleToBook;
//	private Map<String, Book> titleToBook = new HashMap<>();

	public BookStore() {
		titleToBook = new HashMap<>();
//		titleToBook = new ConcurrentHashMap<>();
	}

	public void add(Book book) {
		titleToBook.put(book.getTitle(), book);
	}

	public Book get(String title) {
		return titleToBook.get(title);
	}

	public void save(String filename) throws FileNotFoundException, IOException {
		try (var oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//			for (Book book : titleToBook.values()) {
//				oos.writeObject(book);
//			}
			oos.writeObject(titleToBook);
			oos.flush();
		}
	}

	@SuppressWarnings("unchecked")
	public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (var ois = new ObjectInputStream(new FileInputStream(filename))) {
			titleToBook = (Map<String, Book>)ois.readObject();
		}
	}
}
