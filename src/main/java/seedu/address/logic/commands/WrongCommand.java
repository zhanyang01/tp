package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * displays a message that tells the user the specific command word they keyed in does not take in any arguments..
 */
public class WrongCommand extends Command {

    private final String commandWord;

    public WrongCommand(String commandWord) {
        this.commandWord = commandWord;
    }
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("The Command word " + commandWord + " cannot take in any arguments.");
    }
}
