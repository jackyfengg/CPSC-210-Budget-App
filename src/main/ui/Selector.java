//package ui;
//
/**
 * Represents the UI console interface that the user used to use to create, modify, save, and load budgets
 */
//import model.Controller;
//import model.Expenses;
//import model.Goal;
//import model.Revenues;
//import persistence.JsonLoader;
//import persistence.JsonSaver;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Selector {
//    private static final String JSON_STORE = "./data/Budget.json";
//    private JsonSaver jsonSaver;
//    private JsonLoader jsonLoader;
//    private static final String seperator = "-----------------------------------------------------------------";
//    Controller manager;
//
    // EFFECTS: Creates a new expense list, income list, and goal list
    // then creates a new expense, income, or goal type depending on user input and adds it to their respective list
    // If the user creates a goal, they will be able to fund it by an amount.
    // When finished the user will be able to see a financial summary of their expenses, incomes, and financial goals.
    // In addition, the user can save and load their budgets and modify them from there.
//    @SuppressWarnings("methodlength")
//    public Selector() throws FileNotFoundException, NumberFormatException, StringIndexOutOfBoundsException {
//        Scanner sc = new Scanner(System.in);
//        manager = new Controller();
//        jsonSaver = new JsonSaver(JSON_STORE);
//        jsonLoader = new JsonLoader(JSON_STORE);
//
//        while (true) {  // continuously gets the user console input
//
//            System.out.println("Do you want to add a new expense list, revenue list, goal?");
//            String response;
//
//            try {
//                response = sc.nextLine().toLowerCase().substring(0, 1);
//            } catch (StringIndexOutOfBoundsException e) {
//                break;
//            }
//
//            switch (response) {
//                case "e": // if the user input is "e" make new expense
//                    System.out.print("Enter Expense List Name: ");
//                    String expensename = sc.nextLine();
//                    Expenses e = new Expenses(expensename);
//                    System.out.print("Enter expense amount or Quit: ");
//                    String amount = sc.nextLine();
//
//                    while (amount.length() == 0 || amount.toLowerCase().charAt(0) != 'q') {
//                        try {
//                            e.addexpense(Double.parseDouble(amount));
//                        } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
//                            // ignored exceptions and restart the while loop
//                        }
//
//                        System.out.print("Enter expense amount or Quit: ");
//                        amount = sc.nextLine();
//                    }
//
//                    manager.addExpensetoLOE(e);
//                    System.out.println(seperator);
//                    continue;
//
//
//                case "r": // if the user input is "r" make new revenue
//                    System.out.print("Enter revenue List Name: ");
//                    String revenuename = sc.nextLine();
//                    Revenues r = new Revenues(revenuename);
//                    System.out.print("Enter revenue amount or Quit: ");
//                    String amount2 = sc.nextLine();
//
//                    while (amount2.length() == 0 || amount2.toLowerCase().charAt(0) != 'q') {
//                        try {
//                            r.addrevenue(Double.parseDouble(amount2));
//                        } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
//                            // ignored exceptions and restart the while loop
//                        }
//
//                        System.out.print("Enter revenue amount or Quit: ");
//                        amount2 = sc.nextLine();
//                    }
//
//                    manager.addRevenuetoLOR(r);
//                    System.out.println(seperator);
//                    continue;
//
//                case "g": // if the user input is "g" make new goal
//                    System.out.print("Enter goal name: ");
//                    String response8 = sc.nextLine();
//                    System.out.print("Enter goal amount: ");
//                    double response9 = Double.parseDouble(sc.nextLine());
//                    Goal g = new Goal(response8, response9);
//                    manager.addGoaltoLOG(g);
//                    continue;
//
//                case "a": // if the user input is "a" add amount funded to an existing goal
//                    System.out.println("Input goal index position: ");
//                    int response2 = Integer.parseInt(sc.nextLine());
//                    System.out.println("Input amount to add: ");
//                    double response3 = Double.parseDouble(sc.nextLine());
//                    manager.addgoalamountfunded(response2, response3);
//                    System.out.println(seperator);
//                    continue;
//
//                case "c": // if the user input is "c" change the name of an existing goal
//                    System.out.println("Input goal index position: ");
//                    int response4 = Integer.parseInt(sc.nextLine());
//                    System.out.println("Input new amount needed: ");
//                    double response5 = Double.parseDouble(sc.nextLine());
//                    manager.changegoalamountneeeded(response4, response5);
//                    System.out.println(seperator);
//                    continue;
//
//                case "n": // if the user input is "c" change the amount needed of an existing goal
//                    System.out.println("Input goal index position: ");
//                    int response6 = Integer.parseInt(sc.nextLine());
//                    System.out.println("Input new goal name: ");
//                    String response7 = sc.nextLine();
//                    manager.changegoalname(response6, response7);
//                    System.out.println(seperator);
//                    continue;
//
//                case "h": // if the user input is "h" print out a list of commands
//                    System.out.println(seperator);
//                    System.out.println("E - Create new expense\n"
//                            + "R - Create new revenue\n" + "G - Create new goal\n" + "A - Add goal amount funded\n"
//                            + "C - Change goal name\n" + "N - Change goal amount needed\n" + "S - Save budget\n"
//                            + "L - Load budget\n" + "B - Create financial summary");
//                    System.out.println(seperator);
//                    continue;
//
//                case "s": // if the user input is "s" save the budget to the budget json file
//                    saveBudget();
//                    System.out.println(seperator);
//                    continue;
//
//                case "l": // if the user input is "l" load the budget from the budget json file
//                    loadBudget();
//                    System.out.println(seperator);
//                    continue;
//
//                case "b": // if the user input is "b" load a financial summary of the current budget
//                    System.out.println(seperator);
//                    System.out.println("Financial Goals: ");
//                    for (Goal goals: manager.getGoalList()) {
//                        System.out.println(goals.consoletoString());
//                    }
//
//                    System.out.println("List of Expenses: ");
//                    for (Expenses expenses: manager.getExpenseList()) {
//                        System.out.println(expenses.toString());
//                    }
//
//                    System.out.println("List of Revenues: ");
//                    for (Revenues revenues: manager.getRevenuelist()) {
//                        System.out.println(revenues.toString());
//                    }
//
//                    System.out.println("Total Expenses: $" + -1 * Expenses.getTotalOverallExpenses());
//                    System.out.println("Total Revenue: $" + Revenues.getTotalOverallRevenue());
//                    System.out.println("Budget: $" + (Revenues.getTotalOverallRevenue() +
//                            Expenses.getTotalOverallExpenses() * -1));
//                    System.out.println("Revenue to Expense Ratio: " + Revenues.revenuetoExpenseRatio() + "%");
//                    System.out.println(seperator);
//                    continue;
//
//                case "q": // if the user input is "q" terminate the program and print out a financial summary
//                    break;
//            }
//            break;
//        }
//
//        System.out.println(seperator);
//        System.out.println("Financial Goals: ");
//        for (Goal goals: manager.getGoalList()) {
//            System.out.println(goals.consoletoString());
//        }
//
//        System.out.println("List of Expenses: ");
//        for (Expenses expenses: manager.getExpenseList()) {
//            System.out.println(expenses.toString());
//        }
//
//        System.out.println("List of Revenues: ");
//        for (Revenues revenues: manager.getRevenuelist()) {
//            System.out.println(revenues.toString());
//        }
//
//        System.out.println("Total Expenses: $" + -1 * Expenses.getTotalOverallExpenses());
//        System.out.println("Total Revenue: $" + Revenues.getTotalOverallRevenue());
//        System.out.println("Budget: $" + (Revenues.getTotalOverallRevenue() +
//                Expenses.getTotalOverallExpenses() * -1));
//        System.out.println("Revenue to Expense Ratio: " + Revenues.revenuetoExpenseRatio() + "%");
//
//    }
//
//    // EFFECTS: loads file to manager, throws IOException if unable to read file
//    private void loadBudget() {
//        try {
//            manager = jsonLoader.load();
//            System.out.println("Loaded budget from " + JSON_STORE);
//        } catch (IOException e) { // Throws IOException if unable to load
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    // EFFECTS: saves manager to file, throws FileNotFoundException if file not found
//    private void saveBudget() {
//        try {
//            jsonSaver.open();
//            jsonSaver.write(manager);
//            jsonSaver.close();
//            System.out.println("Saved budget to " + JSON_STORE);
//        } catch (FileNotFoundException e) { // Throws FileNotFoundException if unable to save to file
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//}
