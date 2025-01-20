package br.com.yohandevmeia.rrsystem.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.yohandevmeia.rrsystem.exceptions.JwtBlacklistedException;
import br.com.yohandevmeia.rrsystem.exceptions.JwtInvalidException;
import br.com.yohandevmeia.rrsystem.exceptions.JwtMissingException;
import br.com.yohandevmeia.rrsystem.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	String authHeader = request.getHeader("Authorization");

    	String requestURI = request.getRequestURI();
    	if (requestURI.equals("/login") || requestURI.equals("/register")
    			|| requestURI.equals("/logout")) {
            filterChain.doFilter(request, response);
            return;
        }
    	
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new JwtMissingException("Missing or poorly formatted Authorization header.");
            }

            String token = authHeader.substring(7);

            if (!jwtService.validateToken(token)) {
                throw new JwtInvalidException("Invalid or expired token.");
            }

            String username = jwtService.extractUsername(token);
            List<String> roles = jwtService.extractRoles(token);

            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (JwtBlacklistedException | JwtInvalidException | JwtMissingException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            Map<String, Object> body = new HashMap<>();
           
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("error", "Unauthorized");
            body.put("message", e.getMessage());
            body.put("path", request.getServletPath());
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            body.put("timestamp", LocalDateTime.now().format(formatter));

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(response.getOutputStream(), body);
        }
    } 
}
