/**

@file RentalManagementApp.java
@brief This file serves as the main application file for the RentalManagement App.
@details This file contains the entry point of the application, which is the main method. It initializes the necessary components and executes the RentalManagement App.
*/
/**

@package com.project.RentalManagement
@brief The com.project.RentalManagement package contains all the classes and files related to the RentalManagement App.
*/
package com.project.RentalManagement;
import java.util.Scanner;


/**
 *
 * @class RentalManagementApp
 * @brief This class represents the main application class for the RentalManagement App.
 * @details The RentalManagementApp class provides the entry point for the RentalManagement
 *          App. It initializes the necessary components, performs file operations,
 *          and handles exceptions.
 * @author hikmethankolay
 */
public class RentalManagementApp {

  /**
   * @brief Represents the variables for the login menu, controlling app's running state and navigation.
   */
  static class LoginMenuVariables {
    /** Controls the app's running state. */
    static boolean run = true;

    /** Variable for menu navigation: login. */
    static int loginMenuLogin = 1;

    /** Variable for menu navigation: register. */
    static int loginMenuRegister = 2;

    /** Variable for menu navigation: password reset. */
    static int loginMenuPasswordReset = 3;

    /** Variable for menu navigation: exit. */
    static int loginMenuExit = 4;
  }
  /**
   * @brief The main entry point of the RentalManagement App.
   *
   * @details The main method is the starting point of the RentalManagement App.
   *          It initializes the necessary components, performs file operations and handles exceptions.
   *
   * @param args The command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    RentalManagementLib Rental = new RentalManagementLib();
    Scanner scanner = new Scanner(System.in);

    while (LoginMenuVariables.run) {
      System.out.print("\n--------Login Menu--------");
      System.out.print("\n1-)Login");
      System.out.print("\n2-)Register");
      System.out.print("\n3-)Change Password");
      System.out.print("\n4-)Exit");
      System.out.print("\nPlease enter a choice: ");
      int choiceLoginMenu = scanner.nextInt();

      if (choiceLoginMenu == LoginMenuVariables.loginMenuLogin) {
        Rental.loginMenu(scanner);
      } else if (choiceLoginMenu == LoginMenuVariables.loginMenuRegister) {
        Rental.registerMenu(scanner);
      } else if (choiceLoginMenu == LoginMenuVariables.loginMenuPasswordReset) {
        Rental.changePasswordMenu(scanner);
      } else if (choiceLoginMenu == LoginMenuVariables.loginMenuExit) {
        LoginMenuVariables.run = false;
      } else {
        System.out.print("\nPlease input a correct choice.");
      }
    }

    scanner.close();
  }

}
