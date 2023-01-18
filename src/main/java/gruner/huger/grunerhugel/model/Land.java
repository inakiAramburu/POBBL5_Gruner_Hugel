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
  @Column(name = "latitude")
  float latitude;
  @Column(name = "longitude")
  float longitude;

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

  public Land(Double size, Farm farm, Town town, String latitude, String longitude) {
    this.size = size;
    this.farm = farm;
    this.town = town;
    this.status = "";
    this.latitude = Float.parseFloat(latitude);
    this.longitude = Float.parseFloat(longitude);
  }

  @Override
  public String toString() {
    return "Land [id=" + id + ", size=" + size + ", status=" + status + ", farm=" + farm + ", town=" + town + "]";
  }

}
