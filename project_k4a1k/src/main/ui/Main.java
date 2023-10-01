package ui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

/**
 * Start of the program, creates a new GuiSelector class with a dark look and feel
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            new GuiSelector();
        } catch (Exception ex) {
            System.err.println("Failed to initialize");
        }
    }
}
