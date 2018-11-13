package cn.exrick.xboot.config.interceptor;

import cn.exrick.xboot.config.IgnoredUrlsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Exrickx
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private LimitRaterInterceptor limitRaterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(limitRaterInterceptor);
        // 配置拦截的路径
        ir.addPathPatterns("/**");

        // 配置不拦截的路径
        ir.excludePathPatterns(ignoredUrlsProperties.getUrls());

        //需要配置2：----------- 告知拦截器：/static/common/**  不需要拦截 （配置的是 路径）
        ir.excludePathPatterns("/static1/common/**" );//1.diy - http://localhost:1911/static1/common/public.js  "/static1"测试
        ir.excludePathPatterns("/static2/common/**" );//2.diy - http://localhost:1911/static2/common/public.js  "/static2"测试
        ir.excludePathPatterns("/test1/**" ); //3. 应用success("/resources/"层.html的访问)            spring.mvc.view.prefix= /test1/
        ir.excludePathPatterns("/test2/**" ); //4. 应用success( "/resources/templates" 层.html的访问) spring.mvc.view.prefix= /test2/


        ir.excludePathPatterns("/**" ); //不加无法请求到login界面   教程：https://blog.csdn.net/jiaoshaoping/article/details/80526464
    }





    //添加静态资源文件，外部可以直接访问地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其他静态资源，与本文关系不大
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ TaleUtils.getUplodFilePath()+"upload/");

        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！ 教程：https://www.cnblogs.com/kangkaii/p/9023751.html
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static1/**").addResourceLocations("classpath:/static1/");             //1. "/static1"测试
        registry.addResourceHandler("/static2/**").addResourceLocations("classpath:/templates/static2/");   //2. "/static2"测试

        registry.addResourceHandler("/test1/**").addResourceLocations("classpath:/test1/");           //3. 应用success("/resources/"层.html的访问)            spring.mvc.view.prefix= /test1/
        registry.addResourceHandler("/test2/**").addResourceLocations("classpath:/templates/test2/"); //4. 应用success( "/resources/templates" 层.html的访问) spring.mvc.view.prefix= /test2/
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/error/404").setViewName("/admin/page_error/error_404.html");
    }


}
