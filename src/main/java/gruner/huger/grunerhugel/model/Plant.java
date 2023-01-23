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
  @Column(name = "growth_rate")
  int growthRate;
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
    if (check(weather)) {
      growPlant();
    } else {
      loseHealth();
    }
  }

  public boolean check(Weather weather) {
    boolean result;
    switch (status) {
      case GERMINATION:
        result = checkGermination(weather);
        break;
      case VEGETATIVE:
        result = checkVegetative(weather);
        break;
      case TILLERING:
        result = checkTilleting(weather);
        break;
      case ANTHESIS:
        result = checkAnthesis(weather);
        break;
      case MILKY:
        result = checkMilky(weather);
        break;
      case PASTY:
        result = checkPasty(weather);
        break;
      default:
        result = checkMaturation(weather);
    }
    return result;
  }

  private boolean checkGermination(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getMinOpGerminationTemp(),
        optimalConditions.getMaxOpGerminationTemp());
  }

  private boolean checkVegetative(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getVegetativeTemp());
  }

  private boolean checkTilleting(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getMinTilleringTemp(),
        optimalConditions.getMaxTilleringTemp());
  }

  private boolean checkAnthesis(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getMinMaxAnthesisTemp(),
        optimalConditions.getMaxMinAnthesisTemp());
  }

  private boolean checkMilky(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getMinMaxMilkyTemp(),
        optimalConditions.getMaxMinMilkyTemp());
  }

  private boolean checkPasty(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getMinMaxPastyTemp(),
        optimalConditions.getMaxMinPastyTemp());
  }

  private boolean checkMaturation(Weather weather) {
    return isTemperatureOkay(weather.getTemperature(), optimalConditions.getMinMaxMaturityTemp(),
        optimalConditions.getMaxMinMaturityTemp());
  }

  private boolean isTemperatureOkay(double temp, int minTemp, int maxTemp) {
    return (temp > minTemp && maxTemp > temp);
  }

  private boolean isTemperatureOkay(double temp, int minTemp) {
    return (temp > minTemp);
  }

  public void growPlant() {
    growthRate += 1;
    if (growthRate >= 100) {
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
          status = GERMINATION;
      }
      growthRate = 0;
    }
  }

  public void loseHealth() {
    healthPoint -= 5;
    if (healthPoint <= 0) {
      status = DEAD;
    }
  }

  @Override
  public String toString() {
    return "Plant [id=" + id + ", name=" + name + ", status=" + status + ", health_point=" + healthPoint
        + ", plant_Type=" + optimalConditions + ", land=" + land + "]";
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

  public int getGrowthRate() {
    return growthRate;
  }

  public void setGrowthRate(int growthRate) {
    this.growthRate = growthRate;
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
}
