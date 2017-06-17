package net.tenie.web.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
* 定义获取spring 对象的方法 
*/
@Component
public class ApplicationContextHelper implements ApplicationContextAware{
    // 定义日志接口
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHelper.class);
    
    //Spring应用上下文环境  
    private static ApplicationContext applicationContext = null;     
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("执行了Bean 的初始化");
        ApplicationContextHelper.applicationContext = applicationContext;
    }
    
    /** 
     * @return ApplicationContext 
     */  
     public static ApplicationContext getApplicationContext() {  
       return applicationContext;  
     }  
      
     /** 
     * 获取对象   
     * @param name 
     * @return Object 一个以所给名字注册的bean的实例 
     * @throws BeansException 
     */  
     public static Object getBeanByName(String name) {  
       return applicationContext.getBean(name);  
     } 
     
     /** 
      * 获取对象   
      * @param name 
      * @return Object 一个以所给名字注册的bean的实例 
      * @throws BeansException 
      */  
      public static <T> T getBeanByType(Class<T> beanType) {  
        return applicationContext.getBean(beanType);
      }  
      
     /** 
     * 获取类型为requiredType的对象 
     * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException） 
     * @param name       bean注册名 
     * @param requiredType 返回对象类型 
     * @return Object 返回requiredType类型对象 
     * @throws BeansException 
     */  
     public static <T> Object getBeanByName(String name, Class<T> beanType) {
       return applicationContext.getBean(name, beanType);  
     }  
      
      
     /** 
     * @param name 
     * @return Class 注册对象的类型 
     * @throws NoSuchBeanDefinitionException 
     */  
     public static Class getType(String name){ 
       return applicationContext.getType(name);  
     }  
      
     /** 
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名   
     
     */  
     public static String[] getAliases(String name){  
       return applicationContext.getAliases(name);  
     }  
}
