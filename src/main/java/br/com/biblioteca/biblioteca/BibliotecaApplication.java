package br.com.biblioteca.biblioteca;

import br.com.biblioteca.biblioteca.Enums.Cargo;
import br.com.biblioteca.biblioteca.Enums.Status;
import br.com.biblioteca.biblioteca.models.User;
import br.com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class  BibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }
    @Bean
    public CommandLineRunner criarAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                User admin = new User();
                admin.setNome("admin");
                admin.setEmail("admin@admin.com");
                admin.setSenha(passwordEncoder.encode("admin"));
                admin.setCargo(Cargo.ADMIN);
                admin.setStatus(Status.EM_DIA);
                userRepository.save(admin);
            }
        };
    }
}


