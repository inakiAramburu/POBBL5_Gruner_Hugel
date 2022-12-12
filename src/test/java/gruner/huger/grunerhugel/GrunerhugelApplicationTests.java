package gruner.huger.grunerhugel;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.simulation.Simulation;

@SpringBootTest
class GrunerhugelApplicationTests {
	Simulation sim;

	//@Before
	void contextLoads() {
		
		System.out.println("Test");

	}

	@Test
	@Timeout(31000)
	void test() throws InterruptedException {
		sim = new Simulation();
		sim.run();
	}

	@Test
	@Timeout(16000)
	void test2() throws InterruptedException {
		sim = new Simulation();
		sim.setAccelerator(2);
		sim.run();
	}

}
