public class BookStoreTest {
    public static void main(String[] args) {
        BookStore bookStore = new BookStore();

        boolean saved = bookStore.saveBooks("books.txt");
        boolean res = bookStore.loadBooks("books.txt");
    }
}
