package br.com.biblioteca.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/cadastro", "/salvar").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/home/alugar/**", "/home/meusAlugados").hasRole("ALUNO")
                .requestMatchers("/", "/editar/**", "/excluir/**", "/devolverLivro/**", "/users").hasRole("ADMIN")
                .requestMatchers("/home/cadastroLivro", "/home/salvarLivro", "/home/editarLivro/**", "/home/excluir/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true).
                failureUrl("/login?erro")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll())
                .exceptionHandling(acessobloqueado -> acessobloqueado
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/home");
                })
        );

        return http.build();
    }

}
