/**

@file RentalManagementLib.java
@brief This file serves as a demonstration file for the RentalManagementLib class.
@details This file contains the implementation of the RentalManagementLib class.
*/

/**

@package com.project.RentalManagementLib
@brief The com.project.RentalManagementLib package contains all the classes and files related to the com.project.RentalManagement App.
*/
package com.project.RentalManagement;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import ch.qos.logback.classic.Logger;

/**
@class RentalManagementLib
@brief This class represents a RentalManagementLib that performs file operations.
@details The RentalManagementLib class provides methods to perform file operations such as adding, deleting, editing, and sorting..
@author hikmethankolay
*/
public class RentalManagementLib {

  /**
  * @brief Represents the variables for the main menu, including the app's logged-in state and navigation options.
  */
  static class MainMenuVariables {
    /** Indicates if a user is logged in. */
    static boolean loggedIn = true;

    /** Variable for main menu navigation: property. */
    static int mainMenuProperty = 1;

    /** Variable for main menu navigation: tenant. */
    static int mainMenuTenant = 2;

    /** Variable for main menu navigation: rent tracking. */
    static int mainMenuRentTracking = 3;

    /** Variable for main menu navigation: maintenance. */
    static int mainMenuMaintenance = 4;

    /** Variable for main menu navigation: log out. */
    static int mainMenuLogOut = 5;
  }
  /**
   * @brief Represents the variables for submenus, facilitating menu navigation.
   */
  static class SubMenuVariables {
    /** Variable for submenu navigation: show. */
    static int subMenuShow = 1;

    /** Variable for submenu navigation: add. */
    static int subMenuAdd = 2;

    /** Variable for submenu navigation: edit. */
    static int subMenuEdit = 3;

    /** Variable for submenu navigation: delete. */
    static int subMenuDelete = 4;

    /** Variable for submenu navigation: search. */
    static int subMenuSearch = 5;

    /** Variable for submenu navigation: sort. */
    static int subMenuSort = 6;

    /** Variable for submenu navigation: return. */
    static int subMenuReturn = 7;
  }

  /**
   * @brief Represents tenant information including ID, property ID, rent, birth date, name, and surname.
   */
  static class TenantInfo {
    /** Record's line number. */
    static int recordNumber;

    /** Tenant's ID. */
    static int tenantID;

    /** Property's ID associated with the tenant. */
    static int propertyID;

    /** Rent amount. */
    static int rent;

    /** Tenant's birth date. */
    static String birthDate;

    /** Tenant's name. */
    static String name;

    /** Tenant's surname. */
    static String surname;

    /** Constructor */
    public TenantInfo(int tenantID, int propertyID, int rent, String birthDate, String name, String surname) {
      TenantInfo.tenantID = tenantID;
      TenantInfo.propertyID = propertyID;
      TenantInfo.rent = rent;
      TenantInfo.birthDate = birthDate;
      TenantInfo.name = name;
      TenantInfo.surname = surname;
    }
  }
  /**
   * @brief Represents property information including ID, age, bedroom count, living room count, floor count, size, and address.
   */
  static class PropertyInfo {
    /** Record's line number. */
    static int recordNumber;

    /** Property's ID. */
    static int propertyID;

    /** Property's age. */
    static int propertyAge;

    /** Bedroom count. */
    static int bedrooms;

    /** Living room count. */
    static int livingrooms;

    /** Floor count. */
    static int floors;

    /** Size of the property. */
    static int size;

    /** Address of the property. */
    static String address;

    /** Constructor */
    public PropertyInfo(int propertyID, int propertyAge, int bedrooms, int livingRooms, int floors, int size, String address) {
      PropertyInfo.propertyID = propertyID;
      PropertyInfo.propertyAge = propertyAge;
      PropertyInfo.bedrooms = bedrooms;
      PropertyInfo.livingrooms = livingRooms;
      PropertyInfo.floors = floors;
      PropertyInfo.size = size;
      PropertyInfo.address = address;
    }

  }
  /**
   * @brief Represents rent information including record number, tenant ID, current rent debt, and due date.
   */
  static class RentInfo {
    /** Record's line number. */
    static int recordNumber;

