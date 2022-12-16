package gruner.huger.grunerhugel.model;

import gruner.huger.grunerhugel.model.compositeKeys.FarmSeederId;
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
public class FarmSeeder {

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

    @Override
    public String toString() {
        return "FarmSeeder [farm=" + farm + ", seeder=" + seeder + ", quantity=" + quantity + "]";
    }
    
}
