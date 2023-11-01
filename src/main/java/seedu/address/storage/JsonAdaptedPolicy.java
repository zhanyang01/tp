package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDate;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.policy.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {
    private final String policyName;
    private final String description;
    private final double policyValue;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName}.
     */
    @JsonCreator
    public JsonAdaptedPolicy(
            String policyName,
            String description,
            double policyValue,
            LocalDate startDate,
            LocalDate endDate) {
        this.policyName = policyName;
        this.description = description;
        this.policyValue = policyValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.policyName;
        description = source.description;
        policyValue = source.policyValue;
        startDate = source.startDate;
        endDate = source.endDate;
    }

    @JsonValue
    public String getPolicyName() {
        return policyName;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonValue
    public double getPolicyValue() {
        return policyValue;
    }

    @JsonValue
    public LocalDate getStartDate() {
        return startDate;
    }

    @JsonValue
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's
     * {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted tag.
     */
    public Policy toModelType() throws IllegalValueException {
        if (!Policy.isValidPolicyName(policyName)) {
            throw new IllegalValueException(Policy.POLICY_NAME_MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidDescription(description)) {
            throw new IllegalValueException(Policy.DESCRIPTION_MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidPolicyValue(Double.toString(policyValue))) {
            throw new IllegalValueException(Policy.POLICY_VALUE_MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidDate(startDate)) {
            throw new IllegalValueException(Policy.DATE_MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidDate(endDate)) {
            throw new IllegalValueException(Policy.DATE_MESSAGE_CONSTRAINTS);
        }
        return new Policy(policyName, description, policyValue, startDate, endDate);
    }
}
