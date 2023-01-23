package gruner.huger.grunerhugel.domain;

import gruner.huger.grunerhugel.domain.user.UserDetailsImpl;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDetailsImplTest {

    @Test
    public void coverage() {
        User user = new User();
        Role role = new Role("ADMIN");
        user.setRole(role);
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        userDetailsImpl.getAuthorities();
        userDetailsImpl.getPassword();
        userDetailsImpl.getUsername();
        userDetailsImpl.isAccountNonExpired();
        userDetailsImpl.isAccountNonLocked();
        userDetailsImpl.isCredentialsNonExpired();
        userDetailsImpl.isEnabled();

        assertNotNull(userDetailsImpl);

    }
}
