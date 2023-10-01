package ui;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Chart3DPanel;
import com.orsoncharts.data.StandardPieDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;

/**
 * Represents the graph maker that creates the datasets and makes the 3d graphs of the GUI
 */
public class GraphMaker {

    private Chart3DPanel revenuepiechartpanel;
    private Chart3DPanel expensepiechartpanel;
    private Chart3DPanel goalgraphpanel;

    private StandardPieDataset3D revenuedataset;
    private StandardPieDataset3D expensedataset;
    private StandardCategoryDataset3D<String, String, String> goaldataset;

    /**
     * MODIFIES: this
     * EFFECTS: makes a new revenue, expense, and goal dataset and creates their respective graphs with no data
     */
    public GraphMaker() {
        revenuedataset = new StandardPieDataset3D();
        expensedataset = new StandardPieDataset3D();
        goaldataset = new StandardCategoryDataset3D<>();

        // Creates an empty revenue piechart
        Chart3D chart1 = Chart3DFactory.createPieChart("Revenue Pie Chart",
                "Please enter a revenue before viewing.", revenuedataset);
        revenuepiechartpanel = new Chart3DPanel(chart1);

        // Creates an empty expense piechart
        Chart3D chart2 = Chart3DFactory.createPieChart("Expense Pie Chart",
                "Please enter an expense before viewing.", expensedataset);
        expensepiechartpanel = new Chart3DPanel(chart2);

        // Creates an empty goal chart
        Chart3D chart3 = Chart3DFactory.createStackedBarChart("Goal Graph", "Please enter a goal before "
                        + "viewing.", goaldataset, null,
                null, "Amount");
        goalgraphpanel = new Chart3DPanel(chart3);

    }

    /**
     * MODIFIES: this
     * EFFECTS: adds revenue data to the revenue dataset and updates the revenue pie chart
     */
    public void addtorevenuegraph(String name, double num) {
        revenuedataset.add(name, num);
        Chart3D chart = Chart3DFactory.createPieChart("Revenue Pie Chart", "", revenuedataset);
        revenuepiechartpanel = new Chart3DPanel(chart);
    }

    /**
     * MODIFIES: this
     * EFFECTS: adds expense data to the expense dataset and updates the expense pie chart
     */
    public void addtoexpensegraph(String name, double num) {
        expensedataset.add(name, num);
        Chart3D chart = Chart3DFactory.createPieChart("Expense Pie Chart", "", expensedataset);
        expensepiechartpanel = new Chart3DPanel(chart);
    }

    /**
     * MODIFIES: this
     * EFFECTS: adds goal data to the goal dataset and updates the goal graph
     */
    public void addtogoalgraph(double num, String name) {
        goaldataset.setValue(num, "Amount Needed", "Financial Goals", name);
        Chart3D chart = Chart3DFactory.createStackedBarChart("Goal Graph", "",
                goaldataset, null, null, "Amount");

        goalgraphpanel = new Chart3DPanel(chart);
    }

    /**
     * MODIFIES: this
     * EFFECTS: adds goal funded data to the goal dataset and updates the goal graph
     */
    public void addtogoalfunded(double num, String name) {
        goaldataset.setValue(num, "Amount Funded", "Financial Goals Funded", name);
        Chart3D chart = Chart3DFactory.createStackedBarChart("Goal Graph", "",
                goaldataset, null, null, "Amount");
        goalgraphpanel = new Chart3DPanel(chart);
    }

    public StandardPieDataset3D getRevenuedataset() {
        return revenuedataset;
    }

    public StandardPieDataset3D getExpensedataset() {
        return expensedataset;
    }

    public StandardCategoryDataset3D getGoaldataset() {
        return goaldataset;
    }

    public Chart3DPanel getRevenuepiechartpanel() {
        return revenuepiechartpanel;
    }

    public Chart3DPanel getExpensepiechartpanel() {
        return expensepiechartpanel;
    }

    public Chart3DPanel getGoalgraphpanel() {
        return goalgraphpanel;
    }
}
