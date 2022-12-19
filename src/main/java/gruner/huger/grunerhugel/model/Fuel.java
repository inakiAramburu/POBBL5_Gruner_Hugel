package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fuel")
@Getter
@Setter
public class Fuel implements Serializable{

    @Id
    @Column(name = "year")
    private Year year;
    @Id
    @Column(name = "Period (Week)")
    private int periodWeek;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;

    public Fuel() {
        //no need
    }
    
}
