package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Book;
import model.MyCollection;

// // Represents a writer that writes JSON representation of book collection to file.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of my Book Collection to file
    public void write(MyCollection myCollection) {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson(myCollection.getBooks()));
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // EFFECTS: returns books in this collection as a JSON array
    private JSONArray booksToJson(List<Book> books) {
        JSONArray jsonArray = new JSONArray();
        for (Book book : books) {
            JSONObject json = new JSONObject();
            json.put("title", book.getTitle());
            json.put("genre", new JSONArray(book.getGenre()));
            json.put("readingStatus", book.getReadingStatus());
            json.put("rating", book.getRating());
            json.put("note", book.getNote());
            jsonArray.put(json);
        }
        return jsonArray;
    }
}
