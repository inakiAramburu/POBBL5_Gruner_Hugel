package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "plant")
public class Plant implements Serializable {

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
    // no need
  }

  public void checkOptimalCondition(Weather weather) {
    boolean condition = check(weather);
    if (condition) {
      growPlant();
    } else {
      loseHealth();
    }
  }

  private boolean check(Weather weather) {
    return weather.equals(new Weather());
  }

  private void growPlant() {
    // no need
  }

  private void loseHealth() {
    // no need
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

  public Land getLand() {
    return land;
  }

  public void setLand(Land land) {
    this.land = land;
  }

  @Override
  public String toString() {
    return "Plant [id=" + id + ", name=" + name + ", status=" + status + ", health_point=" + healthPoint
        + ", plant_Type=" + optimalConditions + ", land=" + land + "]";
  }
}
