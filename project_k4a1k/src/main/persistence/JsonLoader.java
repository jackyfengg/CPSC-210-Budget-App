package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Represents a loader that reads workroom from JSON data stored in file
public class JsonLoader {
    private String source;

    // EFFECTS: constructs loader to read from source file
    public JsonLoader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Controller from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Controller load() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseController(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses manager from JSON object and returns it
    private Controller parseController(JSONObject jsonObject) {
        Controller manager = new Controller();
        addExpenses(manager, jsonObject);
        addRevenues(manager, jsonObject);
        addGoals(manager, jsonObject);
        return manager;
    }

    // MODIFIES: this
    // EFFECTS: parses expense-list from JSON object and gets the individual expenses
    private void addExpenses(Controller manager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List of Expenses");
        for (Object json : jsonArray) {
            JSONObject jsonExpense = (JSONObject) json;
            addExpense(manager, jsonExpense);
        }
    }

    // MODIFIES: manager
    // EFFECTS: parses expenses from JSON object and adds them to manager
    private void addExpense(Controller manager, JSONObject jsonExpense) {
        String name = jsonExpense.getString("Expense Name");
        Expenses expense = new Expenses(name);
        JSONArray jsonArray = jsonExpense.getJSONArray("Amounts");
        for (Object json : jsonArray) {
            expense.addexpense(Double.parseDouble(json.toString()));
        }
        manager.addExpensetoLOE(expense);
    }

    // MODIFIES: this
    // EFFECTS: parses revenue-list from JSON object and gets the individual revenues
    private void addRevenues(Controller manager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List of Revenues");
        for (Object json : jsonArray) {
            JSONObject jsonRevenue = (JSONObject) json;
            addRevenue(manager, jsonRevenue);
        }
    }

    // MODIFIES: manager
    // EFFECTS: parses revenues from JSON object and adds them to manager
    private void addRevenue(Controller manager, JSONObject jsonRevenue) {
        String name = jsonRevenue.getString("Revenue Name");
        Revenues revenue = new Revenues(name);
        JSONArray jsonArray = jsonRevenue.getJSONArray("Amounts");
        for (Object json : jsonArray) {
            revenue.addrevenue(Double.parseDouble(json.toString()));
        }
        manager.addRevenuetoLOR(revenue);
    }

    // MODIFIES: this
    // EFFECTS: parses goal-list from JSON object and gets the individual goal
    private void addGoals(Controller manager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Financial Goals");
        for (Object json : jsonArray) {
            JSONObject jsonGoal = (JSONObject) json;
            addGoal(manager, jsonGoal);
        }
    }

    // MODIFIES: manager
    // EFFECTS: parses goal from JSON object and adds them to manager
    private void addGoal(Controller manager, JSONObject jsonGoal) {
        String name = jsonGoal.getString("Goal name");
        double amountneeded = jsonGoal.getDouble("Goal amount");
        double amountfunded = jsonGoal.getDouble("Goal amount received");
        Goal goal = new Goal(name, amountneeded);
        goal.addamount(amountfunded);
        manager.addGoaltoLOG(goal);
    }
}
