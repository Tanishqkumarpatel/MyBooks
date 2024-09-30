package ui;

import java.io.FileNotFoundException;

import model.EventLog;
import model.Event;

// 1import java.util.*;

// main class to run the application.
public class Main {
    public static void main(String[] args) {
        try {
            new MyBooksApplication();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            EventLog eventLog = EventLog.getInstance();
            for (Event event : eventLog) {
                System.out.println(event.toString());
            }
        }));
    }
}