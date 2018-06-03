package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	protected void configure(HttpSecurity http) throws Exception {
		
		  http
		       .authorizeRequests() //认证请求
		       .antMatchers("/about","/login","/signup").permitAll() //允许urls包含about login signup的所有请求
		       .anyRequest().authenticated() //授权任何请求
		       .and().formLogin().loginPage("/login") //自定义页面
		       .and().logout() //登出
		       .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
		       .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); 
		       //使csrf可用
	}

}
