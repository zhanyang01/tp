package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

import java.util.List;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters for all the persons that have target tag being filtered. \n"
            + "Parameters: TAG (must be a valid tag from the predefined list) \n"
            + "Example: " + COMMAND_WORD + " friend";

    private final List<Tag> tags;

    public FilterCommand(List<Tag> tags) {
        this.tags = tags;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return null;
    }
}
