package persistence;

import model.Controller;
import model.Expenses;
import model.Goal;
import model.Revenues;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents tests for JsonSaver
public class JsonSaverTest extends JsonTest {

    // Test saving to an illegal file name
    @Test
    void testSaverInvalidFile() {
        try {
            Controller manager = new Controller();
            JsonSaver saver = new JsonSaver("./data/my\0illegal:fileName.json");
            saver.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Test saving an empty budget file
    @Test
    void testSaverEmptyFile() {
        try {
            System.setIn(new ByteArrayInputStream("s\nq".getBytes()));
            Controller controller = new Controller();
            JsonSaver saver = new JsonSaver("./data/JsonSavertest.json");

            // Test saving the budget to a test file
            saver.open();
            saver.write(controller);
            saver.close();

            // Load the test file and verify that the file is empty
            JsonLoader loader = new JsonLoader("./data/JsonSavertest.json");
            Controller manager = loader.load();

            assertEquals(0, manager.getExpenseList().size());
            assertEquals(0, manager.getRevenuelist().size());
            assertEquals(0, manager.getGoalList().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Test saving a normal budget file
    @Test
    void testSaverNormalFile() {
            try {

                // Create a normal budget
                Controller controller = new Controller();

                Expenses e = new Expenses("Gas");
                e.addexpense(10);
                e.addexpense(20);
                e.addexpense(30);

                Revenues r = new Revenues("Job");
                r.addrevenue(100);
                r.addrevenue(200);
                r.addrevenue(300);

                Goal g = new Goal("Trip", 100);

                controller.addExpensetoLOE(e);
                controller.addRevenuetoLOR(r);
                controller.addGoaltoLOG(g);

                // Test saving the budget to a test file
                JsonSaver saver = new JsonSaver("./data/JsonSavertest.json");

                saver.open();
                saver.write(controller);
                saver.close();

                // Load the test file
                JsonLoader loader = new JsonLoader("./data/JsonSavertest.json");
                Controller manager = loader.load();

                // Verify that data is correct and there is no loss
                checkExpense("Gas", "[10.0, 20.0, 30.0]", manager.getExpenseList().get(0));
                checkRevenue("Job", "[100.0, 200.0, 300.0]", manager.getRevenuelist().get(0));
                checkGoal("Trip", 100, 0, manager.getGoalList().get(0));

            } catch (IOException e) {
                fail("Exception should not have been thrown");
            }
        }
}

