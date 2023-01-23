package gruner.huger.grunerhugel.modelTest.formobjects;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.formobjects.EditSimulation;

@SpringBootTest
public class EditSimulationTest {

    // Creamos una variable de la clase EditSimulation
    EditSimulation editSimulation;
    // Creamos una variable de tipo fecha
    Date date;

    // Este método se ejecuta antes de cada prueba
    // En este caso creamos un objeto EditSimulation y una fecha

    // Prueba para el método setTractorName
    @Test
    public void testSetTractorName() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setTractorName("TractorMaster");
        assertEquals("TractorMaster", editSimulation.getTractorName());
    }

    // Prueba para el método setNumTractor
    @Test
    public void testSetNumTractor() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setNumTractor(5);
        assertEquals(5, editSimulation.getNumTractor());
    }

    // Prueba para el método setNumWorkers
    @Test
    public void testSetNumWorkers() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setNumWorkers(10);
        assertEquals(10, editSimulation.getNumWorkers());
    }

    // Prueba para el método setPlowName
    @Test
    public void testSetPlowName() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setPlowName("PlowMaster");
        assertEquals("PlowMaster", editSimulation.getPlowName());
    }

    // Prueba para el método setNumPlow
    @Test
    public void testSetNumPlow() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setNumPlow(2);
        assertEquals(2, editSimulation.getNumPlow());
    }

    // Prueba para el método setSeederName
    @Test
    public void testSetSeederName() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setSeederName("SeederMaster");
        assertEquals("SeederMaster", editSimulation.getSeederName());
    }

    // Prueba para el método setNumSeeder
    @Test
    public void testSetNumSeeder() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setNumSeeder(3);
        assertEquals(3, editSimulation.getNumSeeder());
    }

    // Prueba para el método setHarvesterName
    @Test
    public void testSetHarvesterName() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setHarvesterName("HarvesterMaster");
        assertEquals("HarvesterMaster", editSimulation.getHarvesterName());
    }

    // Prueba para el método setNumHarvester
    @Test
    public void testSetNumHarvester() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setNumHarvester(1);
        assertEquals(1, editSimulation.getNumHarvester());
    }

    // Prueba para el método setEndDate
    @Test
    public void testSetEndDate() {
        EditSimulation editSimulation = new EditSimulation();
        date = new Date();
        editSimulation.setEndDate(date);
        assertEquals(date, editSimulation.getEndDate());
    }
}