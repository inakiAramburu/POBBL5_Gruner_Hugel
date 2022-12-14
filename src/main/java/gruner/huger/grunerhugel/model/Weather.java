package gruner.huger.grunerhugel.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather")
public class Weather {

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getDirectRadiation() {
        return directRadiation;
    }

    public void setDirectRadiation(double directRadiation) {
        this.directRadiation = directRadiation;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getSnowfall() {
        return snowfall;
    }

    public void setSnowfall(double snowfall) {
        this.snowfall = snowfall;
    }

    public double getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(double cloudcover) {
        this.cloudcover = cloudcover;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public double getSoilTemperature2() {
        return soilTemperature2;
    }

    public void setSoilTemperature2(double soilTemperature2) {
        this.soilTemperature2 = soilTemperature2;
    }

    public double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public double getSoilMoisture2() {
        return soilMoisture2;
    }

    public void setSoilMoisture2(double soilMoisture2) {
        this.soilMoisture2 = soilMoisture2;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
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
