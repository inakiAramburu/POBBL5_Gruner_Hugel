package gruner.huger.grunerhugel.ControllerTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import gruner.huger.grunerhugel.controller.MapController;

@WebMvcTest(value = MapController.class)
public class MapRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    

}
