import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileInspector {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        try {
            // Use the toolkit to get the current working directory of the IDE
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            // Open the file using JFileChooser
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                System.out.println("File selected: " + selectedFile.getAbsolutePath());  // Debug print
                Path file = selectedFile.toPath();
                BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file)));

                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                // Read each line of the file
                while (reader.ready()) {
                    rec = reader.readLine();
                    lines.add(rec);  // Read all the lines into memory in an ArrayList
                    lineCount++;

                    // Echo the line to the screen
                    System.out.println("Line " + lineCount + ": " + rec);

                    // Count words and characters in each line
                    wordCount += rec.split("\\s+").length;
                    charCount += rec.length();
                }

                reader.close();  // Close the reader

                // Print the summary report
                System.out.println("\nSummary Report:");
                System.out.println("File: " + selectedFile.getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);
            } else {
                System.out.println("No file selected. Exiting.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

