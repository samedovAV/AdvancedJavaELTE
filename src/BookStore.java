import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class BookStore {

    Map<String, Book> books = new HashMap<>();

    // Puts some predefined books into the map, to serve as initial data.
    public void addSomeBooks() {
        books.put(" ", new Book());
    }

    // Writes the books into the given file using serialization.
    // Returns whether the save operation was successful.
    public boolean saveBooks(String fileName) {
        try {
            Files.write(Path.of(fileName), books.toString().getBytes());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    // Loads the books from the given file.
    // Returns whether the load operation was successful.
    public boolean loadBooks(String fileName) {
        try {
            Object readedFromFile = new ObjectInputStream(new FileInputStream(fileName)).readObject();
            Book newBook = (Book) readedFromFile;
            //Files.readAllBytes(Path.of(fileName));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

}