    /** Tenant's ID that owes rent. */
    static int tenantID;

    /** Current rent debt. */
    static int currentRentDebt;

    /** Rent's due date. */
    static String dueDate;

    /** Constructor */
    public RentInfo(int tenantID, int currentRentDebt, String dueDate) {
      RentInfo.tenantID = tenantID;
      RentInfo.currentRentDebt = currentRentDebt;
      RentInfo.dueDate = dueDate;
    }
  }

  /**
   * @brief Represents maintenance information including record number, property ID, cost, priority level, maintenance type, and expected finishing date.
   */
  static class MaintenanceInfo {
    /** Record's line number. */
    static int recordNumber;

    /** Property's ID. */
    static int propertyID;

    /** Maintenance cost. */
    static int cost;

    /** Priority level of the maintenance. */
    static int priority;

    /** Maintenance type. */
    static String maintenanceType;

    /** Expected finishing date of the maintenance. */
    static String expectedFinishingDate;

    /** Constructor */
    public MaintenanceInfo(int propertyID, int cost, int priority, String maintenanceType, String expectedFinishingDate) {
      MaintenanceInfo.propertyID = propertyID;
      MaintenanceInfo.cost = cost;
      MaintenanceInfo.priority = priority;
      MaintenanceInfo.maintenanceType = maintenanceType;
      MaintenanceInfo.expectedFinishingDate = expectedFinishingDate;
    }
  }
  /**
   * @brief Opens a binary file, deletes all of its content, and writes given text to it.
   *
   * @param fileName The name of the file to write.
   * @param text The text to write.
   * @return 0 on success.
   */
  public static int file_write(String fileName, String text) {
    // Prepend "1-)" to text
    String prefixText = "1-)" + text + "\n";

    try (// Create a FileOutputStream object
        FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
      fileOutputStream.write(prefixText.getBytes());
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      return -1;
    }

    return 0; // Return 0 to indicate success
  }

  /**
   * @brief Opens a binary file, reads all of its content, separates lines with "\n", and writes them to console.
   * Also returns the contents of the file as a string for unit tests.
   *
   * @param fileName    The name of the file to read from.
   * @param isSorting   A variable to disable writing content to console during sorting.
   * @return The contents of the file as a dynamically allocated string.
   */
  public static String file_read(String fileName, char isSorting) {
    StringBuilder content = new StringBuilder(); // StringBuilder for file content
    int ch;

    try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
      while ((ch = fileInputStream.read()) != -1) {
        if (ch == '\r') continue; // Skip '\r'

        content.append((char) ch); // Append character to content
      }
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      return "-1";
    }

    if (isSorting != 'Y') {
      System.out.print(content); // Print the content to the console
    }

