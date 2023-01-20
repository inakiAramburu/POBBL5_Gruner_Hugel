package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;


class UserTest {
    @Test
	void testUsers() {
        Role role = new Role("ROLE_USER");
        User user = new User("nametest","surnametest","sad@gmail.com","user","pass",role);
        
        assertEquals("nametest", user.getName());
        assertEquals("surnametest", user.getSurname());
        assertEquals("sad@gmail.com",user.getEmail());
        assertEquals("user",user.getUsername());
        assertEquals("pass",user.getPassword());
        assertEquals(role,user.getRole());
	}
    @Test
	void testGetSet() {

        User user = new User();
        Role role = new Role("ROLE_USER");
        user.setName("nametest");
        user.setSurname("surnametest");
        user.setEmail("sad@gmail.com");
        user.setUsername("user");
        user.setPassword("pass");
        user.setRole(role);

        assertEquals("nametest", user.getName());
        assertEquals("surnametest", user.getSurname());
        assertEquals("sad@gmail.com",user.getEmail());
        assertEquals("user",user.getUsername());
        assertEquals("pass",user.getPassword());
        assertEquals(role,user.getRole());
	}


}
