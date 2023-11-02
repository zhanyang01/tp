package seedu.address.logic.parser;

import seedu.address.logic.commands.ToggleModeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FileCommand object
 */
public class ToggleModeCommandParser implements Parser<ToggleModeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FileCommand
     * and returns a FileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ToggleModeCommand parse(String args) throws ParseException {
        try {
            return new ToggleModeCommand();
        } catch (Exception e) {
            System.out.println("ana error occurred while toggling UI mode" + e);
        }
        return new ToggleModeCommand();
    }

}
