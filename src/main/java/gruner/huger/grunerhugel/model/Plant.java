package gruner.huger.grunerhugel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "plant")
public class Plant {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "status")
    String status;
    @Column(name = "health_point")
    int health_point;

    @OneToOne()
    @JoinColumn(name = "FK_type")
    private Plant_Type plant_Type;

    @OneToOne
    @JoinColumn(name = "FK_LandId")
    private Land land;

    public Plant() {
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

    public int getHealth_point() {
        return health_point;
    }

    public void setHealth_point(int health_point) {
        this.health_point = health_point;
    }

    public Plant_Type getPlant_Type() {
        return plant_Type;
    }

    public void setPlant_Type(Plant_Type plant_Type) {
        this.plant_Type = plant_Type;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    @Override
    public String toString() {
        return "Plant [id=" + id + ", name=" + name + ", status=" + status + ", health_point=" + health_point
                + ", plant_Type=" + plant_Type + ", land=" + land + "]";
    }
}
