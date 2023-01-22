package gruner.huger.grunerhugel.domain;



import gruner.huger.grunerhugel.domain.user.UserDetailServiceImpl;
import gruner.huger.grunerhugel.domain.user.UserDetailsImpl;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserDetailsImplTest {
    
    @Test
    public void coverage(){
    User user =new User();
    Role role =new Role("ADMIN");
    user.setRole(role);
    UserDetailsImpl userDetailsImpl =new UserDetailsImpl(user);
    Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
    
    
    userDetailsImpl.getAuthorities();
    userDetailsImpl.getPassword();
    userDetailsImpl.getUsername();
    userDetailsImpl.isAccountNonExpired();
    userDetailsImpl.isAccountNonLocked();
    userDetailsImpl.isCredentialsNonExpired();
    userDetailsImpl.isEnabled();
    
    
    

    }
}
