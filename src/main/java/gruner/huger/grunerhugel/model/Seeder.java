package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "seeder")
public class Seeder implements Serializable {

    @Id
    @Column(name = "seederName")
    private String seederName;
    @Column(name = "price (€)")
    private int price;
    @Column(name = "Maintenance (€/day)")
    private int maintenance;
    @Column(name = "Plant_type")
    private String plantType;
    @Column(name = "Capacity (L)")
    private int capacity;
    @Column(name = "Working amplitude (m)")
    private int workingAmplitude;
    @Column(name = "Working speed (km/h)")
    private int recommendedPower;
    @Column(name = "Fertilized")
    private boolean fertilized;
    @Column(name = "Frontal subjection")
    private String frontalSubjection;

    @OneToMany(mappedBy = "seeder")
    private List<FarmSeeder> farms;

    public Seeder() {
        // no need
    }

    public String getSeederName() {
        return seederName;
    }

    public void setSeederName(String seederName) {
        this.seederName = seederName;
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

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public boolean isFertilized() {
        return fertilized;
    }

    public void setFertilized(boolean fertilized) {
        this.fertilized = fertilized;
    }

    public String getFrontalSubjection() {
        return frontalSubjection;
    }

    public void setFrontalSubjection(String frontalSubjection) {
        this.frontalSubjection = frontalSubjection;
    }

    public List<FarmSeeder> getFarms() {
        return farms;
    }

    public void setFarms(List<FarmSeeder> farms) {
        this.farms = farms;
    }

    @Override
    public String toString() {
        return "Seeder [name=" + seederName + ", price=" + price + ", maintenance=" + maintenance + ", plantType="
                + plantType
                + ", capacity=" + capacity + ", workingAmplitude=" + workingAmplitude + ", recommendedPower="
                + recommendedPower + ", fertilized=" + fertilized + ", frontalSubjection=" + frontalSubjection
                + ", farms=" + farms + "]";
    }

}
