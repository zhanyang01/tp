package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.person.FilterContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split(String.valueOf(PREFIX_TAG));
        return new FilterCommand(new FilterContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }
}
