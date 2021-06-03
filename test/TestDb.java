import com.jfinal.ext.test.ControllerTestCase;
import my.config.MainConfig;
import my.model.Category;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class TestDb extends ControllerTestCase<MainConfig> {
    @BeforeClass
    public static void initConfig(){
        try {
            start(MainConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        List<Category> categories = Category.dao.find("select * from category");
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}
