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
@Table(name = "plow")
@Getter
@Setter
public class Plow implements Serializable{

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "Price (€)")
    private int price;
    @Column(name = "Maintenance (€/day)")
    private int maintenance;
    @Column(name = "Working amplitude (m)")
    private int workingAmplitude;
    @Column(name = "Recommended power (hp)")
    private int recommendedPower;

    @OneToMany(mappedBy = "plow")
    private List<FarmPlow> farms;
    
    public Plow() {
        //no need
    }

    @Override
    public String toString() {
        return "Plow [name=" + name + ", price=" + price + ", maintenance=" + maintenance + ", workingAmplitude="
                + workingAmplitude + ", recommendedPower=" + recommendedPower + "]";
    }
    
}
