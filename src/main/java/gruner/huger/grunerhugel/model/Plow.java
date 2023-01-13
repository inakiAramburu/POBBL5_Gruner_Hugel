package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plow")
public class Plow implements Serializable {

    @Id
    @Column(name = "plowName")
    private String plowName;
    @Column(name = "Price (€)")
    private int price;
    @Column(name = "Maintenance (€/day)")
    private int maintenance;
    @Column(name = "Working amplitude (m)")
    private int workingAmplitude;
    @Column(name = "Recommended power (hp)")
    private int recommendedPower;

    @OneToMany(mappedBy = "plow")
    private List<FarmPlow> farms;

    public Plow() {
        // no need
    }

    public String getPlowName() {
        return plowName;
    }

    public void setPlowName(String plowName) {
        this.plowName = plowName;
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

    public List<FarmPlow> getFarms() {
        return farms;
    }

    public void setFarms(List<FarmPlow> farms) {
        this.farms = farms;
    }

    @Override
    public String toString() {
        return "Plow [name=" + plowName + ", price=" + price + ", maintenance=" + maintenance + ", workingAmplitude="
                + workingAmplitude + ", recommendedPower=" + recommendedPower + "]";
    }

}
