package my.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        if(invocation.getController().getSessionAttr("username") == null)
            invocation.getController().redirect("/login");
        else
            invocation.invoke();
    }
}
