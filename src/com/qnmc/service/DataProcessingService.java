package qnmc.src.com.qnmc.service;

import qnmc.src.com.qnmc.presentation.MenuBar;

import javax.swing.*;
import java.util.Set;

public class DataProcessingService {

    private int k = 0;
    private Set<String> set;

    // Getters and setters for k, set:
    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void clearSet() {
        if (set != null) {
            set.clear();
        }
    }


    public String processDataThree(String input) {


        String bin[] = { "000", "001", "010", "011", "100", "101", "110", "111" };

        int i = Integer.parseInt(input);

        return bin[i];

    }

    public String processDataFour(String input) {

        String bin[] = { "0000", "0001", "0010", "0011", "0100", "0101",
                "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101",
                "1110", "1111" };

        int i = Integer.parseInt(input);

        return bin[i];

    }

    public String processDataFive(String input) {

        String bin[] = { "00000", "00001", "00010", "00011", "00100", "00101",
                "00110", "00111", "01000", "01001", "01010", "01011", "01100",
                "01101", "01110", "01111", "10000", "10001", "10010", "10011",
                "10100", "10101", "10110", "10111", "11000", "11001", "11010",
                "11011", "11100", "11101", "11110", "11111" };

        int i = Integer.parseInt(input);

        return bin[i];

    }

    public void initializeNewSession() {
        if (set != null) {
            set.clear(); // Clear existing data
        }

        // Get and validate the number of bits from the user
        String input = JOptionPane.showInputDialog("Enter the boolean bits (3 to 5): ");
        try {
            int newBits = Integer.parseInt(input); // Parse user input
            if (newBits < 3 || newBits > 5) { // Validate range
                JOptionPane.showMessageDialog(null,
                        "Invalid input. Please enter a value between 3 and 5.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop if invalid
            }
            MenuBar.bits = newBits; // Update the static field
            System.out.println("Updated bits to: " + MenuBar.bits); // Log to the terminal
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number format. Please enter a valid integer.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            MenuBar.bits = 2; // Fallback default value
            System.out.println("Invalid input. Setting bits to default: " + MenuBar.bits); // Log default behavior
        }
    }

    public void displayAlgorithmInfo() {
        // Objective string (used to be in `MenuBar`)
        String objective = "The Quine McCluskey algorithm (or the method of prime implicants) "
                + "\nis a method used for minimization of boolean functions which was developed by W.V. "
                + "\nQuine and Edward J. McCluskey. It is functionally identical to Karnaugh mapping, "
                + "\nbut the tabular form makes it more efficient for use in computer algorithms, and"
                + "\nit also gives a deterministic way to check that the minimal form of a Boolean "
                + "\nfunction has been reached. It is sometimes referred to as the tabulation method.";

        // Display the dialog with the description
        JOptionPane.showMessageDialog(null, objective,
                "Quine McCluskey Prime Implicant Generator",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayDeveloperInfo(int developerId) {
        // Determine the developer information based on the ID passed
        String developerInfo = switch (developerId) {
            case 1 -> "Jane Smith"; // Developer 1's name
            case 2 -> "John Doe";   // Developer 2's name
            case 3 -> "Ashok Kumar"; // Developer 3's name
            default -> "Unknown Developer"; // Fallback
        };

        // Display the dialog with developer information
        JOptionPane.showMessageDialog(null, developerInfo,
                "Quine McCluskey Prime Implicant Generator",
                JOptionPane.INFORMATION_MESSAGE);
    }



}
