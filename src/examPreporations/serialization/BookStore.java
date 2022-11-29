package examPreporations.serialization;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookStore implements Serializable {

    //private Map<String, Book> titleToBook = new HashMap<>();

    private Map<String, Book> titleToBook;

    public BookStore() {
        //titleToBook = new HashMap<>();
        titleToBook = new ConcurrentHashMap<>();
    }

    public void add(Book book) {
        titleToBook.put(book.getTitle(), book);
    }

    public Book get(String title) {
        return titleToBook.get(title);
    }

    public void save(String filename) throws IOException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            //for (Book book : titleToBook.values()) {
            //    oos.writeObject(book);
            //}
            oos.writeObject(titleToBook);
            oos.flush();
        }
    }

    @SuppressWarnings("unchecked")
    public void load(String filename) throws IOException, ClassNotFoundException {
        try (var ois = new ObjectInputStream(new FileInputStream(filename))) {
            titleToBook = (Map<String, Book>) ois.readObject();
        }
    }
}
