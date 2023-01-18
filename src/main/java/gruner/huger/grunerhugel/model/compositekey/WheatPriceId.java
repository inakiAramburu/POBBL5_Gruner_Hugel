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
public class WheatPriceId implements Serializable {

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private Year year;

    public WheatPriceId() {
        // no need
    }

    public WheatPriceId(Year year, String month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public String toString() {
        return "WheatPriceId [month=" + month + ", year=" + year + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
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
        WheatPriceId other = (WheatPriceId) obj;
        if (month == null) {
            if (other.month != null)
                return false;
        } else if (!month.equals(other.month))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }
}
