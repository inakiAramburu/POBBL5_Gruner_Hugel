package gruner.huger.grunerhugel.model.compositekey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FarmSeederId implements Serializable {

    @Column(name = "FK_farm")
    private Integer farmId;

    @Column(name = "FK_seeder")
    private String seederId;

    public FarmSeederId(int id, String name) {
        this.farmId = id;
        this.seederId = name;
    }

    public FarmSeederId() {
        // no need
    }

    @Override
    public int hashCode() {
        return 1;
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
        if (!farmId.equals(other.farmId))
            return false;
        if (!seederId.equals(other.seederId))
            return false;
        return true;
    }

}
