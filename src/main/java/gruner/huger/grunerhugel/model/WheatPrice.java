package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import gruner.huger.grunerhugel.model.compositekey.WheatPriceId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wheat_price")
@Getter
@Setter
public class WheatPrice implements Serializable{

    @EmbeddedId
    private WheatPriceId wheatPriceId;
    @Column(name = "price (T/€)")
    private double price;

    public WheatPrice() {
        //no need
    }

    @Override
    public String toString() {
        return "WheatPrice [wheatPriceId=" + wheatPriceId + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((wheatPriceId == null) ? 0 : wheatPriceId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        WheatPrice other = (WheatPrice) obj;
        if (wheatPriceId == null) {
            if (other.wheatPriceId != null)
                return false;
        } else if (!wheatPriceId.equals(other.wheatPriceId))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        return true;
    }
    
}
