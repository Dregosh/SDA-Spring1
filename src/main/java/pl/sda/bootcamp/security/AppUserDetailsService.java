package pl.sda.bootcamp.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.User;
import pl.sda.bootcamp.repository.UserRepository;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(s);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Not found: " + s);
        }
        return new AppUserDetails(user);
    }
}
