package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents an expense that includes the name and expense amounts
public class Expenses {
    private String expensename;
    private ArrayList<Double> expenses;
    private double totalExpenses;
    private static int TotalOverallExpenses;

    // EFFECTS: Creates a new expense list with a name and amounts
    public Expenses(String name) {
        EventLog.getInstance().logEvent(new Event("Created a new expense with the name: " + name));
        expensename = name;
        expenses = new ArrayList<>();
    }

    // REQUIRES: num > 0
    // MODIFIES: this, TotalExpenses
    // EFFECTS: appends num to the expense amount list and adds it to total expenses
    public void addexpense(double num) {
        EventLog.getInstance().logEvent(new Event("Added " + num + " to " + getExpensename()));
        expenses.add(num);
        totalExpenses += num;
        TotalOverallExpenses += num;
    }

    // MODIFIES: this
    // EFFECTS: converts expense into JSON readable format
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("Expense Name", expensename);
        json.put("Amounts", new JSONArray(expenses));
        return json;
    }

    // EFFECTS: overrides the toString function to produce the output below instead
    @Override
    public String toString() {
        return "|Expense Name: " + expensename + " Amounts: " + expenses + "|\n                ";
    }

    // EFFECTS: returns the totalexpenses
    public static int getTotalOverallExpenses() {
        return TotalOverallExpenses;
    }

    // EFFECTS: returns the expensename
    public String getExpensename() {
        return expensename;
    }

    // EFFECTS: returns the expense amounts
    public ArrayList<Double> getExpenseAmounts() {
        return expenses;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }
}
