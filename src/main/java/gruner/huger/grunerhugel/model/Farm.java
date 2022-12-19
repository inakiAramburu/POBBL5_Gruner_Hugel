package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "farm")
@Getter
@Setter
public class Farm implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String name;
    @Column(name = "money")
    double money;
    @Column(name = "fuel (L)")
    double fuel;

    @OneToOne
    @JoinColumn(name = "FK_user")
    private User user;

    @OneToMany(mappedBy = "farm")
    private List<Land> lands;

    @OneToMany(mappedBy = "farm")
    private List<FarmTractor> tractors;

    @OneToMany(mappedBy = "farm")
    private List<FarmSeeder> seeders;

    @OneToMany(mappedBy = "farm")
    private List<FarmHarvester> harvesters;

    @OneToMany(mappedBy = "farm")
    private List<FarmPlow> plows;

    public Farm() {
        //no need
    }

    @Override
    public String toString() {
        return "Farm [id=" + id + ", name=" + name + ", money=" + money + ", user=" + user + "]";
    }
    
}
