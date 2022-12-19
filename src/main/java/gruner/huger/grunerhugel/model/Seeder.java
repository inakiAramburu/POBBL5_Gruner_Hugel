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
@Table(name = "seeder")
@Getter
@Setter
public class Seeder implements Serializable{

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "price (€)")
    private int price;
    @Column(name = "Maintenance (€/day)")
    private int maintenance;
    @Column(name = "Plant_type")
    private String plantType;
    @Column(name = "Capacity (L)")
    private int capacity;
    @Column(name = "Working amplitude (m)")
    private int workingAmplitude;
    @Column(name = "Working speed (km/h)")
    private int recommendedPower;
    @Column(name = "Fertilized")
    private boolean fertilized;
    @Column(name = "Frontal subjection")
    private String frontalSubjection;

    @OneToMany(mappedBy = "seeder")
    private List<FarmSeeder> farms;

    public Seeder() {
        //no need
    }

    @Override
    public String toString() {
        return "Seeder [name=" + name + ", price=" + price + ", maintenance=" + maintenance + ", plantType=" + plantType
                + ", capacity=" + capacity + ", workingAmplitude=" + workingAmplitude + ", recommendedPower="
                + recommendedPower + ", fertilized=" + fertilized + ", frontalSubjection=" + frontalSubjection
                + ", farms=" + farms + "]";
    }
    
}
