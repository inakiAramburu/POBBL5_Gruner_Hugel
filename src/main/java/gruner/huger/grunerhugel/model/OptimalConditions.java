package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "optimal_conditions")
public class OptimalConditions implements Serializable {

  @Id
  @Column(name = "type_name")
  String name;
  @Column(name = "Germinacion min (ºC)")
  int minGerminationTemp;
  @Column(name = "Germinacion optima min (ºC)")
  int minOpGerminationTemp;
  @Column(name = "Germinacion optima max (ºC)")
  int maxOpGerminationTemp;
  @Column(name = "crecimiento vegetativo  t<(ºC)")
  int vegetativeTemp;
  @Column(name = "macollamiento min(ºC)")
  int minTilleringTemp;
  @Column(name = "macollamiento max(ºC)")
  int maxTilleringTemp;
  @Column(name = "antesis min min (ºC)")
  int minMinAnthesisTemp;
  @Column(name = "antesis min max (ºC)")
  int minMaxAnthesisTemp;
  @Column(name = "antesis max min (ºC)")
  int maxMinAnthesisTemp;
  @Column(name = "antesis max max (ºC)")
  int maxMaxAnthesisTemp;
  @Column(name = "lechoso min min (ºC)")
  int minMinMilkyTemp;
  @Column(name = "lechoso min max (ºC)")
  int minMaxMilkyTemp;
  @Column(name = "lechoso max min (ºC)")
  int maxMinMilkyTemp;
  @Column(name = "lechoso max max (ºC)")
  int maxMaxMilkyTemp;
  @Column(name = "pastoso min min (ºC)")
  int minMinPastyTemp;
  @Column(name = "pastoso min max (ºC)")
  int minMaxPastyTemp;
  @Column(name = "pastoso max min (ºC)")
  int maxMinPastyTemp;
  @Column(name = "pastoso max max (ºC)")
  int maxMaxPastyTemp;
  @Column(name = "maduracion min min (ºC)")
  int minMinMaturityTemp;
  @Column(name = "maduracion min max (ºC)")
  int minMaxMaturityTemp;
  @Column(name = "maduracion max min (ºC)")
  int maxMinMaturityTemp;
  @Column(name = "maduracion max max (ºC)")
  int maxMaxMaturityTemp;

