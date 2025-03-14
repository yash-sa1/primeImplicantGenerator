package qnmc.src.com.qnmc.presentation;


import qnmc.src.com.qnmc.service.DataProcessingService;
import qnmc.src.com.qnmc.utils.ExceptionQuine;
import qnmc.src.com.qnmc.service.GetMintermList;
import qnmc.Quine;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	
	private JLabel minInput;
	private JTextField minIn;
	private JButton nextBt;
	
	private JTextArea resultShow;
	private JButton calBt;
	@SuppressWarnings("unused")
	private int i = 0;



	public String temp; 
	GetMintermList item = new GetMintermList();

	private final DataProcessingService dataProcessingService = new DataProcessingService();

	public String dataThree(String input) {
		return dataProcessingService.processDataThree(input);
	}

	public String dataFour(String input) {
		return dataProcessingService.processDataFour(input);
	}

	public String dataFive(String input) {
		return dataProcessingService.processDataFive(input);
	}

	public GUI() {
		

		super("Quine McCluskey Prime Implicant Generator");

		setLayout(null); 

		setSize(550, 500); 
		setResizable(false);
		panel = new JPanel(); 
		panel.setBounds(0, 0, 500, 500); 

		panel.setLayout(null); 

		MenuBar bar = new MenuBar();
		setJMenuBar(bar);

		

		minInput = new JLabel("Enter Minterm list: ");
		minInput.setBounds(50, 100, 150, 30);
		minInput.setFont(new Font("Verdana", Font.BOLD, 14));
		panel.add(minInput);

		minIn = new JTextField();
		minIn.setBounds(50, 140, 70, 30);

		minIn.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

				@SuppressWarnings("unused")
				int flag = 0;
				int st = MenuBar.bits;

				System.out.println(minIn.getText());
				String tmp = minIn.getText();

				if (st == 3) {

					try {
						dataProcessingService.setK(Integer.parseInt(tmp)); // Use the service setter
					} catch (NumberFormatException e) {
						dataProcessingService.setK(-1); // Set via service if the input is invalid
					}

					if (dataProcessingService.getK() < 0 || dataProcessingService.getK() > 7) { // Use the service getter
						JOptionPane.showMessageDialog(null,
								"Number should be within 0 to 7\nPlease press Next and give your input again",
								"Error", JOptionPane.ERROR_MESSAGE, null);
					} else {
						temp = minIn.getText();
					}
				}

				if (st == 4) {

					try {
						dataProcessingService.setK(Integer.parseInt(tmp)); // Use the service setter
					} catch (NumberFormatException e) {
						dataProcessingService.setK(-1); // Set via service if the input is invalid
					}

					if (dataProcessingService.getK() < 0 || dataProcessingService.getK() > 15) { // Use the service getter
						JOptionPane.showMessageDialog(null,
								"Number should be within 0 to 15\nPlease press Next and give your input again",
								"Error", JOptionPane.ERROR_MESSAGE, null);
					} else {
						temp = minIn.getText();
					}
				}

				if (st == 5) {

					try {
						dataProcessingService.setK(Integer.parseInt(tmp)); // Use the service setter
					} catch (NumberFormatException e) {
						dataProcessingService.setK(-1); // Set via service if the input is invalid
					}

					if (dataProcessingService.getK() < 0 || dataProcessingService.getK() > 31) { // Use the service getter
						JOptionPane.showMessageDialog(null,
								"Number should be within 0 to 31\nPlease press Next and give your input again",
								"Error", JOptionPane.ERROR_MESSAGE, null);
					} else {
						temp = minIn.getText();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(minIn);

		nextBt = new JButton("Next");
		nextBt.setBounds(140, 140, 70, 30);
		nextBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				minIn.setText("");
				item.setMinList(temp);

				
			}
		});
		panel.add(nextBt);

		
		resultShow = new JTextArea();
		resultShow.setBounds(50, 200, 300, 200);
		resultShow.setEditable(false);
		panel.add(resultShow);

		calBt = new JButton("Calculate");
		calBt.setBounds(400, 250, 100, 50);
		calBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Quine quine = new Quine();
				dataProcessingService.setSet(GetMintermList.getMin()); // Set 'set' using the service
				@SuppressWarnings("unused")
				int len = dataProcessingService.getSet().size(); // Access 'set' using the service's getter
				try {
					Iterator<String> it = dataProcessingService.getSet().iterator(); // Work with 'set' via service

					while (it.hasNext()) {
						String str = it.next();

						if (MenuBar.bits == 3)
							quine.addTerm(dataThree(str));
						else if (MenuBar.bits == 4)
							quine.addTerm(dataFour(str));
						else if (MenuBar.bits == 5)
							quine.addTerm(dataFive(str));

						System.out.println(str);
					}

					
					quine.simplify();
					String temp1 = quine.toString();
					
					resultShow.setText(temp1);
				} catch (ExceptionQuine e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		panel.add(calBt);

		setVisible(true); 
		add(panel);

	}
}
