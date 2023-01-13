package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "land")
public class Land implements Serializable {

  @Id
  @Column(name = "id")
  int id;
  @Column(name = "size")
  double size;
  @Column(name = "status")
  String status;

  @ManyToOne
  @JoinColumn(name = "FK_Farm")
  private Farm farm;

  @OneToOne
  @JoinColumn(name = "FK_Town")
  private Town town;

  @OneToMany(mappedBy = "land")
  private List<Plant> plants;

  public Land() {
    // no need
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Farm getFarm() {
    return farm;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public Town getTown() {
    return town;
  }

  public void setTown(Town town) {
    this.town = town;
  }

  public List<Plant> getPlants() {
    return plants;
  }

  public void setPlants(List<Plant> plants) {
    this.plants = plants;
  }

  @Override
  public String toString() {
    return "Land [id=" + id + ", size=" + size + ", status=" + status + ", farm=" + farm + ", town=" + town + "]";
  }

}
