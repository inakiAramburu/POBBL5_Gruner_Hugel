package gruner.huger.grunerhugel.RepositoryTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.RoleRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Farm;

@SpringBootTest
class FarmRepositoryTest {

    @Autowired
    FarmRepository farmRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    Farm farm;

    @Test
    void testFindByUser() {
        assertNotNull(farmRepository.findByUser(userRepository.findByUsername("test")));
    }

    /*
     * @Test
     * void testDeleteFarm() throws IllegalArgumentException{
     * farmRepository.deleteById(farm.getId());//aiqu cambiarlo
     * }
     */

}
