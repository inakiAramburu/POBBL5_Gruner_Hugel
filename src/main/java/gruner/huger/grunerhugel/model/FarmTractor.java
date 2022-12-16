package gruner.huger.grunerhugel.model;

import gruner.huger.grunerhugel.model.compositeKeys.FarmTractorId;
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
@Table(name = "farm_tractor")
@Getter
@Setter
public class FarmTractor {

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
        //no need
    }

    @Override
    public String toString() {
        return "FarmTractor [farm=" + farm + ", tractor=" + tractor + ", quantity=" + quantity + "]";
    }
    
}
