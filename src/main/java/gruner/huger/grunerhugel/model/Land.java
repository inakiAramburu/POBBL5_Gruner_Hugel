package gruner.huger.grunerhugel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "land")
public class Land {
    
    @Id
    @Column(name = "id")
    int id;
    @Column(name = "size")
    double size;
    @Column(name = "status")
    String status;

    @OneToOne
    @JoinColumn(name = "FK_FarmId")
    private Farm farm;
    
    @OneToOne
    @JoinColumn(name = "FK_TownId")
    private Town town;

    public Land(){
      //no need
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


    @Override
    public String toString() {
        return "Land [id=" + id + ", size=" + size + ", status=" + status + ", farm=" + farm + ", town=" + town + "]";
    }

}
