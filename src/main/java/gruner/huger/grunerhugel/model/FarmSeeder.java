package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import gruner.huger.grunerhugel.model.compositekey.FarmSeederId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "farm_seeder")
@Getter
@Setter
public class FarmSeeder implements Serializable{

    @EmbeddedId
    private FarmSeederId id;

    @ManyToOne
    @MapsId("farmId")
    @JoinColumn(name = "FK_farm")
    private Farm farm;

    @ManyToOne
    @MapsId("seederId")
    @JoinColumn(name = "FK_seeder")
    private Seeder seeder;

    @Column(name = "quantity")
    int quantity;

    public FarmSeeder() {
        //no need
    }

    public FarmSeeder(Farm farm, Seeder seeder, int quantity) {
        this.farm = farm;
        this.seeder = seeder;
        this.quantity = quantity;
        this.id = new FarmSeederId(farm.getId(), seeder.getSeederName());
    }

    @Override
    public String toString() {
        return "FarmSeeder [farm=" + farm + ", seeder=" + seeder + ", quantity=" + quantity + "]";
    }
    
}
