package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents an over-arching manager type function that has access to all the expenses, revenues, and goals
public class Controller {
    private ArrayList<Expenses> expenseList;
    private ArrayList<Revenues> revenuelist;
    private ArrayList<Goal> goalList;

    // EFFECTS: creates an empty expense, revenue, and goal lists.
    public Controller() {
        EventLog.getInstance().logEvent(new Event("Created new controller"));
        expenseList = new ArrayList<>();
        revenuelist = new ArrayList<>();
        goalList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a goal to the goal arraylist
    public void addGoaltoLOG(Goal goal) {
        EventLog.getInstance().logEvent(new Event("Added " + goal.getGoalName() + " to list of goals"));
        goalList.add(goal);
    }

    // MODIFIES: this
    // EFFECTS: Adds an expense to the expense arraylist
    public void addExpensetoLOE(Expenses expenses) {
        EventLog.getInstance().logEvent(new Event("Added " + expenses.getExpensename() + " to list of expenses"));
        expenseList.add(expenses);
    }

    // MODIFIES: this
    // EFFECTS: Adds a revenue to the revenue arraylist
    public void addRevenuetoLOR(Revenues revenues) {
        EventLog.getInstance().logEvent(new Event("Added " + revenues.getrevenuename() + " to list of revenues"));
        revenuelist.add(revenues);
    }

    // EFFECTS: Returns the goal arraylist
    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    // EFFECTS: Returns the expenselist
    public ArrayList<Expenses> getExpenseList() {
        return expenseList;
    }

    // EFFECTS: Returns the revenuelist
    public ArrayList<Revenues> getRevenuelist() {
        return revenuelist;
    }

    // REQUIRES: index >= 0, num > 0
    // MODIFIES: goal that corresponds with the index
    // EFFECTS: adds amount funded with the goal that corresponds with the goal index via user input
    public void addgoalamountfunded(int index, double num) {
        EventLog.getInstance().logEvent(new Event("Added " + num + " to " + goalList.get(index).getGoalName()));
        goalList.get(index).addamount(num);
    }

    // REQUIRES: index >= 0, num > 0
    // MODIFIES: goal that corresponds with the index
    // EFFECTS: changes goal name with the goal that corresponds with the goal index via user input
    public void changegoalname(int index, String name) {
        EventLog.getInstance().logEvent(new Event("Changed name of goal to " + name + " from "
                + goalList.get(index).getGoalName()));
        goalList.get(index).changename(name);
    }

    // REQUIRES: index >= 0, num > 0
    // MODIFIES: goal that corresponds with the index
    // EFFECTS: changes amount needed with the goal that corresponds with the goal index via user input
    public void changegoalamountneeeded(int index, double num) {
        EventLog.getInstance().logEvent(new Event("Changed amount needed of "
                + goalList.get(index).getGoalName() + " to " + num));
        goalList.get(index).changeamountneeded(num);
    }

    // MODIFIES: this
    // EFFECTS: converts expense, revenue, and goal lists information to JSON format
    public JSONObject toJson() {
        JSONObject info = new JSONObject();
        info.put("Financial Goals", goalsToJson());
        info.put("List of Expenses", expensesToJson());
        info.put("List of Revenues", revenuesToJson());
        info.put("Total Expenses: $", -1 * Expenses.getTotalOverallExpenses());
        info.put("Total Revenue: $", Revenues.getTotalOverallRevenue());
        info.put("Budget: $", (Revenues.getTotalOverallRevenue() + Expenses.getTotalOverallExpenses() * -1));
        info.put("Revenue to Expense Ratio: ", Revenues.revenuetoExpenseRatio() + "%");
        return info;
    }

    // MODIFIES: this
    // EFFECTS: converts expenselist into a jsonArray
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expenses e: getExpenseList()) {
            jsonArray.put(e.toJsonObject());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: converts revenuelist into a jsonArray
    private JSONArray revenuesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Revenues r: getRevenuelist()) {
            jsonArray.put(r.toJsonObject());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: converts goallist into a jsonArray
    private JSONArray goalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal g: getGoalList()) {
            jsonArray.put(g.toJsonObject());
        }
        return jsonArray;
    }
}
