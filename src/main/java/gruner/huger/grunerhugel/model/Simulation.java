package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "simulation")
@Getter
@Setter
public class Simulation implements Serializable{

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

    @OneToOne
    @JoinColumn(name = "FK_Farm")
    private Farm farm;

    public Simulation() {
        //no need
    }

    @Override
    public String toString() {
        return "Simulation [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", farm=" + farm + "]";
    }

}
