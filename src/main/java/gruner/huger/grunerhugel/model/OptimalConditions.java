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
  int minTemp;
  @Column(name = "Germinacion optima min (ºC)")
  int minOptimalTemp;
  @Column(name = "Germinacion optima max (ºC)")
  int maxOptimalTemp;
  @Column(name = "crecimiento vegetativo  t<(ºC)")
  int growthTemp;
  @Column(name = "macollamiento min(ºC)")
  int minMaturityTemp;
  @Column(name = "macollamiento max(ºC)")
  int maxMaturityTemp;
  @Column(name = "antesis min min (ºC)")
  int minMinAntesisTemp;
  @Column(name = "antesis min max (ºC)")
  int minMaxAntesisTemp;
  @Column(name = "antesis max min (ºC)")
  int maxMinAntesisTemp;
  @Column(name = "antesis max max (ºC)")
  int maxMaxAntesisTemp;
  @Column(name = "lechoso min min (ºC)")
  int minMinLechosoTemp;
  @Column(name = "lechoso min max (ºC)")
  int minMaxLechosoTemp;
  @Column(name = "lechoso max min (ºC)")
  int maxMinLechosoTemp;
  @Column(name = "lechoso max max (ºC)")
  int maxMaxLechosoTemp;
  @Column(name = "pastoso min min (ºC)")
  int minMinPastosoTemp;
  @Column(name = "pastoso min max (ºC)")
  int minMaxPastosoTemp;
  @Column(name = "pastoso max min (ºC)")
  int maxMinPastosoTemp;
  @Column(name = "pastoso max max (ºC)")
  int maxMaxPastosoTemp;
  @Column(name = "maduracion min min (ºC)")
  int minMinMaturityTemp;
  @Column(name = "maduracion min max (ºC)")
  int minMaxMaturityTemp;
  @Column(name = "maduracion max min (ºC)")
  int maxMinMaturityTemp;
  @Column(name = "maduracion max max (ºC)")
  int maxMaxMaturityTemp;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(int minTemp) {
    this.minTemp = minTemp;
  }

  public int getMinOptimalTemp() {
    return minOptimalTemp;
  }

  public void setMinOptimalTemp(int minOptimalTemp) {
    this.minOptimalTemp = minOptimalTemp;
  }

  public int getMaxOptimalTemp() {
    return maxOptimalTemp;
  }

  public void setMaxOptimalTemp(int maxOptimalTemp) {
    this.maxOptimalTemp = maxOptimalTemp;
  }

  public int getGrowthTemp() {
    return growthTemp;
  }

  public void setGrowthTemp(int growthTemp) {
    this.growthTemp = growthTemp;
  }

  public int getMinMaturityTemp() {
    return minMaturityTemp;
  }

  public void setMinMaturityTemp(int minMaturityTemp) {
    this.minMaturityTemp = minMaturityTemp;
  }

  public int getMaxMaturityTemp() {
    return maxMaturityTemp;
  }

  public void setMaxMaturityTemp(int maxMaturityTemp) {
    this.maxMaturityTemp = maxMaturityTemp;
  }

  public int getMinMinAntesisTemp() {
    return minMinAntesisTemp;
  }

  public void setMinMinAntesisTemp(int minMinAntesisTemp) {
    this.minMinAntesisTemp = minMinAntesisTemp;
  }

  public int getMinMaxAntesisTemp() {
    return minMaxAntesisTemp;
  }

  public void setMinMaxAntesisTemp(int minMaxAntesisTemp) {
    this.minMaxAntesisTemp = minMaxAntesisTemp;
  }

  public int getMaxMinAntesisTemp() {
    return maxMinAntesisTemp;
  }

  public void setMaxMinAntesisTemp(int maxMinAntesisTemp) {
    this.maxMinAntesisTemp = maxMinAntesisTemp;
  }

  public int getMaxMaxAntesisTemp() {
    return maxMaxAntesisTemp;
  }

  public void setMaxMaxAntesisTemp(int maxMaxAntesisTemp) {
    this.maxMaxAntesisTemp = maxMaxAntesisTemp;
  }

  public int getMinMinLechosoTemp() {
    return minMinLechosoTemp;
  }

  public void setMinMinLechosoTemp(int minMinLechosoTemp) {
    this.minMinLechosoTemp = minMinLechosoTemp;
  }

  public int getMinMaxLechosoTemp() {
    return minMaxLechosoTemp;
  }

  public void setMinMaxLechosoTemp(int minMaxLechosoTemp) {
    this.minMaxLechosoTemp = minMaxLechosoTemp;
  }

  public int getMaxMinLechosoTemp() {
    return maxMinLechosoTemp;
  }

  public void setMaxMinLechosoTemp(int maxMinLechosoTemp) {
    this.maxMinLechosoTemp = maxMinLechosoTemp;
  }

  public int getMaxMaxLechosoTemp() {
    return maxMaxLechosoTemp;
  }

  public void setMaxMaxLechosoTemp(int maxMaxLechosoTemp) {
    this.maxMaxLechosoTemp = maxMaxLechosoTemp;
  }

  public int getMinMinPastosoTemp() {
    return minMinPastosoTemp;
  }

  public void setMinMinPastosoTemp(int minMinPastosoTemp) {
    this.minMinPastosoTemp = minMinPastosoTemp;
  }

  public int getMinMaxPastosoTemp() {
    return minMaxPastosoTemp;
  }

  public void setMinMaxPastosoTemp(int minMaxPastosoTemp) {
    this.minMaxPastosoTemp = minMaxPastosoTemp;
  }

  public int getMaxMinPastosoTemp() {
    return maxMinPastosoTemp;
  }

  public void setMaxMinPastosoTemp(int maxMinPastosoTemp) {
    this.maxMinPastosoTemp = maxMinPastosoTemp;
  }

  public int getMaxMaxPastosoTemp() {
    return maxMaxPastosoTemp;
  }

  public void setMaxMaxPastosoTemp(int maxMaxPastosoTemp) {
    this.maxMaxPastosoTemp = maxMaxPastosoTemp;
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

  public OptimalConditions() {
    // no need
  }

}
