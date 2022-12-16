package gruner.huger.grunerhugel.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
    private Set<Land> lands;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FarmTractor> tractors;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FarmSeeder> seeders;

    @OneToMany(mappedBy = "farm")
    private Set<FarmHarvester> harvesters;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FarmPlow> plows;

    public Farm() {
        //no need
    }

    @Override
    public String toString() {
        return "Farm [id=" + id + ", name=" + name + ", money=" + money + ", user=" + user + "]";
    }
    
}
