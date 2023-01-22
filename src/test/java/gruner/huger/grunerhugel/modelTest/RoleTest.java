package gruner.huger.grunerhugel.modelTest;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Role;

public class RoleTest {
    @Test 
    public void testConstructor(){
        Role role = new Role(5);
        assertEquals(role.getId(), 5);

        Role roleAdmin = new Role("ADMIN");
        assertEquals(roleAdmin.getId(), 2);
        Role roleUser = new Role("USER");
        assertEquals(roleUser.getId(), 1);


    }
    @Test
    public void testGetSet(){
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        assertEquals(role.getId(), 5);
        assertEquals(role.getName(), "name");
    }
    @Test
    public void testHashCode(){
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        assertEquals(3374823, role.hashCode());
    }
    @Test
    public void testEquals(){
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        Role role2 = new Role();
        role2.setId(5);
        role2.setName("name");
        assertEquals(true, role.equals(role2));
    }
    @Test
    public void testToString(){
        Role role = new Role();
        role.setId(5);
        role.setName("name");
        System.out.println(role.toString());
        assertEquals("Role [id =5 name=name]", role.toString());
    }


}
