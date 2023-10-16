package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's preferred contact method in the address book.
 */
public class PreferredContact {

    public final String value;

    /**
     * Constructs a {@code Preferred Contact}.
     *
     * @param preferred contact.
     */
    public PreferredContact(String preferredContact) {
        requireNonNull(preferredContact);
        value = preferredContact;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PreferredContact)) {
            return false;
        }

        PreferredContact otherPreferredContact = (PreferredContact) other;
        return value.equals(otherPreferredContact.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
