package gruner.huger.grunerhugel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plant_type")
public class PlantType {
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getSnowfall() {
        return snowfall;
    }

    public void setSnowfall(double snowfall) {
        this.snowfall = snowfall;
    }

    @Override
    public String toString() {
        return "Plant_Type [name=" + name + ", maxTemp=" + maxTemp + ", minTemp=" + minTemp + ", radiation=" + radiation
                + ", rain=" + rain + ", precipitation=" + precipitation + ", snowfall=" + snowfall + "]";
    }
}
