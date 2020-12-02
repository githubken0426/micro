package org.server.light.snow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 关闭csrf
		super.configure(http); // 开启认证
	}

	/**
	 * username:eureka-server password:eureka-server(bcrypt加密)
	 *
	 * @return
	 */
	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("eureka-server")
				.password("{bcrypt}$2a$10$xCh3goFhXzV.QPC67xWbCe1QDCbfIwmINe/pCqgYiJjg0Z9pbS7Mu").roles("USER").build();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(user);
		return manager;
	}
}