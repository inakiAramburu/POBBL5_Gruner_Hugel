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

}
