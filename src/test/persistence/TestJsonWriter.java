package persistence;

import model.Book;
import model.MyCollection;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import persistence.JsonReader;
// import persistence.JsonWriter;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            MyCollection collection = new MyCollection();
            JsonWriter writer = new JsonWriter("./data/invalid\0file.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyCollection() {
        try {
            MyCollection collection = new MyCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCollection.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCollection.json");
            collection = reader.read();
            assertEquals(0, collection.getBooks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCollection() {
        try {
            MyCollection collection = new MyCollection();
            collection.addBook(new Book("1", Arrays.asList("1")));
            collection.addBook(new Book("2", Arrays.asList("2")));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCollection.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCollection.json");
            collection = reader.read();
            assertEquals(2, collection.getBooks().size());

            Book readBook1 = collection.getBooks().get(0);
            assertEquals("1", readBook1.getTitle());
            assertEquals(Arrays.asList("1"), readBook1.getGenre());

            Book readBook2 = collection.getBooks().get(1);
            assertEquals("2", readBook2.getTitle());
            assertEquals(Arrays.asList("2"), readBook2.getGenre());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
