package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import gruner.huger.grunerhugel.model.compositekey.FuelId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fuel")
public class Fuel implements Serializable {

    @EmbeddedId
    private FuelId fuelId;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;

    public Fuel() {
        //no need
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fuelId == null) ? 0 : fuelId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
        Fuel other = (Fuel) obj;
        if (fuelId == null) {
            if (other.fuelId != null)
                return false;
        } else if (!fuelId.equals(other.fuelId))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        return true;
    }



    public FuelId getFuelId() {
        return fuelId;
    }



    public void setFuelId(FuelId fuelId) {
        this.fuelId = fuelId;
    }



    public double getPrice() {
        return price;
    }



    public void setPrice(double price) {
        this.price = price;
    }



    public String getCurrency() {
        return currency;
    }



    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
