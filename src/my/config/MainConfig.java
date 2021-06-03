package my.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.template.Engine;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import my.controller.MainController;
import my.model._MappingKit;

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
        ActiveRecordPlugin arp = new ActiveRecordPlugin(getDataSource());
        arp.setDialect(new MysqlDialect());
        arp.setDevMode(PropKit.getBoolean("devMode", true));
        arp.setShowSql(PropKit.getBoolean("showSql", true));
        _MappingKit.mapping(arp);
        me.add(arp);
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

        Prop prop = PropKit.use("myconfig.properties");
        String basePath = PathKit.getWebRootPath() + "\\..\\src";
        String baseModelPackageName = "my.model.base";
        String modelPackageName = "my.model";
        String baseModelPath = basePath + "\\my\\model\\base";
        String modelPath = basePath + "\\my\\model";
        String mappingKitPath = basePath + "\\my\\config";
        String mappingKitPackageName = "my.config";
        Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelPath, modelPackageName, modelPath);
        generator.setDialect(new MysqlDialect());
        generator.setGenerateDaoInModel(true);

        generator.setMappingKitOutputDir(modelPath);
        generator.setMappingKitPackageName(modelPackageName);
        generator.generate();

    }
}

