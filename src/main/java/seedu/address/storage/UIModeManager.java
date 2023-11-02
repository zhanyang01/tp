package seedu.address.storage;

import java.io.*;
import java.util.Objects;

/**
 * Manages the storage and validation of a UIMode.
 */
public class UIModeManager {

    private String UIMode;
    private final String UIModeFilePath; // The path to the file where the UIMode is stored

    /**
     * Constructs a UIModeManager and fetch the UIMode from a file called uiMode.txt,
     * file will be created if it does not exist.
     */
    public UIModeManager() {
        this.UIModeFilePath = "./uiMode.txt";
        this.UIMode = readUIModeFromFile();
    }

    /**
     * Reads the UIMode from a file if it exists, otherwise, returns an empty string.
     *
     * @return The stored UIMode or an empty string if no UIMode is set.
     */
    private String readUIModeFromFile() {
        File UIModeFile = new File(UIModeFilePath);

        if (!UIModeFile.exists()) {
            createUIModeFile(UIModeFile);
            return "MainWindow.fxml";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(UIModeFilePath))) {
            String string = reader.readLine();
            return Objects.requireNonNullElse(string, "MainWindow.fxml");
        } catch (IOException e) {
            // Handle file reading error
            return "MainWindow.fxml";
        }
    }

    /**
     * Creates a UIMode file if it doesn't exist.
     *
     * @param file The file to be created.
     */
    private void createUIModeFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Checks if the input String matches the stored UIMode.
     *
     * @param input The input to be checked against the stored UIMode.
     * @return true if the input matches the stored UIMode, false otherwise.
     */
    public boolean check(String input) {
        return input.equals(UIMode);
    }

    /**
     * Retrieves the stored UIMode.
     *
     * @return The stored UIMode.
     */
    public String getUIMode() {
        return UIMode;
    }

    /**
     * Sets a new UIMode, updating it in memory and writing it to the UIMode file.
     *
     * @param newUIMode The new UIMode to be set.
     */
    public void setUIMode(String newUIMode) {
        if (newUIMode == null) {
            newUIMode = "";
        }
        UIMode = newUIMode;
        File UIModeFile = new File(UIModeFilePath);

        if (!UIModeFile.exists()) {
            createUIModeFile(UIModeFile);
        }
        writeUIModeToFile(newUIMode);
    }


    /**
     * Writes the UIMode to the UIMode file.
     *
     * @param newUIMode The UIMode to be written to the file.
     */
    private void writeUIModeToFile(String newUIMode) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UIModeFilePath, false))) {
            writer.write(newUIMode);
        } catch (IOException e) {
            System.out.println("there was an error writing UIMode to file");
        }
    }
}
