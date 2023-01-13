package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;


public class PlantThread extends Thread implements PropertyChangeListener {
    static final String PROPERTY_NAME = "PLANT_THREAD";
    Map<Land, List<Plant>> fields;
    List<Land> lands;
    private Weather weather;
    private boolean check = false;
    private Lock mutex;
    private Condition checking;
    private PropertyChangeSupport pcs;
    private OptimalConditionsRepository opCondRepository;

    public PlantThread(List<Land> lands, OptimalConditionsRepository oRepository) {
        this.lands = lands;
        this.fields = new HashMap<>();
        this.pcs = new PropertyChangeSupport(this);
        this.mutex = new ReentrantLock();
        this.checking = this.mutex.newCondition();
        this.opCondRepository = oRepository;
    }

    public void addNewPlant(Land land, PlantType plantType) { // needs to be done
        Plant plant = new Plant();
        Optional<OptimalConditions> op = opCondRepository.findById(plantType.getPlantType());
        GrunerhugelApplication.logger.log(Level.SEVERE, "Optimal Condition: {0}'", op.toString());
        if (op.isPresent()) {
            plant.setOptimalConditions(op.get());
            List<Plant> temp = fields.get(land);
            if (temp != null && !temp.isEmpty()) {
                temp.add(plant);
                fields.put(land, temp);
            }
        }
    }

    @Override
    public void run() {
        mutex.lock();
        try {
            check = false;
            while (!Thread.interrupted()) {
                awaitCheck();
                if (check) {
                    lands.forEach(l -> fields.get(l).forEach(f -> f.checkOptimalCondition(weather)));
                    check = false;
                }
            }
        } finally {
            mutex.unlock();
        }
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
        if (WeatherThread.PROPERTY_NAME.equals(arg0.getPropertyName())) {
            check = true;
            if (check) {
                weather = (Weather) arg0.getNewValue();
                mutex.lock();
                try{
                    checking.signal();
                    GrunerhugelApplication.logger.info("plant signal");
                }finally{
                    mutex.unlock();
                }
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
