package ui;

import model.Book;

import model.MyCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the MyBooksApplication.
public class MyBooksApplication {

    private MyCollection bookCollection;
    private Scanner input;

    // EFFECTS: runs the MyBooks application
    public MyBooksApplication() throws FileNotFoundException {
        runMyBooks();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMyBooks() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMenu();
            String command = input.nextLine().trim().toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            toAddBook();
        } else if (command.equals("b")) {
            toRemoveBook();
        } else if (command.equals("c")) {
            viewAllBooks();
        } else if (command.equals("d")) {
            searchBooks();
        } else if (command.equals("e")) {
            addNotes();
        } else if (command.equals("f")) {
            rateBook();
        } else if (command.equals("g")) {
            markBookAsRead();
        } else if (command.equals("h")) {
            readBooks();
        } else if (command.equals("s")) {
            saveCollection();
        } else if (command.equals("l")) {
            loadCollection();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes book Collection
    private void init() {
        bookCollection = new MyCollection();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a Book");
        System.out.println("\tb -> Remove a Book");
        System.out.println("\tc -> View All Books");
        System.out.println("\td -> Search Books By Title");
        System.out.println("\te -> Add Notes to a Book");
        System.out.println("\tf -> Rate a Book");
        System.out.println("\tg -> Mark As Read a Book");
        System.out.println("\th -> View All Mark As Read Books");
        System.out.println("\ts -> Save Collection to File");
        System.out.println("\tl -> Load Collection from File");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds book to collection
    private void toAddBook() {
        System.out.print("Enter book title: ");
        String title = input.nextLine().trim();

        System.out.print("Enter book genres (comma separated): ");
        String genresInput = input.nextLine().trim();
        List<String> genres = new ArrayList<>();
        for (String genre : genresInput.split(",")) {
            genres.add(genre.trim());
        }

        Book book = new Book(title, genres);
        bookCollection.addBook(book);
        System.out.println("Book added to collection.");
    }

    // MODIFIES: this
    // EFFECTS: removes book from collection
    private void toRemoveBook() {
        System.out.print("Enter book title: ");
        String title = input.nextLine().trim();
        Book foundBook = bookCollection.findBook(title);

        if (foundBook != null) {
            bookCollection.removeBook(foundBook);
            System.out.println("Book removed from collection.");
        } else {
            System.out.println("Book is not in the collection.");
        }
    }

    // EFFECTS: displays all books in the collection
    private void viewAllBooks() {
        for (Book book : bookCollection.getBooks()) {
            System.out.println(book);
        }
    }

    // MODIFIES: this
    // EFFECTS: searches book by title and displays it if found, otherwise prints "Book Not Found"
    private void searchBooks() {
        System.out.print("Enter book title: ");
        String title = input.nextLine().trim();
        Book foundBook = bookCollection.findBook(title);

        if (foundBook != null) {
            System.out.println(foundBook);
        } else {
            System.out.println("Book Not Found");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds notes to a book
    private void addNotes() {
        System.out.print("Enter book title to add notes: ");
        String title = input.nextLine().trim();
        Book book = bookCollection.findBook(title);

        if (book != null) {
            System.out.print("Enter notes: ");
            String notes = input.nextLine().trim();
            book.setNote(notes);
            System.out.println("Notes added to book.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // MODIFIES: this
    // EFFECTS: rates a book from 0-5
    private void rateBook() {
        System.out.print("Enter book title to set rating: ");
        String title = input.nextLine().trim();
        Book book = bookCollection.findBook(title);

        if (book != null) {
            System.out.print("Enter rating (0-5): ");
            int rating = input.nextInt();
            input.nextLine();  // Consume newline
            book.setRating(rating);
            System.out.println("Rating set for book.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // MODIFIES: this
    // EFFECTS: marks a book as read
    private void markBookAsRead() {
        System.out.print("Enter book title to mark as read: ");
        String title = input.nextLine().trim();
        Book book = bookCollection.findBook(title);

        if (book != null) {
            book.markAsRead();
            System.out.println("Book marked as read.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // EFFECTS: displays all books marked as read
    private void readBooks() {
        System.out.println("Books you have read so far:");
        for (Book book : bookCollection.getReadBooks()) {
            System.out.println(book);
        }
    }

    // EFFECTS: save the collection to file
    private void saveCollection() {
        try {
            JsonWriter writer = new JsonWriter("./data/bookCollection.json");
            writer.open();
            writer.write(bookCollection);
            writer.close();
            System.out.println("Collection saved to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save collection to file.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the collection from file
    private void loadCollection() {
        try {
            JsonReader reader = new JsonReader("./data/bookCollection.json");
            bookCollection = reader.read();
            System.out.println("Collection loaded from file.");
        } catch (IOException e) {
            System.out.println("Unable to load collection from file.");
        }
    }
}
