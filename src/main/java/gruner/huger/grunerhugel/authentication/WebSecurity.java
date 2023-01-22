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

import gruner.huger.grunerhugel.config.URI;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomSuccesHandler succesHandler,
            MyAccessDeniedHandler accessDeniedHandler) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers("/css/*", "/img/*", "/js/*").permitAll()
                .requestMatchers("/create", URI.LOGIN.getPath(), "/accessDenied", "/error", URI.HOME_TUTORIAL.getPath())
                .permitAll()
                .requestMatchers("/investor").hasAnyAuthority("INVESTOR", "ADMIN")
                .requestMatchers("/main", "/simulation").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated() // aiqu cambiar esto por .hasAuthority("ADMIN");
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.formLogin().loginPage(URI.LOGIN.getPath()).successHandler(succesHandler).failureUrl("/login-error")
                .permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl(URI.LOGIN.getPath())
                .deleteCookies("remember-me").permitAll()
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
