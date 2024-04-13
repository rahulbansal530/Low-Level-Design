import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class LibraryController {

    private static List<User> users;
    private static Map<String, BookItem> booksMap;
    private static Map<String, List<BookItem>> titleMap;
    private static Map<String, List<BookItem>> authorMap;
    private static Map<String, List<BookItem>> isbnMap;

    public LibraryController() {

        users = new ArrayList<>();
        booksMap = new HashMap<>();
        titleMap = new HashMap<>();
        authorMap = new HashMap<>();
        isbnMap = new HashMap<>();
    }

    public static void addBook(BookItem bookItem) {

        booksMap.put(bookItem.getId(), bookItem);
    }
}