package gruner.huger.grunerhugel.config;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.config.URI;

public class URITest {
    
    @Test
    public void testGetPath() {
        assertEquals("/login", URI.LOGIN.getPath());
        assertEquals("/register", URI.REGISTER.getPath());
        assertEquals("/main", URI.HOME_USER_NO_FARM.getPath());
        assertEquals("/simulation", URI.HOME_USER_FARM.getPath());
        assertEquals("/admin", URI.HOME_ADMIN.getPath());
        assertEquals("/investor", URI.HOME_INVESTOR.getPath());
        assertEquals("/tutorial", URI.HOME_TUTORIAL.getPath());
    }

    @Test
    public void testGetView() {
        assertEquals("login", URI.LOGIN.getView());
        assertEquals("user/user-form", URI.REGISTER.getView());
        assertEquals("simulation/simulation-form", URI.HOME_USER_NO_FARM.getView());
        assertEquals("simulation/simulation", URI.HOME_USER_FARM.getView());
        assertEquals("user/user-list", URI.HOME_ADMIN.getView());
        assertEquals("investor", URI.HOME_INVESTOR.getView());
        assertEquals("tutorial/tutorial", URI.HOME_TUTORIAL.getView());
    }
}