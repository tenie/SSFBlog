package net.tenie.myblog.config;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;
import net.tenie.myblog.interceptor.LoginInterceptor; 

@Configuration
public class MyBlogMvcConfig implements WebMvcConfigurer {

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源； *.css,*.js
                //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new LoginInterceptor())
                  .addPathPatterns("/**")   
                  .excludePathPatterns("/lib/**", "/**/*.html" );
            }
        };
    }
    
    // 文件上传大小限制
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值 
        factory.setMaxFileSize( DataSize.ofMegabytes(50)); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize( DataSize.ofMegabytes(50));
        return factory.createMultipartConfig();
    } 

}
