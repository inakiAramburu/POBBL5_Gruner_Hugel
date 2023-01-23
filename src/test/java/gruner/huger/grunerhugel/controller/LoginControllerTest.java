package gruner.huger.grunerhugel.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import gruner.huger.grunerhugel.config.URI;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;
import java.util.Collection;
import java.io.Serializable;
import java.security.Principal;


import org.springframework.security.authentication.AuthenticationManager;


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
    public void testRegister() {
        
    }

    @Test
    public void testLoginError() {
        String result = loginController.loginError(model);
        verify(model).addAttribute("loginError", true);
        assertEquals(URI.LOGIN.getView(), result);
    }
}
