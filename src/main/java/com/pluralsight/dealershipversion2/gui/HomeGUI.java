package com.pluralsight.dealershipversion2.gui;

import static com.pluralsight.dealershipversion2.utils.InputOutput.promptForString;

public class HomeGUI {


    private static HomeGUI homeGUI;
    private DealerGUI dealerGUI;

    private HomeGUI() {
        dealerGUI = DealerGUI.getInstance();
        homeScreen();
    }

    public static HomeGUI getInstance() {

        if (homeGUI != null) {
            return  homeGUI;
        }
        homeGUI = new HomeGUI();
        return  homeGUI;
    }

    private void homeScreen() {

        boolean flag = true;
        while (flag) {
            try {
                String command = promptForString(" (Dealership) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "1" -> dealerGUI.homeScreen();                         // Display all vehicles
                    case "0" -> flag = false;                      // Exit the application
                    default -> System.out.println("Invalid Option. Please choose a number between 1 or 2.\n");
                }
            } catch (Exception e) {
                System.out.println("Wrong command or option!");
                e.printStackTrace();
            }
        }
    }
}
