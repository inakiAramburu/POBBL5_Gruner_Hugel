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
@Table(name = "wheat_price")
@Getter
@Setter
public class WheatPrice implements Serializable{

    @Id
    @Column(name = "month")
    private String month;
    @Id
    @Column(name = "year")
    private Year year;
    @Column(name = "price (T/€)")
    private double price;

    public WheatPrice() {
        //no need
    }

}
