# My Books

## Proposal

**What will the application do?**\
The app will enable users to organize their books, monitor ones they've finished, and make lists of books to read. It will have options for adding comments and ratings to every book.

**Who will use it?**\
People who love reading books and people who want to monitor their reading habits and book collections.

**Why is this project of interest to you?**\
It showcases your skills in managing data input and extraction, as well as developing a professional and easy-to-use interface.

## User Story

- **As a user, I want to be able to add a book to my collection and specify the title, genre, and reading status.**
- **As a user, I want to be able to view a list of all books in my collection.**
- **As a user, I want to be able to view my reading list (Books I Have Read).**
- **As a user, I want to be able to add notes and ratings to each book.**
- **As a user, I want to be able to search for books by title.**
- **As a user, I want to be able to save my book collection to file (if I so choose).**
- **As a user, I want to be able to be able to load my book collection from file (if I so choose).**

# Instructions for Grader

- **You can generate the first required action related to the user story "adding multiple books to a collection" by entering title and genre, then clicking `Add Book`.**
- **Book can be selected by simply left cliking.**
- **after selecting book you may want to remove book or add notes to it or give it rating or mark as read you can do it by simple clicking on the buttons on screen which says `Remove Book`, `Add Notes`, `Rate Book`, `Mark as Read` respectively.**
- **By clicking on `Show Read Books` you can get the books that you had read (subset of collection) and by clicking on `Show All Books` you can get all the books in your collection.**
- **By clicking on `Search Book` you can find the book by entering the exact title of corresponding book in the pop up window.**
- **You can locate my visual component by clicking on `Show Read vs Unread` button (reminder: mark some book as read and click again if you update the book in collection).**
- **You can save the state of my application by clicking on `Save Collection`**
- **You can reload the state of my application by clicking on `Load Collection`**

# Phase 4: Task 2
- **Sample Event:**\
**Thu Aug 08 23:19:46 PDT 2024**\
**Book added: Book 1 Genre: [genre1, genre2]**\
**Thu Aug 08 23:19:54 PDT 2024**\
**Book added: Book 2 Genre: [Genres3]**\
**Thu Aug 08 23:20:10 PDT 2024**\
**Book added: Book 3 Genre: [Genre 4, Genre5]**\
**Thu Aug 08 23:20:22 PDT 2024**\
**Book added: Book 4 Genre: [hahaha]**\
**Thu Aug 08 23:20:35 PDT 2024**\
**Note added to Book 1: Best Book Ever Read**\
**Thu Aug 08 23:20:42 PDT 2024**\
**Rating set for Book 2: 2**\
**Thu Aug 08 23:20:47 PDT 2024**\
**Book marked as read: Book 3**\
**Thu Aug 08 23:21:04 PDT 2024**\
**Book searched: Book 4**\
**Thu Aug 08 23:21:10 PDT 2024**\
**Book removed: Book 4 Genre: [hahaha]**

# Phase 4: Task 3
- **If I had time I would refactor the following:**
- **Add a writable class so that I can make my JsonReader and JsonWriter Class extend it to meake it more readable (just like it was on sample project)**
- **Create a new Class Button so that my MyBooksGuI class can only focus on panel and gui and gets buttons to be clicked from this button class for making it more cohesive**
- **Make a new abstractn Class which handels my applications various action such as add Book and Remove Book so MyBooksApllication and MyBooksGUI can extends it to make it more redable and cohesive**