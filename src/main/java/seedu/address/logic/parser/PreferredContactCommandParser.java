package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_CONTACT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PreferredContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PreferredContact;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class PreferredContactCommandParser implements Parser<PreferredContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     * 
     * @throws ParseException if the user input does not conform the expected format
     */
    public PreferredContactCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PREFERRED_CONTACT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PreferredContactCommand.MESSAGE_USAGE), pe);
        }

        String preferredContactDescriptor = argMultimap.getValue(PREFIX_PREFERRED_CONTACT).orElse("");

        return new PreferredContactCommand(index, new PreferredContact(preferredContactDescriptor));
    }
}
