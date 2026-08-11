package org.example.food.config;

import org.example.food.service.impl.AdminUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminUserDetailsService adminUserDetailsService;

    public SecurityConfig(
            AdminUserDetailsService adminUserDetailsService
    ) {
        this.adminUserDetailsService = adminUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder
    ) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(
                adminUserDetailsService
        );

        provider.setPasswordEncoder(
                passwordEncoder
        );

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/index",
                                "/index.html",
                                "/assets/**",
                                "/admin/vendor/**",
                                "/admin/css/**",
                                "/admin/img/**",
                                "/admin/js/**",
                                "/js/**",
                                "/css/**",
                                "/uploads/**",
                                "/login",
                                "/error/**"
                        )
                        .permitAll()

                        // User yaratmaq yalnız SUPER_ADMIN
                        .requestMatchers("/admin/users/**")
                        .hasRole("SUPER_ADMIN")

                        // Digər admin səhifələri
                        .requestMatchers(
                                "/admin",
                                "/admin/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "SUPER_ADMIN"
                        )

                        .anyRequest()
                        .permitAll()
                )
                .exceptionHandling(exception -> exception

                        .accessDeniedPage(
                                "/error/403"
                        )
                )

                .formLogin(form -> form

                        .loginPage("/login")

                        .loginProcessingUrl("/login")

                        .defaultSuccessUrl(
                                "/admin",
                                true
                        )

                        .failureUrl(
                                "/login?error=true"
                        )

                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }
}