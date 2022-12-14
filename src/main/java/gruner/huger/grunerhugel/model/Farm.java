package gruner.huger.grunerhugel.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "farm")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String name;
    @Column(name = "money")
    double money;

    @OneToOne
    @JoinColumn(name = "FK_user")
    private User user;

    @OneToMany(mappedBy = "farm")
    private Set<Land> lands;

    @ManyToMany
    @JoinTable(name = "farm_tools", 
                joinColumns = {@JoinColumn(name = "FK_farm", referencedColumnName = "id", nullable = false,updatable = false)}, 
                inverseJoinColumns = {@JoinColumn(name="FK_tools",referencedColumnName = "id",nullable = false,updatable = false)})
    private Set<Tools> tools = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "farm_resources", 
                joinColumns = {@JoinColumn(name = "FK_farm", referencedColumnName = "id", nullable = false,updatable = false)}, 
                inverseJoinColumns = {@JoinColumn(name="FK_resources",referencedColumnName = "id",nullable = false,updatable = false)})
    private Set<Resources> resources = new HashSet<>();

    public Farm() {
        //no need
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Farm [id=" + id + ", name=" + name + ", money=" + money + ", user=" + user + "]";
    }
    
}
