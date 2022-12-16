package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seeder")
@Getter
@Setter
public class Seeder implements Serializable{

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "Price (€)")
    private int price;
    @Column(name = "Working amplitude (m)")
    private int workingAmplitude;
    @Column(name = "Recommended power (hp)")
    private int recommendedPower;

    @OneToMany(mappedBy = "seeder")
    private Set<FarmSeeder> farms;

    public Seeder() {
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
        return "Seeder [name=" + name + ", price=" + price + ", workingAmplitude=" + workingAmplitude
                + ", recommendedPower=" + recommendedPower + "]";
    }
}
