package persistence;

import model.Controller;
import model.Event;
import model.EventLog;
import org.json.JSONObject;

import java.io.*;

// Represents a saver that writes JSON representation of workroom to file
public class JsonSaver {
    private static final int TAB = 4;
    private PrintWriter saver;
    private final String destination;

    // EFFECTS: constructs saver to write to destination file
    public JsonSaver(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens saver; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        saver = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of controller to file
    public void write(Controller controller) {
        JSONObject json = controller.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes saver
    public void close() {
        saver.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        saver.print(json);
    }

}
