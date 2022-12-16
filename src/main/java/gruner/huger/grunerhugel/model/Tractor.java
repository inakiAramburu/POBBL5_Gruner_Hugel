package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tractor")
@Getter
@Setter
public class Tractor implements Serializable{
    
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

    @OneToMany(mappedBy = "tractor")
    private Set<FarmTractor> farms;

    public Tractor() {
        //no need
    }

    @Override
    public String toString() {
        return "Tractor [name=" + name + ", price=" + price + ", maintenance=" + maintenance + ", power=" + power
                + ", maxSpeed=" + maxSpeed + ", fuelCapacity=" + fuelCapacity + "]";
    }
}
