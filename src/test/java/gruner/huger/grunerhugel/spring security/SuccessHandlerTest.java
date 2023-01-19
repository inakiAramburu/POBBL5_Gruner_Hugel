import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import gruner.huger.grunerhugel.authentication.CustomSuccesHandler;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
class SuccessHandlerTest {
    HttpServletRequest request ;
    HttpServletResponse response;
    Authentication authentication;
    @Autowired
    UserRepository userRepository;
    FarmRepository farmRepository;
    


@Test
public void determineTargetURLTest() {
    Authentication authAdmin = mock(Authentication.class);
    Authentication authUser = mock(Authentication.class);
    Authentication authInvestor = mock(Authentication.class);
    
    //Admin
    when(authAdmin.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))).thenReturn(true);
    String expectedAdminURL = "/admin";

    CustomSuccesHandler sa=new CustomSuccesHandler();
    String actualAdminURL = sa.determineTargetUrl(authAdmin);
    assertEquals(expectedAdminURL, actualAdminURL);

      /*  //User 
        when(authUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))).thenReturn(true);
        when(userRepository.findByUsername(authUser.getName())).thenReturn(user);
        when(farmRepository.findByUser(user)).thenReturn(farm);
        String expectedUserURL = "/simulation";
        String actualUserURL = determineTargetUrl(authUser);
        assertEquals(expectedUserURL, actualUserURL);
        */
}
    
}
