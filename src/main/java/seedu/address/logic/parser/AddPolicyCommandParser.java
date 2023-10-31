package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.commands.AddPolicyCommand.AddPolicyDescriptor;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;

public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {
    private static final Pattern ADD_POLICY_COMMAND_FORMAT = Pattern.compile("addPolicy\\s+(\\d+)\\s+pn/([^\\s]+)\\s+pd/([^\\s]+)\\s+pv/([\\d.]+)\\s+psd/(\\S+)\\s+ped/(\\S+)");
    @Override
    public AddPolicyCommand parse(String args) throws ParseException {
        final Matcher matcher = ADD_POLICY_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }
        final Index index = ParserUtil.parseIndex(matcher.group(1));
        final String policyName = matcher.group(2);
        final String description = matcher.group(3);
        final String policyValue = matcher.group(4);
        final String startDate = matcher.group(5);
        final String endDate = matcher.group(6);


        AddPolicyDescriptor addPolicyDescriptor = new AddPolicyDescriptor();
        parsePolicyForEdit(policyName, description, policyValue, startDate, endDate).ifPresent(addPolicyDescriptor::setPolicies);
        return new AddPolicyCommand(index, addPolicyDescriptor);
    }

    private Optional<Set<Policy>> parsePolicyForEdit(String policyName, String description, String policyValue, String startDate, String endDate) throws ParseException {
        assert policyName != null;
        assert description != null;
        assert policyValue != null;
        assert startDate != null;
        assert endDate != null;


        return Optional.of(ParserUtil.parsePolicy(policyName, description, policyValue, startDate, endDate));
    }

}



