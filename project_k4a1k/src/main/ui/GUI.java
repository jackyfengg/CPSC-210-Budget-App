//package ui;

/**
 * Represents an early design of the GUI with no implementation
 */

//import com.orsoncharts.Chart3D;
//import com.orsoncharts.Chart3DFactory;
//import com.orsoncharts.Chart3DPanel;
//import com.orsoncharts.data.StandardPieDataset3D;
//import net.miginfocom.swing.MigLayout;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GUI {
//    JFrame frame;
//
//    JPanel welcomepanel;
//    JPanel toppanel;
//    JPanel centerpanel;
//    JPanel westpanel;
//    JPanel expensepanel;
//    JPanel revenuepanel;
//    JPanel goalpanel;
//
//    JMenuBar menubar;
//    JMenu editmenu;
//    JMenu filemenu;
//    JMenuItem expensemenu;
//    JMenuItem revenuemenu;
//    JMenuItem goalmenu;
//    JMenuItem savemenu;
//    JMenuItem loadmenu;
//    JMenuItem exitmenu;
//    JMenuItem graphmenu;
//
//    JTextArea expensearea;
//    JTextArea revenuearea;
//    JTextArea goalarea;
//
//    JLabel welcomelabel;
//
//    JScrollPane expensescroll;
//    JScrollPane revenuescroll;
//    JScrollPane goalscroll;
//
//    // JScrollBar expensescrollbar;
//
//    JLabel expenselistlable;
//    JLabel revenuelistlable;
//    JLabel goalistlable;
//
//    JTextField expensefield = new JTextField(20);
//    JTextField revenuefield = new JTextField(20);
//    JTextField goalfield = new JTextField(20);
//
//    @SuppressWarnings("methodlength")
//    public GUI() {
//        JLabel title = new JLabel("Budgeting App");
//        title.setForeground(Color.white);
//        title.setFont(new Font("sans-serif", Font.BOLD, 24));
//
//        StandardPieDataset3D dataset = new StandardPieDataset3D();
//        dataset.add("Milk Products", 200);
//        dataset.add("Meat", 114);
//        dataset.add("Car", 114);
//        dataset.add("Water", 121);
//        Chart3D chart = Chart3DFactory.createPieChart("Pie Chart", "Subtitle", dataset);
//        Chart3DPanel chartPanel = new Chart3DPanel(chart);
//        chartPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        chartPanel.setBackground(Color.GRAY);
//
//        revenuelistlable = new JLabel();
//        revenuelistlable.setText("Enter Revenue List Name: ");
//        revenuelistlable.setForeground(Color.white);
//        revenuefield.setFont(new Font("sans-serif", Font.PLAIN, 14));
//
//        expenselistlable = new JLabel();
//        expenselistlable.setText("Enter Expense List Name: ");
//        expenselistlable.setForeground(Color.white);
//        revenuefield.setFont(new Font("sans-serif", Font.PLAIN, 14));
//
//        goalistlable = new JLabel();
//        goalistlable.setText("Enter Goal List Name: ");
//        goalistlable.setForeground(Color.white);
//        goalfield.setFont(new Font("sans-serif", Font.PLAIN, 14));
//
//        revenuepanel = new JPanel();
//        revenuepanel.setLayout(new MigLayout("wrap", "[][]", "[]"));
//        revenuepanel.setBackground(Color.GRAY);
//        revenuepanel.add(revenuelistlable);
//        revenuepanel.add(revenuefield);
//        revenuepanel.setVisible(false);
//
//        expensepanel = new JPanel();
//        expensepanel.setLayout(new MigLayout("wrap", "[][]", "[]"));
//        expensepanel.setBackground(Color.GRAY);
//        expensepanel.add(expenselistlable);
//        expensepanel.add(expensefield);
//        expensepanel.setVisible(false);
//
//        goalpanel = new JPanel();
//        goalpanel.setLayout(new MigLayout("wrap", "[][]", "[]"));
//        goalpanel.setBackground(Color.GRAY);
//        goalpanel.add(goalistlable);
//        goalpanel.add(goalfield);
//        goalpanel.setVisible(false);
//
//        toppanel = new JPanel();
//        toppanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        toppanel.setBackground(Color.orange);
//        toppanel.add(title);
//
//        centerpanel = new JPanel();
//        centerpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        centerpanel.setBackground(Color.GRAY);
//
//        centerpanel.add(expensepanel);
//        centerpanel.add(revenuepanel);
//        centerpanel.add(goalpanel);
//
//
//        welcomepanel = new JPanel();
//        welcomepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        welcomepanel.setBackground(Color.GRAY);
//
//        welcomelabel = new JLabel("Welcome!");
//        welcomelabel.setForeground(Color.white);
//        welcomelabel.setFont(new Font("sans-serif", Font.PLAIN, 30));
//        welcomepanel.add(welcomelabel);
//
//        westpanel = new JPanel();
//        westpanel.setLayout(new GridLayout(3, 1));
//        westpanel.setBackground(Color.DARK_GRAY);
//
//
//        Font font = new Font("sans-serif", Font.PLAIN, 20);
//        UIManager.put("Menu.font", font);
//        UIManager.put("MenuItem.font", font);
//
//        menubar = new JMenuBar();
//
//        editmenu = new JMenu("Edit");
//        filemenu = new JMenu("File");
//
//        savemenu = new JMenuItem("Save");
//        loadmenu = new JMenuItem("Load");
//        exitmenu = new JMenuItem("Exit");
//        exitmenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//
//        graphmenu = new JMenuItem("Graph");
//        graphmenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.remove(welcomepanel);
//                expensepanel.setVisible(false);
//                goalpanel.setVisible(false);
//                revenuepanel.setVisible(false);
//                chartPanel.setVisible(true);
//                frame.remove(centerpanel);
//                frame.add(chartPanel);
//
//                frame.validate();
//                frame.repaint();
//            }
//        });
//
//        expensemenu = new JMenuItem("New Expense");
//        expensemenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.remove(welcomepanel);
//                frame.remove(chartPanel);
//                frame.add(centerpanel);
//                revenuepanel.setVisible(false);
//                goalpanel.setVisible(false);
//                chartPanel.setVisible(false);
//                expensepanel.setVisible(true);
//
//                centerpanel.validate();
//                centerpanel.repaint();
//            }
//        });
//
//        revenuemenu = new JMenuItem("New Revenue");
//        revenuemenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.remove(welcomepanel);
//                frame.remove(chartPanel);
//                frame.add(centerpanel);
//                expensepanel.setVisible(false);
//                goalpanel.setVisible(false);
//                chartPanel.setVisible(false);
//                revenuepanel.setVisible(true);
//
//                centerpanel.validate();
//                centerpanel.repaint();
//            }
//        });
//
//        goalmenu = new JMenuItem("New Goal");
//        goalmenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.remove(welcomepanel);
//                frame.remove(chartPanel);
//                frame.add(centerpanel);
//                revenuepanel.setVisible(false);
//                expensepanel.setVisible(false);
//                chartPanel.setVisible(false);
//                goalpanel.setVisible(true);
//
//                centerpanel.validate();
//                centerpanel.repaint();
//            }
//        });
//
//        revenuearea = new JTextArea();
//        revenuearea.setColumns(60);
//        revenuearea.setLineWrap(true);
//        revenuearea.setRows(15);
//        revenuearea.setWrapStyleWord(true);
//        revenuearea.setEditable(false);
//        revenuescroll = new JScrollPane(revenuearea);
//
//        expensearea = new JTextArea();
//        expensearea.setColumns(60);
//        expensearea.setLineWrap(true);
//        expensearea.setRows(15);
//        expensearea.setWrapStyleWord(true);
//        expensearea.setEditable(false);
//        expensescroll = new JScrollPane(expensearea);
//
//        goalarea = new JTextArea();
//        goalarea.setColumns(60);
//        goalarea.setLineWrap(true);
//        goalarea.setRows(15);
//        goalarea.setWrapStyleWord(true);
//        goalarea.setEditable(false);
//        goalscroll = new JScrollPane(goalarea);
//
//        revenuepanel.add(revenuescroll, "span, grow");
//        expensepanel.add(expensescroll, "span, grow");
//        goalpanel.add(goalscroll, "span, grow");
//
//        filemenu.add(savemenu);
//        filemenu.add(loadmenu);
//        filemenu.addSeparator();
//        filemenu.add(exitmenu);
//
//        editmenu.add(graphmenu);
//        editmenu.addSeparator();
//        editmenu.add(revenuemenu);
//        editmenu.add(expensemenu);
//        editmenu.add(goalmenu);
//
//        menubar.add(filemenu);
//        menubar.add(editmenu);
//
//        frame = new JFrame("Budgeting Application");
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.setSize(800, 500);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(toppanel, BorderLayout.NORTH);
//        frame.add(welcomepanel, BorderLayout.CENTER);
//      //  frame.add(chartPanel, BorderLayout.CENTER);
//        frame.setJMenuBar(menubar);
//    }
//}
