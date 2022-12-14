package gruner.huger.grunerhugel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plow")
public class Plow {

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "Price (€)")
    private int price;
    @Column(name = "Maintenance (€/day)")
    private int maintenance;
    @Column(name = "Working amplitude (m)")
    private int workingAmplitude;
    @Column(name = "Recommended power (hp)")
    private int recommendedPower;
    
    public Plow() {
        //no need
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public int getWorkingAmplitude() {
        return workingAmplitude;
    }

    public void setWorkingAmplitude(int workingAmplitude) {
        this.workingAmplitude = workingAmplitude;
    }

    public int getRecommendedPower() {
        return recommendedPower;
    }

    public void setRecommendedPower(int recommendedPower) {
        this.recommendedPower = recommendedPower;
    }

    @Override
    public String toString() {
        return "Plow [name=" + name + ", price=" + price + ", maintenance=" + maintenance + ", workingAmplitude="
                + workingAmplitude + ", recommendedPower=" + recommendedPower + "]";
    }
    
}
