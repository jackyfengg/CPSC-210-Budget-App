package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents a revenue that includes the name and revenue amounts
public class Revenues {
    private String revenuename;
    private ArrayList<Double> revenues;
    private Double totalRevenue;
    private static int TotalOverallRevenue;

    // REQUIRES: Income name and income amounts
    // EFFECTS: Creates a new income list with a name and amounts
    public Revenues(String name) {
        EventLog.getInstance().logEvent(new Event("Created a new revenue with the name: " + name));
        revenuename = name;
        revenues = new ArrayList<>();
        totalRevenue = 0.0;
    }

    // REQUIRES: num > 0
    // MODIFIES: this, TotalRevenue
    // EFFECTS: appends num to the revenue amount list and adds it to total revenue
    public void addrevenue(double num) {
        EventLog.getInstance().logEvent(new Event("Added " + num + " to " + getrevenuename()));
        revenues.add(num);
        totalRevenue += num;
        TotalOverallRevenue += num;
    }

    // MODIFIES: this
    // EFFECTS: converts revenue into JSON readable format
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("Revenue Name", revenuename);
        json.put("Amounts", new JSONArray(revenues));
        return json;
    }

    // EFFECTS: Overrides the toString function to produce the output below instead
    @Override
    public String toString() {
        return "|Revenue Name: " + revenuename + " Amounts: " + revenues + "|\n               ";
    }

    // EFFECTS: Returns the total revenue
    public static int getTotalOverallRevenue() {
        return TotalOverallRevenue;
    }

    // EFFECTS: Returns the revenue name
    public String getrevenuename() {
        return revenuename;
    }

    // EFFECTS: returns the revenue amounts
    public ArrayList<Double> getrevenues() {
        return revenues;
    }

    // REQUIRES: TotalRevenue and TotalExpenses > 0
    // EFFECTS: returns the ratio of revenues to expenses
    public static double revenuetoExpenseRatio() {
        return Math.round(100 * (double) getTotalOverallRevenue() / Expenses.getTotalOverallExpenses());

    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }
}
