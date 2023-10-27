package seedu.address.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
