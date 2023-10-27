package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.storage.PasswordManager;

public class ChangePasswordCommandTest {

    @Test
    public void different_passwordManager() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        String wrongPassword = password + "wrong";
        ChangePasswordCommand wrongChangePasswordCommand = new ChangePasswordCommand(wrongPassword, password);
        ChangePasswordCommand rightChangePasswordCommand = new ChangePasswordCommand(password, password);
        assert(!wrongChangePasswordCommand.equals(rightChangePasswordCommand));
    }

    @Test
    public void same_passwordManager() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        ChangePasswordCommand firstChangePasswordCommand = new ChangePasswordCommand(password, password);
        ChangePasswordCommand secondChangePasswordCommand = new ChangePasswordCommand(password, password);
        assert(firstChangePasswordCommand.equals(secondChangePasswordCommand));
    }

    @Test
    public void same_passwordManager_toString() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        ChangePasswordCommand firstChangePasswordCommand = new ChangePasswordCommand(password, password);
        ChangePasswordCommand secondChangePasswordCommand = new ChangePasswordCommand(password, password);
        assert(firstChangePasswordCommand.toString().equals(secondChangePasswordCommand.toString()));
    }

}
