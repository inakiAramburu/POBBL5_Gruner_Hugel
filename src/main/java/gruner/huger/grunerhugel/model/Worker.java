package gruner.huger.grunerhugel.model;

// import java.util.concurrent.BlockingQueue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "worker")
public class Worker implements Runnable { // extends Thread //most probably

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne
    @JoinColumn(name = "FK_FarmId")
    private Farm farm;

    boolean pagado = false;
    boolean ey = true;
    // BlockingQueue<String> blockingQueue;

    public Worker() {
        // no need
    }

    // public Worker(BlockingQueue<String> blockingQueue){
    // this.blockingQueue = blockingQueue;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
        Balance.moneyCost(900);
    }

    @Override
    public String toString() {
        return "Worker [id=" + id + ", farm=" + farm + "]";
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
