package gruner.huger.grunerhugel.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomSuccesHandler succesHandler) throws Exception {
        
        http.authorizeHttpRequests()
            .requestMatchers("/css/**", "/img/**","/js/**").permitAll()
            .requestMatchers("/register", "/create", "/login").permitAll()
            .requestMatchers("/investor").hasAuthority("INVESTOR")
            .requestMatchers("/main", "/simulation").hasAuthority("USER")
            .anyRequest().hasAuthority("ADMIN")
            .and().csrf().disable()
            .cors().disable();
            
        http.csrf().disable().cors().disable()
            .formLogin().loginPage("/login").successHandler(succesHandler).failureUrl("/login-error").permitAll()
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").deleteCookies("remember-me").permitAll()
            .and().rememberMe();

        return http.build();
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
