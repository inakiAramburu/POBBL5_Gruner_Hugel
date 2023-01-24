package gruner.huger.grunerhugel.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.ui.Model;
import gruner.huger.grunerhugel.config.URI;
import gruner.huger.grunerhugel.domain.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    @Mock
private UserRepository userRepository;

@Mock
private SecurityContext securityContext;

@Mock
private Authentication authentication;

@Mock
private Model model;

@InjectMocks
private LoginController loginController;

    @Test
    public void testLoginError() {
        String result = loginController.loginError(model);
        verify(model).addAttribute("loginError", true);
        assertEquals(URI.LOGIN.getView(), result);
    }
}
