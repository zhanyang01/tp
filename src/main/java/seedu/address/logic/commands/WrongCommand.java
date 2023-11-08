package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class WrongCommand extends Command {

    private final String commandWord;

    public WrongCommand (String commandWord) {
        this.commandWord = commandWord;
    }
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("The Command word " + commandWord + " cannot take in any arguments.");
    }
}
