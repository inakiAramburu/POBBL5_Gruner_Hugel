package gruner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ParametersUserTest {

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {"&&","%%","@","//","(",")","||","==","??","¿¿", "'","!"})
    void testWithParams(String string) {
        User user = userRepository.findByUsername("test");
        user.setSurname(string);
        User savedUser =userRepository.save(user);
        assertEquals(user, savedUser);
    }

}
