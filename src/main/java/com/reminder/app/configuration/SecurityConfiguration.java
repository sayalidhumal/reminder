package com.reminder.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.reminder.app.model.type.RoleType;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService loginService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		        .authorizeRequests()
		        .antMatchers("/add-user").access(RoleType.ADMIN.getName())
		        .antMatchers("/favicon.ico").permitAll()
		        .antMatchers("/*").permitAll()
		        .anyRequest().authenticated()
		        .and()
		        .formLogin()
		        .loginPage("/login")
		        .defaultSuccessUrl("/main")
		        .failureUrl("/login-error")
		        .and()
		        .logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login")
		        .deleteCookies("JSESSIONID")
		        .invalidateHttpSession(true);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		        .ignoring()
		        .antMatchers("/css/**")
		        .antMatchers("/js/**")
		        .antMatchers("/image/**")
		        .antMatchers("/main/css/**")
		        .antMatchers("/main/js/**")
		        .antMatchers("/main/image/**");
	}
}
