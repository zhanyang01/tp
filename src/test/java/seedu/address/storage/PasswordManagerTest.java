package seedu.address.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class PasswordManagerTest {

    private PasswordManager passwordManager;
    private String originalpassword;

    @BeforeEach
    public void setUp() {
        passwordManager = new PasswordManager();
        originalpassword = passwordManager.getPassword();
    }

    @Test
    public void testSetAndRead() throws Exception {
        passwordManager.setPassword("samplePassword");
        assert(passwordManager.check("samplePassword"));
    }

    @Test
    public void testCreateFolder() throws Exception {
        passwordManager.setPassword("samplePassword");
        assert(passwordManager.check("samplePassword"));
    }

    @AfterEach
    public void setOriginalPasswordBack() {
        passwordManager.setPassword(originalpassword);
    }

}
