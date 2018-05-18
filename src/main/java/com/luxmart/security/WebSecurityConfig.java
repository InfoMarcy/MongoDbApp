package com.luxmart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	// to get the users details
	@Autowired
	private UserDetailsService  userDetailsService;	
	
	
	@Override 
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("*.js")
		.antMatchers("*.css")
		.antMatchers("*.ico");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
       
            .antMatchers("/users/**").hasAuthority("Admin")
            .antMatchers("/users/**").hasAnyAuthority(new String[] {"Admin", "User"})
            .antMatchers("/register").permitAll()
            .antMatchers("/error").permitAll()                              
            .antMatchers("/hotels", "/").permitAll()
            .antMatchers("/index").permitAll()
     
            .anyRequest().fullyAuthenticated()
        	  .and()
        	    .formLogin()
        	    .loginPage("/login").permitAll()  
        	    .failureUrl("/login?error").permitAll()
        	    .defaultSuccessUrl("/")        	    
        	    .usernameParameter("email").passwordParameter("password")        	    
        	  .and()	  
        	    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	    .deleteCookies("remember-me")
        	    .logoutSuccessUrl("/index")        	   
        	   .and()
        	   .exceptionHandling().accessDeniedPage("/403")
        	   .and()
        	   .rememberMe();
        
//        http.csrf().disable();

    }


@Autowired
public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {	 
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
}
	
}
