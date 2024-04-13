import java.util.List;

public class Customer {
    private List<BookItem> borrowedBooks;

    public void issueBook(BookItem bookItem) {
        if (borrowedBooks.size() > 5) {
            //TODO: can throw exception here
            return;
        }
        borrowedBooks.add(bookItem);
    }

    public void returnBook(BookItem bookItem) {
        borrowedBooks.remove(bookItem);
    }

    public void renewBook(BookItem bookItem) {
        // TODO : what to do
    }

    public List<BookItem> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BookItem> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
