package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Town;

public class LandTest {

    @Test
    public void costructorTest() {
        Farm farm = new Farm();
        Town town = new Town();
        Land land = new Land(10.00, farm, town, "100", "100");
        assertEquals(land.getFarm(), farm);
        assertEquals(land.getTown(), town);
        assertEquals(land.getSize(), 10.00);
        assertEquals(100, land.getLatitude());
        assertEquals(100, land.getLongitude());

    }

    @Test
    public void testGetSet() {
        Farm farm = new Farm();
        Town town = new Town();
        Land land = new Land();
        land.setFarm(farm);
        land.setTown(town);
        land.setSize(10);
        land.setLatitude(100);
        land.setLongitude(100);
        land.setId(5);
        land.setStatus("null");
        List<Plant> plants = new ArrayList<>();
        land.setPlants(plants);
        assertEquals(land.getPlants(), plants);
        assertEquals(land.getStatus(), "null");
        assertEquals(land.getId(), 5);
        assertEquals(land.getFarm(), farm);
        assertEquals(land.getTown(), town);
        assertEquals(land.getSize(), 10);
        assertEquals(100, land.getLatitude());
        assertEquals(100, land.getLongitude());

    }

    @Test
    public void testToString() {
        Farm farm = new Farm();
        Town town = new Town();
        Land land = new Land(10.00, farm, town, "100", "100");
        System.out.println(land.toString());
        assertEquals(land.toString(),
                "Land [id=0, size=10.0, status=Empty, farm=Farm [id=0, money=0.0, user=null], town=Town [id=0, name=null, latitude=0.0, longitude=0.0]]");
    }

    @Test
    public void testEquals() {
        Farm farm = new Farm();
        Town town = new Town();
        Land land = new Land(10.00, farm, town, "100", "100");
        Land land2 = new Land(10.00, farm, town, "100", "100");
        assertEquals(land, land2);
    }

    @Test
    public void testHashCode() {
        Farm farm = new Farm();
        Town town = new Town();
        Land land = new Land(10.00, farm, town, "100", "100");
        Land land2 = new Land(10.00, farm, town, "100", "100");
        assertEquals(land.hashCode(), land2.hashCode());
    }

}
