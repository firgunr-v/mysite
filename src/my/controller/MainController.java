package my.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import my.interceptor.LoginInterceptor;

/**
 * Created by Rajab on 2017/03/30.
 */
public class MainController extends Controller {

    public void index() {
        renderHtml("<h1>hello!</h1>");
    }

    public void login() {
        renderFreeMarker("test.ftl");
    }

    public void loginCheck() {
        String username = getPara("username", "");
        String password = getPara("password", "");
        if (username.isEmpty() || password.isEmpty()){
            setAttr("errMsg", "用户名或密码为空");
            forwardAction("/login");
        }else if(username.equals("admin") && password.equals("admin")){
            setSessionAttr("username", username);
            redirect("/success");
        }else{
            setAttr("errMsg", "用户名或密码错误");
            forwardAction("/login");
        }

    }

    @Before(LoginInterceptor.class)
    public void success(){
        renderFreeMarker("success.ftl");
    }

    public void main(){
        renderFreeMarker("main.ftl");
    }

    public void logout(){

    }

}
