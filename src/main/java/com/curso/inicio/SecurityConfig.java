package com.curso.inicio;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
	@Bean
	public InMemoryUserDetailsManager  usersDetails() {
		List<UserDetails> users=List.of(
				User.withUsername("user1")
				 .password("{noop}user1")
				 .roles("INVITADO")
				 .build(),
				 User.withUsername("user2")
				 .password("{noop}user2")
				 .roles("OPERADOR")
				 .build(),
				 User.withUsername("user3")
				 .password("{noop}user3")
				 .roles("ADMIN")
				 .build(),
				 User.withUsername("user4")
				 .password("{noop}user4")
				 .roles("OPERADOR", "ADMIN")
				 .build()
				 );
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/agregar").hasRole("ADMIN")
		.antMatchers("/actualizar/**").hasAnyRole("ADMIN","OPERADOR")
		.antMatchers("/eliminar/*").hasAnyRole("ADMIN","OPERADOR")
		.antMatchers("/ver").authenticated()
		.antMatchers("/ver/**").authenticated()
		.and()
		.httpBasic(); //autenticación básica
		return http.build();
	}
}
