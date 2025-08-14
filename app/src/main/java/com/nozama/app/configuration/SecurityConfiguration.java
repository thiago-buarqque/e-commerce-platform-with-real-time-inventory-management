package com.nozama.app.configuration;

import com.nozama.app.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/api/products/**").authenticated()//
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
  }

  @Bean
  public static BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    BCryptPasswordEncoder encoder = bCryptPasswordEncoder();

    UserDetails user = User.builder()
            .username("user")
            .password(encoder.encode("user"))
            .roles(UserRole.USER.name())
            .build();

    UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("admin"))
            .roles(UserRole.ADMIN.name(), UserRole.USER.name())
            .build();

    return new InMemoryUserDetailsManager(user, admin);
  }
}

