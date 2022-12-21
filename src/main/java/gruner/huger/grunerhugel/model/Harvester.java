package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "harvester")
@Getter
@Setter
public class Harvester implements Serializable{

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
        //no need
    }

    @Override
    public String toString() {
        return "Harvester [harvesterName=" + harvesterName + ", price=" + price + ", maintenance=" + maintenance
                + ", power=" + power + ", maxSpeed=" + maxSpeed + ", fuelCapacity=" + fuelCapacity
                + ", cultivationCapacity=" + cultivationCapacity + ", farms=" + farms + "]";
    }
}
