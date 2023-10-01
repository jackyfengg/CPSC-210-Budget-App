package ui;

import model.*;
import model.Event;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import persistence.JsonLoader;
import persistence.JsonSaver;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Represents the UI interface that the user used to use to create, modify, visualize and save, and load budgets
 */

public class GuiSelector {
    private static final String JSON_STORE = "./data/Budget.json";
    private JsonSaver jsonSaver;
    private JsonLoader jsonLoader;

    JLabel title;
    Font font;

    int goalindex;

    Revenues revenue;
    Expenses expense;
    Goal goal;

    String gname;

    Controller manager;
    GraphMaker graphs;

    JFrame frame;

    JPanel toppanel;
    JPanel centerpanel;
    JPanel expensepanel;
    JPanel revenuepanel;
    JPanel goalpanel;
    JPanel welcomepanel;

    JMenuBar menubar;

    JMenu rfunction;
    JMenu efunction;
    JMenu gfunction;

    JMenu editmenu;
    JMenu filemenu;

    JMenuItem goalmenu;
    JMenuItem goalsave;
    JMenuItem goalclear;
    JMenuItem goaladd;

    JMenuItem expensemenu;
    JMenuItem expensesave;
    JMenuItem expenseclear;

    JMenuItem revenuemenu;
    JMenuItem revenuesave;
    JMenuItem revenueclear;

    JMenuItem savemenu;
    JMenuItem loadmenu;
    JMenuItem exitmenu;
    JMenuItem revenuegraphmenu;
    JMenuItem expensegraphmenu;
    JMenuItem goalgraphmenu;

    JTextArea expensearea;
    JTextArea revenuearea;
    JTextArea goalarea;

    JScrollPane expensescroll;
    JScrollPane revenuescroll;
    JScrollPane goalscroll;

    JLabel welcomelabel;
    JLabel expenselistlable;
    JLabel revenuelistlable;
    JLabel goallistlable;

    JTextField revenuetextfield = new JTextField(20);
    JTextField revenueamounttextfield = new JTextField(20);

    JTextField expensetextfield = new JTextField(20);
    JTextField expenseamounttextfield = new JTextField(20);

    JTextField goaltextfield = new JTextField(20);
    JTextField goaltextfield2 = new JTextField(20);
    JTextField goalamounttextfield = new JTextField(20);
    JTextField goalamounttextfield2 = new JTextField(20);

    /**
     * EFFECTS: Makes a GUI that the user will use to create new revenues, expenses, and goals.
     * User will be able to visualize their budget through 3d graphs
     * Lastly, users will be able to save and load their budget
     * When the user exits the program will print a log event of their actions
     */
    public GuiSelector() throws NumberFormatException, StringIndexOutOfBoundsException {

        // Initializes everything
        initializeFrameandPanels();
        initializemenu();

        // Sets up everything
        setupframe();
        setuppanel();
        setupmenu();
        setuptextareas();
        setuptextareas2();

        // Adds the actions when the buttons are pressed
        exitmenuaction();
        graphmenuactions();
        transcationmenuactions();
        transcationmenuactions2();
        addgoalfundedmenuaction();
        savemenuaction();
        loadmenuaction();

        // Adds the action of creating a revenue, expense, or goal with their name and amounts
        addnewrevenue();
        addnewexpense();
        addnewgoal();
        addgoalfunded();

        // Adds the action of save and clearing a revenue, expense, or goal
        saveandclearrevenue();
        saveandclearexpense();
        saveandcleargoal();

        // Reloads the GUI
        reloadgui();
    }

