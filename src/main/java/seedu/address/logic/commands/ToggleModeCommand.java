package seedu.address.logic.commands;


import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.UiModeManager;

import static java.util.Objects.requireNonNull;

/**
 * Create/Opens the folder of a person identified using it's displayed index
 * from the address book.
 */
public class ToggleModeCommand extends Command {

    public static final String COMMAND_WORD = "toggleMode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Toggles between LightMode and DarkMode";

    public static final String TOGGLE_SUCCESS = "Changed Mode, the next time InsuraHub is opened it will be in ";
    private UiModeManager uiModeManager;
    private String newMode;

    public ToggleModeCommand() {
        this.uiModeManager = new UiModeManager();
        if (uiModeManager.getUiMode().equals("lightWindow.fxml")) {
            this.newMode = "DarkMode";
        } else {
            this.newMode = "LightMode";
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String uiMode = uiModeManager.getUiMode();
        if (uiMode.equals("MainWindow.fxml")) {
            uiModeManager.setUiMode("lightWindow.fxml");
        } else {
            uiModeManager.setUiMode("MainWindow.fxml");
        }
        return new CommandResult(TOGGLE_SUCCESS + newMode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
