package gruner.huger.grunerhugel.authentication;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import gruner.huger.grunerhugel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomSuccesHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String targetUrl = determineTargetUrl(authentication);

        if(response.isCommitted()) {
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication){
        String url = "/login?error=true";

        System.out.println(authentication.getAuthorities());

        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){
            url = "/admin";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))){
            url = "/main";
        }

        return url;
    }
    
    
}
