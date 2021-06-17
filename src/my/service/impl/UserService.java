package my.service.impl;

/**
 * Created by rajab on 17/4/13.
 */
public abstract class UserService {
    public enum LoginResult {
        LOGIN_OK,
        INPUT_INVALID,
        PASSWORD_WRONG,
        USER_NOT_EXIST
    };
    public abstract LoginResult checkLogin (String username, String password);
}
