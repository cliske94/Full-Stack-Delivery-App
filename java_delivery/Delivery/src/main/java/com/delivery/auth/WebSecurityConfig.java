package com.delivery.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.deliver.api.auth.JwtConfigurer;
import com.deliver.api.auth.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private static JwtTokenProvider jwtTokenProvider;

	@Autowired
	private MySQLUserDetailService mySQLUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtTokenProvider getProvider() {
		return new JwtTokenProvider();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mySQLUserDetailsService).passwordEncoder(passwordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/", "/home", "/register", "/auth/signin", "/css/**.css").permitAll()
				.antMatchers(HttpMethod.GET, "/api/**").permitAll().antMatchers(HttpMethod.DELETE, "/api/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.POST, "/api/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/", true).permitAll().and().logout().permitAll().and()
				.apply(new JwtConfigurer(jwtTokenProvider));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/javax.faces.resource/**");
	}
}
