package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Represents a tests for revenues
public class RevenueTest {
    Controller manager;
    Revenues revenue;

    // Test and verify constructor for revenues
    @BeforeEach
    public void constructortest() {
        manager = new Controller();
        revenue = new Revenues("Salary");
        assertEquals("Salary", revenue.getrevenuename());
        assertEquals(0, Revenues.getTotalOverallRevenue());
    }

    @Test
    // Test the revenue class
    public void revenuetest() {

        // Test adding revenues
        assertEquals(0, revenue.getTotalRevenue());
        revenue.addrevenue(100);
        assertEquals(100, Revenues.getTotalOverallRevenue());
        revenue.addrevenue(200);
        revenue.addrevenue(300);
        assertEquals(600, Revenues.getTotalOverallRevenue());
        manager.addRevenuetoLOR(revenue);

        // Test toJsonObject and toString functions
        assertEquals("{\"Amounts\":[100,200,300],\"Revenue Name\":\"Salary\"}", revenue.toJsonObject().toString());
        assertEquals("[|Revenue Name: Salary Amounts: [100.0, 200.0, 300.0]|\n" +
                "               ]", manager.getRevenuelist().toString());
    }
}
