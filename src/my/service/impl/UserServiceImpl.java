package my.service.impl;

/**
 * Created by rajab on 17/4/13.
 */
public class UserServiceImpl extends UserService {
    @Override
    public LoginResult checkLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()){
            return LoginResult.INPUT_INVALID;
        }else if(username.equals("admin") && password.equals("admin")){
            return LoginResult.LOGIN_OK;
        }else{
            return LoginResult.PASSWORD_WRONG;
        }
    }
}
