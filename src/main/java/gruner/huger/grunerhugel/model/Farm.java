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
    @JoinColumn(name = "FK_userId")
    private User user;

    public Farm() {
    }

    
}
