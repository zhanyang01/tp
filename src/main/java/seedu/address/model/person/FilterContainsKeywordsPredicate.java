package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        for (String keyword : keywords) {
            System.out.println("Current keyword: " + keyword);
            boolean keywordFound = false;
            for (Tag tag : person.getTags()) {
                String tagName = tag.getTagName();
                System.out.println("Current tagName: " + tagName);
                if (keyword.equalsIgnoreCase(tagName)) {
                    keywordFound = true;
                    System.out.println("Match! ");
                    break;
                } else {
                    keywordFound = false;
                }
            }
            if (!keywordFound) {
                return false;
            }
        }
        return true;
    }

    /**for (Tag tag : person.getTags()) {
            String tagName = tag.getTagName();
            for (String keyword : keywords) {
                if (keyword.equalsIgnoreCase(tagName)) {
                    return true;
                }
            }
        }
        return false;**/



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
