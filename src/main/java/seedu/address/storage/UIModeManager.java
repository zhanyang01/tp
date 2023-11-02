package seedu.address.storage;

import java.io.*;
import java.util.Objects;

/**
 * Manages the storage and validation of a UIMode.
 */
public class UIModeManager {

    private String UiMode;
    private final String UiModeFilePath; // The path to the file where the UIMode is stored

    /**
     * Constructs a UiModeManager and fetch the UiMode from a file called uiMode.txt,
     * file will be created if it does not exist.
     */
    public UiModeManager() {
        this.UiModeFilePath = "./uiMode.txt";
        this.UiMode = readUiModeFromFile();
    }

    /**
     * Reads the UIMode from a file if it exists, otherwise, returns an empty string.
     *
     * @return The stored UIMode or an empty string if no UIMode is set.
     */
    private String readUIModeFromFile() {
        File UiModeFile = new File(UiModeFilePath);

        if (!UiModeFile.exists()) {
            createUiModeFile(UiModeFile);
            return "MainWindow.fxml";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(UiModeFilePath))) {
            String string = reader.readLine();
            return Objects.requireNonNullElse(string, "MainWindow.fxml");
        } catch (IOException e) {
            // Handle file reading error
            return "MainWindow.fxml";
        }
    }

    /**
     * Creates a UiMode file if it doesn't exist.
     *
     * @param file The file to be created.
     */
    private void createUiModeFile(File file) {
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
        return input.equals(UiMode);
    }

    /**
     * Retrieves the stored UIMode.
     *
     * @return The stored UIMode.
     */
    public String getUiMode() {
        return UiMode;
    }

    /**
     * Sets a new UIMode, updating it in memory and writing it to the UIMode file.
     *
     * @param newUiMode The new UIMode to be set.
     */
    public void setUiMode(String newUiMode) {
        if (newUiMode == null) {
            newUiMode = "";
        }
        UiMode = newUiMode;
        File UIModeFile = new File(UiModeFilePath);

        if (!UIModeFile.exists()) {
            createUiModeFile(UIModeFile);
        }
        writeUiModeToFile(newUiMode);
    }


    /**
     * Writes the UIMode to the UIMode file.
     *
     * @param newUIMode The UIMode to be written to the file.
     */
    private void writeUiModeToFile(String newUIMode) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UiModeFilePath, false))) {
            writer.write(newUIMode);
        } catch (IOException e) {
            System.out.println("there was an error writing UIMode to file");
        }
    }
}
