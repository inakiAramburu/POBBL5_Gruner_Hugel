package gruner.huger.grunerhugel.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity{

    @Autowired
    private CustomSuccesHandler customSuccesHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.authorizeHttpRequests()
            .requestMatchers("/css/**", "/img/**","/js/**").permitAll()
            .requestMatchers("/register", "/create", "/login", "/", "/error").permitAll()
            .anyRequest().authenticated();
            
        http.csrf().disable()
            .formLogin().loginPage("/login").successHandler(customSuccesHandler).failureUrl("/login-error").permitAll()
            .and().logout().logoutSuccessUrl("/").permitAll();

        return http.build();
    }

    @Bean 
    public WebSecurityCustomizer webSecurityCustomizer(){ //HAY QUE CAMBIARLO EN UN FUTURO
        return web -> web.ignoring().requestMatchers("/resources/**", "/static/**");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
