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
import br.com.yohandevmeia.rrsystem.services.BlackListService;
import br.com.yohandevmeia.rrsystem.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final BlackListService blackListService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, BlackListService blackListService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.blackListService = blackListService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);

                if (blackListService.isTokenBlacklisted(token)) {
                    throw new JwtBlacklistedException("The token is blacklisted. Therefore, it was invalidated.");
                }

                if (jwtService.validateToken(token)) {
                    String username = jwtService.extractUsername(token);
                    List<String> roles = jwtService.extractRoles(token);

                    List<GrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new JwtInvalidException("Invalid token.");
                }
            } else {
                throw new JwtMissingException("Missing or poorly formatted Authorization header.");
            }

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
