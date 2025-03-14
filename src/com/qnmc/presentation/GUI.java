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
	private JPanel panel;
	private JLabel minInputLabel;
	private JTextField minInputField;
	private JButton nextButton;
	private JTextArea resultTextArea;
	private JButton calculateButton;

	// Data dependencies
	private final DataProcessingService dataProcessingService = new DataProcessingService();
	private final GetMintermList getMintermList = new GetMintermList();
	private String tempInput; // Temporarily stores validated user input

	@SuppressWarnings("unused")
	private int i = 0; // Unused placeholder for future use

	public GUI() {
		super("Quine McCluskey Prime Implicant Generator");
		initializeFrame(); // Sets up JFrame and panel properties
		initializeMenuBar(); // Adds the menu bar to the frame
		initializeComponents(); // Creates GUI components
		addComponentsToPanel(); // Adds components to the main panel
		setVisible(true); // Makes the GUI visible
	}

	private void initializeFrame() {
		setSize(550, 500); // Sets the frame dimensions
		setResizable(false); // Disables resizing for fixed layout
		setLayout(null); // Uses absolute positioning
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the program exits on close

		panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
		panel.setLayout(null);
		add(panel); // Adds the main panel to the JFrame
	}

	private void initializeMenuBar() {
		MenuBar menuBar = new MenuBar(); // Custom MenuBar logic exists elsewhere
		setJMenuBar(menuBar); // Attaches the JMenuBar to the frame
	}

	private void initializeComponents() {
		// Label for the input field
		minInputLabel = new JLabel("Enter Minterm list: ");
		minInputLabel.setBounds(50, 100, 150, 30);
		minInputLabel.setFont(new Font("Verdana", Font.BOLD, 14));

		// Input field for user-entered minterm list
		minInputField = new JTextField();
		minInputField.setBounds(50, 140, 70, 30);
		minInputField.addKeyListener(createInputValidationListener()); // KeyListener validates input dynamically

		// "Next" button to reset input and store the current value
		nextButton = new JButton("Next");
		nextButton.setBounds(140, 140, 70, 30);
		nextButton.addActionListener(e -> handleNextAction()); // Handles the "Next" button action

		// Text area to display operation results
		resultTextArea = new JTextArea();
		resultTextArea.setBounds(50, 200, 300, 200);
		resultTextArea.setEditable(false); // Prevents user edits to results

		// "Calculate" button to process minterm and compute results
		calculateButton = new JButton("Calculate");
		calculateButton.setBounds(400, 250, 100, 50);
		calculateButton.addActionListener(e -> handleCalculateAction()); // Handles the calculation action
	}

	private void addComponentsToPanel() {
		panel.add(minInputLabel); // Adds label to the panel
		panel.add(minInputField); // Adds input field to the panel
		panel.add(nextButton); // Adds "Next" button to the panel
		panel.add(resultTextArea); // Adds the result display to the panel
		panel.add(calculateButton); // Adds "Calculate" button to the panel
	}

	private KeyListener createInputValidationListener() {
		// Validates input dynamically and ensures it's within range
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateInput(minInputField.getText()); // Validate input on key release
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};
	}

	private void validateInput(String input) {
		int menuBarBits = MenuBar.bits; // Retrieve input size setting from MenuBar
		int upperBound = (int) Math.pow(2, menuBarBits) - 1; // Calculate valid input range based on `bits`

		try {
			dataProcessingService.setK(Integer.parseInt(input)); // Attempt to parse user input
		} catch (NumberFormatException e) {
			dataProcessingService.setK(-1); // Assign error flag for invalid inputs
		}

		// Check if the parsed input is out of bounds
		if (dataProcessingService.getK() < 0 || dataProcessingService.getK() > upperBound) {
			JOptionPane.showMessageDialog(
					null,
					"Number should be within 0 to " + upperBound + "\nPlease press Next and give your input again",
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
		} else {
			tempInput = input; // Update valid input
		}
	}

	private void handleNextAction() {
		minInputField.setText(""); // Clear the text field for the next input
		getMintermList.setMinList(tempInput); // Update minterm list with validated input
	}

	private void handleCalculateAction() {
		Quine quine = new Quine(); // Initialize Quine object for processing
		dataProcessingService.setSet(GetMintermList.getMin()); // Syncs data set with minterms list

		try {
			// Loop through minterms and process each one
			Iterator<String> iterator = dataProcessingService.getSet().iterator();
			while (iterator.hasNext()) {
				String term = iterator.next();
				if (MenuBar.bits == 3) {
					quine.addTerm(dataProcessingService.processDataThree(term));
				} else if (MenuBar.bits == 4) {
					quine.addTerm(dataProcessingService.processDataFour(term));
				} else if (MenuBar.bits == 5) {
					quine.addTerm(dataProcessingService.processDataFive(term));
				}
			}

			quine.simplify(); // Simplify minterms using quine logic
			resultTextArea.setText(quine.toString()); // Display calculated result in UI
		} catch (ExceptionQuine e) {
			e.printStackTrace(); // TODO: Handle ExceptionQuine appropriately
		}
	}
}