package qnmc.src.com.qnmc;

import qnmc.src.com.qnmc.presentation.GUI;
import qnmc.src.com.qnmc.presentation.MenuBar;
import qnmc.src.com.qnmc.service.DataProcessingService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

        }



        String s = JOptionPane
                .showInputDialog("Enter the boolean bits(3 to 5): ");
        try {
            MenuBar.bits= Integer.parseInt(s);
        } catch (NumberFormatException e) {

            MenuBar.bits= 2;
        }

        if (MenuBar.bits< 3 || MenuBar.bits> 5) {
            JOptionPane.showMessageDialog(null,
                    "Wrong input. Press File and then NEW", "Error",
                    JOptionPane.ERROR_MESSAGE, null);

        }


        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}