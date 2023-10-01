package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Represents a tests for expenses
class ExpensesTest {
    Controller manager;
    Expenses expense;
    EventLog eventLog = EventLog.getInstance();


    // Test and verify constructor for expenses
    @BeforeEach
    public void constructortest() {
        manager = new Controller();
        expense = new Expenses("Groceries");
        assertEquals("Groceries", expense.getExpensename());
        assertEquals(0, Expenses.getTotalOverallExpenses());
        }


    // Test the expense, event, and event-log classes
    @Test
    public void expensetest() {

        // Test the event log
        Event event1 = new Event("Test");
        Event event2 = new Event("Test");
        Event event3 = new Event("Test2");

        assertTrue(event1.equals(event2));
        assertFalse(event1.equals(event3));
        assertFalse(event1.equals(null));
        assertFalse(event1.equals("Test"));
        assertTrue(event1.equals(event1));

        Event event4 = new Event("Test");

        assertFalse(event1.equals(event4));

        eventLog.logEvent(event1);

        for (Event e : eventLog) {
            assertEquals("Created new controller", e.getDescription());
            break;
        }

        for (Event e : eventLog) {
            assertNotEquals("Sat Aug 05 18:37:20 PDT 2023", e.getDate());
            break;
        }

        for (Event e : eventLog) {
            assertNotEquals(797043704, e.hashCode());
            break;
        }

        for (Event e : eventLog) {
            assertNotEquals("Sat Aug 05 18:37:20 PDT 2023\n" +
                    "Test", e.toString());
        }

        eventLog.clear();

        for (Event e : eventLog) {
            assertEquals("Event log cleared.", e.getDescription());
            break;
        }

        EventLog eventLog2 = EventLog.getInstance();

        assertTrue(eventLog.equals(eventLog2));

        // Test adding expenses
        assertEquals(0, expense.getTotalExpenses());
        expense.addexpense(100);
        assertEquals(100, Expenses.getTotalOverallExpenses());
        expense.addexpense(200);
        expense.addexpense(300);
        assertEquals(600, Expenses.getTotalOverallExpenses());
        manager.addExpensetoLOE(expense);

        // Test toJsonObject and toString functions
        assertEquals(0.0, Revenues.revenuetoExpenseRatio());
        assertEquals("{\"Amounts\":[100,200,300],\"Expense Name\":\"Groceries\"}", expense.toJsonObject().toString());
        assertEquals("[|Expense Name: Groceries Amounts: [100.0, 200.0, 300.0]|\n" +
                "                ]", manager.getExpenseList().toString());
    }

}



