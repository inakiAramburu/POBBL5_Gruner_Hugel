package gruner.huger.grunerhugel.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.model.Farm;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FarmRepositoryTest {

    @Autowired
    FarmRepository farmRepository;

    @Test
    public void testFindByUser() {
        Farm farm = farmRepository.findById(31).get();

        Farm result = farmRepository.findByUser(farm.getUser());

        assertNotNull(result);
    }
    
}