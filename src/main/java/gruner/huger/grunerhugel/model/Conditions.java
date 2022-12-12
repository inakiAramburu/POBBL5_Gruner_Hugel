package gruner.huger.grunerhugel.model;

public class Conditions {
    String name;
    double maxTemp;
    double minTemp;
    double radiation;
    double precipitation;
    double rain;
    double snowfall;

    public Conditions(String name, double maxTemp, double minTemp, double radiation, double precipitation, double rain,
            double snowfall) {
        this.name = name;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.radiation = radiation;
        this.precipitation = precipitation;
        this.rain = rain;
        this.snowfall = snowfall;
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

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
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

}
