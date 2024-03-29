package gruner.huger.grunerhugel.authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.config.URI;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FarmRepository farmRepository;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    public String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=true";

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            url = URI.HOME_ADMIN.getPath();
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            try {
                Farm farm = farmRepository.findByUser(user);
                if (farm == null) {
                    throw new NullPointerException();
                }
                url = URI.HOME_USER_FARM.getPath();
            } catch (NullPointerException e) {
                GrunerhugelApplication.logger.info("No simulations found");
                url = URI.HOME_TUTORIAL.getPath();
            }
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("INVESTOR"))) {
            url = URI.HOME_INVESTOR.getPath();
        }

        return url;
    }
}
