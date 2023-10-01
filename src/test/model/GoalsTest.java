package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Represents a tests for goals
public class GoalsTest {
    Controller manager;
    Goal goal;

    // Test and verify constructor for goals
    @BeforeEach
    public void testconstructor() {
        manager = new Controller();
        goal = new Goal("Vacation", 1000);
        assertEquals("Vacation", goal.getGoalName());
        assertEquals(1000, goal.getGoalAmountNeeded());
        assertEquals(0, goal.getGoalAmountReceived());
    }

    // Test the expense class
    @Test
    public void goaltest() {

        // Test creating new goal
        manager.addGoaltoLOG(goal);
        manager.addgoalamountfunded(0, 100);

        // Verify goal
        assertEquals(1000, goal.getGoalAmountNeeded());
        assertEquals(100, goal.getGoalAmountReceived());
        assertEquals(10, goal.percentReached());

        // Test changing goal name, amount needed, amount received, and percent reached
        manager.changegoalname(0, "New Car");
        assertEquals("New Car", goal.getGoalName());
        manager.changegoalamountneeeded(0, 10);
        assertEquals(10, goal.getGoalAmountNeeded());
        assertEquals(100, goal.getGoalAmountReceived());
        assertEquals(100, goal.percentReached());

        // Test toJsonObject and toString functions
        assertEquals("{\"Goal amount\":10,\"Goal name\":\"New Car\",\"Goal amount received\":100}", goal.toJsonObject().toString());
        assertEquals("[|Goal name: New Car, Goal amount: 10.0, Goal amount received: 100.0, " +
                "Goal Percent Reached: 100.0%|\n" + "                ]", manager.getGoalList().toString());

    }
}
