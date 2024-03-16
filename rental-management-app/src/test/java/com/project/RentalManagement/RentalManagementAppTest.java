/**

@file RentalManagementAppTest.java
@brief This file contains the test cases for the RentalManagementApp class.
@details This file includes test methods to validate the functionality of the RentalManagementApp class. It uses JUnit for unit testing.
*/
package com.project.RentalManagement;

import static org.junit.Assert.*;

import java.io.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/**

@class RentalManagementAppTest
@brief This class represents the test class for the RentalManagementApp class.
@details The RentalManagementAppTest class provides test methods to verify the behavior of the CalculatorApp class. It includes test methods for successful execution, object creation, and error handling scenarios.
@author ugur.coruh
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentalManagementAppTest {

  /**
   * @brief This method is executed once before all test methods.
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * @brief This method is executed once after all test methods.
   * @throws Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @brief This method is executed before each test method.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @brief This method is executed after each test method.
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
  }
  /**
   * @brief Redirects System.in and System.out to the given files.
   */
  private void redirectSystemIO(String inputFilePath, String outputFilePath) throws FileNotFoundException {
    System.setIn(new FileInputStream(inputFilePath));
    System.setOut(new PrintStream(new FileOutputStream(outputFilePath)));
  }

  /**
   * @brief Resets System.in and System.out to their original sources.
   */
  private void resetSystemIO() {
    System.setIn(new BufferedInputStream(new FileInputStream(FileDescriptor.in)));
    System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
  }

  /**
     * @brief Tests for register user menu.
     *
     */
  @Test
  public void test01_RegisterMenu() throws IOException {
    RentalManagementLib lib = new RentalManagementLib();
    // Redirect System.in and System.out from/to test files
    redirectSystemIO("register_menu_input_test.txt", "register_menu_output_test.txt");

    // Call the method under test
    lib.registerMenu();

    // Reset System.in and System.out to their original sources
    resetSystemIO();

    // Expected output
    String expectedOutput = "\nPlease enter your new username: "
    + "\nPlease enter your new password: "
    + "\nPlease enter your new recovery key: "
    + "\n------------WARNING------------"
    + "\nThis process will delete all previous records, do you still wish to proceed?[Y/n]: "
    + "\nRegister is successful and all previous records are deleted.";

    // Read the output from the file and compare
    String actualOutput = RentalManagementLib.file_read("register_menu_output_test.txt",'Y');
    assertEquals(expectedOutput, actualOutput);
  }
  /**
  * @brief Tests for login user menu.
  *
  */
  @Test
  public void test_02MainMenu() throws IOException {
    RentalManagementLib lib = new RentalManagementLib();
    // Redirect System.in and System.out from/to test files
    redirectSystemIO("login_menu_input_test.txt", "login_menu_output_test.txt");

    // Call the method under test
    lib.mainMenu();

    // Reset System.in and System.out to their original sources
    resetSystemIO();

    // Expected output
    String expectedOutput = "\nPlease enter your username: "
    + "\nPlease enter your password: "
    + "\nLogin Successful"
    + "\n--------Main Menu--------"
    + "\n1-)Properties"
    + "\n2-)Tenants"
    + "\n3-)Rent Tracking"
    + "\n4-)Maintenance Tracking"
    + "\n5-)Log out"
    +"\nPlease enter a choice: ";

    // Read the output from the file and compare
    String actualOutput = RentalManagementLib.file_read("login_menu_output_test.txt",'Y');
    assertEquals(expectedOutput, actualOutput);
  }

  /**
   * @brief Tests for change password user menu.
   *
   */
  @Test
  public void test_03ChangePasswordMenu() throws IOException {
    RentalManagementLib lib = new RentalManagementLib();
    // Redirect System.in and System.out from/to test files
    redirectSystemIO("change_password_menu_input_test.txt", "change_password_menu_output_test.txt");

    // Call the method under test
    lib.changePasswordMenu();

    // Reset System.in and System.out to their original sources
    resetSystemIO();

    // Expected output
    String expectedOutput = "\nPlease enter your recovery key: "
    + "\nPlease enter your new password: "
    + "\nRecovery Key Approved"
    + "\nPassword changed successfully";

    // Read the output from the file and compare
    String actualOutput = RentalManagementLib.file_read("change_password_menu_output_test.txt",'Y');
    assertEquals(expectedOutput, actualOutput);
  }

}
