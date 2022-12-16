package gruner.huger.grunerhugel.model;

import gruner.huger.grunerhugel.model.compositeKeys.FarmPlowId;
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
@Table(name = "farm_plow")
@Getter
@Setter
public class FarmPlow {

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
        //no need
    }

    @Override
    public String toString() {
        return "FarmPlow [farm=" + farm + ", plow=" + plow + ", quantity=" + quantity + "]";
    }
    
}
