/**

@file RentalManagement.java
@brief This file serves as a demonstration file for the RentalManagement class.
@details This file contains the implementation of the RentalManagement class.
*/

/**

@package com.project.RentalManagement
@brief The com.project.RentalManagement package contains all the classes and files related to the com.project.RentalManagement App.
*/
package com.project.RentalManagementLib;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import ch.qos.logback.classic.Logger;
/**

@class RentalManagement
@brief This class represents a RentalManagement that performs file operations.
@details The RentalManagement class provides methods to perform file operations such as adding, deleting, editing, and sorting..
@author hikmethankolay
*/
public class RentalManagementLib {

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
            System.out.println("\nFile operation failed");
            return -1;
        }

        if (lineNumberToDelete < 1 || lineNumberToDelete > lines.size()) {
            System.out.println("\nInvalid line number.");
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
            System.out.println("\nFile operation failed");
            e.printStackTrace();
            return -1;
        }

        System.out.println("\nData successfully deleted");
        return 0;
    }

}
