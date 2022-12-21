package gruner.huger.grunerhugel.model.compositekey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FarmPlowId implements Serializable{

    @Column(name = "FK_farm")
    private Integer farmId;

    @Column(name = "FK_plow")
    private String plowId; 
    
    public FarmPlowId(int id, String name) {
        this.farmId = id;
        this.plowId = name;
    }

    public FarmPlowId() {
        //no need
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((farmId == null) ? 0 : farmId.hashCode());
        result = prime * result + ((plowId == null) ? 0 : plowId.hashCode());
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
        FarmPlowId other = (FarmPlowId) obj;
        if (farmId == null) {
            if (other.farmId != null)
                return false;
        } else if (!farmId.equals(other.farmId))
            return false;
        if (plowId == null) {
            if (other.plowId != null)
                return false;
        } else if (!plowId.equals(other.plowId))
            return false;
        return true;
    }

    
    
}
