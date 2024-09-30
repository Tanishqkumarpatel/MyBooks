package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Book;
import model.EventLog;
import model.Event;
import model.MyCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

// MyBooksGUi is java application
public class MyBooksGUI extends JFrame implements ActionListener {
    private MyCollection bookCollection;
    private DefaultListModel<Book> bookListModel;
    private JList<Book> bookList;
    private JTextField titleField;
    private JTextField genreField;
    private static final String JSON_STORE = "./data/bookCollection.json";
    private JPanel graphPanel;

    // MODIFIES: this
    // EFFECTS: Initializes the GUI and sets up the user interface

    public MyBooksGUI() {
        super("My Books Collection");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 700));

        bookCollection = new MyCollection();
        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);

        setupUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: Adds and arranges components in the main panel

    @SuppressWarnings("methodlength")
    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel inputPanel = new JPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField(10);
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Genre:"));
        genreField = new JTextField(10);
        inputPanel.add(genreField);

        JButton addButton = new JButton("Add Book");
        addButton.setActionCommand("addBook");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remove Book");
        removeButton.setActionCommand("removeBook");
        removeButton.addActionListener(this);
        inputPanel.add(removeButton);

        mainPanel.add(new JScrollPane(bookList), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        JButton markAsReadButton = new JButton("Mark As Read");
        markAsReadButton.setActionCommand("markAsRead");
        markAsReadButton.addActionListener(this);
        actionPanel.add(markAsReadButton);

        JButton showAllBooksButton = new JButton("Show All Books");
        showAllBooksButton.setActionCommand("showAllBooks");
        showAllBooksButton.addActionListener(this);
        actionPanel.add(showAllBooksButton);

        JButton showReadBooksButton = new JButton("Show Read Books");
        showReadBooksButton.setActionCommand("showReadBooks");
        showReadBooksButton.addActionListener(this);
        actionPanel.add(showReadBooksButton);

        JButton addNotesButton = new JButton("Add Notes");
        addNotesButton.setActionCommand("addNotes");
        addNotesButton.addActionListener(this);
        actionPanel.add(addNotesButton);

        JButton rateBookButton = new JButton("Rate Book");
        rateBookButton.setActionCommand("rateBook");
        rateBookButton.addActionListener(this);
        actionPanel.add(rateBookButton);

        JButton searchBookButton = new JButton("Search Book");
        searchBookButton.setActionCommand("searchBook");
        searchBookButton.addActionListener(this);
        actionPanel.add(searchBookButton);

        JButton saveButton = new JButton("Save Collection");
        saveButton.setActionCommand("saveCollection");
        saveButton.addActionListener(this);
        actionPanel.add(saveButton);

        JButton loadButton = new JButton("Load Collection");
        loadButton.setActionCommand("loadCollection");
        loadButton.addActionListener(this);
        actionPanel.add(loadButton);

        JButton showBarGraphButton = new JButton("Show Read vs Unread");
        showBarGraphButton.setActionCommand("showBarGraph");
        showBarGraphButton.addActionListener(this);
        actionPanel.add(showBarGraphButton);

        graphPanel = new JPanel();
        mainPanel.add(graphPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: Performs the appropriate action based on the button clicked

    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addBook":
                addBook();
                break;
            case "removeBook":
                removeBook();
                break;
            case "markAsRead":
                markBookAsRead();
                break;
            case "showAllBooks":
                showAllBooks();
                break;
            case "showReadBooks":
                showReadBooks();
                break;
            case "addNotes":
                addNotes();
                break;
            case "rateBook":
                rateBook();
                break;
            case "searchBook":
                searchBook();
                break;
            case "saveCollection":
                saveCollection();
                break;
            case "loadCollection":
                loadCollection();
                break;
            case "showBarGraph":
                showBarGraph();
                break;
        }
    }

    // MODIFIES: genreList, bookCollection, bookListModel, titleField, genreField
    // EFFECTS: add book to collection

    private void addBook() {
        String title = titleField.getText().trim();
        String[] genres = genreField.getText().trim().split(",");
        List<String> genreList = new ArrayList<>();
        for (String genre : genres) {
            genreList.add(genre.trim());
        }
        Book book = new Book(title, genreList);
        bookCollection.addBook(book);
        bookListModel.addElement(book);
        titleField.setText("");
        genreField.setText("");
    }

    // MODIFIES: bookList, selectedBook
    // EFFECTS: mark selected book as read

    private void markBookAsRead() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            selectedBook.markAsRead();
            bookList.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to mark as read.");
        }
    }

    // MODIFIES: bookList, readBooksModel
    // EFFECTS: displays only read books.

    private void showReadBooks() {
        DefaultListModel<Book> readBooksModel = new DefaultListModel<>();
        for (Book book : bookCollection.getReadBooks()) {
            readBooksModel.addElement(book);
        }
        bookList.setModel(readBooksModel);
    }

    // MODIFIES: bookCollection, bookListModel
    // EFFECTS: removes the selected book from the collection

    private void removeBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            bookCollection.removeBook(selectedBook);
            bookListModel.removeElement(selectedBook);
            JOptionPane.showMessageDialog(this, "Book removed from collection.");
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to remove.");
        }
    }

    // MODIFIES: bookList
    // EFFECTS: Sets the book list model to contain all books

    private void showAllBooks() {
        bookList.setModel(bookListModel);
    }

    // MODIFIES: bookList, selectedBook
    // EFFECTS: sets the notes to the selected book

    private void addNotes() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            String notes = JOptionPane.showInputDialog(this, "Enter notes:");
            selectedBook.setNote(notes);
            bookList.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to add notes.");
        }
    }

    // MODIFIES: selectedBook, bookList
    // EFFECTS: sets the given rating to selected book

    private void rateBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            String ratingStr = JOptionPane.showInputDialog(this, "Enter rating (0-5):");
            try {
                int rating = Integer.parseInt(ratingStr);
                if (rating >= 0 && rating <= 5) {
                    selectedBook.setRating(rating);
                    bookList.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Rating must be between 0 and 5.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid rating.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a book to rate.");
        }
    }

    // MODIFIES: bookList
    // EFFECTS: provides the book with the given title

    private void searchBook() {
        String title = JOptionPane.showInputDialog(this, "Enter book title:");
        Book foundBook = bookCollection.findBook(title);

        if (foundBook != null) {
            DefaultListModel<Book> searchResultModel = new DefaultListModel<>();
            searchResultModel.addElement(foundBook);
            bookList.setModel(searchResultModel);
        } else {
            JOptionPane.showMessageDialog(this, "Book not found.");
        }
    }

    // MODIFIES: writer
    // EFFECTS: Writes the current book collection to a JSON file

    private void saveCollection() {
        JsonWriter writer = new JsonWriter(JSON_STORE);
        try {
            writer.open();
            writer.write(bookCollection);
            writer.close();
            JOptionPane.showMessageDialog(this, "Collection saved to file.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save collection to file.");
        }
    }

    // MODIFIES: bookCollection, bookListModel
    // EFFECTS: Reads the book collection from a JSON file and updates the list
    // model

    private void loadCollection() {
        JsonReader reader = new JsonReader(JSON_STORE);
        try {
            bookCollection = reader.read();
            bookListModel.clear();
            for (Book book : bookCollection.getBooks()) {
                bookListModel.addElement(book);
            }
            JOptionPane.showMessageDialog(this, "Collection loaded from file.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load collection from file.");
        }
    }

    // MODIFIES: graphPanel, data
    // EFFECTS: display the bar graph (comparison) for read books vs unread books
    private void showBarGraph() {
        graphPanel.removeAll();
        Map<String, Integer> data = new HashMap<>();
        data.put("Read", bookCollection.getReadBooks().size());
        data.put("Unread", bookCollection.getBooks().size() - bookCollection.getReadBooks().size());
        BarGraphPanel barGraphPanel = new BarGraphPanel(data);
        graphPanel.add(barGraphPanel);
        graphPanel.revalidate();
        graphPanel.repaint();
    }

    // EFFECTS: Runs the MyBooksGUI application.
    public static void main(String[] args) {
        new MyBooksGUI();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            EventLog eventLog = EventLog.getInstance();
            for (Event event : eventLog) {
                System.out.println(event.toString());
            }
        }));
    }
}
