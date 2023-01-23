package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Role;

@SpringBootTest
class RoleTest {
    @Test
    void testConstructor() {
        Role role = new Role(5);
        assertEquals(5, role.getId());

        Role roleAdmin = new Role("ADMIN");
        assertEquals(2, roleAdmin.getId());
        Role roleUser = new Role("USER");
        assertEquals(1, roleUser.getId());

    }

    @Test
    void testGetSet() {
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        assertEquals(5, role.getId());
        assertEquals("name", role.getName());
    }

    @Test
    void testHashCode() {
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        assertEquals(3374823, role.hashCode());
    }

    @Test
    void testEquals() {
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        Role role2 = new Role();
        role2.setId(5);
        role2.setName("name");
        assertEquals(true, role.equals(role2));
    }

    @Test
    void testToString() {
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        System.out.println(role.toString());
        assertEquals("Role [id =5 name=name]", role.toString());
    }

}
