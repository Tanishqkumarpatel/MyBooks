package model;

import java.util.*;

// Book specify title, genre, reading status, rating and note.
public class Book {
    String title;
    List<String> genre;
    Boolean readingStatus;
    int rating;
    String note;

    // EFFECTS: initiates the Book

    public Book(String title, List<String> genre) {
        this.title = title;
        this.genre = genre;
        this.readingStatus = false;
        // this.rating = null;
        this.note = "";
    }

    public String getTitle() {
        return this.title;
    }

    public List<String> getGenre() {
        return this.genre;
    }

    public Boolean getReadingStatus() {
        return this.readingStatus;
    }

    public int getRating() {
        return this.rating;
    }

    public String getNote() {
        return note;
    }

    // MODIFIES: this
    // EFFECTS: Mark book as read

    public void markAsRead() {
        this.readingStatus = true;
        EventLog.getInstance().logEvent(new Event("Book marked as read: " + this.title));
    }

    public void setRating(int r) {
        this.rating = r;
        EventLog.getInstance().logEvent(new Event("Rating set for " + this.title + ": " + r));
    }

    public void setNote(String n) {
        note = n;
        EventLog.getInstance().logEvent(new Event("Note added to " + this.title + ": " + n));
    }

    // EFFECTS: returns string representation of this Book.
    @Override
    public String toString() {
        return "Book{" 
            + "title='" + title + '\'' 
            + ", genres=" + genre 
            + ", isRead=" + readingStatus 
            + ", notes='" + note + '\'' 
            + ", rating=" + rating 
            + '}';
    }
}
