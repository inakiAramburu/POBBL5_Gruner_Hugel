package gruner.huger.grunerhugel.model;

// import java.util.concurrent.BlockingQueue;

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
public class Worker implements Serializable, Runnable { // extends Thread //most probably

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne
    @JoinColumn(name = "FK_FarmId")
    private Farm farm;

    boolean pagado = false;
    // BlockingQueue<String> blockingQueue;

    public Worker() {
        // no need
    }

    @Override
    public String toString() {
        return "Worker [id=" + id + ", farm=" + farm + "]";
    }

    @Override
    public void run() {
        

    }
}
