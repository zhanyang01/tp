package seedu.address.model.policy;

import java.time.LocalDate;
import java.util.Objects;

public class Policy {

    private final String name;
    private final String description;
    private final double policyValue;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Policy(String name, String description, double policyValue, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.policyValue = policyValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPolicyValue() {
        return policyValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Double.compare(policy.policyValue, policyValue) == 0 &&
                Objects.equals(name, policy.name) &&
                Objects.equals(description, policy.description) &&
                Objects.equals(startDate, policy.startDate) &&
                Objects.equals(endDate, policy.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, policyValue, startDate, endDate);
    }

    @Override
    public String toString() {
        return  "Policy name: " + name + "\n" +
                "Policy description: " + description + '\n' +
                "Value: " + policyValue + '\n'+
                "Start date: " + startDate + '\n' +
                "End date: " + endDate + '\n';

    }
}
