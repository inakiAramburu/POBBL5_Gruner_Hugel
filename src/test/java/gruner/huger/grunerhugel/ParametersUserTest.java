package gruner.huger.grunerhugel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.User;

@SpringBootTest
public class ParametersUserTest {

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {"&&","%%","@","//","(",")","||","==","??","¿¿", "'","!"})
    void testWithParams(String string) {
        User user = userRepository.findByUsername("test");
        user.setSurname(string);
        User savedUser =userRepository.save(user);
        System.out.println(savedUser);
        assertEquals(user, savedUser); //arreglame esto qu da error
    }

}
