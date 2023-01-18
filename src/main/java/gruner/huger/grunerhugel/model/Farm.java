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

@Entity
@Table(name = "farm")
public class Farm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "numWorkers")
    int numWorkers;
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
        // no need
    }

    public Farm(User user, int budget, int numWorkers) {
        this.user = user;
        this.money = budget;
        this.numWorkers = numWorkers;
        this.fuel = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumWorkers() {
        return numWorkers;
    }

    public void setNumWorkers(int numWorkers) {
        this.numWorkers = numWorkers;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public List<FarmTractor> getTractors() {
        return tractors;
    }

    public void setTractors(List<FarmTractor> tractors) {
        this.tractors = tractors;
    }

    public List<FarmSeeder> getSeeders() {
        return seeders;
    }

    public void setSeeders(List<FarmSeeder> seeders) {
        this.seeders = seeders;
    }

    public List<FarmHarvester> getHarvesters() {
        return harvesters;
    }

    public void setHarvesters(List<FarmHarvester> harvesters) {
        this.harvesters = harvesters;
    }

    public List<FarmPlow> getPlows() {
        return plows;
    }

    public void setPlows(List<FarmPlow> plows) {
        this.plows = plows;
    }

    @Override
    public String toString() {
        return "Farm [id=" + id + ", money=" + money + ", user=" + user + "]";
    }

}
