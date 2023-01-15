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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "land")
@Getter
@Setter
public class Land implements Serializable{
    
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

    public Land(){
      //no need
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
