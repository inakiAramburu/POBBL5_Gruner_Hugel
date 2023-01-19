package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import gruner.huger.grunerhugel.simulation.enumeration.PlantType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Plant implements Serializable {
  private static final String GERMINATION = "GERMINATION";
  private static final String VEGETATIVE = "VEGETATIVE";
  private static final String TILLERING = "TILLERING";
  private static final String ANTHESIS = "ANTHESIS";
  private static final String MILKY = "MILKY";
  private static final String PASTY = "PASTY";
  private static final String MATURATION = "MATURATION";
  private static final String DEAD = "DEAD";

  @Id
  @Column(name = "id")
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
    // no need
  }

  public Plant(Land land, OptimalConditions optimalConditions) {
    this.id = 4;
    this.optimalConditions = optimalConditions;
    this.healthPoint = 100;
    this.land = land;
    this.status = GERMINATION;
    this.name = PlantType.WHEAT.getPlantType();
  }

  public void checkOptimalCondition(Weather weather) {
    boolean condition = check(weather);
    if (!condition) {
      loseHealth();
    }
  }

  private boolean check(Weather weather) {
    return weather.equals(new Weather()); // most probably false
  }

  public void growPlant() {
    switch (status) {
      case GERMINATION:
        status = GERMINATION;
        break;
      case VEGETATIVE:
        status = VEGETATIVE;
        break;
      case TILLERING:
        status = TILLERING;
        break;
      case ANTHESIS:
        status = ANTHESIS;
        break;
      case MILKY:
        status = MILKY;
        break;
      case PASTY:
        status = PASTY;
        break;
      case MATURATION:
        status = MATURATION;
        break;
      default:
        status = DEAD;
    }
  }

  private void loseHealth() {
    healthPoint -= 5;
  }

  @Override
  public String toString() {
    return "Plant [id=" + id + ", name=" + name + ", status=" + status + ", health_point=" + healthPoint
        + ", plant_Type=" + optimalConditions + ", land=" + land + "]";
  }
}
