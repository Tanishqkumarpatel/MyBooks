package model;

import java.util.ArrayList;
import java.util.List;

public class MyCollection {

    List<Book> books;

    // EFFECTS: initiates the MyCollection.

    public MyCollection() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    // MODIFIES: this
    // EFFECTS: adds a book to the collection

    public void addBook(Book b) {
        books.add(b);
        EventLog.getInstance().logEvent(new Event("Book added: " + b.getTitle() + " Genre: " + b.getGenre()));
    }

    // REQUIRES: Assumes book is in the collection.
    // MODIFIES: this
    // EFFECTS: removes a book from the collection

    public void removeBook(Book b) {
        books.remove(b);
        EventLog.getInstance().logEvent(new Event("Book removed: " + b.getTitle() + " Genre: " + b.getGenre()));
    }

    // EFFECTS: returns the book if found in the collection book with the
    // exact title (non-Case sensitive). if not found then return null.

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                EventLog.getInstance().logEvent(new Event("Book searched: " + book.getTitle()));
                return book;
            }
        }
        return null;
    }

    // EFFECTS: returns the list of book marked as reaad if found in the collection,
    // book with the exact title (non-Case sensitive). if not found then return
    // empty list.

    public List<Book> getReadBooks() {
        List<Book> readBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.readingStatus) {
                readBooks.add(book);
            }
        }
        return readBooks;
    }
}
