package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "worker")
@Getter
@Setter
public class Worker implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne
    @JoinColumn(name = "FK_FarmId")
    private Farm farm;

    public Worker() {
      //no need
    }

    @Override
    public String toString() {
        return "Worker [id=" + id + ", farm=" + farm + "]";
    }
}
