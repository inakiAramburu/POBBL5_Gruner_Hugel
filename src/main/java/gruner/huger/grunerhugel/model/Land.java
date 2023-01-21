package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import gruner.huger.grunerhugel.simulation.enumeration.LandStatus;
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
    this.status = LandStatus.EMPTY.getStatus();
    this.latitude = Float.parseFloat(latitude);
    this.longitude = Float.parseFloat(longitude);
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

  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  public float getLongitude() {
    return longitude;
  }

  public void setLongitude(float longitude) {
    this.longitude = longitude;
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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + id;
      long temp;
      temp = Double.doubleToLongBits(size);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      result = prime * result + ((status == null) ? 0 : status.hashCode());
      result = prime * result + ((farm == null) ? 0 : farm.hashCode());
      result = prime * result + ((town == null) ? 0 : town.hashCode());
      result = prime * result + ((plants == null) ? 0 : plants.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Land other = (Land) obj;
      if (id != other.id)
        return false;
      if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
        return false;
      if (status == null) {
        if (other.status != null)
          return false;
      } else if (!status.equals(other.status))
        return false;
      if (farm == null) {
        if (other.farm != null)
          return false;
      } else if (!farm.equals(other.farm))
        return false;
      if (town == null) {
        if (other.town != null)
          return false;
      } else if (!town.equals(other.town))
        return false;
      if (plants == null) {
        if (other.plants != null)
          return false;
      } else if (!plants.equals(other.plants))
        return false;
      return true;
    }

    
}
