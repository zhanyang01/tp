package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds to the tag of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "Jurong West";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tags successfully for person %1$s";

    public static final String MESSAGE_NOT_EDITED = "One tag must be provided.";

    private final Index index;
    private final AddTagCommand.AddTagDescriptor addTagDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param addTagDescriptor details to edit the person with
     */

    public AddTagCommand(Index index, AddTagDescriptor addTagDescriptor) {
        requireNonNull(index);
        requireNonNull(addTagDescriptor);
        this.index = index;
        this.addTagDescriptor = new AddTagDescriptor(addTagDescriptor);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person personWithAddedTag = createPersonWithAddedTag(personToEdit, addTagDescriptor);
        personWithAddedTag.addTags(personToEdit.getTags());

        model.setPerson(personToEdit, personWithAddedTag);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, Messages.format(personWithAddedTag)));


    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code addTagDescriptor}.
     */
    private static Person createPersonWithAddedTag(Person personToEdit, AddTagDescriptor addTagDescriptor) {
        assert personToEdit != null;

        Name updatedName = addTagDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = addTagDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = addTagDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = addTagDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = addTagDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    public static <T> boolean hasDuplicates(Set<T> set) {
        Set<T> tempSet = new HashSet<>();
        for (T element : set) {
            if (tempSet.contains(element)) {
                return true;
            }
            tempSet.add(element);
        }
        return false;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        AddTagCommand otherAddTagCommand = (AddTagCommand) other;
        return index.equals(otherAddTagCommand.index)
                && addTagDescriptor.equals(otherAddTagCommand.addTagDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }

    /**
     * Stores the details to add the tag with. Added tag will be added to current list of tag.
     */

    public static class AddTagDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public AddTagDescriptor() {}

        /**
         * Copy constructor.s
         * A defensive copy of {@code tags} is used internally.
         */
        public AddTagDescriptor(AddTagDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddTagCommand.AddTagDescriptor)) {
                return false;
            }

            AddTagCommand.AddTagDescriptor otherAddTagDescriptor = (AddTagCommand.AddTagDescriptor) other;
            return Objects.equals(name, otherAddTagDescriptor.name)
                    && Objects.equals(phone, otherAddTagDescriptor.phone)
                    && Objects.equals(email, otherAddTagDescriptor.email)
                    && Objects.equals(address, otherAddTagDescriptor.address)
                    && Objects.equals(tags, otherAddTagDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        }
    }
}

