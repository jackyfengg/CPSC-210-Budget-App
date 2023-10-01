package model;

import org.json.JSONObject;

// Represents a goal with a goal amount needed, amount received, and name
public class Goal {
    private double goalAmountNeeded;
    private double goalAmountReceived;
    private String goalName;

    // EFFECTS: Creates a new goal instance with a name and amount
    public Goal(String name, double num) {
        EventLog.getInstance().logEvent(new Event("Created a new goal with the name: "
                + name + " and amount needed: " + num));
        goalName = name;
        goalAmountNeeded = num;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: Adds amount to goalamountreceived
    public void addamount(double amount) {
        goalAmountReceived += amount;
    }
    
    // EFFECTS: returns the percentage of how close the goal is to completion
    public double percentReached() {
        if (goalAmountReceived / goalAmountNeeded * 100 > 100) {
            return 100;
        } else {
            return Math.round(goalAmountReceived / goalAmountNeeded * 100);
        }
    }

    // EFFECTS: Returns the goalname
    public String getGoalName() {
        return goalName;
    }

    // REQUIRES: name length > 0
    // MODIFIES: this
    // EFFECTS:  Changes the goalname to name
    public void changename(String name) {
        goalName = name;
    }

    // REQUIRES: num > 0
    // MODIFIES: this
    // EFFECTS: Changes the goalamountneeded to num
    public void changeamountneeded(double num) {
        goalAmountNeeded = num;
    }

    // EFFECTS: Returns the goalamountneeded
    public double getGoalAmountNeeded() {
        return goalAmountNeeded;
    }

    // EFFECTS: Returns the goalAmountReceived
    public double getGoalAmountReceived() {
        return goalAmountReceived;
    }

    // MODIFIES:
    // EFFECTS: converts revenue into JSON readable format
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("Goal name", goalName);
        json.put("Goal amount", goalAmountNeeded);
        json.put("Goal amount received", goalAmountReceived);
        return json;
    }

    @Override
    // EFFECTS: Overrides the toString function to produce the output below instead
    public String toString() {
        return ("|Goal name: " + getGoalName() + ", Goal amount: "
                + getGoalAmountNeeded() + ", Goal amount received: "
                + getGoalAmountReceived() + ", Goal Percent Reached: "
                + percentReached() + "%|\n                ");
    }
}
