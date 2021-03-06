package com.fcs.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fcs.common.ShiroRealm;
import com.fcs.common.shiro.web.filter.captcha.CaptchaValidateFilter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * Created by Lucare.Feng on 2017/3/6.
 */
@Configuration
public class ShiroConfiguration {


    // 验证码开关
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    // 验证码类型
    @Value("${shiro.user.captchaType}")
    private String captchaType;


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    @Bean(name = "hashedCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName(AuthConstant.hashAlgorithmName);
////        credentialsMatcher.setHashIterations(AuthConstant.hashIterations);
//        credentialsMatcher.setHashIterations(1);
////        credentialsMatcher.setStoredCredentialsHexEncoded(AuthConstant.hexEncodedEnabled);默认就是这个
//        return credentialsMatcher;
//    }

    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //看了源码  默认就是这个  没必要在此构建了
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }
    /*
     * 常用过滤器
     * anon：无需认证
     * authc：认证后可访问
     * user：使用remember me可访问
     * perms:该资源必须得到资源权限才可访问
     * role：得到角色权限可访问
     *
     * */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager  securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        filterChainDefinitionManager.put("/static/**", "anon");
        filterChainDefinitionManager.put("/templates/**", "anon");
        filterChainDefinitionManager.put("/login", "anon");
        filterChainDefinitionManager.put("/xyhd", "anon");
        filterChainDefinitionManager.put("/register","anon");
        filterChainDefinitionManager.put("/details","anon");
        filterChainDefinitionManager.put("/activitydetails","anon");
        filterChainDefinitionManager.put("/captcha**", "anon");
        // 退出 logout地址，shiro去清除session
        filterChainDefinitionManager.put("/logout", "logout");
        filterChainDefinitionManager.put("/**.html", "authc");
        filterChainDefinitionManager.put("/index", "authc");
        filterChainDefinitionManager.put("/profile", "authc");
        filterChainDefinitionManager.put("/note", "authc");
        filterChainDefinitionManager.put("/mate/**", "authc");
        filterChainDefinitionManager.put("/admin/**", "authc,roles[guetmates,admin]");

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("captchaValidate", captchaValidateFilter());
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }



    /**
     * 自定义验证码过滤器
     */
    @Bean(name = "captchaValidate")
    public CaptchaValidateFilter captchaValidateFilter()
    {
        CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
        captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        captchaValidateFilter.setCaptchaType(captchaType);
        return captchaValidateFilter;
    }

}
