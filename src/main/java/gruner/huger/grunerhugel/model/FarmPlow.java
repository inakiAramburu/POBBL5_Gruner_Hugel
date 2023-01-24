package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import gruner.huger.grunerhugel.model.compositekey.FarmPlowId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "farm_plow")
public class FarmPlow implements Serializable {

    @EmbeddedId
    private FarmPlowId id;

    @ManyToOne
    @MapsId("farmId")
    @JoinColumn(name = "FK_farm")
    private Farm farm;

    @ManyToOne
    @MapsId("plowId")
    @JoinColumn(name = "FK_plow")
    private Plow plow;

    @Column(name = "quantity")
    int quantity;

    public FarmPlow() {
        // no need
    }

    public FarmPlow(Farm farm, Plow plow, int quantity) {
        this.farm = farm;
        this.plow = plow;
        this.quantity = quantity;
        this.id = new FarmPlowId(farm.getId(), plow.getPlowName());
    }

    public FarmPlowId getId() {
        return id;
    }

    public void setId(FarmPlowId id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Plow getPlow() {
        return plow;
    }

    public void setPlow(Plow plow) {
        this.plow = plow;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FarmPlow [farm=" + farm + ", plow=" + plow + ", quantity=" + quantity + "]";
    }

}
