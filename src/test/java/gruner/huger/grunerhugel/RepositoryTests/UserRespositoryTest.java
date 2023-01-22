package gruner.huger.grunerhugel.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import gruner.huger.grunerhugel.domain.repository.RoleRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.User;
import gruner.huger.grunerhugel.model.Role;

@SpringBootTest
class UserRespositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void createUserTest() {
        User user = new User();
        user.setUsername("test0");
        user.setName("test");
        user.setSurname("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setRole(roleRepository.findById(1));
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
    }

    @Test
    @Rollback(false)
    void testFindRoles() {
        Role role = roleRepository.findById(1);
        assertEquals("USER", role.getName());

        role = roleRepository.findById(2);
        assertEquals("ADMIN", role.getName());

        role = roleRepository.findById(3);
        assertEquals("INVESTOR", role.getName());
    }

    @Test
    @Rollback(false)
    void testFindUser() {
        User user = userRepository.findByUsername("agarzo");
        assertNotNull(user);
        assertEquals("agarzo", user.getUsername());
        assertEquals("aritz", user.getName());
        assertEquals("garzo", user.getSurname());
        assertEquals("$2a$10$yM03BcAR0A5R.8UtwcITm./ODBQn1BpMiL.4n5HRxcvtKELV46xZ6", user.getPassword());
        assertEquals("agarzo@gmail.com", user.getEmail());
        assertEquals("USER", user.getRole().getName());
    }

    @Test
    @Rollback(false)
    void testUpdateUser() {
        User user = userRepository.findByUsername("test");
        user.setSurname("test0");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals("test0", savedUser.getSurname());
    }

    @Test
    @Rollback(false)
    void testFindAllUsers() {
        Iterable<User> users = userRepository.findAll();
        assertNotNull(users);
        assertThat(users).size().isPositive();
    }

    @Test
    @Rollback(false)
    void testFindAllRoles() {
        Iterable<Role> roles = roleRepository.findAll();
        assertNotNull(roles);
        assertThat(roles).size().isPositive();
    }

    @Test
    void deleteUserTest() {
        User user = userRepository.findByUsername("test0");
        userRepository.delete(user);
        assertNull(userRepository.findByUsername("test0"));
    }
}