    /**
     * EFFECTS: loads file to manager, throws IOException if unable to read file
     */
    private void loadBudget() {
        try {
            manager = jsonLoader.load();
          //  System.out.println("Loaded budget from " + JSON_STORE);
        } catch (IOException e) { // Throws IOException if unable to load
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    /**
     * EFFECTS: saves manager to file, throws FileNotFoundException if file not found
     */
    private void saveBudget() {
        try {
            jsonSaver.open();
            jsonSaver.write(manager);
            jsonSaver.close();
         //   System.out.println("Saved budget to " + JSON_STORE);
        } catch (FileNotFoundException e) { // Throws FileNotFoundException if unable to save to file
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /**
     * EFFECTS: Reloads the entire GUI
     */
    private void reloadgui() {

        welcomepanel.validate(); // Refreshes the Welcome panel
        welcomepanel.repaint();

        revenuegraphmenu.validate(); // Refreshes the revenue graph menu panel
        revenuegraphmenu.repaint();

        expensegraphmenu.validate(); // Refreshes the expense graph menu panel
        expensegraphmenu.repaint();

        goalgraphmenu.validate(); // Refreshes the goal graph menu panel
        goalgraphmenu.repaint();

        revenuepanel.validate(); // Refreshes the revenue panel
        revenuepanel.repaint();

        expensepanel.validate(); // Refreshes the expense panel
        expensepanel.repaint();

        goalpanel.validate(); // Refreshes the goal panel
        goalpanel.repaint();

        frame.validate(); // Refreshes the frmae
        frame.repaint();
    }

    /**
     * MODIFIES: this
     * EFFECTS: removes everything from the frame and menu-bar, and sets the visibility of all the panels to false
     */
    private void state0() {
        // Removes every panel from the frame
        frame.remove(welcomepanel);
        frame.remove(centerpanel);
        frame.remove(graphs.getExpensepiechartpanel());
        frame.remove(graphs.getGoalgraphpanel());
        frame.remove(graphs.getRevenuepiechartpanel());

        // Removes the revenue, expense, and goal functions from the menu-bar
        menubar.remove(rfunction);
        menubar.remove(efunction);
        menubar.remove(gfunction);

        // Sets all the panels to be not visible
        expensepanel.setVisible(false);
        goalpanel.setVisible(false);
        revenuepanel.setVisible(false);

        graphs.getExpensepiechartpanel().setVisible(false);
        graphs.getRevenuepiechartpanel().setVisible(false);
        graphs.getGoalgraphpanel().setVisible(false);
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the frame and panels
     */
    private void initializeFrameandPanels() {
        // Initialize the misc things
        title = new JLabel("Budgeting Application");
        font = new Font("sans-serif", Font.PLAIN, 15);
        jsonSaver = new JsonSaver(JSON_STORE);
        jsonLoader = new JsonLoader(JSON_STORE);
        manager = new Controller();
        graphs = new GraphMaker();

        // Initialize the labels
        revenuelistlable = new JLabel();
        expenselistlable = new JLabel();
        goallistlable = new JLabel();
        welcomelabel = new JLabel("Welcome! Use the Edit menu to start.");

        // Initialize the panels
        revenuepanel = new JPanel();
        expensepanel = new JPanel();
        goalpanel = new JPanel();
        toppanel = new JPanel();
        centerpanel = new JPanel();
        welcomepanel = new JPanel();

        frame = new JFrame("Budgeting Application");
    }

    /**
     * MODIFIES: this
     * EFFECTS: initializes the menu-bar and buttons
     */
    private void initializemenu() {
        //  Initialize the menu bar
        menubar = new JMenuBar();

        // Initialize the menus
        rfunction = new JMenu("Revenue");
        efunction = new JMenu("Expense");
        gfunction = new JMenu("Goal");

        editmenu = new JMenu("Edit");
        filemenu = new JMenu("File");

        // Initialize the menu buttons
        expensesave = new JMenuItem("Save Expense");
        expenseclear = new JMenuItem("Clear Expense");

        revenuesave = new JMenuItem("Save Revenue");
        revenueclear = new JMenuItem("Clear Revenue");

        goalsave = new JMenuItem("Save Goal");
        goalclear = new JMenuItem("Clear Goal");
        goaladd = new JMenuItem("Add Amount Funded");

        savemenu = new JMenuItem("Save");
        loadmenu = new JMenuItem("Load");
        exitmenu = new JMenuItem("Exit");

        revenuegraphmenu = new JMenuItem("Revenue Pie Chart");
        revenuemenu = new JMenuItem("New Revenue");

        expensegraphmenu = new JMenuItem("Expense Pie Chart");
        expensemenu = new JMenuItem("New Expense");

        goalgraphmenu = new JMenuItem("Goal Graph");
        goalmenu = new JMenuItem("New Goal");
    }

    /**
     * MODIFIES: this
     * EFFECTS: Sets the frame up, adds the welcome panel in frame, and applies the uniform font for the menu and label
     */
    private void setupframe() {

        // Sets up the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(menubar);
        frame.add(toppanel, BorderLayout.NORTH);
        frame.add(welcomepanel, BorderLayout.CENTER);

        // Applies a uniform font to the menus and labels
        title.setFont(new Font("sans-serif", Font.BOLD, 24));
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("Label.font", new Font("sans-serif", Font.PLAIN, 14));
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up the panel with MigLayout and add their respective label and text-fields
     */
    private void setuppanel() {
        // Sets up the list label
        revenuelistlable.setText("Enter Revenue List Name: ");
        expenselistlable.setText("Enter Expense List Name: ");
        goallistlable.setText("Enter Goal List Name: ");
        welcomelabel.setFont(new Font("sans-serif", Font.PLAIN, 30));

        // Sets up the panels
        revenuepanel.setLayout(new MigLayout("wrap", "[][]", "[]"));
        revenuepanel.add(revenuelistlable);
        revenuepanel.add(revenuetextfield);
        revenuepanel.setVisible(false);

        expensepanel.setLayout(new MigLayout("wrap", "[][]", "[]"));
        expensepanel.add(expenselistlable);
        expensepanel.add(expensetextfield);
        expensepanel.setVisible(false);

        goalpanel.setLayout(new MigLayout("wrap", "[][]", "[]"));
        goalpanel.setVisible(false);

        toppanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        toppanel.add(title);

        centerpanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        centerpanel.add(expensepanel);
        centerpanel.add(revenuepanel);
        centerpanel.add(goalpanel);

        welcomepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        welcomepanel.add(welcomelabel);
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up the menu with their respective menu buttons and adds it to the menu-bar
     */
    private void setupmenu() {
        menubar.setFont(new Font("sans-serif", Font.PLAIN, 15));
        filemenu.add(savemenu);
        filemenu.add(loadmenu);
        filemenu.addSeparator();
        filemenu.add(exitmenu);

        editmenu.add(revenuegraphmenu);
        editmenu.add(expensegraphmenu);
        editmenu.add(goalgraphmenu);
        editmenu.addSeparator();
        editmenu.add(revenuemenu);
        editmenu.add(expensemenu);
        editmenu.add(goalmenu);

        rfunction.add(revenuesave);
        rfunction.add(revenueclear);

        efunction.add(expensesave);
        efunction.add(expenseclear);

        gfunction.add(goalsave);
        gfunction.add(goalclear);
        gfunction.add(goaladd);

        menubar.add(filemenu);
        menubar.add(editmenu);

    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up the revenue and expense text areas and adds it to their respective panels
     */
    private void setuptextareas() {
        revenuearea = new JTextArea();
        revenuearea.setColumns(60);
        revenuearea.setLineWrap(true);
        revenuearea.setRows(15);
        revenuearea.setWrapStyleWord(true);
        revenuearea.setEditable(false);
        revenuearea.setBackground(Color.DARK_GRAY);
        revenuescroll = new JScrollPane(revenuearea);
        revenuescroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        revenuepanel.add(revenuescroll, "span, grow");

        expensearea = new JTextArea();
        expensearea.setColumns(60);
        expensearea.setLineWrap(true);
        expensearea.setRows(15);
        expensearea.setWrapStyleWord(true);
        expensearea.setEditable(false);
        expensearea.setBackground(Color.DARK_GRAY);
        expensescroll = new JScrollPane(expensearea);
        expensescroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        expensepanel.add(expensescroll, "span, grow");
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up the goal text area and adds it to their respective panel
     */
    private void setuptextareas2() {
        goalarea = new JTextArea();
        goalarea.setColumns(60);
        goalarea.setLineWrap(true);
        goalarea.setRows(15);
        goalarea.setWrapStyleWord(true);
        goalarea.setEditable(false);
        goalarea.setBackground(Color.DARK_GRAY);
        goalscroll = new JScrollPane(goalarea);
        goalscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        goalpanel.add(goalscroll, "span, grow");
    }

    /**
     * MODIFIES: this
     * EFFECTS: exits the program when the user clicks the exit menu button and prints out the entry log
     */
    private void exitmenuaction() {
        exitmenu.addActionListener(e -> {

            System.out.println("Start of EventLog : \n");
            for (Event eventprint : EventLog.getInstance()) { // Prints out a log of events
                System.out.println(eventprint.getDate() + " " + eventprint.getDescription() + "\n");
            }

            System.out.println("End of EventLog.");

            System.exit(0); // exits the program
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets all other panels to be false and adds a visible 3d graphs to the frame depending on user input
     */
    private void graphmenuactions() {
        revenuegraphmenu.addActionListener(e -> {

            state0(); // resets everything to zero state
            graphs.getRevenuepiechartpanel().setVisible(true);
            frame.add(graphs.getRevenuepiechartpanel());
            reloadgui();
        });

        expensegraphmenu.addActionListener(e -> {

            state0(); // resets everything to zero state
            graphs.getExpensepiechartpanel().setVisible(true);
            frame.add(graphs.getExpensepiechartpanel());
            reloadgui();
        });

        goalgraphmenu.addActionListener(e -> {

            state0(); // resets everything to zero state
            graphs.getGoalgraphpanel().setVisible(true);
            frame.add(graphs.getGoalgraphpanel());
            reloadgui();
        });
    }


    /**
     * MODIFIES: this
     * EFFECTS: sets all other panels to be false and adds a revenue or expense panel
     * to the frame depending on user input
     */
    private void transcationmenuactions() {
        revenuemenu.addActionListener(e -> {

            state0();
            frame.add(centerpanel);
            menubar.add(rfunction);
            revenuepanel.setVisible(true);

            reloadgui();
        });

        expensemenu.addActionListener(e -> {


            state0();
            frame.add(centerpanel);
            menubar.add(efunction);
            expensepanel.setVisible(true);
            reloadgui();
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets all other panels to be false and adds and sets up a goal panel to the frame
     */
    private void transcationmenuactions2() {
        goalmenu.addActionListener(e -> {
            state0();
            goalpanel.remove(goalamounttextfield);
            goalpanel.remove(goaltextfield2);
            goalpanel.remove(goalamounttextfield2);
            goallistlable.setText("Enter Goal Name: ");
            goalpanel.add(goallistlable);
            goalpanel.add(goaltextfield);
            goalpanel.add(goalscroll, "span, grow");
            frame.add(centerpanel);
            menubar.add(gfunction);
            goalpanel.setVisible(true);

            reloadgui();
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: makes a new revenue with a user specified name and amounts
     */
    private void addnewrevenue() {
        revenuetextfield.addActionListener(e -> {

            String name = revenuetextfield.getText();
            revenue = new Revenues(revenuetextfield.getText());
            revenuelistlable.setText("Enter Revenue Amount: "); // Removes the old label and adds a new one

            // Removes the old text field and text area and adds a new one in
            revenuepanel.remove(revenuetextfield);
            revenuepanel.remove(revenuescroll);
            revenueamounttextfield.setEditable(true);
            revenuepanel.add(revenueamounttextfield);
            revenuepanel.add(revenuescroll, "span, grow");
            revenuearea.append("|Revenue Name: " + name + " Amounts: [");

            reloadgui();
        });

        revenueamounttextfield.addActionListener(e -> {
            try {

                revenue.addrevenue(Double.parseDouble(revenueamounttextfield.getText()));
                revenuearea.append((Double.parseDouble(revenueamounttextfield.getText())) + ", ");
                revenueamounttextfield.setText("");
            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
                // ignored exceptions
            }
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: saves the revenue and clears the revenue from the GUI
     */
    private void saveandclearrevenue() {
        revenuesave.addActionListener(e -> {

            manager.addRevenuetoLOR(revenue);
            graphs.addtorevenuegraph(revenue.getrevenuename(), revenue.getTotalRevenue());
            revenueamounttextfield.setEditable(false);
        });

        revenueclear.addActionListener(e -> {

            revenuearea.append("]|");
            revenuepanel.remove(revenueamounttextfield);
            revenuepanel.remove(revenuescroll);
            revenuearea.append("\n\n");
            revenuelistlable.setText("Enter Revenue List Name: ");
            revenuetextfield.setText("");
            revenueamounttextfield.setText("");
            revenuepanel.add(revenuetextfield);
            revenuepanel.add(revenuescroll, "span, grow");

            reloadgui();
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: makes a new expense with a user specified name and amounts
     */
    private void addnewexpense() {
        expensetextfield.addActionListener(e -> {

            String name = expensetextfield.getText();
            expense = new Expenses(expensetextfield.getText());
            expenselistlable.setText("Enter Expense Amount: ");
            expensepanel.remove(expensetextfield);
            expensepanel.remove(expensescroll);
            expensepanel.add(expenseamounttextfield);
            expensepanel.add(expensescroll, "span, grow");
            expensearea.append("|Expense Name: " + name + " Amounts: [");

            reloadgui();

        });

        expenseamounttextfield.addActionListener(e -> {
            try {

                expense.addexpense(Double.parseDouble(expenseamounttextfield.getText()));
                expensearea.append(Double.parseDouble(expenseamounttextfield.getText()) + ", ");
                expenseamounttextfield.setText("");
            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
                // ignored exceptions
            }
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: saves the expense and clears the expense from the GUI
     */
    private void saveandclearexpense() {
        expensesave.addActionListener(e -> {

            manager.addExpensetoLOE(expense);
            graphs.addtoexpensegraph(expense.getExpensename(), expense.getTotalExpenses());
        });

        expenseclear.addActionListener(e -> {

            expensearea.append("]|");
            expensepanel.remove(expenseamounttextfield);
            expensepanel.remove(expensescroll);
            expensearea.append("\n\n");
            expenselistlable.setText("Enter Expense List Name: ");
            expensetextfield.setText("");
            expenseamounttextfield.setText("");
            expensepanel.add(expensetextfield);
            expensepanel.add(expensescroll, "span, grow");

            reloadgui();
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: makes a new goal with a user specified name and amount needed
     */
    private void addnewgoal() {
        goaltextfield.addActionListener(e -> {

            gname = goaltextfield.getText();
            goaltextfield.setEditable(false);
            goallistlable.setText("Enter Goal Amount Needed: ");
            goalpanel.remove(goaltextfield);
            goalpanel.remove(goalarea);
            goalpanel.add(goalamounttextfield);
            goalpanel.add(goalscroll, "span, grow");
            goalarea.append("|Goal name: " + gname + ", ");

            reloadgui();
        });

        goalamounttextfield.addActionListener(e -> {
            try {

                goal = new Goal(gname, (Double.parseDouble(goalamounttextfield.getText())));
                goalarea.append("Goal amount: " + Double.parseDouble(goalamounttextfield.getText()) + ", ");
                goalamounttextfield.setEditable(false);
            } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
                // ignored exceptions
            }
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: saves the goal and clears the goal from the GUI
     */
    private void saveandcleargoal() {
        goalsave.addActionListener(e -> {

            manager.addGoaltoLOG(goal);
            graphs.addtogoalgraph(goal.getGoalAmountNeeded(), goal.getGoalName());
        });

        goalclear.addActionListener(e -> {

            goalarea.append("Goal amount received: 0.0, Goal Percent Reached: 0.0%|");
            goaltextfield.setEditable(true);
            goalpanel.remove(goalamounttextfield2);
            goalpanel.remove(goaltextfield2);
            goalpanel.remove(goalamounttextfield);
            goalpanel.remove(goalscroll);
            goalarea.append("\n\n");
            goallistlable.setText("Enter Goal Name: ");
            goaltextfield.setText("");
            goalamounttextfield.setText("");
            goalamounttextfield.setEditable(true);
            goalpanel.add(goaltextfield);
            goalpanel.add(goalscroll, "span, grow");

            reloadgui();
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets all other panels to be false and adds and sets up a goal add funded panel to the frame
     */
    private void addgoalfundedmenuaction() {
        goaladd.addActionListener(e -> {

            goalpanel.remove(goalscroll);
            goalpanel.remove(goaltextfield);
            goalpanel.remove(goalamounttextfield2);
            goalpanel.remove(goalamounttextfield);
            goalpanel.remove(goalamounttextfield2);
            goallistlable.setText("Enter Goal Index: ");
            goalamounttextfield2.setText("");
            goaltextfield2.setText("");
            goalpanel.add(goaltextfield2);
            goalpanel.add(goalscroll, "span, grow");

            reloadgui();
        });
    }

    /**
     * REQUIRES: user input > 0, goallist.size() > 0
     * MODIFIES: this, Controller
     * EFFECTS: Adds an amount funded to a goal in the goal list depending on user input
     */
    private void addgoalfunded() {
        goaltextfield2.addActionListener(e -> {

            goalindex = Integer.parseInt(goaltextfield2.getText());
            goallistlable.setText("Enter Goal Amount Funded: ");
            goalpanel.remove(goaltextfield2);
            goalpanel.remove(goalscroll);
            goalamounttextfield2.setEditable(true);
            goalpanel.add(goalamounttextfield2);
            goalpanel.add(goalscroll, "span, grow");

            reloadgui();
        });

        goalamounttextfield2.addActionListener(e -> {
            try {

                manager.addgoalamountfunded(goalindex, Double.parseDouble(goalamounttextfield2.getText()));
                goalamounttextfield2.setEditable(false);
                graphs.addtogoalfunded(manager.getGoalList().get(goalindex).getGoalAmountReceived(),
                        manager.getGoalList().get(goalindex).getGoalName());
            } catch (NumberFormatException | StringIndexOutOfBoundsException | NullPointerException ignored) {
                // ignored exceptions
            }
        });
    }

    /**
     * EFFECTS: calls the saveBudget() method when the menu button is pressed
     */
    private void savemenuaction() {
        savemenu.addActionListener(e -> saveBudget());
    }

    /**
     * EFFECTS: calls the loadBudget() method when the menu button is pressed
     * and loads all the data into the graphs' datasets
     */
    private void loadmenuaction() {
        loadmenu.addActionListener(e -> {

            loadBudget();
            state0();
            graphs = new GraphMaker();

            for (Revenues r : manager.getRevenuelist()) {
                graphs.addtorevenuegraph(r.getrevenuename(), r.getTotalRevenue());
                revenuearea.append("\n" + r + "\n");
            }

            for (Expenses ex : manager.getExpenseList()) {
                graphs.addtoexpensegraph(ex.getExpensename(), ex.getTotalExpenses());
                expensearea.append("\n" + ex + "\n");
            }

            for (Goal g : manager.getGoalList()) {
                graphs.addtogoalgraph(g.getGoalAmountNeeded(), g.getGoalName());
                goalarea.append("\n" + g + "\n");
            }

            for (Goal g : manager.getGoalList()) {
                graphs.addtogoalfunded(g.getGoalAmountReceived(), g.getGoalName());
            }

            reloadgui();
        });
    }

}

