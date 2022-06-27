package erp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import erp.model.User;
import erp.repository.UserRepository;

@Service
public class CustomUserDetailsImplementation implements UserDetailsService {

    private final CustomUserDetailsImplementation jwtUserDetailsService;
    private final UserRepository userRepository;

    public CustomUserDetailsImplementation(UserRepository userRepository,@Lazy  CustomUserDetailsImplementation jwtUserDetailsService) {
        this.userRepository = userRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username '%s' is not found.", username));
        } else {
            return user;
        }
    }
}
