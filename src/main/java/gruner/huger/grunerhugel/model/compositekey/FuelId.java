package gruner.huger.grunerhugel.model.compositekey;

import java.io.Serializable;
import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FuelId implements Serializable {
    @Column(name = "year")
    private Year year;

    @Column(name = "Period (Week)")
    private int week;

    public FuelId() {
        // no need
    }

    public FuelId(Year year, int week) {
        this.year = year;
        this.week = week;
    }

    @Override
    public String toString() {
        return "FuelIdentity [year=" + year + ", week=" + week + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        result = prime * result + week;
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
        FuelId other = (FuelId) obj;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        if (week != other.week)
            return false;
        return true;
    }
}
