package my.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.template.Engine;
import my.controller.MainController;

/**
 * Created by Rajab on 2017/03/30.
 */
public class MainConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        //开启Jfinal的开发模式
        me.setDevMode(true);

    }

    @Override
    public void configRoute(Routes me) {

        //设置视图的默认目录
        me.setBaseViewPath("/pages");
        //   http://localhost/xxx ->  MainController.xxx()
        me.add("/", MainController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {
        //设置上下文路径 可以在页面使用base来获取
        me.add(new ContextPathHandler("base"));
    }

    public static void main(String[] args) {
        JFinal.start("web", 8080, "/");
    }
}
