package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_VALUE;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.commands.AddPolicyCommand.AddPolicyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.policy.Policy;

/**
 * Parses input arguments and creates a new AddPolicyCommand object
 */
public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {

    @Override
    public AddPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                                PREFIX_POLICY_NAME,
                                PREFIX_POLICY_DESCRIPTION,
                                PREFIX_POLICY_VALUE,
                                PREFIX_POLICY_START_DATE,
                                PREFIX_POLICY_END_DATE);
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE), pe);
        }

        final String policyName = argMultimap.getValue(PREFIX_POLICY_NAME).get();
        final String description = argMultimap.getValue(PREFIX_POLICY_DESCRIPTION).get();
        final String policyValue = argMultimap.getValue(PREFIX_POLICY_VALUE).get();
        final String startDate = argMultimap.getValue(PREFIX_POLICY_START_DATE).get();
        final String endDate = argMultimap.getValue(PREFIX_POLICY_END_DATE).get();

        AddPolicyDescriptor addPolicyDescriptor = new AddPolicyDescriptor();
        parsePolicyForEdit(policyName, description, policyValue, startDate, endDate)
                .ifPresent(addPolicyDescriptor::setPolicies);
        return new AddPolicyCommand(index, addPolicyDescriptor);
    }

    private Optional<Set<Policy>> parsePolicyForEdit(
            String policyName,
            String description,
            String policyValue,
            String startDate,
            String endDate) throws ParseException {
        assert policyName != null;
        assert description != null;
        assert policyValue != null;
        assert startDate != null;
        assert endDate != null;

        LocalDate parsedStartDate;
        LocalDate parsedEndDate;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedStartDate = LocalDate.parse(startDate, formatter);
            parsedEndDate = LocalDate.parse(endDate, formatter);
            if (parsedStartDate.isAfter(parsedEndDate)) {
                throw new ParseException("End date cannot be earlier than the start date");
            }
        } catch (DateTimeParseException e) {
            throw new ParseException(Policy.DATE_MESSAGE_CONSTRAINTS);
        }

        return Optional.of(ParserUtil.parsePolicy(policyName, description, policyValue, startDate, endDate));
    }



}


