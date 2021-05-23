package my.controller;

import com.jfinal.core.Controller;

/**
 * Created by Rajab on 2017/03/30.
 */
public class MainController extends Controller {

    public void index() {
        renderHtml("<h1>hello!</h1>");
    }
}
