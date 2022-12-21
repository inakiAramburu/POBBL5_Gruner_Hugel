package gruner.huger.grunerhugel.model.compositeKeys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FarmTractorId implements Serializable{
    
    @Column(name = "FK_farm")
    private Integer farmId;

    @Column(name = "FK_tractor")
    private String tractorId;
    
    public FarmTractorId(int id, String name) {
        this.farmId = id;
        this.tractorId = name;
    }

    public FarmTractorId() {
        //no need
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((farmId == null) ? 0 : farmId.hashCode());
        result = prime * result + ((tractorId == null) ? 0 : tractorId.hashCode());
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
        FarmTractorId other = (FarmTractorId) obj;
        if (farmId == null) {
            if (other.farmId != null)
                return false;
        } else if (!farmId.equals(other.farmId))
            return false;
        if (tractorId == null) {
            if (other.tractorId != null)
                return false;
        } else if (!tractorId.equals(other.tractorId))
            return false;
        return true;
    }

}
