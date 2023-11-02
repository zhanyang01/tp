package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.UIModeManager;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Create/Opens the folder of a person identified using it's displayed index
 * from the address book.
 */
public class ToggleModeCommand extends Command {

    public static final String COMMAND_WORD = "ToggleMode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Toggles between LightMode and DarkMode";

    public static final String TOGGLE_SUCCESS = "Changed Mode, the next time InsuraHub is opened it will be in " ;
    private UIModeManager uiModeManager;
    private String newMode;

    public ToggleModeCommand() {
        this.uiModeManager = new UIModeManager();
        if (uiModeManager.getUIMode().equals("lightWindow.fxml")) {
            this.newMode = "DarkMode";
        } else {
            this.newMode = "LightMode";
        }

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String uiMode = uiModeManager.getUIMode();
        if (uiMode.equals("MainWindow.fxml")) {
            uiModeManager.setUIMode("lightWindow.fxml");
        } else {
            uiModeManager.setUIMode("MainWindow.fxml");
        }
        return new CommandResult(TOGGLE_SUCCESS+ newMode);
    }

    /**
     * Creates a folder with the name ClientFiles if it does not exist,
     * creates a folder within that folder named {@code folderName} if it does not
     * exist,
     * then opens the folder.
     */

    @Override
    public boolean equals(Object other) {
        return other == this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
