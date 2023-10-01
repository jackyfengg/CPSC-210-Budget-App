package persistence;

import model.Controller;
import model.Expenses;
import model.Revenues;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
// Represents tests for JsonLoader
public class JsonLoaderTest extends JsonTest{

    // Test loading if there is no file
    @Test
    void testLoaderNonExistentFile() {
        JsonLoader loader = new JsonLoader("./data/noSuchFile.json");
        try {
            Controller manager = loader.load();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Test loading if the budget file is empty
    @Test
    void testLoaderEmptyFile() {
        JsonLoader loader = new JsonLoader("./data/BudgetTestEmpty.json");
        try {
            Controller manager = loader.load();
            assertEquals("[]", manager.getGoalList().toString());
            assertEquals("[]", manager.getExpenseList().toString());
            assertEquals("[]", manager.getRevenuelist().toString());
            assertEquals(0, Expenses.getTotalOverallExpenses());
            assertEquals(0, Revenues.getTotalOverallRevenue());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // Test loading a normal budget file
    @Test
    void testLoaderNormalFile() {
        JsonLoader loader = new JsonLoader("./data/BudgetTestNormal.json");
        try {
            Controller manager = loader.load();

            // Verify that data is correct and there is no loss
            assertEquals(2, manager.getExpenseList().size());
            assertEquals(2, manager.getRevenuelist().size());
            assertEquals(2, manager.getGoalList().size());

            assertEquals("[|Goal name: Vacation, Goal amount: 30000.0, Goal amount received: 10000.0, Goal Percent Reached: 33.0%|\n" +
                    "                , |Goal name: New Car, Goal amount: 100000.0, Goal amount received: 20000.0, Goal Percent Reached: 20.0%|\n" +
                    "                ]", manager.getGoalList().toString());
            assertEquals("[|Expense Name: Food Amounts: [20.0, 30.0, 40.0, 50.0, 60.0]|\n" +
                    "                , |Expense Name: Clothes Amounts: [100.0, 200.0, 300.0, 400.0]|\n" +
                    "                ]", manager.getExpenseList().toString());
            assertEquals("[|Revenue Name: Salary Amounts: [100000.0, 2000.0]|\n" +
                    "               , |Revenue Name: Misc Amounts: [3000.0]|\n" +
                    "               ]", manager.getRevenuelist().toString());

            assertEquals(1200, Expenses.getTotalOverallExpenses());
            assertEquals(105000, Revenues.getTotalOverallRevenue());

            checkExpense("Food", "[20.0, 30.0, 40.0, 50.0, 60.0]", manager.getExpenseList().get(0));
            checkExpense("Clothes", "[100.0, 200.0, 300.0, 400.0]", manager.getExpenseList().get(1));

            checkRevenue("Salary", "[100000.0, 2000.0]", manager.getRevenuelist().get(0));
            checkRevenue("Misc", "[3000.0]", manager.getRevenuelist().get(1));

            checkGoal("Vacation", 30000, 10000, manager.getGoalList().get(0));
            checkGoal("New Car", 100000, 20000, manager.getGoalList().get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
