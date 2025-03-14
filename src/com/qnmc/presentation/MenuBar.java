package qnmc.src.com.qnmc.presentation;

import qnmc.src.com.qnmc.service.DataProcessingService;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	// Add static field for bits
	public static int bits = 2; // Default value for bits

	public MenuBar(DataProcessingService dataProcessingService) {
		// File menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		add(fileMenu);

		// "New" menu item
		JMenuItem newMenuItem = new JMenuItem("New  Ctrl+N", KeyEvent.VK_N);
		fileMenu.add(newMenuItem);
		newMenuItem.addActionListener(e -> dataProcessingService.initializeNewSession()); // Delegate "New" logic

		// "Exit" menu item
		JMenuItem exitMenuItem = new JMenuItem("Exit  Alt+F4", KeyEvent.VK_X);
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(e -> System.exit(0)); // Exit application

		// Help menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		add(helpMenu);

		// "About Quine McCluskey Algorithm" menu item
		JMenuItem aboutAlgorithmItem = new JMenuItem("About Quine McCluskey Algorithm", KeyEvent.VK_A);
		helpMenu.add(aboutAlgorithmItem);
		aboutAlgorithmItem.addActionListener(e -> dataProcessingService.displayAlgorithmInfo()); // Show algorithm info

		// About menu
		JMenu aboutMenu = new JMenu("About...");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		add(aboutMenu);

		// Developer 1 info
		JMenuItem dev1Item = new JMenuItem("Developer 1", KeyEvent.VK_1);
		aboutMenu.add(dev1Item);
		dev1Item.addActionListener(e -> dataProcessingService.displayDeveloperInfo(1)); // Show Developer 1 info

		// Developer 2 info
		JMenuItem dev2Item = new JMenuItem("Developer 2", KeyEvent.VK_2);
		aboutMenu.add(dev2Item);
		dev2Item.addActionListener(e -> dataProcessingService.displayDeveloperInfo(2)); // Show Developer 2 info

		// Developer 3 info
		JMenuItem dev3Item = new JMenuItem("Developer 3", KeyEvent.VK_3);
		aboutMenu.add(dev3Item);
		dev3Item.addActionListener(e -> dataProcessingService.displayDeveloperInfo(3)); // Show Developer 3 info
	}
}