  public OptimalConditions() {
    // no need
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMinGerminationTemp() {
    return minGerminationTemp;
  }

  public void setMinGerminationTemp(int minGerminationTemp) {
    this.minGerminationTemp = minGerminationTemp;
  }

  public int getMinOpGerminationTemp() {
    return minOpGerminationTemp;
  }

  public void setMinOpGerminationTemp(int minOpGerminationTemp) {
    this.minOpGerminationTemp = minOpGerminationTemp;
  }

  public int getMaxOpGerminationTemp() {
    return maxOpGerminationTemp;
  }

  public void setMaxOpGerminationTemp(int maxOpGerminationTemp) {
    this.maxOpGerminationTemp = maxOpGerminationTemp;
  }

  public int getVegetativeTemp() {
    return vegetativeTemp;
  }

  public void setVegetativeTemp(int vegetativeTemp) {
    this.vegetativeTemp = vegetativeTemp;
  }

  public int getMinTilleringTemp() {
    return minTilleringTemp;
  }

  public void setMinTilleringTemp(int minTilleringTemp) {
    this.minTilleringTemp = minTilleringTemp;
  }

  public int getMaxTilleringTemp() {
    return maxTilleringTemp;
  }

  public void setMaxTilleringTemp(int maxTilleringTemp) {
    this.maxTilleringTemp = maxTilleringTemp;
  }

  public int getMinMinAnthesisTemp() {
    return minMinAnthesisTemp;
  }

  public void setMinMinAnthesisTemp(int minMinAnthesisTemp) {
    this.minMinAnthesisTemp = minMinAnthesisTemp;
  }

  public int getMinMaxAnthesisTemp() {
    return minMaxAnthesisTemp;
  }

  public void setMinMaxAnthesisTemp(int minMaxAnthesisTemp) {
    this.minMaxAnthesisTemp = minMaxAnthesisTemp;
  }

  public int getMaxMinAnthesisTemp() {
    return maxMinAnthesisTemp;
  }

  public void setMaxMinAnthesisTemp(int maxMinAnthesisTemp) {
    this.maxMinAnthesisTemp = maxMinAnthesisTemp;
  }

  public int getMaxMaxAnthesisTemp() {
    return maxMaxAnthesisTemp;
  }

  public void setMaxMaxAnthesisTemp(int maxMaxAnthesisTemp) {
    this.maxMaxAnthesisTemp = maxMaxAnthesisTemp;
  }

  public int getMinMinMilkyTemp() {
    return minMinMilkyTemp;
  }

  public void setMinMinMilkyTemp(int minMinMilkyTemp) {
    this.minMinMilkyTemp = minMinMilkyTemp;
  }

  public int getMinMaxMilkyTemp() {
    return minMaxMilkyTemp;
  }

  public void setMinMaxMilkyTemp(int minMaxMilkyTemp) {
    this.minMaxMilkyTemp = minMaxMilkyTemp;
  }

  public int getMaxMinMilkyTemp() {
    return maxMinMilkyTemp;
  }

  public void setMaxMinMilkyTemp(int maxMinMilkyTemp) {
    this.maxMinMilkyTemp = maxMinMilkyTemp;
  }

  public int getMaxMaxMilkyTemp() {
    return maxMaxMilkyTemp;
  }

  public void setMaxMaxMilkyTemp(int maxMaxMilkyTemp) {
    this.maxMaxMilkyTemp = maxMaxMilkyTemp;
  }

  public int getMinMinPastyTemp() {
    return minMinPastyTemp;
  }

  public void setMinMinPastyTemp(int minMinPastyTemp) {
    this.minMinPastyTemp = minMinPastyTemp;
  }

  public int getMinMaxPastyTemp() {
    return minMaxPastyTemp;
  }

  public void setMinMaxPastyTemp(int minMaxPastyTemp) {
    this.minMaxPastyTemp = minMaxPastyTemp;
  }

  public int getMaxMinPastyTemp() {
    return maxMinPastyTemp;
  }

  public void setMaxMinPastyTemp(int maxMinPastyTemp) {
    this.maxMinPastyTemp = maxMinPastyTemp;
  }

  public int getMaxMaxPastyTemp() {
    return maxMaxPastyTemp;
  }

  public void setMaxMaxPastyTemp(int maxMaxPastyTemp) {
    this.maxMaxPastyTemp = maxMaxPastyTemp;
  }

  public int getMinMinMaturityTemp() {
    return minMinMaturityTemp;
  }

  public void setMinMinMaturityTemp(int minMinMaturityTemp) {
    this.minMinMaturityTemp = minMinMaturityTemp;
  }

  public int getMinMaxMaturityTemp() {
    return minMaxMaturityTemp;
  }

  public void setMinMaxMaturityTemp(int minMaxMaturityTemp) {
    this.minMaxMaturityTemp = minMaxMaturityTemp;
  }

  public int getMaxMinMaturityTemp() {
    return maxMinMaturityTemp;
  }

  public void setMaxMinMaturityTemp(int maxMinMaturityTemp) {
    this.maxMinMaturityTemp = maxMinMaturityTemp;
  }

  public int getMaxMaxMaturityTemp() {
    return maxMaxMaturityTemp;
  }

  public void setMaxMaxMaturityTemp(int maxMaxMaturityTemp) {
    this.maxMaxMaturityTemp = maxMaxMaturityTemp;
  }

  @Override
  public String toString() {
    return "OptimalConditions [name=" + name + ", minGerminationTemp=" + minGerminationTemp + ", minOpGerminationTemp="
        + minOpGerminationTemp + ", maxOpGerminationTemp=" + maxOpGerminationTemp + ", vegetativeTemp=" + vegetativeTemp
        + ", minTilleringTemp=" + minTilleringTemp + ", maxTilleringTemp=" + maxTilleringTemp + ", minMinAnthesisTemp="
        + minMinAnthesisTemp + ", minMaxAnthesisTemp=" + minMaxAnthesisTemp + ", maxMinAnthesisTemp="
        + maxMinAnthesisTemp + ", maxMaxAnthesisTemp=" + maxMaxAnthesisTemp + ", minMinMilkyTemp=" + minMinMilkyTemp
        + ", minMaxMilkyTemp=" + minMaxMilkyTemp + ", maxMinMilkyTemp=" + maxMinMilkyTemp + ", maxMaxMilkyTemp="
        + maxMaxMilkyTemp + ", minMinPastyTemp=" + minMinPastyTemp + ", minMaxPastyTemp=" + minMaxPastyTemp
        + ", maxMinPastyTemp=" + maxMinPastyTemp + ", maxMaxPastyTemp=" + maxMaxPastyTemp + ", minMinMaturityTemp="
        + minMinMaturityTemp + ", minMaxMaturityTemp=" + minMaxMaturityTemp + ", maxMinMaturityTemp="
        + maxMinMaturityTemp + ", maxMaxMaturityTemp=" + maxMaxMaturityTemp + "]";
  }

}
