package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FilterContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FilterContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        for (Tag tag : person.getTags()) {
            String tagName = tag.getTagName(); // Replace with the actual method to get the tag name
            for (String keyword : keywords) {
                if (keyword.equalsIgnoreCase(tagName)) {
                    return true; // Match found, exit early
                }
            }
        }
        return false; // No match found after checking all tags and keywords
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterContainsKeywordsPredicate)) {
            return false;
        }

        FilterContainsKeywordsPredicate otherFilterContainsKeywordsPredicate = (FilterContainsKeywordsPredicate) other;
        return keywords.equals(otherFilterContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
