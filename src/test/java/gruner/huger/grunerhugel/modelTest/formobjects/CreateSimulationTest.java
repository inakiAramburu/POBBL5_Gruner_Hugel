package gruner.huger.grunerhugel.modelTest.formobjects;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.formobjects.CreateSimulation;

import java.util.Date;

@SpringBootTest
public class CreateSimulationTest {

    // Creamos una variable de la clase CreateSimulation
    CreateSimulation createSimulation;
    // Creamos una variable de tipo fecha
    Date date;

    // Este método se ejecuta antes de cada prueba
    // En este caso creamos un objeto CreateSimulation y una fecha

    // Prueba para el método setStartDate
    @Test
    public void testSetStartDate() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setStartDate(date);
        assertEquals(date, createSimulation.getStartDate());
    }

    // Prueba para el método setEndDate
    @Test
    public void testSetEndDate() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setEndDate(date);
        assertEquals(date, createSimulation.getEndDate());
    }

    // Prueba para el método setBudget
    @Test
    public void testSetBudget() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setBudget(1000);
        assertEquals(1000, createSimulation.getBudget());
    }

    // Prueba para el método setTractorName
    @Test
    public void testSetTractorName() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setTractorName("John Deere");
        assertEquals("John Deere", createSimulation.getTractorName());
    }

    // Prueba para el método setNumTractor
    @Test
    public void testSetNumTractor() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setNumTractor(3);
        assertEquals(3, createSimulation.getNumTractor());
    }

    // Prueba para el método setNumWorkers
    @Test
    public void testSetNumWorkers() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setNumWorkers(5);
        assertEquals(5, createSimulation.getNumWorkers());
    }

    // Prueba para el método setPlowName
    @Test
    public void testSetPlowName() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setPlowName("PlowMaster");
        assertEquals("PlowMaster", createSimulation.getPlowName());
    }

    // Prueba para el método setNumPlow
    @Test
    public void testSetNumPlow() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setNumPlow(2);
        assertEquals(2, createSimulation.getNumPlow());
    }

    // Prueba para el método setSeederName
    @Test
    public void testSetSeederName() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setSeederName("SeedMaster");
        assertEquals("SeedMaster", createSimulation.getSeederName());
    }

    // Prueba para el método setNumSeeder
    @Test
    public void testSetNumSeeder() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setNumSeeder(4);
        assertEquals(4, createSimulation.getNumSeeder());
    }

    // Prueba para el método setHarvesterName
    @Test
    public void testSetHarvesterName() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setHarvesterName("HarvestMaster");
        assertEquals("HarvestMaster", createSimulation.getHarvesterName());
    }

    // Prueba para el método setNumHarvester
    @Test
    public void testSetNumHarvester() {
        CreateSimulation createSimulation = new CreateSimulation();
        date = new Date();
        createSimulation.setNumHarvester(1);
        assertEquals(1, createSimulation.getNumHarvester());
    }
}
