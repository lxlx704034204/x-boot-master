


新增功能来源项目位置： D:\Commonly_used_documents\springbootTest\1-learn-daydayup\daydayup-master\vue-springBoot-noSeparate\2\springbootvuemvc-master - 副本

★   BeanUtil JsonUtils ConvertUtil_diy

★    为什么使用Jersey?
    
    刚开始使用Jersey的时候,我也会有疑问,Spring家族已经很完善,为什么要用Jersey,但是后来做项目多了就感受到两者的差异.
    
    1.Jersey是JAX-RS标准的参考实现，是Java领域中开发REST式web/服务的"正统"工具,Spring属于自成一派,不是严格意义上的实现REST,但是springMVC已经支持RestFul风格,这个对我来说并不影响我开发项目.
    2.这点很关键,就是spring家族的庞大,导致spring依赖很重,打包占用较大的空间,Jersey相比就比较轻,反而更适合项目开发


★    搜索前后端不分离的springboot+vue项目方法：[src="//cdn.bootcss.com/element-ui/]
    
    <!--<script src="//cdn.bootcss.com/vue/2.1.8/vue.min.js"></script>--> 2.5.2/ 2.4.4/
     Vue引入.使用CDN方法
★   BootCDN（国内） : https://cdn.bootcss.com/vue/2.2.2/vue.min.js ， （国内不稳定）           
     unpkg：https://unpkg.com/vue/dist/vue.js, 会保持和 npm 发布的最新的版本一致。（推荐使用）
     cdnjs : https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js，如（<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js"></script>）


本项目地址： 由（https://blog.csdn.net/u010631302/article/details/80422464） 基础上改进而来
★1：   (这个连分页都没有，自己在此项目基础上加的带分页的springboot+vue)
★2：    // 动态查询条件（Specification复杂用法）
		Specification<Member> spec = (root, query, cb) -> {
			......
		};
    
    
参考：

	前后端分离 全栈开发（测试过可以通过命令npm run dev 运行package.json运行前端界面！）
	https://github.com/carter659/spring-boot-vue-element（http://www.cnblogs.com/GoodHelper/p/8430543.html）     
    
   
    
 这篇文章也很给力：https://www.imooc.com/article/22537（前后端分离 Spring Boot + Vue 开发单页面应用 个人总结（二））   
    
    
    
    
    
    
    
    
    
    
spring data jpa 后端分页（无界面！）： https://blog.csdn.net/Axela30W/article/details/80741880