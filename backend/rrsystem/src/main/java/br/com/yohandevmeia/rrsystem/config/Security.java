package br.com.yohandevmeia.rrsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import br.com.yohandevmeia.rrsystem.services.JwtService;

@Configuration
@EnableWebSecurity
public class Security {
	
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService, UserDetailsService userDetailsService) throws Exception {
        http.csrf((csrf) -> csrf.disable())
			.cors(cors -> cors.configurationSource(request -> {
                var corsConfig = new CorsConfiguration();
                corsConfig.addAllowedOrigin("http://localhost:3000");
                corsConfig.addAllowedHeader("*");
                corsConfig.addAllowedMethod("*");
                corsConfig.setAllowCredentials(true);
                return corsConfig;
            }))
        	.sessionManagement((session) -> session
        			.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorize) -> authorize 
            		.requestMatchers("/logout").permitAll()
            		.requestMatchers(HttpMethod.POST, "/login").permitAll()
            		// Clients routes
            	    .requestMatchers(HttpMethod.POST, "/clients").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/clients/{id}", "/clients/email/**").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.PUT, "/clients/**").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.DELETE, "/clients/{id}").hasRole("CLIENT")

            	    // Administrators routes
            	    .requestMatchers(HttpMethod.POST, "/admins").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.GET, "/admins/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.DELETE, "/admins/{id}").hasRole("ADMIN")

            	    // Equipments routes
            	    .requestMatchers(HttpMethod.POST, "/equipments").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.GET, "/equipments/**").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.PUT, "/equipments/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.DELETE, "/equipments/{id}").hasRole("ADMIN")

            	    // Reports routes
            	    .requestMatchers(HttpMethod.POST, "/reports").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.GET, "/reports/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.PUT, "/reports/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.DELETE, "/reports/{id}").hasRole("ADMIN")

            	    // Reservations routes
            	    .requestMatchers(HttpMethod.POST, "/reservations").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.GET, "/reservations/**").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.PUT, "/reservations/**").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.DELETE, "/reservations/{id}").hasRole("CLIENT")

            	    // Rooms routes
            	    .requestMatchers(HttpMethod.POST, "/rooms").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.GET, "/rooms/**").hasRole("CLIENT")
            	    .requestMatchers(HttpMethod.PUT, "/rooms/**").hasRole("ADMIN")
            	    .requestMatchers(HttpMethod.DELETE, "/rooms/{id}").hasRole("ADMIN")
                .anyRequest().authenticated())
            .addFilterBefore(new JwtAuthenticationFilter(jwtService, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    

    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    	return configuration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEnconder() {
    	return new BCryptPasswordEncoder();
    }
}