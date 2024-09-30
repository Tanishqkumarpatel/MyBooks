package persistence;

import model.Book;
import model.MyCollection;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import persistence.JsonReader;
// import persistence.JsonWriter;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {
    
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            MyCollection readCollection = reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCollection.json");
        try {
            MyCollection readCollection = reader.read();
            assertEquals(0, readCollection.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCollection.json");
        try {
            MyCollection readCollection = reader.read();
            assertEquals(2, readCollection.getBooks().size());

            Book readBook1 = readCollection.getBooks().get(0);
            assertEquals("1", readBook1.getTitle());
            assertEquals(Arrays.asList("1"), readBook1.getGenre());
            assertFalse(readBook1.getReadingStatus());

            Book readBook2 = readCollection.getBooks().get(1);
            assertEquals("2", readBook2.getTitle());
            assertEquals(Arrays.asList("2"), readBook2.getGenre());
            assertTrue(readBook2.getReadingStatus());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
