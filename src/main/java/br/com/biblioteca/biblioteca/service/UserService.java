package br.com.biblioteca.biblioteca.service;


import br.com.biblioteca.biblioteca.Enums.Cargo;
import br.com.biblioteca.biblioteca.models.User;
import br.com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Credencias invalidas"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getSenha())
                .roles(user.getCargo().toString())
                .build();
    }

    public void register(String email, String password, Cargo cargo){
        User user = new User();
        user.setEmail(email);
        user.setCargo(cargo);
        user.setSenha(passwordEncoder.encode(password));

        userRepository.save(user);
    }


}
