package gruner.huger.grunerhugel.modelTest;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Simulation;

public class SimulationTest {
    @Test
    public void constructorTest(){
        Date startDate =new Date();
        Date endDate=new Date();
        Farm farm =new Farm();
        Simulation simulation =new Simulation(startDate,endDate,farm);
        assertEquals(simulation.getStartDate(), startDate);
        assertEquals(simulation.getEndDate(), endDate);
        assertEquals(simulation.getFarm(), farm);
    }

    @Test
    public void testGetSet(){
        Simulation simulation =new Simulation();

        Date startDate =new Date();
        Date endDate=new Date();
        Farm farm =new Farm();
        simulation.setId(0);
        simulation.setStartDate(startDate);
        simulation.setEndDate(endDate);
        simulation.setFarm(farm);
        assertEquals(simulation.getId(), 0);
        assertEquals(simulation.getStartDate(), startDate);
        assertEquals(simulation.getEndDate(), endDate);
        assertEquals(simulation.getFarm(), farm);
    }
    @Test
    public void testToString(){
        Simulation simulation =new Simulation();

        Date startDate =new Date();
        Date endDate=new Date();
        Farm farm =new Farm();
        simulation.setId(0);
        simulation.setStartDate(startDate);
        simulation.setEndDate(endDate);
        simulation.setFarm(farm);
        assertEquals(simulation.toString(), "Simulation [id=0, startDate="+startDate+", endDate="+endDate+", farm="+farm+"]");
    }
}
