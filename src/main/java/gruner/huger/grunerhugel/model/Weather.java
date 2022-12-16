package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather")
@Getter
@Setter
public class Weather implements Serializable{

    @Id
    @Column(name = "dateTime")
    private Date dateTime;
    @Column(name = "temperature_2m (ºC)")
    private double temperature;
    @Column(name = "direct_radiation (W/m²)")
    private double directRadiation;
    @Column(name = "rain (mm)")
    private double rain;
    @Column(name = "snowfall (cm)")
    private double snowfall;
    @Column(name = "cloudcover (%)")
    private double cloudcover;
    @Column(name = "precipitation (mm)")
    private double precipitation;
    @Column(name = "windspeed_10m (km/h)")
    private double windspeed;
    @Column(name = "soil_temperature_0_to_7cm (°C)")
    private double soilTemperature;
    @Column(name = "soil_temperature_7_to_28cm (°C)")
    private double soilTemperature2;
    @Column(name = "soil_moisture_0_to_7cm (m³/m³)")
    private double soilMoisture;
    @Column(name = "soil_moisture_7_to_28cm (m³/m³)")
    private double soilMoisture2;

    @ManyToOne
    @JoinColumn(name = "village", referencedColumnName = "name")
    private Town town;

    public Weather() {
        // no need
    }

    @Override
    public String toString() {
        return "Weather [dateTime=" + dateTime + ", temperature=" + temperature + ", directRadiation=" + directRadiation
                + ", rain=" + rain + ", snowfall=" + snowfall + ", cloudcover=" + cloudcover + ", precipitation="
                + precipitation + ", windspeed=" + windspeed + ", soilTemperature=" + soilTemperature
                + ", soilTemperature2=" + soilTemperature2 + ", soilMoisture=" + soilMoisture + ", soilMoisture2="
                + soilMoisture2 + ", town=" + town.getName() + "]";
    }

}
