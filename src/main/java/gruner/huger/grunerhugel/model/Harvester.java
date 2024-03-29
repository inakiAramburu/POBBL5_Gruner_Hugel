package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "harvester")
public class Harvester implements Serializable {

    @Id
    @Column(name = "harvesterName")
    private String harvesterName;
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
    @Column(name = "Cultivation capacity (L)")
    private int cultivationCapacity;

    @OneToMany(mappedBy = "harvester")
    private List<FarmHarvester> farms;

    public Harvester() {
        // no need
    }

    public String getHarvesterName() {
        return harvesterName;
    }

    public void setHarvesterName(String harvesterName) {
        this.harvesterName = harvesterName;
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

    public int getCultivationCapacity() {
        return cultivationCapacity;
    }

    public void setCultivationCapacity(int cultivationCapacity) {
        this.cultivationCapacity = cultivationCapacity;
    }

    public List<FarmHarvester> getFarms() {
        return farms;
    }

    public void setFarms(List<FarmHarvester> farms) {
        this.farms = farms;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((harvesterName == null) ? 0 : harvesterName.hashCode());
        result = prime * result + price;
        result = prime * result + maintenance;
        result = prime * result + power;
        result = prime * result + maxSpeed;
        result = prime * result + fuelCapacity;
        result = prime * result + cultivationCapacity;
        result = prime * result + ((farms == null) ? 0 : farms.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Harvester other = (Harvester) obj;
        if (harvesterName == null) {
            if (other.harvesterName != null)
                return false;
        } else if (!harvesterName.equals(other.harvesterName))
            return false;
        if (price != other.price)
            return false;
        if (maintenance != other.maintenance)
            return false;
        if (power != other.power)
            return false;
        if (maxSpeed != other.maxSpeed)
            return false;
        if (fuelCapacity != other.fuelCapacity)
            return false;
        if (cultivationCapacity != other.cultivationCapacity)
            return false;
        if (farms == null) {
            if (other.farms != null)
                return false;
        } else if (!farms.equals(other.farms))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Harvester [harvesterName=" + harvesterName + ", price=" + price + ", maintenance=" + maintenance
                + ", power=" + power + ", maxSpeed=" + maxSpeed + ", fuelCapacity=" + fuelCapacity
                + ", cultivationCapacity=" + cultivationCapacity + ", farms=" + farms + "]";
    }
}
