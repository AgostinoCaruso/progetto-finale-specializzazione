package progetto.finale.org.progetto_finale_specializzazione.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final DatabaseUserDetailService databaseUserDetailService;

    SecurityConfiguration(DatabaseUserDetailService databaseUserDetailService) {
        this.databaseUserDetailService = databaseUserDetailService;
    }
    
@Bean
@SuppressWarnings("removal")
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/games/create", "/games/edit/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/games/**").hasAuthority("ADMIN")
            .requestMatchers("/genre", "/genre/**").hasAuthority("ADMIN")
            .requestMatchers("/games", "/games/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/**").permitAll()
        )
        .formLogin(form -> form
            .defaultSuccessUrl("/games", true)
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login") // opzionale: dove andare dopo logout
        )
        .exceptionHandling();

    return http.build();
}


    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
