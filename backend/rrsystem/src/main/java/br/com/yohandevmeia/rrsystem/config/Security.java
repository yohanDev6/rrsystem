package br.com.yohandevmeia.rrsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {
    
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((authorize) -> authorize
            		.requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
            		
            		// Clients routes
	            	.requestMatchers(HttpMethod.POST, "/clients").permitAll()
	            	.requestMatchers(HttpMethod.GET, "/clients/{id}", "/clients/email/**").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.PUT, "/clients/").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.DELETE, "/clients/{id}").hasRole("CLIENT")
	            	
	            	// Administrators routes
	            	.requestMatchers(HttpMethod.POST, "/admins").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.GET, "/admins", "/admins/{id}").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.PUT, "/admins").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.DELETE, "/admins/{id}").hasRole("ADMIN")
	            	
	            	// Equipments routes
	            	.requestMatchers(HttpMethod.POST, "/equipments").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.GET, "/equipments", "/equipments/{id}").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.PUT, "/equipments").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.DELETE, "/equipments/{id}").hasRole("ADMIN")
	            	
	            	// Reports routes
	            	.requestMatchers(HttpMethod.POST, "/reports").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.GET, "/reports", "/reports/{id}").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.PUT, "/reports").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.DELETE, "/reports/{id}").hasRole("ADMIN")
	            	
	            	// Reservations routes
	            	.requestMatchers(HttpMethod.POST, "/reservations").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.GET, "/reservations", "/reservations/{id}").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.PUT, "/reservations").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.DELETE, "/reservations/{id}").hasRole("CLIENT")
	            	
	            	// Rooms routes
	            	.requestMatchers(HttpMethod.POST, "/rooms").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.GET, "/rooms", "/rooms/{id}").hasRole("CLIENT")
	            	.requestMatchers(HttpMethod.PUT, "/rooms").hasRole("ADMIN")
	            	.requestMatchers(HttpMethod.DELETE, "/rooms/{id}").hasRole("ADMIN")
            	.requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
                )
            .formLogin((form) -> form
            		.loginPage("/login/")
            		.defaultSuccessUrl("/")
            		.permitAll())
            .logout((logout) -> logout
            		.logoutSuccessUrl("/login?logout")
            		.permitAll());
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
