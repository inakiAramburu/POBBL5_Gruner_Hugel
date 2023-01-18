package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import gruner.huger.grunerhugel.simulation.enumeration.PlantType;
import gruner.huger.grunerhugel.simulation.thread.WheatStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "plant")
@Getter
@Setter
public class Plant implements Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "status")
    String status;
    @Column(name = "health_point")
    int healthPoint;

    @OneToOne()
    @JoinColumn(name = "FK_type")
    private OptimalConditions optimalConditions;

    @ManyToOne
    @JoinColumn(name = "FK_land")
    private Land land;

    public Plant() {
      //no need
    }

    public Plant(Land land, OptimalConditions optimalConditions){
      this.optimalConditions = optimalConditions;
      this.healthPoint = 100;
      this.land = land;
      this.status = WheatStatus.GERMINATION.getStatus();
      this.name = PlantType.WHEAT.getPlantType();
    }

    public void checkOptimalCondition(Weather weather){
      boolean condition = check(weather);
      if(condition){
        growPlant();
      } else {
        loseHealth();
      }
    }

    private boolean check(Weather weather){
      return weather.equals(new Weather()); //  most probably false
    }

    private void growPlant(){
      //  no need
    }

    private void loseHealth(){
      healthPoint -= 20;
    }

    @Override
    public String toString() {
        return "Plant [id=" + id + ", name=" + name + ", status=" + status + ", health_point=" + healthPoint
                + ", plant_Type=" + optimalConditions + ", land=" + land + "]";
    }
}
