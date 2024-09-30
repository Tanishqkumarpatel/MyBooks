package model;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMyCollection {
    MyCollection books;
    Book b1;
    Book b2;
    List<String> genre1;
    List<String> genre2;

    @BeforeEach
    void runBefore() {
        books = new MyCollection();
        genre1 = new ArrayList<String>();
        genre1.add("fantasy");
        genre1.add("adventure");
        genre2 = new ArrayList<String>();
        genre2.add("comedy");
        b1 = new Book("Abc", genre1);
        b2 = new Book("def", genre2);
    }

    @Test
    public void getBooksTest() {
        books.addBook(b1);
        books.addBook(b2);
        List<Book> booklist = books.getBooks();
        assertEquals(2, booklist.size());
        assertEquals("Abc", booklist.get(0).getTitle());
        assertEquals("def", booklist.get(1).getTitle());
    }

    @Test
    public void addBookTest() {
        books.addBook(b1);
        assertEquals(1, books.getBooks().size());
        assertEquals("Abc", books.getBooks().get(0).getTitle());
    }

    @Test
    public void removeBookTest() {
        books.addBook(b1);
        books.addBook(b2);
        books.removeBook(b1);
        assertEquals(1, books.getBooks().size());
        assertEquals("def", books.getBooks().get(0).getTitle());
    }

    @Test 
    public void findBooksTest() {
        books.addBook(b1);
        books.addBook(b2);
        Book foundBook = books.findBook("abc");
        assertNotNull(foundBook);
        assertEquals(genre1, foundBook.getGenre());

        Book anotherBook = books.findBook("jg");
        assertNull(anotherBook);
    }

    @Test 
    public void getReadBooksTest() {
        books.addBook(b1);
        books.addBook(b2);
        b1.markAsRead();
        List<Book> readBooks = books.getReadBooks();
        assertEquals(1, readBooks.size());
        assertEquals("Abc", readBooks.get(0).getTitle());
    }
}