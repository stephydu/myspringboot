package com.du.myspringboot;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
//在入口类启动时加载config.properties
@PropertySource("classpath:config.properties")
//MapperScan注解会在SpringBoot启动的时候扫描mapper包，并根据xml自动生成对应的实现类
@MapperScan("com.du.myspringboot.mapper")
public class MyspringbootApplication {
//    //在入口类中注册Filter
//    @Bean //@Bean会将方法中的返回对象在SpringBoot启动的时候放入IOC容器
//    public FilterRegistrationBean filterRegiste(){
//        FilterRegistrationBean regFilter = new FilterRegistrationBean();
//        //创建并注册AccessRecorderFilter
//        regFilter.setFilter(new AccessRecorderFilter());
//        //对所有请求进行拦截
//        regFilter.addUrlPatterns("/*");
//        //过滤器名字
//        regFilter.setName("AccessRecorder");
//        //设置排序,如果系统中有多个过滤器,order就决定了那个过滤器先执行，数字越小越靠前执行
//        regFilter.setOrder(1);
//        return regFilter;
//    }
    @Bean //手动初始化DruidDataSource 对象
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }
    //注册后台界面Servlet bean , 用于显示后台界面
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建StatViewServlet，绑定到/druid/路径下
        //开启后，访问localhost/druid就可以看到druid管理后台
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet() , "/druid/*");
        Map<String ,String > param = new HashMap<String,String>();
        param.put("loginUsername" , "admin");
        param.put("loginPassword" , "sa1234");
        param.put("allow" , "");//哪些IP允许访问后台“”代表所有地址
        param.put("deny" , "33.31.51.88");//不允许这个IP访问
        bean.setInitParameters(param);
        return bean;
    }
    //用于监听获取应用的数据 ， Filter用于收集数据, Servlet用于展现数据
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter()); //设置过滤器
        bean.addUrlPatterns("/*");
        Map<String,String> param = new HashMap<String,String>();
        //排除静态资源
        param.put("exclusions" , "*.png,*.woff,*.js,*.css,/druid/*");
        bean.setInitParameters(param);
        return bean;
    }
    public static void main(String[] args) {

//        SpringApplication.run(MyspringbootApplication.class, args);
        SpringApplication app = new SpringApplication(MyspringbootApplication.class);
        //关闭Banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
