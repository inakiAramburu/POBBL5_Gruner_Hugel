package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "optimal_conditions")
@Getter
@Setter
public class PlantType implements Serializable{
    
    @Id
    @Column(name = "type_name")
    String name;
    @Column(name = "max_temp")
    double maxTemp;
    @Column(name = "min_temp")
    double minTemp;
    @Column(name = "radiation")
    double radiation;
    @Column(name = "rain")
    double rain;
    @Column(name = "precipitation")
    double precipitation;
    @Column(name = "snowfall")
    double snowfall;

    public PlantType() {
      //no need
    }

    @Override
    public String toString() {
        return "Plant_Type [name=" + name + ", maxTemp=" + maxTemp + ", minTemp=" + minTemp + ", radiation=" + radiation
                + ", rain=" + rain + ", precipitation=" + precipitation + ", snowfall=" + snowfall + "]";
    }
}
