package gruner.huger.grunerhugel.simulation.thread;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.model.Fuel;

@SpringBootTest
public class FuelThreadTest {
    /*
    @Mock
private FuelRepository fuelRepository;
@Autowired
    FarmRepository farmRepository;
private FuelThread fuelThread;

    @Mock
    private TimeThread timeThread;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fuelThread = new FuelThread(fuelRepository);

        
    }
  /*  @Test
    public void testUpdateFuel() {
        // Arrange
        Year year = Year.of(2022);
        int week = 5;
        Fuel fuel = new Fuel();
        fuel.setPrice(5.0);
        when(fuelRepository.findByYearAndPeriod(year, week)).thenReturn(Optional.of(fuel));

        // Act
        fuelThread.updateFuel();

        // Assert
        assertEquals(5.0, FuelThread.buyFuel(1), 0.0);
    }
   
  @Test
  public void testFuelThread2() {
     
        timeThread = mock(TimeThread.class);
        Date date2 = new Date();
        LocalDate date = LocalDate.of(2000, 1, 2);
        
        date2=Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
       
        when(timeThread.getActualDate()).thenReturn(date2);
    // Arrange
      FuelRepository fuelRepository = mock(FuelRepository.class);
      FuelThread fuelThread = new FuelThread(fuelRepository);
      //Fuel fuel = new Fuel(Year.of(2020), 1, 10);
      Fuel fuel = new Fuel();
      Optional<Fuel> opFuel = Optional.of(fuel);
      // Act
      when(fuelRepository.findByYearAndPeriod(Year.of(2020), 1)).thenReturn(opFuel);
      timeThread.start();
      fuelThread.start();
      FuelThread.callSignal();
      fuelThread.updateFuel();
      // Assert
      assertTrue(fuelThread.isAlive());
      assertEquals(fuel, FuelThread.getFuel());
      assertEquals(100, FuelThread.buyFuel(10), 0);
      FuelThread.pause();
  }




   @Test
    public void testCallSignal0() {
        // Arrange
        fuelThread.start();

        // Act
        FuelThread.callSignal();

        // Assert
        assertEquals(true, fuelThread.isAlive());
    }
    @Test
    public void testFuelThread() {
    // Arrange
    FuelRepository fuelRepository = mock(FuelRepository.class);
    FuelThread fuelThread = new FuelThread(fuelRepository);
    //Fuel fuel = new Fuel(Year.of(2020), 1, 10);
    Fuel fuel = new Fuel();
    Optional<Fuel> opFuel = Optional.of(fuel);
    // Act
    when(fuelRepository.findByYearAndPeriod(Year.of(2020), 1)).thenReturn(opFuel);
    fuelThread.start();
    FuelThread.callSignal();
    fuelThread.updateFuel();
    // Assert
    assertTrue(fuelThread.isAlive());
    assertEquals(fuel, FuelThread.getFuel());
    assertEquals(100, FuelThread.buyFuel(10), 0);
    FuelThread.pause();
    }

    @Test
    public void testCallSignal() {
    // Arrange
    FuelRepository fuelRepository = mock(FuelRepository.class);
    FuelThread fuelThread = new FuelThread(fuelRepository);
    Fuel fuel = new Fuel();
    Optional<Fuel> opFuel = Optional.of(fuel);

    TimeThread timeThread = new TimeThread(null, null, null)
    when(fuelRepository.findByYearAndPeriod(Year.of(2020), 1)).thenReturn(opFuel);
    // Act
    FuelThread.callSignal();
    fuelThread.run();

    // Assert
    assertEquals(fuel, FuelThread.fuel);
    assertEquals(100, FuelThread.buyFuel(10), 0);
    }

    @Test
    public void testPause() {
    // Arrange
    FuelRepository fuelRepository = mock(FuelRepository.class);
    FuelThread fuelThread = new FuelThread(fuelRepository);
    // Act
    FuelThread.pause();
    fuelThread.run();

    // Assert
    assertFalse(fuelThread.isAlive());
    }

    @Test
    public void testBuyFuel() {
    // Arrange
    Fuel fuel = new Fuel();
    FuelThread.fuel = fuel;
    fuel.setPrice(10);
    // Act
    double price = FuelThread.buyFuel(10);
    // Assert
    assertEquals(100, price, 0);
    }
    */
}
