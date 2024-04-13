# Library Management System

import java.util.*;
// Design Library management system

Requirements

Library
Multiple customers - each customer with a unique id
Multiple copies of each book


customers
customers should be able to borrow books for a particular time period 
    - maximum 5 books
    - each book can be borrowed for maximum 10 days
customers can search for a particular book
customers can subscribe to a book that is not available and later they can get notifications for the book when it becomes available


Admin
add a book
remove a book
check availability
check a book is issued to which customer

--------------------------
### Library

String name;
Address location;

Class Address {
    int pincode;
    String street;
    String city;
    String state;
    String country;
}

Book
    String uniqueId;
    String title;
    List<Author> author;
    BookType bookType;

Class BookItem extends Book {
    String code;
    Date publishedDate;
    Date issuedDate;
    BookStatus bookStatus;
}

public enum BookType {
    ROMANTIC, FANTASY
}

public enum BookStatus {
    ISSUED, AVAILABLE
}

Class User {
    String firstName;
    String lastName;
}
Class Author extends User{
    List<Book> booksPublished;
}

Class Customer extends User{
    String email;
    String phoneNumber;
    Search searchObj;

}

Class Admin extends User{
    String email;
    String phoneNumber;
    Search searchObj;
    BookService bookService;

    void addBook(BookItem bookItem);
    void editBook(String code);
    void removeBook(String code);
}

Class Search {
    List<BookItem> getBookByTitle(String title);
    List<BookItem> getBookByAuthor(Author author);
    List<BookItem> getBookByBookType(BookType bookType);
}

Class BookService {
    issueBook(BookItem bookItem, Customer customer);
    returnBook(BookItem bookItem, Customer customer);
    subscribeToABook(BookItem bookItem, Customer customer);
    getissuedDetails(BookItem bookItem);
}

Class Server {
    sendNotifications(Customer customer, BookItem bookItem);
}

