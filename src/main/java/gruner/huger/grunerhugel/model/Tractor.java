package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tractor")
public class Tractor implements Serializable {

    @Id
    @Column(name = "tractorName")
    private String tractorName;
    @Column(name = "Price")
    private int price;
    @Column(name = "Maintenance (€/day)")
    private int maintenance;
    @Column(name = "Power")
    private int power;
    @Column(name = "Maximum speed (km/h)")
    private int maxSpeed;
    @Column(name = "Fuel capacity (L)")
    private int fuelCapacity;

    @OneToMany(mappedBy = "tractor")
    private List<FarmTractor> farms;

    public Tractor() {
        // no need
    }

    public String getTractorName() {
        return tractorName;
    }

    public void setTractorName(String tractorName) {
        this.tractorName = tractorName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public List<FarmTractor> getFarms() {
        return farms;
    }

    public void setFarms(List<FarmTractor> farms) {
        this.farms = farms;
    }

    @Override
    public String toString() {
        return "Tractor [name=" + tractorName + ", price=" + price + ", maintenance=" + maintenance + ", power=" + power
                + ", maxSpeed=" + maxSpeed + ", fuelCapacity=" + fuelCapacity + "]";
    }
}
