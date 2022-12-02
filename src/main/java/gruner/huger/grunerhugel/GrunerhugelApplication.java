package gruner.huger.grunerhugel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gruner.huger.grunerhugel.simulation.Simulation;

@SpringBootApplication
public class GrunerhugelApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrunerhugelApplication.class, args);
		// probe the simulation
		Simulation sim = new Simulation();
		sim.run();
	}

}
