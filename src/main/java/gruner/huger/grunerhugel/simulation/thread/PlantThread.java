package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;

public class PlantThread extends Thread implements PropertyChangeListener {
    static final String PROPERTY_NAME = "PLANT_THREAD";
    Map<Land, List<Plant>> fields;
    Map<String, Weather> forecast;
    private List<Land> lands;
    private static boolean check = false;
    private static Lock mutex;
    private static Condition checking;
    private PropertyChangeSupport pcs;
    private LandRepository lRepository;
    private PlantRepository pRepository;
    private OptimalConditionsRepository oRepository;

    public PlantThread(LandRepository landRepository, PlantRepository plantRepository,
            OptimalConditionsRepository opCondRepository, List<Land> lands) {
        this.lands = lands;
        this.fields = new HashMap<>();
        this.pcs = new PropertyChangeSupport(this);
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
        this.lRepository = landRepository;
        this.pRepository = plantRepository;
        this.oRepository = opCondRepository;
        updateMap();
    }

    // public void addPlant(Land land, PlantType pType) {
    // OptimalConditions oConditions = oRepository.findByName(pType.getPlantType());
    // Plant plant = new Plant(land, oConditions);
    // pRepository.save(plant);
    // }

    private void updateMap() {
        updateLands();
        for (Land land : lands) {
            List<Plant> plants = pRepository.findByLand(land);
            fields.put(land, plants);
        }
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "PlantThread Id: {0}", this.getId());
        mutex.lock();
        try {
            while (!Thread.interrupted()) {
                awaitCheck();
                if (check) {
                    GrunerhugelApplication.logger.info("UPDATE PLANTS");
                    updatePlants();
                    check = false;
                }
            }
        } finally {
            mutex.unlock();
        }
    }

    private void updatePlants() {
        // updateLands();
        // take plants from each land
        // plant has function to check conditions (Weather)
        forecast = WeatherThread.getForecast();
        // lands.forEach(land -> fields.get(land)
        // .forEach(plant ->
        // plant.checkOptimalCondition(forecast.get(land.getTown().getName()))));
        for (Land land : lands) {
            GrunerhugelApplication.logger.info("lands");
            List<Plant> plants = fields.get(land);
            if (plants != null)
                for (Plant plant : plants) {
                    GrunerhugelApplication.logger.info("plants");
                    plant.checkOptimalCondition(forecast.get(land.getTown().getName()));
                }
        }
        // update to database
        lands.forEach(land -> fields.get(land).forEach(plant -> pRepository.save(plant)));
    }

    private void updateLands() {
        lands = (List<Land>) lRepository.findByFarm(SimulationProcesses.farm);
    }

    private void awaitCheck() {
        while (!check) {
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("PlantThread Interrupted!");
                this.interrupt();
            }
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        String property = arg0.getPropertyName();
        switch (property) {
            case TimeThread.TIME_RESUME:
                break;
            case TimeThread.TIME_PAUSE:
                this.pcs.firePropertyChange(TimeThread.TIME_PAUSE, null, arg0.getNewValue());
                try {
                    GrunerhugelApplication.logger.info("Latch PlantThread");
                    TimeThread.cDownLatch.await();
                } catch (InterruptedException e) {
                    GrunerhugelApplication.logger.warning("CountDown Interrupted!");
                    this.interrupt();
                }
                break;
            default:
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public static void callSignal() {
        mutex.lock();
        try {
            check = true;
            checking.signal();
        } finally {
            mutex.unlock();
        }
    }
}
