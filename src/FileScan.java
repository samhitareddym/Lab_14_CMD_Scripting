import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScan {
    public static void main(String[] args) {
        File file;

        // Check if a command-line argument is passed
        if (args.length > 0) {
            file = new File(args[0]); // Use the first argument as the file name

            if (!file.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {
            // Launch JFileChooser if no argument is passed
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected. Exiting program.");
                return;
            }
        }

        // Scan the file and count lines, words, and characters
        try (Scanner scanner = new Scanner(file)) {
            int lines = 0, words = 0, characters = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines++;
                characters += line.length();
                words += line.split("\\s+").length;
            }

            System.out.println("File scanned: " + file.getName());
            System.out.println("Lines: " + lines);
            System.out.println("Words: " + words);
            System.out.println("Characters: " + characters);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to read the file.");
        }
    }
}
