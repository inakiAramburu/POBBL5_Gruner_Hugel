package gruner.huger.grunerhugel.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurity{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private DataSource dataSource;

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userService;

    public WebSecurity(PasswordEncoder passwordEncoder, UserDetailsService userService){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.authorizeHttpRequests().requestMatchers("/create").permitAll()
            .requestMatchers("/register").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").successForwardUrl("/main").failureForwardUrl("/error").permitAll()
            .and().logout().logoutSuccessUrl("/login").permitAll();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);

        return auth;
    }

    
}
