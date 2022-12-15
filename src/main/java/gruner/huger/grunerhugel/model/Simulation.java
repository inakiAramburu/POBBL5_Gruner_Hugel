package gruner.huger.grunerhugel.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "simulation")
public class Simulation {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @OneToOne
    @JoinColumn(name = "FK_Farm")
    private Farm farm;
    

    public Simulation() {
        //no need
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Farm getFarm() {
        return farm;
    }


    public void setFarm(Farm farm) {
        this.farm = farm;
    }


    @Override
    public String toString() {
        return "Simulation [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", farm=" + farm + "]";
    }

}
