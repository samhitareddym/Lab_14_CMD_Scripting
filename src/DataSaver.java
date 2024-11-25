import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> dataRecords = new ArrayList<>();
        String firstName, lastName, email, idNumber, yearOfBirth;
        String continueInput = "y"; // Starts with 'y' to enter the loop

        // Loop to collect data from the user
        while (continueInput.equalsIgnoreCase("y")) {
            // Use SafeInput methods to collect user data (ensure SafeInput is implemented properly)
            firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            idNumber = SafeInput.getNonZeroLenString(scanner, "Enter ID Number (6 digits)");
            email = SafeInput.getNonZeroLenString(scanner, "Enter Email");
            yearOfBirth = SafeInput.getRegExString(scanner, "Enter Year of Birth (YYYY)", "\\d{4}");

            // Format the record as CSV
            String record = String.format("%s, %s, %s, %s, %s", firstName, lastName, idNumber, email, yearOfBirth);
            dataRecords.add(record);

            // Ask if the user wants to input another record
            continueInput = String.valueOf(SafeInput.getYNConfirm(scanner, "Do you want to add another record? (y/n): "));
        }

        // Once all records are collected, ask for the file name to save the data
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the filename to save the records (with .csv extension): ");

        // Write data to the CSV file
        try {
            // Get the src directory of your IntelliJ project to store the file
            File srcDirectory = new File(System.getProperty("user.dir") + "/src");
            if (!srcDirectory.exists()) {
                srcDirectory.mkdirs(); // Create src directory if it doesn't exist
            }
            Path filePath = Paths.get(srcDirectory.getPath(), fileName);

            // Write the CSV data to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
            for (String record : dataRecords) {
                writer.write(record);
                writer.newLine();
            }

            writer.close();  // Close the writer
            System.out.println("Data saved successfully to " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