    return content.toString(); // Return the content as a string
  }

  /**
  * Appends given text to a text file with an automatically calculated line number.
  * Calculates the new line's line number by finding the last line's line number.
  *
  * @param fileName The name of the file to append to.
  * @param text     The text to append to the file.
  * @return 0 on success, -1 on failure.
  */
  public static int file_append(String fileName, String text) {
    File file = new File(fileName);
    String lastLine = "";
    int lineNumber = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;

      while ((line = reader.readLine()) != null) {
        lastLine = line;
      }
    } catch (IOException e) {
      System.err.print("\nFile operation failed.");
      return -1;
    }

    if (!lastLine.isEmpty()) {
      // Extract the line number from the last line
      String[] parts = lastLine.split("-\\)");

      if (parts.length > 0) {
        lineNumber = Integer.parseInt(parts[0]);
      }
    }

    lineNumber++;

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
      writer.write(lineNumber + "-)" + text + "\n");
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      return -1;
    }

    return 0;
  }
  /**
   * @brief This function opens a binary file, finds the line that the user wants to edit, and replaces it with new text.
   *
   * @param fileName              The name of the file to edit.
   * @param lineNumberToEdit     The line number to edit.
   * @param newLine              The new text to replace the existing line.
   * @return 0 on success.
   */
  public static int file_edit(String fileName, int lineNumberToEdit, String newLine) {
    File file = new File(fileName);
    ArrayList<String> lines = new ArrayList<>();
    int lineCount = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;

      while ((line = reader.readLine()) != null) {
        lines.add(line);
        lineCount++;
      }
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      return -1;
    }

    if (lineNumberToEdit > 0 && lineNumberToEdit <= lineCount) {
      // Directly replace the line without additional formatting
      lines.set(lineNumberToEdit - 1, lineNumberToEdit + "-)" + newLine);
    } else {
      System.out.print("\nInvalid line number.");
      return -1;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      // Write updated lines back to file
      for (String line : lines) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      e.printStackTrace();
    }

    System.out.print("\nData successfully edited.");
    return 0;
  }

  /**
   * @brief Opens a binary file, deletes the line the user wanted, and makes adjustments on the line number accordingly.
   *
   * @param fileName              The name of the file to delete the line from.
   * @param lineNumberToDelete   The line number to delete.
   * @return 0 on success.
   */
  public static int file_line_delete(String fileName, int lineNumberToDelete) {
    File file = new File(fileName);
    ArrayList<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;

      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      return -1;
    }

    if (lineNumberToDelete < 1 || lineNumberToDelete > lines.size()) {
      System.out.print("\nInvalid line number.");
      return -1;
    }

    lines.remove(lineNumberToDelete - 1); // Remove the specified line

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      // Write updated lines back to file, adjusting line numbers if necessary
      for (int j = 0; j < lines.size(); j++) {
        String line = lines.get(j);
        writer.write((j + 1) + "-)" + line.substring(line.indexOf("-)") + 2));
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.print("\nFile operation failed");
      e.printStackTrace();
      return -1;
    }

    System.out.print("\nData successfully deleted");
    return 0;
  }

  /**
   * @brief This function is for user login
   *
   * Function reads user.bin file and checks if username and password match with inputted username and password
   * @param username username.
   * @param password password.
   * @param user_file file that contains user info.
   * @return 0 on success.
   * @return -1 on fail.
  */
  public static int user_login(String username, String password, String userFile) {
    String usernameRead = "";
    String passwordRead = "";
    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader(userFile));
      int count = 0;
      int i;

      while ((i = br.read()) != -1) {
        char c = (char) i;

        if (c == '/') {
          count++;
          continue;
        }

        if (count == 0) {
          usernameRead += c;
        } else if (count == 1) {
          passwordRead += c;
        } else if (count == 2) {
          break;
        }
      }
    } catch (IOException e) {
      System.out.print("There is no user info, Please register first.");
      return -1;
    }

    finally {
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (username.equals(usernameRead) && password.equals(passwordRead)) {
      System.out.print("\nLogin Successful");
      return 0;
    } else {
      System.out.print("\nWrong username or password");
      return -1;
    }
  }
  /**
   * @brief This function changes the password of a user.
   * @param recovery_key recovery_key.
   * @param new_password new password.
   * @param user_file file that contains user info.
   * @return 0 on success.
   * @return -1 on fail.
  */
  public static int user_change_password(String recoveryKey, String newPassword, String userFile) {
    String usernameRead = "";
    String recoveryKeyRead = "";
    int count = 0;

    try {
      BufferedReader reader = new BufferedReader(new FileReader(userFile));
      int i;

      while ((i = reader.read()) != -1) {
        char ch = (char) i;

        if (ch == '/') {
          count++;
          continue;
        }

        if (count == 0) {
          usernameRead += ch;
        } else if (count == 1) {
          continue; // Skip reading password
        } else if (count == 2) {
          recoveryKeyRead += ch;
        }
      }

      reader.close();
    } catch (IOException e) {
      System.out.print("\nThere is no user info, Please register first.");
      return -1;
    }

    if (recoveryKey.equals(recoveryKeyRead)) {
      System.out.print("\nRecovery Key Approved");

      try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(userFile));
        writer.write(usernameRead + "/" + newPassword + "/" + recoveryKeyRead);
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
        return -1;
      }

      System.out.print("\nPassword changed successfully");
      return 0;
    } else {
      System.out.print("\nWrong Recovery Key");
      return -1;
    }
  }
  /**
  * @brief This function is for user register
  *
  * Function creates a user file in binary format and writes inputted username and password in it. Additionally deletes all previous records.
  * @param new_username new username.
  * @param new_password new password.
  * @param new_recovery_key new recovery key
  * @param user_file file that contains user info.
  * @return 0 on success.
  * @return -1 on fail.
  */
  public static int user_register(String newUsername, String newPassword, String newRecoveryKey, String userFile) {
    try {
      // Create FileOutputStream with userFile
      FileOutputStream fileOutputStream = new FileOutputStream(userFile);
      // Write the user information with a separator (e.g., newline character)
      String userInfo = newUsername + "/" + newPassword + "/" + newRecoveryKey;
      fileOutputStream.write(userInfo.getBytes());
      // Close the stream
      fileOutputStream.close();
      System.out.print("\nRegister is successful and all previous records are deleted.");
      return 0;
    } catch (IOException e) {
      System.out.print("Error: " + e.getMessage());
      return -1;
    }
  }
  /**
   * @brief add property record.
   *
   * @return 0.
   */
  public static int add_property_record() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nPlease enter PropertyID:");
    int propertyID = scanner.nextInt();
    System.out.print("\nPlease enter PropertyAge:");
    int propertyAge = scanner.nextInt();
    System.out.print("\nPlease enter Bedrooms:");
    int bedrooms = scanner.nextInt();
    System.out.print("\nPlease enter LivingRooms:");
    int livingRooms = scanner.nextInt();
    System.out.print("\nPlease enter Floors:");
    int floors = scanner.nextInt();
    System.out.print("\nPlease enter Size:");
    int size = scanner.nextInt();
    scanner.nextLine(); // Consume newline left-over
    System.out.println("\nPlease enter Address:");
    String address = scanner.nextLine();
    String formattedRecord = String.format("PropertyID:%d / PropertyAge:%d / Bedrooms:%d / Livingrooms:%d / Floors:%d / Size:%dm2 / Address:%s",
                                           propertyID, propertyAge, bedrooms, livingRooms, floors, size, address);
    File file = new File("property_records.bin");

    if (!file.exists()) {
      file_write("property_records.bin",formattedRecord);
      scanner.close();
      return 0;
    } else {
      file_append("property_records.bin",formattedRecord);
      scanner.close();
      return 0;
    }
  }
  /**
   * @brief edit property record.
   *
   * @return 0.
   */
  public static int edit_property_record() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nPlease enter record number to edit:");
    int RecordNumberToEdit = scanner.nextInt();
    System.out.print("\nPlease enter PropertyID:");
    int propertyID = scanner.nextInt();
    System.out.print("\nPlease enter PropertyAge:");
    int propertyAge = scanner.nextInt();
    System.out.print("\nPlease enter Bedrooms:");
    int bedrooms = scanner.nextInt();
    System.out.print("\nPlease enter LivingRooms:");
    int livingRooms = scanner.nextInt();
    System.out.print("\nPlease enter Floors:");
    int floors = scanner.nextInt();
    System.out.print("\nPlease enter Size:");
    int size = scanner.nextInt();
    scanner.nextLine(); // Consume newline left-over
    System.out.print("\nPlease enter Address:");
    String address = scanner.nextLine();
    String formattedRecord = String.format("PropertyID:%d / PropertyAge:%d / Bedrooms:%d / Livingrooms:%d / Floors:%d / Size:%dm2 / Address:%s",
                                           propertyID, propertyAge, bedrooms, livingRooms, floors, size, address);

    if (file_edit("property_records.bin", RecordNumberToEdit, formattedRecord) == 0) {
      scanner.close();
      return 0;
    } else {
      scanner.close();
      return -1;
    }
  }
  /**
   * @brief delete property record.
   *
   * @return 0.
   */
  public static int delete_property_record() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nPlease enter record number to delete:");
    int RecordNumberToDelete = scanner.nextInt();

    if (file_line_delete("property_records.bin", RecordNumberToDelete) == 0) {
      scanner.close();
      return 0;
    } else {
      scanner.close();
      return -1;
    }
  }
  /**
   * @brief sort property record.
   *
   * @return 0.
   */
  public static int sort_property_record() {
    return 0;
  }
  /**
   * @brief search property record.
   *
   * @return 0.
   */
  public static int search_property_record() {
    return 0;
  }
  /**
   * @brief add teneat record.
   *
   * @return 0.
   */
  public static int add_tenant_record() {
	  Scanner scanner = new Scanner(System.in);
	    System.out.print("\nPlease enter TenantID:");
	    int tenantID = scanner.nextInt();
	    System.out.print("\nPlease enter PropertyID:");
	    int propertyID = scanner.nextInt();
	    System.out.print("\nPlease enter Rent:");
	    int rent = scanner.nextInt();
	    System.out.print("\nPlease enter BirthDate:");
	    String birthdate = scanner.nextLine();
	    System.out.print("\nPlease enter Name:");
	    String name = scanner.nextLine();
	    System.out.print("\nPlease enter Surname:");
	    String surname = scanner.nextLine();
	    scanner.nextLine(); // Consume newline left-over
	    String formattedRecord = String.format("TenantID:%d / PropertyID:%d / Rent:%d / Birthdate:%s / Name:%s / Surname:%s",
	                                           tenantID,propertyID, rent, birthdate, name, surname);
	    File file = new File("tenant_records.bin");

	    if (!file.exists()) {
	      file_write("tenant_records.bin",formattedRecord);
	      scanner.close();
	      return 0;
	    } else {
	      file_append("tenant_records.bin",formattedRecord);
	      scanner.close();
	      return 0;
	    }
	  }
  /**
   * @brief edit teneat record.
   *
   * @return 0.
   */
  public static int edit_tenant_record() {
	  Scanner scanner = new Scanner(System.in);
	  System.out.print("\nPlease enter record number to edit:");
	    int RecordNumberToEdit = scanner.nextInt();
	    System.out.print("\nPlease enter TenantID:");
	    int tenantID = scanner.nextInt();
	    System.out.print("\nPlease enter PropertyID:");
	    int propertyID = scanner.nextInt();
	    System.out.print("\nPlease enter Rent:");
	    int rent = scanner.nextInt();
	    System.out.print("\nPlease enter BirthDate:");
	    String birthdate = scanner.nextLine();
	    System.out.print("\nPlease enter Name:");
	    String name = scanner.nextLine();
	    System.out.print("\nPlease enter Surname:");
	    String surname = scanner.nextLine();
	    scanner.nextLine(); // Consume newline left-over
	    String formattedRecord = String.format("TenantID:%d / PropertyID:%d / Rent:%d / Birthdate:%s / Name:%s / Surname:%s",
	                                           tenantID,propertyID, rent, birthdate, name, surname);
	  if (file_edit("tenant_records.bin", RecordNumberToEdit, formattedRecord) == 0) {
	      scanner.close();
	      return 0;
	    } else {
	      scanner.close();
	      return -1;
	    }
	  }
  /**
   * @brief delete teneat record.
   *
   * @return 0.
   */
  public static int delete_tenant_record() {
	  Scanner scanner = new Scanner(System.in);
	    System.out.print("\nPlease enter record number to delete:");
	    int RecordNumberToDelete = scanner.nextInt();

	    if (file_line_delete("tenant_records.bin", RecordNumberToDelete) == 0) {
	      scanner.close();
	      return 0;
	    } else {
	      scanner.close();
	      return -1;
	    }
	  }
  
    
  /**
   * @brief sort teneat record.
   *
   * @return 0.
   */
  public static int sort_tenant_record() {
    return 0;
  }
  /**
   * @brief search teneat record.
   *
   * @return 0.
   */
  public static int search_tenant_record() {
    return 0;
  }
  /**
   * @brief add rent record.
   *
   * @return 0.
   */
  public static int add_rent_record() {
	  Scanner scanner = new Scanner(System.in);
	    System.out.print("\nPlease enter TenantID:");
	    int tenantID = scanner.nextInt();
	    System.out.print("\nPlease enter CurrentRentDebt:");
	    int currentrentdebt = scanner.nextInt();
	    System.out.print("\nPlease enter DueDate:");
	    String duedate = scanner.nextLine();
	    scanner.nextLine(); // Consume newline left-over
	    String formattedRecord = String.format("TenantID:%d / CurrentRentDebt:%d / DueDate:%s",tenantID,currentrentdebt,duedate);
	    File file = new File("rent_records.bin");

	    if (!file.exists()) {
	      file_write("rent_records.bin",formattedRecord);
	      scanner.close();
	      return 0;
	    } else {
	      file_append("rent_records.bin",formattedRecord);
	      scanner.close();
	      return 0;
	    }
	  }
  /**
   * @brief edit rent record.
   *
   * @return 0.
   */
  public static int edit_rent_record() {
	  Scanner scanner = new Scanner(System.in);
	  System.out.print("\nPlease enter record number to edit:");
	    int RecordNumberToEdit = scanner.nextInt();
	    System.out.print("\nPlease enter TenantID:");
	    int tenantID = scanner.nextInt();
	    System.out.print("\nPlease enter CurrentRentDebt:");
	    int currentrentdebt = scanner.nextInt();
	    System.out.print("\nPlease enter DueDate:");
	    String duedate = scanner.nextLine();
	    scanner.nextLine(); // Consume newline left-over
	    String formattedRecord = String.format("TenantID:%d / CurrentRentDebt:%d / DueDate:%s",tenantID,currentrentdebt,duedate);
	    
	  if (file_edit("rent_records.bin", RecordNumberToEdit, formattedRecord) == 0) {
	      scanner.close();
	      return 0;
	    } else {
	      scanner.close();
	      return -1;
	    }
	  }
  /**
   * @brief delete rent record.
   *
   * @return 0.
   */
  public static int delete_rent_record() {
    return 0;
  }
  /**
   * @brief sort rent record.
   *
   * @return 0.
   */
  public static int sort_rent_record() {
    return 0;
  }
  /**
   * @brief search rent record.
   *
   * @return 0.
   */
  public static int search_rent_record() {
    return 0;
  }
  /**
   * @brief add maintenance record.
   *
   * @return 0.
   */
  public static int add_maintenance_record() {
    return 0;
  }
  /**
   * @brief edit maintenance record.
   *
   * @return 0.
   */
  public static int edit_maintenance_record() {
    return 0;
  }
  /**
   * @brief delete maintenance record.
   *
   * @return 0.
   */
  public static int delete_maintenance_record() {
    return 0;
  }
  /**
   * @brief sort maintenance record.
   *
   * @return 0.
   */
  public static int sort_maintenance_record() {
    return 0;
  }
  /**
  * @brief search maintenance record.
  *
  * @return 0.
  */
  public static int search_maintenance_record() {
    return 0;
  }
  /**
   * @brief properties menu.
   *
   * @return 0.
   */
  public int propertiesMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("\n--------Properties--------");
      System.out.print("\n1-)Show Properties");
      System.out.print("\n2-)Add Property");
      System.out.print("\n3-)Edit Properties");
      System.out.print("\n4-)Delete Properties");
      System.out.print("\n5-)Search Properties");
      System.out.print("\n6-)Sort Properties");
      System.out.print("\n7-)Return to Main Menu");
      System.out.print("\nPlease enter a choice: ");
      int choiceProperties = scanner.nextInt();

      if (choiceProperties == SubMenuVariables.subMenuShow) {
        System.out.print("\n--------------Property Records--------------\n");
        file_read("property_records.bin",'N');
      } else if (choiceProperties == SubMenuVariables.subMenuAdd) {
        add_property_record();
      } else if (choiceProperties == SubMenuVariables.subMenuEdit) {
        edit_property_record();
      } else if (choiceProperties == SubMenuVariables.subMenuDelete) {
        delete_property_record();
      } else if (choiceProperties == SubMenuVariables.subMenuSearch) {
        search_property_record();
      } else if (choiceProperties == SubMenuVariables.subMenuSort) {
        sort_property_record();
      } else if (choiceProperties == SubMenuVariables.subMenuReturn) {
        break;
      } else {
        System.out.print("\nPlease input a correct choice.");
      }
    }

    scanner.close();
    return 0;
  }
  /**
  * @brief tenants menu.
  *
  * @return 0.
  */
  public int tenantsMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("\n--------Tenants--------");
      System.out.print("\n1-)Show Tenants");
      System.out.print("\n2-)Add Tenants");
      System.out.print("\n3-)Edit Tenants");
      System.out.print("\n4-)Delete Tenants");
      System.out.print("\n5-)Search Tenants");
      System.out.print("\n6-)Sort Tenants");
      System.out.print("\n7-)Return to Main Menu");
      System.out.print("\nPlease enter a choice: ");
      int choiceTenants = scanner.nextInt();

      if (choiceTenants == SubMenuVariables.subMenuShow) {
        System.out.print("\n--------------Tenant Records--------------\n");
        file_read("tenant_records.bin", 'N');
      } else if (choiceTenants == SubMenuVariables.subMenuAdd) {
        add_tenant_record();
      } else if (choiceTenants == SubMenuVariables.subMenuEdit) {
        edit_tenant_record();
      } else if (choiceTenants == SubMenuVariables.subMenuDelete) {
        delete_tenant_record();
      } else if (choiceTenants == SubMenuVariables.subMenuSearch) {
        search_tenant_record();
      } else if (choiceTenants == SubMenuVariables.subMenuSort) {
        sort_tenant_record();
      } else if (choiceTenants == SubMenuVariables.subMenuReturn) {
        break;
      } else {
        System.out.print("\nPlease input a correct choice.");
      }
    }

    scanner.close();
    return 0;
  }
  /**
  * @brief rents menu.
  *
  * @return 0.
  */
  public int rentsMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("\n--------Rent Tracking--------");
      System.out.print("\n1-)Show Rents");
      System.out.print("\n2-)Add Rents");
      System.out.print("\n3-)Edit Rents");
      System.out.print("\n4-)Delete Rents");
      System.out.print("\n5-)Search Rents");
      System.out.print("\n6-)Sort Rents");
      System.out.print("\n7-)Return to Main Menu");
      System.out.print("\nPlease enter a choice: ");
      int choiceRents = scanner.nextInt();

      if (choiceRents == SubMenuVariables.subMenuShow) {
        System.out.print("\n--------------Rent Records--------------\n");
        file_read("rent_records.bin", 'N');
      } else if (choiceRents == SubMenuVariables.subMenuAdd) {
        add_rent_record();
      } else if (choiceRents == SubMenuVariables.subMenuEdit) {
        edit_rent_record();
      } else if (choiceRents == SubMenuVariables.subMenuDelete) {
        delete_rent_record();
      } else if (choiceRents == SubMenuVariables.subMenuSearch) {
        search_rent_record();
      } else if (choiceRents == SubMenuVariables.subMenuSort) {
        sort_rent_record();
      } else if (choiceRents == SubMenuVariables.subMenuReturn) {
        break;
      } else {
        System.out.print("\nPlease input a correct choice.");
      }
    }

    scanner.close();
    return 0;
  }
  /**
  * @brief maintenance menu.
  *
  * @return 0.
  */
  public int maintenanceMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("\n--------Maintenance Tracking--------");
      System.out.print("\n1-)Show Maintenances");
      System.out.print("\n2-)Add Maintenances");
      System.out.print("\n3-)Edit Maintenances");
      System.out.print("\n4-)Delete Maintenances");
      System.out.print("\n5-)Search Maintenances");
      System.out.print("\n6-)Sort Maintenances");
      System.out.print("\n7-)Return to Main Menu");
      System.out.print("\nPlease enter a choice: ");
      int choiceMaintenances = scanner.nextInt();

      if (choiceMaintenances == SubMenuVariables.subMenuShow) {
        System.out.print("\n--------------Maintenance Records--------------\n");
        file_read("maintenance_records.bin", 'N');
      } else if (choiceMaintenances == SubMenuVariables.subMenuAdd) {
        add_maintenance_record();
      } else if (choiceMaintenances == SubMenuVariables.subMenuEdit) {
        edit_maintenance_record();
      } else if (choiceMaintenances == SubMenuVariables.subMenuDelete) {
        delete_maintenance_record();
      } else if (choiceMaintenances == SubMenuVariables.subMenuSearch) {
        search_maintenance_record();
      } else if (choiceMaintenances == SubMenuVariables.subMenuSort) {
        sort_maintenance_record();
      } else if (choiceMaintenances == SubMenuVariables.subMenuReturn) {
        break;
      } else {
        System.out.print("\nPlease input a correct choice.");
      }
    }

    scanner.close();
    return 0;
  }
  /**
   * @brief main menu.
   *
   * @return 0.
   */
  public int mainMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("\n--------Main Menu--------");
      System.out.print("\n1-)Properties");
      System.out.print("\n2-)Tenants");
      System.out.print("\n3-)Rent Tracking");
      System.out.print("\n4-)Maintenance Tracking");
      System.out.print("\n5-)Log out");
      System.out.print("\nPlease enter a choice: ");
      int choiceMainMenu = scanner.nextInt();

      if (choiceMainMenu == MainMenuVariables.mainMenuProperty) {
        propertiesMenu();
      } else if (choiceMainMenu == MainMenuVariables.mainMenuTenant) {
        tenantsMenu();
      } else if (choiceMainMenu == MainMenuVariables.mainMenuRentTracking) {
        rentsMenu();
      } else if (choiceMainMenu == MainMenuVariables.mainMenuMaintenance) {
        maintenanceMenu();
      } else if (choiceMainMenu == MainMenuVariables.mainMenuLogOut) {
        break;
      } else {
        System.out.print("\nPlease input a correct choice.");
      }
    }

    scanner.close();
    return 0;
  }

  /**
   * @brief Displays the login menu, allowing the user to log in.
   *
   * @return 0 indicating the process completed.
   */
  public int loginMenu() {
    Scanner scanner = new Scanner(System.in);
    String userName = "";
    String password = "";
    String userFile = "user.bin";
    System.out.print("Please enter your username: ");
    userName = scanner.next();
    System.out.print("\nPlease enter your password: ");
    password = scanner.next();

    if (user_login(userName, password, userFile) == 0) {
      mainMenu();
    }

    scanner.close();
    return 0;
  }

  /**
   * @brief Displays the register menu, allowing a new user to register.
   * This process may delete all previous records if confirmed by the user.
   *
   * @return 0 indicating the process completed.
   */
  public int registerMenu() {
    Scanner scanner = new Scanner(System.in);
    String userName = "";
    String password = "";
    String recoveryKey = "";
    String userFile = "user.bin";
    char warning;
    System.out.print("Please enter your new username: ");
    userName = scanner.next();
    System.out.print("\nPlease enter your new password: ");
    password = scanner.next();
    System.out.print("\nPlease enter your new recovery key: ");
    recoveryKey = scanner.next();
    System.out.print("\n------------WARNING------------");
    System.out.print("\nThis process will delete all previous records, do you still wish to proceed?[Y/n]: ");
    warning = scanner.next().charAt(0);

    if (warning == 'Y') {
      user_register(userName, password, recoveryKey, userFile);
    } else {
      System.out.println("\nProcess terminated.");
    }

    scanner.close();
    return 0;
  }
  /**
   * @brief Displays the change password menu, allowing users to change their password using a recovery key.
   *
   * @return 0 indicating the process completed.
   */
  public int changePasswordMenu() {
    Scanner scanner = new Scanner(System.in);
    String password = "";
    String recoveryKey = "";
    String userFile = "user.bin";
    System.out.print("\nPlease enter your recovery key: ");
    recoveryKey = scanner.next();
    System.out.print("\nPlease enter your new password: ");
    password = scanner.next();
    user_change_password(recoveryKey, password, userFile);
    scanner.close();
    return 0;
  }

}
