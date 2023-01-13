package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

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
    List<Land> lands;
    List<Plant> plants;
    private boolean check = false;
    private Lock mutex;
    private Condition checking;
    private PropertyChangeSupport pcs;
    private LandRepository lRepository;
    private PlantRepository pRepository;
    private OptimalConditionsRepository oRepository;

    public PlantThread(LandRepository landRepository, PlantRepository plantRepository,
            OptimalConditionsRepository opCondRepository, List<Land> lands) {
        this.lands = lands;
        this.plants = new ArrayList<>();
        this.fields = new HashMap<>();
        this.pcs = new PropertyChangeSupport(this);
        this.mutex = new ReentrantLock();
        this.checking = this.mutex.newCondition();
        this.lRepository = landRepository;
        this.pRepository = plantRepository;
        this.oRepository = opCondRepository;
    }

    public void addPlant(Land land, PlantType pType) {
        OptimalConditions oConditions = oRepository.findByName(pType.getPlantType());
        Plant plant = new Plant(land, oConditions);
        List<Plant> pList = fields.get(land);
        if(pList == null) pList = new ArrayList<>();
        pList.add(plant);
        fields.put(land, pList);
    }

    @Override
    public void run() {
        mutex.lock();
        try {
            check = false;
            while (!Thread.interrupted()) {
                awaitCheck();
                if (check) {
                    updatePlants();
                    check = false;
                }
            }
        } finally {
            mutex.unlock();
        }
    }

    private void updatePlants() {
        updateLands();
        // take plants from each land
        // plant has function to check conditions (Weather)
        lands.forEach(land -> fields.get(land)
                .forEach(plant -> plant.checkOptimalCondition(forecast.get(land.getTown().getName()))));
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
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        String property = arg0.getPropertyName();
        switch (property) {
            case WeatherThread.WEATHER_CHECK:
                mutex.lock();
                try {
                    check = true;
                    if (check) {
                        forecast = (Map<String, Weather>) arg0.getNewValue(); // can't delete sonarlint warning
                        checking.signal();
                        GrunerhugelApplication.logger.info("plant signal");
                    }
                } finally {
                    mutex.unlock();
                }
                break;
            case TimeThread.TIME_PAUSE:
                //updateland
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
}
