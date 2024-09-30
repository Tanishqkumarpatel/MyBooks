package model;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBook {

    Book b1;
    Book b2;
    List<String> genre1;
    List<String> genre2;
    
    @BeforeEach
    void runBefore() {
        genre1 = new ArrayList<String>();
        genre1.add("fantasy");
        genre1.add("adventure");
        genre2 = new ArrayList<String>();
        genre2.add("comedy");
        b1 = new Book("Abc", genre1);
        b2 = new Book("def", genre2);

    }

    @Test
    public void constructorTest() {
        assertEquals("Abc", b1.getTitle());
        assertEquals(genre1, b1.getGenre());
        assertFalse(b1.getReadingStatus());
    }

    @Test
    void getTitleTest() {
        assertEquals("def", b2.getTitle());
    }

    @Test
    void getGenreTest() {
        assertEquals(genre2, b2.getGenre());
    }

    @Test
    void getReadingStatusTest() {
        assertFalse(b1.getReadingStatus());
    }

    @Test
    void getRatingTest() {
        b1.setRating(4);
        assertEquals(4, b1.getRating());
    }

    @Test
    void getNoteTest() {
        b1.setNote("I liked it!");
        assertEquals("I liked it!", b1.getNote());
    }

    @Test
    void markAsReadTest() {
        b2.markAsRead();
        assertTrue(b2.getReadingStatus());
    }

    @Test
    void setRatingTest() {
        b1.setRating(3);
        assertEquals(3, b1.getRating());
    }

    @Test
    void setNoteTest() {
        b2.setNote("Nice!");
        assertEquals("Nice!", b2.getNote());
    }

    @Test
    public void testToString() {
        assertEquals(b1.toString(), "Book{" 
                + "title='" + b1.getTitle() + '\'' 
                + ", genres=" + b1.getGenre() 
                + ", isRead=" + b1.getReadingStatus() 
                + ", notes='" + b1.getNote() + '\'' 
                + ", rating=" + b1.getRating() 
                + '}');
    }
}
