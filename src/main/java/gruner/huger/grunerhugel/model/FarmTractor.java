package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import gruner.huger.grunerhugel.model.compositekey.FarmTractorId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "farm_tractor")
public class FarmTractor implements Serializable {

    @EmbeddedId
    private FarmTractorId id;

    @ManyToOne
    @MapsId("farmId")
    @JoinColumn(name = "FK_farm")
    private Farm farm;

    @ManyToOne
    @MapsId("tractorId")
    @JoinColumn(name = "FK_tractor")
    private Tractor tractor;

    @Column(name = "quantity")
    int quantity;

    public FarmTractor() {
        // no need
    }

    public FarmTractor(Farm farm, Tractor tractor, int quantity) {
        this.farm = farm;
        this.tractor = tractor;
        this.quantity = quantity;
        this.id = new FarmTractorId(farm.getId(), tractor.getTractorName());
    }

    public FarmTractorId getId() {
        return id;
    }

    public void setId(FarmTractorId id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Tractor getTractor() {
        return tractor;
    }

    public void setTractor(Tractor tractor) {
        this.tractor = tractor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FarmTractor [farm=" + farm + ", tractor=" + tractor + ", quantity=" + quantity + "]";
    }

}
