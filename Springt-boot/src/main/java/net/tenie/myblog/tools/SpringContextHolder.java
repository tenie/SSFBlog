package net.tenie.myblog.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware; 

/**ApplicationContextAware 接口的说明:
 * 1.  这个bean需要在xml中配置才会被注册到IoC容器
 * 2. 注册bean后会例子执行setApplicationContext()这个方法; 有点类似初始化的方法的意味
 *  应用:
 *  		把ApplicationContext放入到当期bean ; 做出静态的属性, 可以在所有的类中获取spring bean了
 */

 
//@Component  //不能使用注解来注册这个ApplicationContextAware接口的bean, 因为没有xml的优先级高; 在Spring initbean的执行中可能会找不到这个bean
public class SpringContextHolder implements ApplicationContextAware {

   private static ApplicationContext applicationContext;
   
    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
    	
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
