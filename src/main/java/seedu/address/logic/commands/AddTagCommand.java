package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
public abstract class AddTagCommand extends Command {

    public static final String COMMAND_WORD  = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds any number of tags to an the corresponding person identified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: TAG (must be a valid tag from the pre-defined list), INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + "<friend> /1";
    public static final String MESSAGE_SUCCESS = "Added tags %s successfully for person %1$s";

    private final Index targetIndex;

    public AddTagCommand(Index targetIndex) { this.targetIndex = targetIndex; }

}
