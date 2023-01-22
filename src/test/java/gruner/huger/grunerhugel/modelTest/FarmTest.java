package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;


import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;


class FarmTest {

	@Test
	void testConstructor() {
		User user = new User();
		Farm farm = new Farm(user,5,10);
	}
    @Test
	void testGetSet() {
		Farm farm = new Farm();
		farm.setMoney(1000);
		assertEquals(1000, farm.getMoney());
		
		//NumWorkers
		farm.setNumWorkers(20);
		assertEquals(20, farm.getNumWorkers());
		
		//Users
		User user = new User();
        Role role = new Role("ROLE_USER");
        user.setName("nametest");
        user.setSurname("surnametest");
        user.setEmail("sad@gmail.com");
        user.setUsername("user");
        user.setPassword("pass");
        user.setRole(role);
		
		farm.setUser(user);
		assertEquals(user, farm.getUser());
		
		//Fuel
		farm.setFuel(10);
		assertEquals(10, farm.getFuel());
		
		//ID
		farm.setId(15);
		assertEquals(15, farm.getId());

		//Harvesters
		List<FarmHarvester> harvesters=new ArrayList<>();
		farm.setHarvesters(harvesters);
		assertEquals(harvesters, farm.getHarvesters());
		
		
		//Lands
		Land land = new Land();
		List<Land> lands=new ArrayList<>();
		lands.add(land);
		farm.setLands(lands);
		assertEquals(lands, farm.getLands());


		//FarmPlow
		List<FarmPlow> plows = new ArrayList<>();
		farm.setPlows(plows);
		assertEquals(plows, farm.getPlows());
		
		//FarmSeeder
		List<FarmSeeder> seeders=new ArrayList<>();
		farm.setSeeders(seeders);
		assertEquals(seeders, farm.getSeeders());
		
		//FarmTractor
		List<FarmTractor> tractors = new ArrayList<>();
		farm.setTractors(tractors);
		assertEquals(tractors, farm.getTractors());
		
		System.out.println(farm.toString());
		//toString
			}

	

}
