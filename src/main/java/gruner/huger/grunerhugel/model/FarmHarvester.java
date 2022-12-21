package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import gruner.huger.grunerhugel.model.compositekey.FarmHarvesterId;
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
@Table(name = "farm_harvester")
@Getter
@Setter
public class FarmHarvester implements Serializable{

    @EmbeddedId
    private FarmHarvesterId id;

    @ManyToOne
    @MapsId("farmId")
    @JoinColumn(name = "FK_farm")
    private Farm farm;

    @ManyToOne
    @MapsId("harvesterId")
    @JoinColumn(name = "FK_harvester")
    private Harvester harvester;

    @Column(name = "quantity")
    int quantity;

    public FarmHarvester() {
        //no need
    }

    public FarmHarvester(Farm farm, Harvester harvester, int quantity) {
        this.farm = farm;
        this.harvester = harvester;
        this.quantity = quantity;
        this.id = new FarmHarvesterId(farm.getId(), harvester.getHarvesterName());
    }
}
