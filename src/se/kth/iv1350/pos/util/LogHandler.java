package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Hanterar loggning av tekniska fel till fil.
 * Singleton (!): endast en instans får existera.
 */
public class LogHandler {
    private static final String LOG_FILE = "log.txt";
    private static LogHandler instance;
    private PrintWriter writer;

    private LogHandler() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE, true), true);
        } catch (IOException e) {
            System.out.println("Kunde inte öppna loggfil: " + LOG_FILE);
            e.printStackTrace();
        }
    }

    /*
     * Returnerar den enda instansen av loggaren.
     */
    public static LogHandler getInstance() {
        if (instance == null) {
            instance = new LogHandler();
        }
        return instance;
    }

    /*
     * Skriver felmeddelande.
     */
    public void log(String message) {
        writer.println(message);
    }
}
