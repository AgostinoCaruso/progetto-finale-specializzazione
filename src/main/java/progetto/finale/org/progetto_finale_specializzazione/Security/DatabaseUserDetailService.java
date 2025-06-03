package progetto.finale.org.progetto_finale_specializzazione.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import progetto.finale.org.progetto_finale_specializzazione.Model.User;
import progetto.finale.org.progetto_finale_specializzazione.Repository.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUsername(username);

        if(userAttempt.isEmpty()){
            throw new UsernameNotFoundException("There are no username " + username);
        }

        return new DatabaseUserDetails(userAttempt.get());
    }
    
}
