package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;

import seedu.address.logic.commands.ChangePasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;


/**
 * Parses input arguments and creates a new FileCommand object
 */
public class ChangePasswordCommandParser implements Parser<ChangePasswordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FileCommand
     * and returns a FileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangePasswordCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OLD_PASSWORD, PREFIX_NEW_PASSWORD);
        if (!arePrefixesPresent(argMultimap, PREFIX_OLD_PASSWORD, PREFIX_NEW_PASSWORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ChangePasswordCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_OLD_PASSWORD, PREFIX_NEW_PASSWORD);
        String oldPassword = argMultimap.getValue(PREFIX_OLD_PASSWORD).get();
        String newPassword = argMultimap.getValue(PREFIX_NEW_PASSWORD).get();

        return new ChangePasswordCommand(oldPassword, newPassword);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
