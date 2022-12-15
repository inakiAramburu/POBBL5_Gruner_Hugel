package gruner.huger.grunerhugel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tractor")
public class Tractor {
    
    @Id
    @Column(name = "name")
    private String name;
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

    public Tractor() {
        //no need
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Tractor [name=" + name + ", price=" + price + ", maintenance=" + maintenance + ", power=" + power
                + ", maxSpeed=" + maxSpeed + ", fuelCapacity=" + fuelCapacity + "]";
    }

}
