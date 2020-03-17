//package com.chuyue.shrio;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//@Configuration
//public class ShiroConfig {
//    @Bean
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//        System.out.println("--------------------shiro filter-------------------");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
//        //注意过滤器配置顺序 不能颠倒
//        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/favicon.ico", "anon");
//        filterChainDefinitionMap.put("/hello", "anon");
//        //拦截其他所有接口
//        filterChainDefinitionMap.put("/**", "authc");
//        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后要跳转的链接 自行处理。不用shiro进行跳转
//        // shiroFilterFactoryBean.setSuccessUrl("user/index");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * shiro 用户数据注入
//     * @return
//     */
//    @Bean
//    public MyShiroRealm shiroRealm(){
//        MyShiroRealm shiroRealm = new MyShiroRealm();
//        return shiroRealm;
//    }
//
//    @Bean
//    public SessionManager sessionManager(){
//        System.out.println("******sessionManager()");
//        return new MySessionManager();
//    }
//
//
//    /**
//     * 配置管理层。即安全控制层
//     * @return
//     */
//    @Bean
//    public SecurityManager securityManager(MyShiroRealm realm,SessionManager sessionManager){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(realm);
//        securityManager.setSessionManager(sessionManager);
//        return securityManager;
//    }
//
//
//}