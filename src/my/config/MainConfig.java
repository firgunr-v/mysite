package my.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.template.Engine;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import my.controller.MainController;

import javax.sql.DataSource;

/**
 * Created by Rajab on 2017/03/30.
 */
public class MainConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        Prop prop = PropKit.use("myconfig.properties");
        //开启Jfinal的开发模式
        me.setDevMode(PropKit.getBoolean("devMode", true));

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
     /*   ActiveRecordPlugin arp = new ActiveRecordPlugin(getDataSource());
        arp.setDialect(new MysqlDialect());
        arp.setDevMode(PropKit.getBoolean("devMode", true));
        arp.setShowSql(PropKit.getBoolean("showSql", true));
        _MappingKit.mapping(arp);
        me.add(arp);*/
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
       // generateModels();
    }

    public static DataSource getDataSource() {

        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl(PropKit.get("jdbcurl"));
        mysqlDataSource.setUser(PropKit.get("user"));
        mysqlDataSource.setPassword(PropKit.get("password"));
        return mysqlDataSource;

    }

    public static void generateModels() {
        // 读取配置文件
        Prop prop = PropKit.use("myconfig.properties");
        //配置生成的源文件位置，getWebRootPath()为web所在目录,..为上一级目录，应该生成在src目录中
        String basePath = PathKit.getWebRootPath() + "\\..\\src";
        //模型基类包名
        String baseModelPackageName = "my.model.base";
        //模型类包名
        String modelPackageName = "my.model";
        //模型基类生成的位置，要放在相应的包中
        String baseModelPath = basePath + "\\my\\model\\base";
        //模型类生成的位置
        String modelPath = basePath + "\\my\\model";
        Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelPath, modelPackageName, modelPath);
        //SQL方言为MYSQL
        generator.setDialect(new MysqlDialect());
        //是否在model中生成dao字段
        generator.setGenerateDaoInModel(true);
        generator.setMappingKitOutputDir(modelPath);
        generator.setMappingKitPackageName(modelPackageName);
        generator.generate();

    }
}

