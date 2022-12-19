package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "town")
@Getter
@Setter
public class Town implements Serializable{
    
    @Id 
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "latitude")
    double latitude;
    @Column(name = "longitude")
    double longitude;

    @OneToMany(mappedBy = "town")
    private List<Weather> weather;

    public Town() {
      // no need
    }

    @Override
    public String toString() {
        return "Town [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }
}
