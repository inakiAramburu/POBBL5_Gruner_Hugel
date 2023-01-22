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

@Entity
@Table(name = "plant")
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

  public Plant(OptimalConditions optimalConditions, Land land) {
    this.optimalConditions = optimalConditions;
    this.healthPoint = 100;
    this.land = land;
    this.status = GERMINATION;
    this.name = PlantType.WHEAT.getPlantType();
  }

public void checkOptimalCondition(Weather weather) {
    boolean condition = check(weather);
    if (condition) {
      growPlant();
    } else {
      loseHealth();
    }
  }

  public boolean check(Weather weather) {
    return weather.equals(new Weather());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getHealthPoint() {
    return healthPoint;
  }

  public void setHealthPoint(int healthPoint) {
    this.healthPoint = healthPoint;
  }

  public OptimalConditions getOptimalConditions() {
    return optimalConditions;
  }

  public void setOptimalConditions(OptimalConditions optimalConditions) {
    this.optimalConditions = optimalConditions;
  }

  public void growPlant() {
    switch (status) {
      case GERMINATION:
        status = VEGETATIVE;
        break;
      case VEGETATIVE:
        status = TILLERING;
        break;
      case TILLERING:
        status = ANTHESIS;
        break;
      case ANTHESIS:
        status = MILKY;
        break;
      case MILKY:
        status = PASTY;
        break;
      case PASTY:
        status = MATURATION;
        break;
      default:
        status = DEAD;
    }
  }

  public Land getLand() {
    return land;
  }

  public void setLand(Land land) {
    this.land = land;
  }

  public void loseHealth() {
    healthPoint -= 5;
  }

  @Override
  public String toString() {
    return "Plant [id=" + id + ", name=" + name + ", status=" + status + ", health_point=" + healthPoint
        + ", plant_Type=" + optimalConditions + ", land=" + land + "]";
  }
}
