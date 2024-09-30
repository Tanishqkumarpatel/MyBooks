package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Book;
import model.MyCollection;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads My book collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses collection from JSON object and returns it
    private MyCollection parseCollection(JSONObject jsonObject) {
        MyCollection myCollection = new MyCollection();
        addBooks(myCollection, jsonObject);
        return myCollection;
    }

    // MODIFIES: myCollection
    // EFFECTS: parses books from JSON object and adds them to collection
    private void addBooks(MyCollection myCollection, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(myCollection, nextBook);
        }
    }

    // MODIFIES: myCollection
    // EFFECTS: parses book from JSON object and adds it to collection
    private void addBook(MyCollection myCollection, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        List<String> genre = new ArrayList<>();
        JSONArray jsonGenres = jsonObject.getJSONArray("genre");
        for (Object genreObj : jsonGenres) {
            genre.add((String) genreObj);
        }
        Book book = new Book(title, genre);
        book.setNote(jsonObject.getString("note"));
        book.setRating(jsonObject.getInt("rating"));
        if (jsonObject.getBoolean("readingStatus")) {
            book.markAsRead();
        }
        myCollection.addBook(book);
    }
}
