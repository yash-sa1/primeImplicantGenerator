package qnmc.src.com.qnmc.presentation;

import qnmc.src.com.qnmc.service.DataProcessingService;
import qnmc.src.com.qnmc.utils.ExceptionQuine;
import qnmc.src.com.qnmc.service.GetMintermList;
import qnmc.Quine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	// GUI Components
	private JPanel mainPanel;
	private JLabel mintermInputLabel;
	private JTextField mintermInputField;
	private JButton nextButton;
	private JTextArea resultDisplayArea;
	private JButton calculateButton;

	// Business Logic Dependencies
	private final DataProcessingService dataProcessingService = new DataProcessingService();
	private final GetMintermList mintermListService = new GetMintermList();
	private String validatedInput; // Temporarily stores validated minterm input

	@SuppressWarnings("unused")
	private int unusedVariable = 0;

	// Constructor for the GUI
	public GUI() {
		super("Quine McCluskey Prime Implicant Generator");

		setupMainFrame(); // Sets up the JFrame and panel
		setupMenuBar();   // Configures the menu bar
		System.out.println("setupMenuBar executed.");
		setupGUIComponents(); // Sets up input fields, buttons, and result display
		System.out.println("setupGUIComponents executed.");
		addComponentsToMainPanel(); // Adds all components to the main panel
		System.out.println("GUI fully set up.");
		setVisible(true); // Makes the GUI window visible
	}

	// Configures JFrame settings and layout
	private void setupMainFrame() {
		setSize(550, 500); // Set dimensions for the JFrame
		setResizable(false); // Prevent resizing
		setLayout(null); // Use absolute layout for precise positioning
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 500, 500);
		mainPanel.setLayout(null);
		add(mainPanel); // Add the panel to the JFrame
	}

	// Adds a menu bar to the frame (defined elsewhere in the project)
	private void setupMenuBar() {
		MenuBar menuBar = new MenuBar(dataProcessingService); // Instantiate custom menu bar
		setJMenuBar(menuBar); // Attach the menu bar to the JFrame
	}

	// Sets up the GUI input fields, buttons, and result display
	private void setupGUIComponents() {
		// Label to prompt the user for the minterm list
		mintermInputLabel = new JLabel("Enter Minterm list: ");
		mintermInputLabel.setBounds(50, 100, 150, 30);
		mintermInputLabel.setFont(new Font("Verdana", Font.BOLD, 14));

		// Text field for user to input minterm values
		mintermInputField = new JTextField();
		mintermInputField.setBounds(50, 140, 70, 30);
		mintermInputField.addKeyListener(createMintermValidationListener()); // Add listener for input validation

		// "Next" button to store the validated input
		nextButton = new JButton("Next");
		nextButton.setBounds(140, 140, 70, 30);
		nextButton.addActionListener(e -> handleNextButtonAction()); // Add event handler for the button

		// Text area for displaying results of calculation
		resultDisplayArea = new JTextArea();
		resultDisplayArea.setBounds(50, 200, 300, 200);
		resultDisplayArea.setEditable(false); // Prevent user from editing results

		// "Calculate" button to process the user input and compute results
		calculateButton = new JButton("Calculate");
		calculateButton.setBounds(400, 250, 100, 50);
		calculateButton.addActionListener(e -> handleCalculateButtonAction()); // Add event handler for button
	}

	// Adds all initialized components to the main panel
	private void addComponentsToMainPanel() {
		mainPanel.add(mintermInputLabel);
		mainPanel.add(mintermInputField);
		mainPanel.add(nextButton);
		mainPanel.add(resultDisplayArea);
		mainPanel.add(calculateButton);
	}

	// Creates a KeyListener to validate minterm input as the user interacts
	private KeyListener createMintermValidationListener() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateMintermInput(mintermInputField.getText()); // Validate the text field input
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		};
	}

	// Validates user-input minterms and enforces proper range
	private void validateMintermInput(String input) {
		int userBitSetting = MenuBar.bits; // Get bits setting from the menu bar
		int maxAllowedValue = (int) Math.pow(2, userBitSetting) - 1; // Calculate the max allowed value using bits

		try {
			dataProcessingService.setK(Integer.parseInt(input)); // Parse and store input
		} catch (NumberFormatException e) {
			dataProcessingService.setK(-1); // Set invalid flag if parsing fails
		}

		// Check if input falls within the valid range
		if (dataProcessingService.getK() < 0 || dataProcessingService.getK() > maxAllowedValue) {
			JOptionPane.showMessageDialog(
					null,
					"Number should be within 0 to " + maxAllowedValue + "\nPlease press Next and give your input again",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
		} else {
			validatedInput = input; // Update the validated input value
		}
	}

	// Clears the input field and stores the validated input in the minterm list
	private void handleNextButtonAction() {
		mintermInputField.setText(""); // Clear the input field for the next entry
		mintermListService.setMinList(validatedInput); // Send validated input to the minterm list service
	}

	// Processes the user's minterm data and calculates the results
	private void handleCalculateButtonAction() {
		Quine quineSimplifier = new Quine(); // Instantiate the Quine simplification object
		dataProcessingService.setSet(GetMintermList.getMin()); // Sync data set with the minterm list

		try {
			Iterator<String> mintermIterator = dataProcessingService.getSet().iterator();
			while (mintermIterator.hasNext()) {
				String minterm = mintermIterator.next();

				// Process minterms based on the selected bit setting
				if (MenuBar.bits == 3) {
					quineSimplifier.addTerm(dataProcessingService.processDataThree(minterm));
				} else if (MenuBar.bits == 4) {
					quineSimplifier.addTerm(dataProcessingService.processDataFour(minterm));
				} else if (MenuBar.bits == 5) {
					quineSimplifier.addTerm(dataProcessingService.processDataFive(minterm));
				}
			}

			quineSimplifier.simplify(); // Perform Quine-McCluskey simplification
			resultDisplayArea.setText(quineSimplifier.toString()); // Display the simplified results
		} catch (ExceptionQuine e) {
			JOptionPane.showMessageDialog(
				null,
				"An error occurred: " + e.getMessage(),
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
		}
	}
}