package gruner.huger.grunerhugel.model.compositekey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FarmSeederId implements Serializable{
    
    @Column(name = "FK_farm")
    private Integer farmId;

    @Column(name = "FK_seeder")
    private String seederId;
    
    public FarmSeederId(int id, String name) {
        this.farmId = id;
        this.seederId = name;
    }

    public FarmSeederId() {
        //no need
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((farmId == null) ? 0 : farmId.hashCode());
        result = prime * result + ((seederId == null) ? 0 : seederId.hashCode());
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
        FarmSeederId other = (FarmSeederId) obj;
        if (farmId == null) {
            if (other.farmId != null)
                return false;
        } else if (!farmId.equals(other.farmId))
            return false;
        if (seederId == null) {
            if (other.seederId != null)
                return false;
        } else if (!seederId.equals(other.seederId))
            return false;
        return true;
    }

    

}
