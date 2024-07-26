package com.gigbuddy.restapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // add support for jdbc - no more hard coding users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
    // As spring security automatically looks for tables called users and authorities we dont need to specify where to look
        return new JdbcUserDetailsManager(dataSource);
    }

    // restrict access based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("MODERATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("MODERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
        );
//        use http basic authentication
        http.httpBasic(Customizer.withDefaults());

//        disable csrf
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}


//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test1234")
//                .roles("USER")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test1234")
//                .roles("USER", "MODERATOR")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test1234")
//                .roles("USER", "MODERATOR", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }
