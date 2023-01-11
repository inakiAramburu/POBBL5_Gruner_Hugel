package gruner.huger.grunerhugel.model.compositekey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FarmHarvesterId implements Serializable {

    @Column(name = "FK_farm")
    private Integer farmId;
    @Column(name = "FK_harvester")
    private String harvesterId;

    public FarmHarvesterId(int id, String name) {
        this.farmId = id;
        this.harvesterId = name;
    }

    public FarmHarvesterId() {
        // no need
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((farmId == null) ? 0 : farmId.hashCode());
        result = prime * result + ((harvesterId == null) ? 0 : harvesterId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FarmHarvesterId other = (FarmHarvesterId) obj;
        if (farmId == null) {
            if (other.farmId != null)
                return false;
        } else if (!farmId.equals(other.farmId))
            return false;
        if (harvesterId == null) {
            if (other.harvesterId != null)
                return false;
        } else if (!harvesterId.equals(other.harvesterId))
            return false;
        return true;
    }

}
