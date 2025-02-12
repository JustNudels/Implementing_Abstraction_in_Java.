/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package salescalculator;

/**
 *
 * @author kyleg
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Abstract class Item
abstract class Item {
    protected String name;
    protected double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract double calculateTotal();
}

// This is a Subclass for Phones
class Phone extends Item {
    private int quantitySold;

    public Phone(String name, double price, int quantitySold) {
        super(name, price);
        this.quantitySold = quantitySold;
    }

    @Override
    public double calculateTotal() {
        return price * quantitySold;
    }
}

// This is a Subclass for Repair Services
class RepairService extends Item {
    private int hours;

    public RepairService(String name, double price, int hours) {
        super(name, price);
        this.hours = hours;
    }

    @Override
    public double calculateTotal() {
        return price * hours;
    }
}

// Our Main class for the Swing application
public class SalesCalculator {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Sales Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(0, 1));

        // JPanel for Phones
        JPanel phonePanel = new JPanel(new GridLayout(3, 2));
        phonePanel.setBorder(BorderFactory.createTitledBorder("Phone Details"));
        JLabel phonePriceLabel = new JLabel("Price:");
        JTextField phonePriceField = new JTextField("500.0");
        JLabel phoneQuantityLabel = new JLabel("Quantity Sold:");
        JTextField phoneQuantityField = new JTextField("10");
        phonePanel.add(phonePriceLabel);
        phonePanel.add(phonePriceField);
        phonePanel.add(phoneQuantityLabel);
        phonePanel.add(phoneQuantityField);

        // JPanel for Repair Services
        JPanel repairPanel = new JPanel(new GridLayout(3, 2));
        repairPanel.setBorder(BorderFactory.createTitledBorder("Repair Service Details"));
        JLabel repairPriceLabel = new JLabel("Price per Hour:");
        JTextField repairPriceField = new JTextField("50.0");
        JLabel repairHoursLabel = new JLabel("Number of Hours:");
        JTextField repairHoursField = new JTextField("5");
        repairPanel.add(repairPriceLabel);
        repairPanel.add(repairPriceField);
        repairPanel.add(repairHoursLabel);
        repairPanel.add(repairHoursField);

        // Button to calculate sales
        JButton calculateButton = new JButton("Calculate Sales");

        // Text area for Displaying Results
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add an action listener to calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get phone details
                    double phonePrice = Double.parseDouble(phonePriceField.getText());
                    int phoneQuantity = Integer.parseInt(phoneQuantityField.getText());
                    Phone phone = new Phone("Phone", phonePrice, phoneQuantity);

                    // Get repair service details
                    double repairPrice = Double.parseDouble(repairPriceField.getText());
                    int repairHours = Integer.parseInt(repairHoursField.getText());
                    RepairService repair = new RepairService("Repair", repairPrice, repairHours);

                    // Calculate totals
                    double phoneTotal = phone.calculateTotal();
                    double repairTotal = repair.calculateTotal();

                    // Display results
                    resultArea.setText("--- Sales Results ---\n" +
                                       "Phones: Total Sales = \u20B1" + phoneTotal + "\n" +
                                       "Repairs: Total Sales = \u20B1" + repairTotal);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Error: Please enter valid numeric values.");
                }
            }
        });

        // Add components to frames
        frame.add(phonePanel);
        frame.add(repairPanel);
        frame.add(calculateButton);
        frame.add(scrollPane);

        // Set frame visibility to true
        frame.setVisible(true);
    }
}
