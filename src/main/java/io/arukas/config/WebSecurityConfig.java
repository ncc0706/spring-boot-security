package io.arukas.config;

import io.arukas.security.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;


    /**
     * Web安全
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                // 静态资源
                .antMatchers("/vendor/**", "/assets/**", "/favicon.ico");
    }

    /**
     * HTTP请求安全处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//.csrf().disable() // 关闭csrf
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/u1").hasRole("U1")
                .and()
                .formLogin().loginPage("/account/signin")
                .loginProcessingUrl("/login").defaultSuccessUrl("/")
//                .loginProcessingUrl("/account/s")
//                .usernameParameter("userid")
//                .passwordParameter("passwd")
                .successHandler((req, res, auth) -> {
                    for (GrantedAuthority authority : auth.getAuthorities()) {
                        System.out.println(authority.getAuthority());
                    }
                    System.out.println(auth.getName());
                    // 需要注册session才可以获取当前在线用户
                    sessionRegistry().registerNewSession(req.getSession().getId(), auth.getPrincipal());
                    res.sendRedirect("/"); // Redirect user to index/home page
                })
                .failureHandler((req, res, exp) -> {
                    String errMsg = "";
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        errMsg = "Invalid username or password.";
                    } else {
                        errMsg = "Unknown error - " + exp.getMessage();
                    }
                    System.out.println("errMsg: " + errMsg);
                    req.getSession().setAttribute("message", errMsg);
                    res.sendRedirect("/account/signin"); // Redirect user to login page with error message.
                })
                .permitAll()
                .and()
                .logout()
//                    .logoutUrl("/account/signout")
//                .logoutSuccessUrl("/account/signin")
//                .addLogoutHandler()
                //.logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error/403")
                .and()
                .csrf().disable()
        ;


    }

    /**
     * 身份验证管理生成器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("u").password("u").roles("u")
        ;

    }
}
