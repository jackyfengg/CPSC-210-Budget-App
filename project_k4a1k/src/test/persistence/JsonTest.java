package persistence;

import model.Expenses;
import model.Goal;
import model.Revenues;

import static org.junit.jupiter.api.Assertions.*;
// Represents a superclass for JsonLoaderTest and JsonSaverTest for convient functions
public class JsonTest {

    // Check if expense is the same
    protected void checkExpense(String name, String amount, Expenses e) {
        assertEquals(name, e.getExpensename());
        assertEquals(amount, e.getExpenseAmounts().toString());
    }

    // Check if revenue is the same
    protected void checkRevenue(String name, String amount, Revenues r) {
        assertEquals(name, r.getrevenuename());
        assertEquals(amount, r.getrevenues().toString());
    }

    // Check if goal is the same
    protected void checkGoal(String name, double amountneed, double amountfunded, Goal g) {
        assertEquals(name, g.getGoalName());
        assertEquals(amountneed, g.getGoalAmountNeeded());
        assertEquals(amountfunded, g.getGoalAmountReceived());
    }
}
